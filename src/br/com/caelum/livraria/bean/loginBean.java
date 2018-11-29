package br.com.caelum.livraria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
		boolean usuarioExiste = new UsuarioDAO().existeUsuario(this.usuario);
		if(usuarioExiste) {
			return "livro?faces-redirect=true";
		}
		return null;
	}
	
}
