package hql_jpa;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class Programa {
	
	private static final Logger log = LoggerFactory.getLogger(Programa.class);
	
	private static EntityManagerFactory factoria = Persistence.createEntityManagerFactory("PersistenciaPeliculas");
	
	public static void main(String[] args) {
		EntityManager manager = null;
		try {
			manager = factoria.createEntityManager();
			
			Query consultaJacobo = manager.createQuery("from Actor a where a.nombre = :name");
//			Query consultaJacobo = manager.createQuery("from hql_jpa.Actor a where a.nombre = :name",Actor.class);
			consultaJacobo.setParameter("name", "Jacobo");
			
			List<?> actores = consultaJacobo.getResultList();
			
			for (Object actor : actores) {
				log.info(actor.toString());
			}
			
			Query consultaActorDireccion = manager.createQuery("SELECT a.nombre, d.calle, d.num FROM hql_jpa.Actor a"
					+ " INNER JOIN a.direc d WHERE a.nombre = :name");
			consultaActorDireccion.setParameter("name", "Jacobo");
			
			List<?> actoresDirecciones = consultaActorDireccion.getResultList();
			
			for(int i=0; i<actoresDirecciones.size(); i++) {
				Object[] registro = (Object[]) actoresDirecciones.get(i);
				String nom = (String) registro[0];
				String call = (String) registro[1];
				String num = (String) registro[2];
				log.info("Registro recuperado de HQL "+nom+"-"+call+"-"+num);
			}
			
		} catch (IllegalStateException e) {
			// TODO: handle exception
			log.error("Error durante la creacion del contexto de persistencia: "+e.getMessage());
		} finally {
			log.debug("Cerrando contexto de persistencia");
			if(manager.isOpen())
				manager.close();
			log.debug("Cerrando factoria de persistencia");
			if(factoria.isOpen())
				factoria.close();
		}
	}
}
