package br.com.caelum.livraria.dto;

import java.util.List;

import br.com.caelum.livraria.modelo.Autor;

public class AutorDTO {
	private int quantidadeElementos;
	private List<Autor> autores;
	
	public int getQuantidadeElementos() {
		return quantidadeElementos;
	}
	public void setQuantidadeElementos(int quantidadeElementos) {
		this.quantidadeElementos = quantidadeElementos;
	}
	public List<Autor> getAutores() {
		return autores;
	}
	public void setAutores(List<Autor> listaDeAutores) {
		this.autores = listaDeAutores;
	}
	
	
}
