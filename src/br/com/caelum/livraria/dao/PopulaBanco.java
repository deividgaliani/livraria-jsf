package br.com.caelum.livraria.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;

import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Genero;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.Tema;
import br.com.caelum.livraria.modelo.Usuario;

public class PopulaBanco {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		Autor assis = geraAutor("Machado de Assis");
		em.persist(assis);

		Autor amado = geraAutor("Jorge Amado");
		em.persist(amado);

		Autor coelho = geraAutor("Paulo Coelho");
		em.persist(coelho);
		
		Genero romance = new Genero("Romance");
		Genero drama = new Genero("Drama");
		Genero acao = new Genero("Ação");
		Genero realismo = new Genero("Realismo");
		Genero ficcao = new Genero("Ficção");
		Genero comedia = new Genero("Comédia");
		em.persist(romance);
		em.persist(drama);
		em.persist(acao);
		em.persist(realismo);
		em.persist(comedia);
		em.persist(ficcao);

		Livro casmurro = geraLivro("978-8-52-504464-8", "Dom Casmurro",
				"10/01/1899", 24.90, assis, realismo.getDescricao());
		Livro memorias = geraLivro("978-8-50-815415-9",
				"Memorias Postumas de Bras Cubas", "01/01/1881", 19.90, assis, romance.getDescricao());
		Livro quincas = geraLivro("978-8-50-804084-1", "Quincas Borba",
				"01/01/1891", 16.90, assis, ficcao.getDescricao());

		em.persist(casmurro);
		em.persist(memorias);
		em.persist(quincas);
		

		Livro alquimista = geraLivro("978-8-57-542758-3", "O Alquimista",
				"01/01/1988", 19.90, coelho, ficcao.getDescricao());
		Livro brida = geraLivro("978-8-50-567258-7", "Brida", "01/01/1990",
				12.90, coelho, acao.getDescricao());
		Livro valkirias = geraLivro("978-8-52-812458-8", "As Valkirias",
				"01/01/1992", 29.90, coelho, ficcao.getDescricao());
		Livro maao = geraLivro("978-8-51-892238-9", "O Diario de um Mago",
				"01/01/1987", 9.90, coelho, ficcao.getDescricao());

		em.persist(alquimista);
		em.persist(brida);
		em.persist(valkirias);
		em.persist(maao);

		Livro capitaes = geraLivro("978-8-50-831169-1", "Capitaes da Areia",
				"01/01/1937", 6.90, amado, ficcao.getDescricao());
		Livro flor = geraLivro("978-8-53-592569-9",
				"Dona Flor e Seus Dois Maridos", "01/01/1966", 18.90, amado, comedia.getDescricao());

		em.persist(capitaes);
		em.persist(flor);
		
		Usuario usuario = geraUsuario("Deivid", "teste@teste.com", "123456");
		em.persist(usuario);
		
		Tema aristo = new Tema("Aristo" ,"aristo");
		Tema vader = new Tema("Vader", "vader");
		Tema rocket = new Tema("Rocket", "rocket");
		Tema cupertino = new Tema("Cupertino", "cupertino");
		
		em.persist(vader);
		em.persist(aristo);
		em.persist(rocket);
		em.persist(cupertino);
		

		em.getTransaction().commit();	
		em.close();

	}

	private static Usuario geraUsuario(String nome, String email, String senha) {
		Usuario user = new Usuario();
		user.setNome(nome);
		user.setEmail(email);
		user.setSenha(senha);
		return user;
	}

	private static Autor geraAutor(String nome) {
		Autor autor = new Autor();
		autor.setNome(nome);
		return autor;
	}

	private static Livro geraLivro(String isbn, String titulo, String data,
			double preco, Autor autor, String genero) {
		Livro livro = new Livro();
		livro.setIsbn(isbn);
		livro.setTitulo(titulo);
		livro.setDataLancamento(parseData(data));
		livro.setPreco(preco);
		livro.setGenero(genero);
		livro.adicionaAutor(autor);
		return livro;
	}

	private static Calendar parseData(String data) {
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

}
