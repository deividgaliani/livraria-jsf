package br.com.caelum.livraria.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.caelum.livraria.dao.UsuarioDAO;
import br.com.caelum.livraria.modelo.Usuario;

@ManagedBean
@ViewScoped
public class LoginBean {
	
	private Usuario usuario;
	
	public LoginBean() {
		this.usuario =  new Usuario();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String logar() {
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		Usuario usuario = new UsuarioDAO().buscarUsuario(this.usuario);
		if(usuario != null) {
			this.usuario = usuario;
			currentInstance.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			return "livro?faces-redirect=true";
		}else {
			currentInstance.addMessage(null, new FacesMessage("Usuário ou senha inválido"));
		}
		return null;
	}
	
	public String deslogar() {
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		currentInstance.getExternalContext().getSessionMap().remove("usuarioLogado");
		this.usuario = new Usuario();
		return "login?faces-redirect=true";
	}
	
	public Usuario getUsuarioLogado() {
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		Usuario logado = (Usuario) currentInstance.getExternalContext().getSessionMap().get("usuarioLogado");
		return logado;
	}
	
}
