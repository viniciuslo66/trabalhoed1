package igreja.ModelDAO;

import java.sql.SQLException;

import igreja.ModelVO.LiderVO;
import igreja.ModelVO.PessoaVO;
	
	public interface PessoaInterDAO<VO extends PessoaVO> extends ConnectBDinterDAO<VO>{

		public void alterarEndereco(VO vo) throws SQLException;
		
		public void alterarEstadoCivil(VO vo) throws SQLException;
		
		public void alterarEscolaridade(VO pessoa) throws SQLException;
		
		public void alterarProfissao(VO vo) throws SQLException;		
		
		void alterarCongrega(VO pessoa) throws SQLException;
	}
