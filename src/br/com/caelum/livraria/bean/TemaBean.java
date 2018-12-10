package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Tema;

@ManagedBean
@SessionScoped
public class TemaBean {
	
	private String tema = "cupertino";

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}
	
	public List<Tema> getTemas(){
		DAO<Tema> dao = new DAO<Tema>(Tema.class);
		return dao.listaTodos();
	}
}
