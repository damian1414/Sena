package com.prueba.ejb;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.prueba.model.TipoUsuario;

@Stateless
public class TipoUsuarioFacade  extends AbstractFacade<TipoUsuario> implements TipoUsuarioFacadeLocal{

	@PersistenceContext(unitName = "senaDS")
	private EntityManager em;
	
	public TipoUsuarioFacade() {
		super(TipoUsuario.class);
	}
	

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}

}
