package igreja.ModelVO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import igreja.ModelDAO.AvisoDAO;
import igreja.ModelDAO.IgrejaDAO;
import igreja.ModelDAO.VisitanteDAO;
import igreja.ModelDAO.PessoaDAO;
import igreja.ModelVO.PessoaVO;


import java.util.Scanner;


public class Teste {

	

	static Scanner scan = new Scanner(System.in);
	static IgrejaDAO igreDAO = new IgrejaDAO();
	static IgrejaVO igreja = new IgrejaVO();
	static VisitanteVO visVo = new VisitanteVO();
	static VisitanteDAO visDao = new VisitanteDAO();
	static PessoaDAO pesDao = new PessoaDAO();
	static AvisoDAO avisoDAO = new AvisoDAO();
	static AvisoVO aviso = new AvisoVO();
	
	public static void main(String[] args) throws SQLException, ParseException {

		menuGeral();
		System.out.println("UsuÃ¡rio digitou 0, programa terminado");
	}



	//======================= CRUD IGREJAS ===========================
	public static void adicionarIgreja(Scanner scan){

		System.out.println("Criando uma igreja, entre com as informaÃ§Ãµes:");

		igreja.setTipoIgreja(leInformacaoInt("Tipo (1.Igreja; 2.CongregaÃ§Ã£o; 3.Ponto de PregaÃ§Ã£o): ", scan));
		igreja.setNomeIgreja(leInformacao("Nome: ", scan));
		igreja.setCepIgreja(leInformacao("CEP: ", scan));
		igreja.setLogradouroIgreja(leInformacao("EndereÃ§o: ", scan));
		igreja.setBairroIgreja(leInformacao("Bairro: ", scan));
		igreja.setCidadeIgreja(leInformacao("Cidade: ", scan));
		igreja.setUfIgreja(leInformacao("UF: ", scan));
		igreja.setFoneIgreja(leInformacao("Telefone: ", scan));
		igreja.setSiteIgreja(leInformacao("Site: ", scan));
		igreja.setEmailIgreja(leInformacao("E-mail: ", scan));
		igreja.setCnpjIgreja(leInformacao("CNPJ: ", scan));
		igreja.setEscolaDominicalIgreja(leInformacao("HorÃ¡rio da Escola Dominical: ", scan));
		igreja.setCultoIgreja(leInformacao("HorÃ¡rio do Culto: ", scan));
		igreja.setOficialPastorIgreja(leInformacao("Pastor: ", scan));
		igreja.setCondicaoPastorIgreja(leInformacaoInt("CondiÃ§Ã£o (1.Efetivo; 2. Auxiliar; 3.Evangelista ou 4.MissionÃ¡rio): ", scan));
		System.out.println("Data de organizaÃ§Ã£o: " + (new GregorianCalendar().getTime()));
		igreja.setDataOrganizacaoIgreja(new GregorianCalendar(2020,01,15));
		igreja.setPresbiterioIgreja(leInformacao("PresbitÃ©rio: ", scan));
		igreja.setObservacaoIgreja(leInformacao("ObservaÃ§Ã£o: ", scan));

		igreDAO.inserir(igreja);

		System.out.println("Igreja adicionada com sucesso!");
		System.out.println(igreja);
	}

	private static void excluirIgreja(Scanner scan){

		igreja.setIdIgreja(leInformacaoInt("ID da igreja: ", scan));
		igreDAO.remover(igreja);

	}

	
	private static void listarIgrejas() {
		// Listar todas as Igrejas
		List<IgrejaVO> igrejas = igreDAO.listar();
		for (IgrejaVO igre2 : igrejas) {
			System.out.println(igre2.getIdIgreja() + "\t" + igre2.getNomeIgreja() + "\t"
					+ igre2.getOficialPastorIgreja() + "\t" + igre2.getFoneIgreja());
		}
	}
	
	private static void imprimirIgrejas(){
		List<IgrejaVO> igrejas = igreDAO.listar();
		for (IgrejaVO igre2 : igrejas) {
			System.out.println(igre2.toString());
		}
	}

	//=====================================================================

		
		
	//=============== UTILITÃ�RIOS =======================

	// protected static Date leInformacaoData(String msg, Scanner scan) throws ParseException{
	// 	System.out.println(msg);
	// 	String date = scan.nextLine();
	// 	DateFormat data = DateFormat.getDateInstance();

	// 	Date da = data.parse(date);

		
	// 	return da; 
	// }

	protected static String leInformacao(String msg, Scanner scan){

		System.out.println(msg);
		String entrada = scan.nextLine();

		return entrada;
	}

