package br.com.caelum.livraria.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.caelum.livraria.modelo.Usuario;

public class UsuarioDAO {
	
	public boolean existeUsuario(Usuario usuario) {
		EntityManager em = new JPAUtil().getEntityManager();
		TypedQuery<Usuario> query = em.createQuery(
				"select u from Usuario u "
				+ " where u.email = :uEmail and u.senha = :uSenha ", Usuario.class);
		query.setParameter("uEmail", usuario.getEmail());
		query.setParameter("uSenha", usuario.getSenha());
		Usuario result = query.getSingleResult();
		em.close();		
		return result != null;
	}
}
