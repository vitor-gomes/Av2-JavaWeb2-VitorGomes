package br.org.prefeitura.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class JPAUtil {

	public EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("av2-javaweb2").createEntityManager();
	}

}
