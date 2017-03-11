package com.prueba.ejb;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.prueba.model.Usuario;

@Stateless
public class UsuarioFacade  extends AbstractFacade<Usuario> implements UsuarioFacadeLocal{

	@PersistenceContext(unitName = "senaDS")
	private EntityManager em;
	
	public UsuarioFacade() {
		super(Usuario.class);
	}
	

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}
	
	@Override
	public Usuario iniciarSession(Usuario us){
		Usuario usuario = null;
		String consulta;
		try {
			consulta= " FROM Usuario u  WHERE u.usuario = ?1 AND u.clave = ?2 ";
			Query query = em.createQuery(consulta);
			query.setParameter(1, us.getUsuario());
			query.setParameter(2, us.getClave());
			List<Usuario> listaUsuario=query.getResultList();
			if(!listaUsuario.isEmpty()){
				usuario=listaUsuario.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		
		return usuario;
	}


	@Override
	public void editarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		String actualizar;
		try {
			actualizar= " Update Usuario u  set u.usuario = ?1, tipoUsuario.idTipoUsuario =?2   WHERE u.usuario.idUsuario = ?3  ";
			Query query = em.createQuery(actualizar);
			query.setParameter(1, usuario.getUsuario());
			query.setParameter(2, usuario.getTipoUsuario().getIdTipoUsuario());
			query.setParameter(3, usuario.getIdUsuario());
			query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}

}
