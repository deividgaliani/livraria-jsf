package br.com.caelum.livraria.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.caelum.livraria.modelo.Usuario;

public class UsuarioDAO {
	
	public Usuario buscarUsuario(Usuario usuario) {
		EntityManager em = new JPAUtil().getEntityManager();
		TypedQuery<Usuario> query = em.createQuery(
				" from Usuario usuario "
				+ " where usuario.email = :email and usuario.senha = :senha ", Usuario.class);
		query.setParameter("email", usuario.getEmail())
			.setParameter("senha", usuario.getSenha());
		try {
			Usuario result = query.getSingleResult();
			return result;
		}catch (Exception e) {
			//doNothing
		}finally {
			em.close();
		}
		return null;
	}
}
