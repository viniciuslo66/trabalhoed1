package igreja.ModelBO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import exception.InsertException;
import igreja.ModelDAO.ComunDAO;
import igreja.ModelVO.ComunVO;

public class LiderBO {

	static private ComunDAO<ComunVO> dao = new ComunDAO<ComunVO>();

	public void inserir(ComunVO vo) throws InsertException {
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

	public void remover(ComunVO vo) throws InsertException {
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

	public ComunVO buscarComun(int idVisitante) {
		// toDO
		ComunVO visitante = new ComunVO();

		return visitante;
	}

	public List<ComunVO> listar() {
		List<ComunVO> visitantes = dao.listar();

		return visitantes;
	}
}
