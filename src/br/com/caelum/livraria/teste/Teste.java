package br.com.caelum.livraria.teste;

import java.util.List;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Genero;

public class Teste {

	public static void main(String[] args) {
		DAO<Genero> dao = new DAO<Genero>(Genero.class);
		List<Genero> generos = dao.listaTodos();
		
		for (Genero g : generos) {
			System.out.println(g.getDescricao());
		}
	}

}
