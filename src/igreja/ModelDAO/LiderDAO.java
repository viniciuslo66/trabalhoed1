package igreja.ModelDAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import igreja.ModelVO.LiderVO;
import igreja.ModelVO.PessoaVO;

public class LiderDAO<VO extends PessoaVO> extends ConnectBD implements PessoaInterDAO<VO> {

	public List<LiderVO> listar() {
		String sql = "select * from Pessoa inner join Comungante where Pessoa.idPessoa = Lider.idPessoa";
		Statement st;
		ResultSet resultado = null;
		List<LiderVO> lider = new ArrayList<LiderVO>();

		try {
			st = getConnection().createStatement();
			resultado = st.executeQuery(sql);
			while (resultado.next()) {
				LiderVO lid = new LiderVO();
				lid.setIdPessoa(resultado.getInt("idPessoa"));
				((LiderVO) lid).setIdComun(resultado.getInt("idLider"));
				((LiderVO) lid).setDataPriEleicao(toCalendar(resultado.getDate("DataPriEleicao")));
				((LiderVO) lid).setDataUltEleicao(toCalendar(resultado.getDate("DataUltEleicao")));
				((LiderVO) lid).setCargoOficio(resultado.getInt("cargoOficial"));
				((LiderVO) lid).setEntidade(resultado.getInt("entidade"));
				lid.setNomePessoa(resultado.getString("nomePessoa"));
				lid.setCongregaPessoa(resultado.getInt("congregaPessoa"));
				lid.setLogradouroPessoa(resultado.getString("logradouroPessoa"));
				lid.setBairroPessoa(resultado.getString("bairroPessoa"));
				lid.setCidadePessoa(resultado.getString("cidadePessoa"));
				lid.setDataNascimentoPessoa(toCalendar(resultado.getDate("nascimentoPessoa")));
				lid.setCepPessoa(resultado.getString("cepPessoa"));
				lid.setComplementoPessoa(resultado.getString("lidplementoPessoa"));
				lid.setUfPessoa(resultado.getString("ufPessoa"));
				lid.setPontoReferPessoa(resultado.getString("pontoReferPessoa"));
				lid.setEmailPessoa(resultado.getString("emailPessoa"));
				lid.setFonePessoa(resultado.getString("fonePessoa"));
				lid.setNaturalidadePessoa(resultado.getString("naturalidadePessoa"));
				lid.setSexoPessoa(resultado.getInt("sexoPessoa"));
				lid.setDataCasamentoPessoa(toCalendar(resultado.getDate("dataCasamentoPessoa")));
				lid.setDataEmissaoIdentPessoa(toCalendar(resultado.getDate("dataEmissaoIdentPessoa")));
				lid.setConjugePessoa(resultado.getString("conjugePessoa"));
				lid.setIdentPessoa(resultado.getString("identPessoa"));
				lid.setEscolaridadePessoa(resultado.getInt("escolaridadePessoa"));
				lid.setOrgEmissorIdentPessoa(resultado.getString("orgEmissorIdentPessoa"));
				lid.setNomePaiPessoa(resultado.getString("nomePaiPessoa"));
				lid.setNomeMaePessoa(resultado.getString("nomeMaePessoa"));
				lid.setProfissaoPessoa(resultado.getString("profissaoPessoa"));

				lider.add(lid);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lider;
	}

	public static Calendar toCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public void inserir(LiderVO lider) throws SQLException {

		String sql = "insert into lider(idPessoa, dataPriEleicao, dataUltEleicao, CargoOficio, entidade) values(?,?,?,?,?)";
		PreparedStatement ptst;
		try {
			ptst = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ptst.setInt(1, lider.getIdPessoa());
			ptst.setDate(2, new Date(((LiderVO) lider).getDataPriEleicao().getTimeInMillis()));
			ptst.setDate(3, new Date(((LiderVO) lider).getDataUltEleicao().getTimeInMillis()));
			ptst.setInt(4, lider.getCargoOficio());
			ptst.setInt(5, lider.getEntidade());

			int affectedRows = ptst.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("A inserção falhou. Nenhuma linha foi alterada.");
			}
			ResultSet generatedKeys = ptst.getGeneratedKeys();
			if (generatedKeys.next()) {
				((LiderVO) lider).setIdComun(generatedKeys.getInt(1));
			} else {
				throw new SQLException("A inserção falhou. Nenhum id foi retornado.");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void remover(VO lider) throws SQLException {
		String sql = "DELETE FROM lider WHERE idLider = ?";
		PreparedStatement ptst;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setInt(1, ((LiderVO) lider).getIdLider());
			ptst.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public ResultSet buscar(LiderVO lider) throws SQLException {
		String sql = "select nome from Pessoa inner join Comungante where Pessoa.idPessoa = Lider.idPessoa";
		PreparedStatement ptst;
		ResultSet resultado = null;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setInt(1, lider.getIdLider());
			resultado = ptst.executeQuery();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return resultado;
	}

	public PessoaVO buscarById (Integer id) throws SQLException {
		String sql = "SELECT pessoa.nomePessoa, lider.dataPriEleicao, lider.cargoOficial FROM pessoa JOIN lider ON pessoa.idPessoa = lider.idPessoa WHERE lider.idLider = ?";
		PreparedStatement ptst;
		ResultSet res = null;

		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setInt(1, id);
			res = ptst.executeQuery();
			if (res.next()) {
				LiderVO lider = new LiderVO();
				lider.setIdLider(res.getInt("idLider"));

				PessoaVO pessoa = new PessoaVO();
				pessoa.setIdPessoa(res.getInt("idPessoa"));
				pessoa.setNomePessoa(res.getString("NomePessoa"));


			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public void alterarEndereco(VO vo) throws SQLException {
		// TODO Auto-generated method stub

	}
	@Override
	public void alterarEstadoCivil(VO vo) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void alterarEscolaridade(VO pessoa) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void alterarProfissao(VO vo) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void alterarCongrega(VO pessoa) throws SQLException {
		// TODO Auto-generated method stub

	}
}
