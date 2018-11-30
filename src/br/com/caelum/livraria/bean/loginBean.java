package br.com.caelum.livraria.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.caelum.livraria.dao.UsuarioDAO;
import br.com.caelum.livraria.modelo.Usuario;

@ManagedBean
@ViewScoped
public class loginBean {
	
	private Usuario usuario = new Usuario();
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String logar() {
		Usuario usuario = new UsuarioDAO().buscarUsuario(this.usuario);
		if(usuario != null) {
			return "livro?faces-redirect=true";
		}else {
			FacesContext.getCurrentInstance().addMessage("loginMsgError", new FacesMessage("Usuário ou senha inválido"));
		}
		return null;
	}
	
}