	protected static int leInformacaoInt(String msg, Scanner scan){
		
		boolean entradaValida = false;
		int num = 0;

		while (!entradaValida){

			try {

				System.out.println(msg);
				String entrada = scan.nextLine();
				scan.nextLine();

				num = Integer.parseInt(entrada);

				entradaValida = true;

			} catch (Exception e){
				System.out.println("Entrada invÃ¡lida, digite novamente");
			}
		}

		return num;
	}

	//==========================================================

	//=======================MENUS=============================

	public static void menuGeral () throws SQLException, ParseException{
		int opcao;
			do{
			System.out.println("=========================");
			System.out.println("Digite a opÃ§Ã£o desejada:");
			System.out.println("1: CRUD Igreja ");
			System.out.println("2: CRUD Visitantes");
			System.out.println("3: CRUD Avisos");
			System.out.println("0: Sair");
			System.out.println("=========================");
			opcao = scan.nextInt();
			
				switch (opcao) {
					case 1:
						menuIgreja();
						break;
					case 2: 
						menuVisitante();
						break;
					case 3: 
						menuAviso();
						break;	
					default:
						break;
					}
				
			}while(opcao != 0);
	}

	public static void menuIgreja(){
		int opcao;	
		do{
			System.out.println("=========================");
			System.out.println("Digite a opÃ§Ã£o desejada:");
			System.out.println("1: Adicionar Igreja ");
			System.out.println("2: Listar Igreja");
			System.out.println("3: Imprimir Igrejas");
			System.out.println("4: Deletar Igreja");
			System.out.println("0: Sair");
			System.out.println("=========================");

			opcao = scan.nextInt();
			
			switch (opcao) {
				case 1:
					adicionarIgreja(scan);
					break;
				case 2: 
					listarIgrejas();
					break;
				case 3: 
					imprimirIgrejas();
					break;
				case 4: 
					excluirIgreja(scan);
					break;		
				default:
					break;
				}
		}while(opcao != 0);
	}
	public static void menuVisitante() throws SQLException, ParseException{
		int opcao;	
		do{
			System.out.println("=========================");
			System.out.println("Digite a opÃ§Ã£o desejada:");
			System.out.println("1: Adicionar Visitante ");
			System.out.println("2: Listar Visitantes");
			System.out.println("3: Imprimir Visitante");
			System.out.println("4: Deletar Visitante");
			System.out.println("0: Sair");
			System.out.println("=========================");

			opcao = scan.nextInt();
			
			switch (opcao) {
				case 1:
					adicionarVisitante();
					break;
				case 2: 
					listarVisitantes();
					break;
				case 3: 
					imprimirVisitantes();
					break;
				case 4: 
					deletaVisitante();
					break;		
				default:
					break;
				}
		}while(opcao != 0);
	}


	public static void menuAviso() throws SQLException, ParseException{
		int opcao;	
		do{
			System.out.println("=========================");
			System.out.println("Digite a opÃ§Ã£o desejada:");
			System.out.println("1: Adicionar Aviso ");
			System.out.println("2: Listar Aviso");
			System.out.println("3: Imprimir Aviso");
			System.out.println("4: Deletar Aviso");
			System.out.println("0: Sair");
			System.out.println("=========================");

			opcao = scan.nextInt();
			
			switch (opcao) {
				case 1:
					adicionarAviso();
					break;
				case 2: 
					listarAvisos();
					break;
				case 3: 
					imprimirAviso();
					break;
				case 4: 
					excluirAvisoPorId(scan);
					break;		
				default:
					break;
				}
		}while(opcao != 0);
	}
	//======================================================

	// ====================== CRUD AVISOS ====================

	private static void adicionarAviso() throws SQLException, ParseException{
		System.out.println("Criando um aviso, entre com as informaÃ§Ãµes: ");
		scan.nextLine();
		aviso.setAssuntoAviso(leInformacao("Assunto: ", scan));
		aviso.setConteudoAviso(leInformacao("ConteÃºdo: ", scan));
		aviso.setDataAviso(new GregorianCalendar(2000,01,15));
		aviso.setDestinoAviso(leInformacao("DestinatÃ¡rios: ", scan));
		aviso.setIdLider(leInformacaoInt("Id lider: ", scan));
		aviso.setOrigemAviso(leInformacao("Origim: ", scan));

		avisoDAO.inserir(aviso);
	}

	private static void excluirAvisoPorId (Scanner scan) {
		aviso.setIdAviso(leInformacaoInt("Id: ", scan));
		avisoDAO.removerAvisoporId(aviso);
	}
	
