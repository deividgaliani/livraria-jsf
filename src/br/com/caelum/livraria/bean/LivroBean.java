package br.com.caelum.livraria.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Genero;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.LivroDataModel;

@ManagedBean
@ViewScoped
public class LivroBean {

	private Livro livro = new Livro();
	
	private Integer autorId;
	
	private Integer livroId;

	private List<Livro> livros;
	
	private LivroDataModel livroDataModel = new LivroDataModel();
	
	private List<String> generos;

	public Livro getLivro() {
		return livro;
	}	
	
	public List<String> getGeneros() {
		DAO<Genero> dao = new DAO<Genero>(Genero.class);
		List<Genero> generos = dao.listaTodos();
		this.generos = new ArrayList<>();
		for (Genero g : generos) {
			this.generos.add(g.getDescricao());
		}
		return this.generos;
	}

	public List<Livro> getLivros(){
		DAO<Livro> dao = new DAO<Livro>(Livro.class);
		if(this.livros == null) {
			this.livros = dao.listaTodos();
		}
		return livros;
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

	public LivroDataModel getLivroDataModel() {
		return livroDataModel;
	}

	public void setLivroDataModel(LivroDataModel livroDataModel) {
		this.livroDataModel = livroDataModel;
	}

	public void gravar() {
		if (livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Livro deve ter pelo menos um Autor."));
			return;
		}
		System.out.println("Gravando livro " + this.livro.getTitulo());
		DAO<Livro> dao = new DAO<Livro>(Livro.class);
		if(this.livro.getId() == null) {
			dao.adiciona(this.livro);
		}else {
			dao.atualiza(this.livro);
		}
		this.livros = dao.listaTodos();
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
