package br.com.caelum.livraria.dto;

import java.util.List;

import br.com.caelum.livraria.modelo.Livro;

public class LivroDTO {
	private int quantidadeElementos;
	private List<Livro> livros;
	
	public int getQuantidadeElementos() {
		return quantidadeElementos;
	}
	public void setQuantidadeElementos(int quantidadeElementos) {
		this.quantidadeElementos = quantidadeElementos;
	}
	public List<Livro> getLivros() {
		return livros;
	}
	public void setLivros(List<Livro> listaDeLivros) {
		this.livros = listaDeLivros;
	}
	
	
}
