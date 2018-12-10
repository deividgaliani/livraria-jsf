package br.com.caelum.livraria.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.validator.ValidatorException;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.AutorDataModel;

@ManagedBean
@ViewScoped
public class AutorBean {

	private Autor autor = new Autor();
	
	private Integer autorId;
	
	private AutorDataModel autorDataModel = new AutorDataModel();

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public Autor getAutor() {
		return autor;
	}	

	public AutorDataModel getAutorDataModel() {
		return autorDataModel;
	}

	public void setAutorDataModel(AutorDataModel autorDataModel) {
		this.autorDataModel = autorDataModel;
	}

	public void gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());
		if(this.autor.getId() == null) {
			new DAO<Autor>(Autor.class).adiciona(this.autor);
		}else {
			new DAO<Autor>(Autor.class).atualiza(this.autor);
		}
		this.autor = new Autor();
	}
	
	public void alterar(Autor autor) {
		this.autor = autor;
	}
	
	public void remover(Autor autor) {
		try {
			new DAO<Autor>(Autor.class).remove(autor);
		}catch (Exception e) {
			e.printStackTrace();
			throw new ValidatorException(new FacesMessage("Erro ao tentar excluir o autor"));
		}
	}
	
	public void carregarAutorPorId() {
		this.autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
	}
}
