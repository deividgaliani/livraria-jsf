package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.validator.ValidatorException;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Genero;

@ManagedBean
@ViewScoped
public class GeneroBean {

	private Genero genero = new Genero();
	
	private Integer generoId;	

	public Integer getGeneroId() {
		return generoId;
	}

	public void setGeneroId(Integer generoId) {
		this.generoId = generoId;
	}

	public Genero getGenero() {
		return genero;
	}
	
	public List<Genero> getGeneros(){
		return new DAO<Genero>(Genero.class).listaTodos();
	}

	public void gravar() {
		if(this.genero.getId() == null) {
			new DAO<Genero>(Genero.class).adiciona(this.genero);
		}else {
			new DAO<Genero>(Genero.class).atualiza(this.genero);
		}
		this.genero = new Genero();
	}
	
	public void alterar(Genero genero) {
		this.genero = genero;
	}
	
	public void remover(Genero genero) {
		try {
			new DAO<Genero>(Genero.class).remove(genero);
		}catch (Exception e) {
			e.printStackTrace();
			throw new ValidatorException(new FacesMessage("Erro ao tentar excluir o genero"));
		}
	}
	
	public void carregarGeneroPorId() {
		this.genero = new DAO<Genero>(Genero.class).buscaPorId(this.generoId);
	}
}
