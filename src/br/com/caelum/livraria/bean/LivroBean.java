package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;

@ManagedBean
@ViewScoped
public class LivroBean {

	private Livro livro = new Livro();
	
	private Integer autorId;
	
	private Integer livroId;

	public Livro getLivro() {
		return livro;
	}	
	
	public List<Livro> getLivros(){
		return new DAO<Livro>(Livro.class).listaTodos();
	}
	
	public Integer getAutorId() {
		return autorId;
	}
	
	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public List<Autor> getAutores(){
		return new DAO<Autor>(Autor.class).listaTodos();
	}
	
	public List<Autor> getAutoresDoLivro(){
		return this.livro.getAutores();
	}	

	public Integer getLivroId() {
		return livroId;
	}

	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}

	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());
		if (livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor."));
			return;
		}
		if(this.livro.getId() == null) {
			new DAO<Livro>(Livro.class).adiciona(this.livro);
		}else {
			new DAO<Livro>(Livro.class).atualiza(this.livro);
		}
		this.livro = new Livro();
	}
	
	public void gravarAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
		if(!this.livro.getAutores().contains(autor)) {
			this.livro.adicionaAutor(autor);
		}
	}
	
	public void validaISBN(FacesContext fc, UIComponent component, Object value) {
		String valor = value.toString();
		if(!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("ISBN deve começar com dígito 1"));
		}
	}
	
	public void remover(Livro livro) {
		new DAO<Livro>(Livro.class).remove(livro);
	}
	
	public void removerAutorDoLivro(Autor autor) {
		this.livro.remover(autor);
	}
	
	public void editar(Livro livro) {
		this.livro = livro;
	}
	
	public void carregarLivroPorId() {
		this.livro = new DAO<Livro>(Livro.class).buscaPorId(this.livroId);
	}

}
