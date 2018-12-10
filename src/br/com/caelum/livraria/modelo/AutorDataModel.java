package br.com.caelum.livraria.modelo;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.caelum.livraria.dao.AutorDAO;
import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.dto.AutorDTO;

public class AutorDataModel extends LazyDataModel<Autor>{
	private static final long serialVersionUID = 1L;
	private DAO<Autor> dao;
	
	public AutorDataModel() {
		dao = new DAO<Autor>(Autor.class);
		super.setRowCount(dao.quantidadeDeElementos());
	}
	
	@Override
	public List<Autor> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		AutorDAO dao = new AutorDAO();
		AutorDTO dto = dao.listaTodosPaginada(first, pageSize, filters, sortField, sortOrder);
		super.setRowCount(dto.getQuantidadeElementos());
		return dto.getAutores();
	}
	
}