	private static void listarAvisos() {
		// Listar todos os Avisos
		List<AvisoVO> avisos = avisoDAO.listar();
		for (AvisoVO aviso : avisos) {
			System.out.println(aviso.getIdAviso() + "\t" + aviso.getAssuntoAviso() + "\t"
					+ aviso.getAssuntoAviso());
		}
	}

	private static void imprimirAviso(){
		List<AvisoVO> avisos = avisoDAO.listar();
		for (AvisoVO avisos2 : avisos) {
			System.out.println(avisos2.toString());
		}
	}

	//========================================================

	//==================== CRUD VISITANTES ==================

	public static void adicionarVisitante() throws SQLException, ParseException{
		VisitanteDAO<VisitanteVO> visDAO = new VisitanteDAO<VisitanteVO>();
		PessoaDAO<PessoaVO> pessoaDAO = new PessoaDAO<PessoaVO>();
		VisitanteVO vis = new VisitanteVO();
		vis.setIdVisitante(leInformacaoInt("Digite o id do visitante:",scan));
		vis.setIdPessoa(leInformacaoInt("Digite o id da pessoa:",scan));
		vis.setNomePessoa(leInformacao("Nome:", scan));
		vis.setDataNascimentoPessoa(new GregorianCalendar(2000,01,15));
		vis.setCepPessoa(leInformacao("Digite o CEP:", scan));
		vis.setLogradouroPessoa(leInformacao("EndereÃ§o: ", scan));
		vis.setComplementoPessoa(leInformacao("Complemento do endereÃ§o:", scan));
		vis.setBairroPessoa(leInformacao("Digite o bairro:", scan));
		vis.setCidadePessoa(leInformacao("Nome da Cidade:", scan));
		vis.setUfPessoa(leInformacao("UF", scan));
		vis.setPontoReferPessoa(leInformacao("Ponto de referencia: ", scan));
		vis.setEmailPessoa(leInformacao("Email: ",scan));
		vis.setFonePessoa(leInformacao("Telefone:", scan));
		vis.setNaturalidadePessoa(leInformacao("Naturalidade:", scan));
		vis.setSexoPessoa(leInformacaoInt("Sexo: ", scan));
		vis.setEstCivilPessoa(leInformacaoInt("Estado civil:", scan));
		vis.setConjugePessoa(leInformacao("Conjuge:", scan));
		vis.setDataCasamentoPessoa(new GregorianCalendar(2020,01,15));
		vis.setIdentPessoa(leInformacao("Identidade: ", scan));
		vis.setOrgEmissorIdentPessoa(leInformacao("OrgÃ£o Emissor: ", scan));
		vis.setDataEmissaoIdentPessoa(new GregorianCalendar(2012, 0, 14));
		vis.setEscolaridadePessoa(leInformacaoInt("Escolaridade:", scan));
		vis.setProfissaoPessoa(leInformacao("ProfissÃ£o", scan));
		vis.setNomePaiPessoa(leInformacao("Nome do pai:", scan));
		vis.setNomeMaePessoa(leInformacao("Nome da mÃ£e: ", scan));
		vis.setCongregaPessoa(1);
		vis.setDataUltVisita(new GregorianCalendar(2020,01,15));
		
		
		visDAO.inserir(vis);
		pessoaDAO.inserir(vis);
	}
	

	private static void listarVisitantes() {
		VisitanteDAO<VisitanteVO> visDAO = new VisitanteDAO<VisitanteVO>();
		List<VisitanteVO> visitantes = visDAO.listar();
		for (VisitanteVO vis2 : visitantes) {
			//System.out.println(vis2.toString());
			System.out.println(vis2.getIdPessoa() + "\t" + vis2.getNomePessoa() + "\t"
			+ vis2.getLogradouroPessoa() + "\t" +  vis2.getBairroPessoa());
		}
	}

	private static void imprimirVisitantes(){
		VisitanteDAO<VisitanteVO> visDAO = new VisitanteDAO<VisitanteVO>();
		List<VisitanteVO> visitantes = visDAO.listar();
		for (VisitanteVO vis2 : visitantes) {
			System.out.println(vis2.toString());
		}
	}

	private static void deletaVisitante() throws SQLException{
		VisitanteDAO<VisitanteVO> visDAO = new VisitanteDAO<VisitanteVO>();
		VisitanteVO vis = new VisitanteVO();
		int id;
		listarVisitantes();
		System.out.println("Escolha o id para o visitante ser deletado: ");
		id = scan.nextInt();
		vis.setIdVisitante(id);
		

		visDAO.remover(vis);
	}

}
