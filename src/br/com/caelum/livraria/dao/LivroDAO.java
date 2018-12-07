package br.com.caelum.livraria.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.primefaces.model.SortOrder;

import br.com.caelum.livraria.dto.LivroDTO;
import br.com.caelum.livraria.modelo.Livro;

public class LivroDAO {
	
	public LivroDTO listaTodosPaginada(int firstResult, int maxResults, Map<String, Object> filters, 
			String sortField, SortOrder sortOrder) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT l FROM Livro l ");
		sb.append(addConditions(filters));
		sb.append(sortConditions(sortField, sortOrder));
		EntityManager em = new JPAUtil().getEntityManager();
		Query query = em.createQuery(sb.toString());
		int qte_row = query.getResultList().size();
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		LivroDTO dto= new LivroDTO();
		try {
			List<Livro> livros = query.getResultList();
			dto.setLivros(livros);
			dto.setQuantidadeElementos(qte_row);
			return dto;
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			em.close();			
		}
		return null;
	}
	
	private String sortConditions(String sortField, SortOrder sortOrder) {
		StringBuilder sb = new StringBuilder();
		if(sortField != null) {
			sb.append(" ORDER BY " + sortField);
			if(sortOrder.toString().equalsIgnoreCase("DESCENDING")) {
				sb.append(" DESC ");
			}else {
				sb.append(" ASC ");
			}
		}
		return sb.toString();
	}

	private String addConditions(Map<String, Object> filters) {
		StringBuilder sb = new StringBuilder();
		sb.append(" where (1 = 1) ");
		for (String key : filters.keySet()) {
			String valor = (String) filters.get(key);
			if(valor != null) {
				sb.append(" and " + key + " like '%" + valor + "%' ");
			}
		}
		return sb.toString();
	}
}
