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

public class ComunDAO<VO extends PessoaVO> extends ConnectBD implements PessoaInterDAO<VO> {

	public List<ComunVO> listar() {
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
				((ComunVO) com).setIdComun(resultado.getInt("idComun"));
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

				comun.add(com);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return comun;
	}

	public static Calendar toCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	@Override
	public void inserir(VO comun) throws SQLException {
		String sql = "insert into comun(idComun, idPessoa) values(?,?)";
		PreparedStatement ptst;
		try {
			ptst = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ptst.setInt(1, ((ComunVO) comun).getIdComun());
			ptst.setInt(2, comun.getIdPessoa());

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

	@Override
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

	@Override
	public ResultSet buscar(VO vo) throws SQLException {
		// TODO Auto-generated method stub
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
