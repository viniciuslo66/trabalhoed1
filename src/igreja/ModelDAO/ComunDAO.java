package igreja.ModelDAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import igreja.ModelVO.ComunVO;
import igreja.ModelVO.PessoaVO;

public class ComunDAO<VO extends PessoaVO> extends ConnectBD {

	public List<ComunVO> listar() throws SQLException{
		String sql = "select * from Pessoa inner join Comungante where Pessoa.idPessoa = Comungante.idPessoa";
		Statement st;
		ResultSet resultado = null;
		List<ComunVO> comun = new ArrayList<ComunVO>();

		try {
			st = getConnection().createStatement();
			resultado = st.executeQuery(sql);
			while (resultado.next()) {
				ComunVO com = new ComunVO();
				com.setIdPessoa(resultado.getInt("idPessoa"));
				com.setNomePessoa(resultado.getString("nomePessoa"));
				com.setCongregaPessoa(resultado.getInt("congregaPessoa"));
				com.setLogradouroPessoa(resultado.getString("logradouroPessoa"));
				com.setBairroPessoa(resultado.getString("bairroPessoa"));
				com.setCidadePessoa(resultado.getString("cidadePessoa"));
				com.setDataNascimentoPessoa(toCalendar(resultado.getDate("nascimentoPessoa")));
				com.setCepPessoa(resultado.getString("cepPessoa"));
				com.setComplementoPessoa(resultado.getString("complementoPessoa"));
				com.setUfPessoa(resultado.getString("ufPessoa"));
				com.setPontoReferPessoa(resultado.getString("pontoReferPessoa"));
				com.setEmailPessoa(resultado.getString("emailPessoa"));
				com.setFonePessoa(resultado.getString("fonePessoa"));
				com.setNaturalidadePessoa(resultado.getString("naturalidadePessoa"));
				com.setSexoPessoa(resultado.getInt("sexoPessoa"));
				com.setDataCasamentoPessoa(toCalendar(resultado.getDate("dataCasamentoPessoa")));
				com.setDataEmissaoIdentPessoa(toCalendar(resultado.getDate("dataEmissaoIdentPessoa")));
				com.setConjugePessoa(resultado.getString("conjugePessoa"));
				com.setIdentPessoa(resultado.getString("identPessoa"));
				com.setEscolaridadePessoa(resultado.getInt("escolaridadePessoa"));
				com.setOrgEmissorIdentPessoa(resultado.getString("orgEmissorIdentPessoa"));
				com.setNomePaiPessoa(resultado.getString("nomePaiPessoa"));
				com.setNomeMaePessoa(resultado.getString("nomeMaePessoa"));
				com.setProfissaoPessoa(resultado.getString("profissaoPessoa"));
				((ComunVO) com).setIdComun(resultado.getInt("idComun"));
				((ComunVO) com).setNumOrdem(resultado.getInt("numOrdem"));
				((ComunVO) com).setDataBatismo(toCalendar(resultado.getDate("dataBatismo")));
				((ComunVO) com).setPastorBatismo(resultado.getString("pastorBatismo"));
				((ComunVO) com).setIgrejaBatismo(resultado.getString("igrejaBatismo"));
				((ComunVO) com).setDataProfFe(toCalendar(resultado.getDate("dataProffe")));
				((ComunVO) com).setTipoMembro(resultado.getInt("tipoMembro"));
				((ComunVO) com).setDataAdmissao(toCalendar(resultado.getDate("dataAdmissao")));
				((ComunVO) com).setMeioAdmissao(resultado.getInt("meioAdmissao"));
				((ComunVO) com).setDataDemissao(toCalendar(resultado.getDate("dataDemissao")));
				((ComunVO) com).setMeioDemissao(resultado.getInt("meiodemissao"));
				((ComunVO) com).setSituacao(resultado.getBoolean("situacao"));

				comun.add(com);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comun;
	}

	public static Calendar toCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public void inserir(ComunVO comun) throws SQLException {
		String sql = "insert into comun(idPessoa, numOrdem, dataBatismo, pastorBatismo, igrejaBatismo, dataProffe, PastorProffe "
				+ "igrejaProffe, tipoMembro, dataAdmissao. meioAdmissao, dataDemissao, meioDemissao, situacao) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ptst;
		try {
			ptst = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ptst.setInt(1, comun.getIdPessoa());
			ptst.setInt(2, comun.getNumOrdem());
			ptst.setDate(3, new Date(comun.getDataBatismo().getTimeInMillis()));
			ptst.setString(4, comun.getPastorBatismo());
			ptst.setString(5, comun.getIgrejaBatismo());
			ptst.setDate(6, new Date(comun.getDataProfFe().getTimeInMillis()));
			ptst.setString(7, comun.getPastorProfFe());
			ptst.setString(8, comun.getIgrejaProfFe());
			ptst.setInt(9, comun.getTipoMembro());
			ptst.setDate(10, new Date(comun.getDataAdmissao().getTimeInMillis()));
			ptst.setInt(11, comun.getMeioAdmissao());
			ptst.setDate(12, new Date(comun.getDataDemissao().getTimeInMillis()));
			ptst.setInt(13, comun.getMeioDemissao());
			ptst.setBoolean(14, comun.isSituacao());

			int affectedRows = ptst.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("A inserção falhou. Nenhuma linha foi alterada.");
			}
			ResultSet generatedKeys = ptst.getGeneratedKeys();
			if (generatedKeys.next()) {
				((ComunVO) comun).setIdComun(generatedKeys.getInt(1));
			} else {
				throw new SQLException("A inserção falhou. Nenhum id foi retornado.");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void remover(VO comun) throws SQLException {
		String sql = "DELETE FROM visitante WHERE idComun=?";
		PreparedStatement ptst;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setInt(1, ((ComunVO) comun).getIdComun());
			ptst.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public ResultSet buscar(ComunVO lider) throws SQLException {
		String sql = "select nome from Pessoa inner join comungante where Pessoa.idPessoa = comungante.idPessoa";
		PreparedStatement ptst;
		ResultSet resultado = null;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setInt(1, lider.getIdComun());
			resultado = ptst.executeQuery();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return resultado;
	}

	public ComunVO buscarById(Integer id) throws SQLException {
		String sql = "SELECT pessoa.nomePessoa, comungante.numOrdem FROM pessoa "
				+ "INNER JOIN comunganteON lider.idPessoa = pessoa.idPessoa WHERE comungante.idComun = ?";
		PreparedStatement ptst;
		ResultSet res = null;
		ComunVO lider = new ComunVO();
		PessoaVO pessoa = new PessoaVO();

		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setInt(1, id);
			res = ptst.executeQuery();
			if (res.next()) {
				pessoa.setNomePessoa(res.getString("NomePessoa"));
				lider.setNumOrdem(res.getInt("numOrdem"));
				lider.setPessoa(pessoa);
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lider;
	}
}
