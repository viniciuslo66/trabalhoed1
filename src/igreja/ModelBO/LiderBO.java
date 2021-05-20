package igreja.ModelBO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import exception.InsertException;
import igreja.ModelDAO.LiderDAO;
import igreja.ModelVO.LiderVO;

public class LiderBO {

	static private LiderDAO<LiderVO> dao = new LiderDAO<LiderVO>();

	public void inserir(LiderVO vo) throws InsertException {
		try {
			ResultSet rs = dao.buscar(vo);
			if (rs.next()) {
				throw new InsertException("Impossível inserir, pois já existe um Comungante com esse id");
			} else {
				dao.inserir(vo);
			}
		} catch (SQLException e) {
			throw new InsertException(e.getMessage());
		}
	}

	public void remover(LiderVO vo) throws InsertException {
		try {
			ResultSet rs = dao.buscar(vo);
			if (rs.next()) {
				dao.remover(vo);
			} else {
				throw new InsertException("Impossível remover, pois não existe nenhum Comungante com esse id");
			}
		} catch (SQLException e) {
			throw new InsertException(e.getMessage());
		}

	}

	public LiderVO buscarLider(int idLider) {
		LiderVO visitante = new LiderVO();

		return visitante;
	}

	public List<LiderVO> listar() {
		List<LiderVO> visitantes = dao.listar();

		return visitantes;
	}
}
