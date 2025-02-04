package hql_jpa;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class Programa {
	
	private static final Logger log = LoggerFactory.getLogger(Programa.class);
	
	private static EntityManagerFactory factoria = Persistence.createEntityManagerFactory("PersistenciaPeliculas");
	
	public static void main(String[] args) {
		EntityManager manager = null;
		EntityTransaction trans = null;
		
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
				Integer num = (Integer) registro[2];
				log.info("Registro recuperado de HQL "+nom+"-"+call+"-"+num);
			}
			
			// Para realizar modificaciones y que bajen a la BDD necesito operar bajo una transaccion.
			
			trans = manager.getTransaction();
			
			trans.begin();
			
			Query altaDireccion = manager.createQuery("INSERT INTO hql_jpa.Direccion (calle, num) VALUES (:calledir, :numdir)");
			altaDireccion.setParameter("calledir", "Calle FCB");
			altaDireccion.setParameter("numdir", 5);
			
			if (altaDireccion.executeUpdate() == 1) {
				log.info("Se ha dado de alta por HQL correctamente la direccion");
			}
			else {
				log.warn("No se han dado de alta por HQL la direccion");
			}
			
			trans.commit();
			
			trans = manager.getTransaction();
			
			trans.begin();
			
			Query cambiaDireccion = manager.createQuery("UPDATE hql_jpa.Direccion SET calle = :nuevaCalle WHERE calle = :viejaCalle AND num =:numCalle");
			
			cambiaDireccion.setParameter("nuevaCalle", "Calle Madrid");
			cambiaDireccion.setParameter("viejaCalle", "Calle FCB");
			cambiaDireccion.setParameter("numCalle", 5);
			
			if (cambiaDireccion.executeUpdate() > 0) {
				log.info("Se ha actualizado por HQL correctamente la direccion");
			}
			else {
				log.warn("No se ha actualizado por HQL la direccion");
			}
			
			trans.commit();
			
			/*
			 * 		¡¡¡¡ATENCION!!!!
			 * Para borrar en cascada en HQL, necesito que exista en la BD una restriccion "ON DELETE CASCADE"
			 * No hara caso de la anotacion @OneToOne (cascade = CascadeType.REMOVE) ni @OneToOne (cascade = CascadeType.ALL)
			 */
			trans = manager.getTransaction();
			
			trans.begin();
			
			Query borrarActor = manager.createQuery("DELETE hql_jpa.Actor WHERE nombre = :nomActor");
			borrarActor.setParameter("nomActor", "Santiago Segura");
			
			if(borrarActor.executeUpdate() > 0)
				log.info("Se ha borrado el actor Santiago Segura"); // Al tener FK con ON DELETE CASCADE, tambien deberia borrar su direccion
			else
				log.warn("No se ha borrado el actor Santiago Segura");
			
			trans.commit();
			
			trans = manager.getTransaction();
			
			trans.begin();
			
			Query borrarDireccion = manager.createQuery("DELETE hql_jpa.Direccion WHERE codigo_direccion = :cod");
			borrarActor.setParameter("cod", 21);
			
			if(borrarActor.executeUpdate() > 0)
				log.info("Se ha borrado el actor Santiago Segura"); // Al tener FK con ON DELETE CASCADE, tambien deberia borrar su direccion
			else
				log.warn("No se ha borrado el actor Santiago Segura");
			
			trans.commit();
			
		} catch (IllegalStateException e) {
			// TODO: handle exception
			log.error("Error durante la creacion del contexto de persistencia: "+e.getMessage());
		} catch (PersistenceException e) {
			log.error("Error durante la persistencia de registros: "+e.getMessage());
			if (trans != null && trans.isActive())
				trans.rollback();
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
