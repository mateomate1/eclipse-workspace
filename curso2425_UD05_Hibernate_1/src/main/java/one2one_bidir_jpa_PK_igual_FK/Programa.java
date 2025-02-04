package one2one_bidir_jpa_PK_igual_FK;

import java.time.LocalDate;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;

public class Programa {
	
	private static EntityManagerFactory factoria = Persistence.createEntityManagerFactory("PersistenciaPeliculas");
	private static final Logger log = LoggerFactory.getLogger(Programa.class);
	
	public static void main(String[] args) {
		Director director = new Director();
		director.setCodigo_director(102);
		director.setNombre_director("Luis Buñuel");
		
		NacionalidadDirector nacionalidad = new NacionalidadDirector("española");
		
		director.setNacionalidad(nacionalidad);
		
		EntityManager manager = null;
		EntityTransaction transaction = null;
		try {
			manager = factoria.createEntityManager();
			transaction = manager.getTransaction();
			
			transaction.begin();
			
			manager.persist(director);
			
			transaction.commit();
			
			manager.close();
			
			manager = factoria.createEntityManager();
			
			//Con esto verificamos el comportamiento del fetch: recuperamos las peliculas de primeras o solo al acceder a ellas
			Director dirBuscado = manager.find(Director.class, 102);
			
			if(dirBuscado != null) {
				log.info("Director encontrado: {}", dirBuscado.getNombre_director());
//				for (Pelicula p : dirBuscado.getPelis_director()) {
//					log.info("Pelicula: {}",p.getTitulo_pelicula());
//				}
				
				transaction = manager.getTransaction();
				transaction.begin();
				manager.remove(dirBuscado);
				transaction.commit();
				
			}
			
			manager.close();
			
		} catch (IllegalStateException e) {
			log.error("Error durante la gestion del contexto de persistencia");
		} catch (PersistenceException e) {
			log.error("Error durante la persistencia de objetos");
			if(transaction.isActive())
				transaction.rollback();
		} finally {
			if(manager != null && manager.isOpen())
				manager.close();
			if(factoria.isOpen())
				factoria.close();
		}
	}
}
