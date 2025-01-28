package many2many_bidir_basic_jpa;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;

public class Programa {
	
	private static final Logger log = LoggerFactory.getLogger(Programa.class);
	
	private static EntityManagerFactory factoria = Persistence.createEntityManagerFactory("PersistenciaPeliculas");
	
	public static void main(String[] args) {
		EntityManager manager = null;
		EntityTransaction transaction = null;
		
		Actor actor1, actor2;
		Pelicula peli;
		
		try {
			manager = factoria.createEntityManager();
			
			actor1 = new Actor("Santiago Segura", new Direccion("Calle sinfin", 12));
			actor2 = new Actor("Toni Acosta", new Direccion("Avenida del final", 7));
			
			peli = new Pelicula("Padre no hay mas que uno", LocalDate.of(2019, 8, 1));
			
			peli.addActor(actor1);
			peli.addActor(actor2);
			
			transaction = manager.getTransaction();
			
			transaction.begin();
			
			manager.persist(peli);
			
			transaction.commit();
			
		} catch (IllegalStateException e) {
			log.error("Error en la gestion de persistencia: {}",e.getMessage());
		} catch (PersistenceException e) {
			log.error("Error en la persistencia de datos: {}",e.getMessage());
			if(transaction.isActive())
				transaction.rollback();
		}
	}
}
