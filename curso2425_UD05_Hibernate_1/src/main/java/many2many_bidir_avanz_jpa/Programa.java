package many2many_bidir_avanz_jpa;

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
		Actor actor1 = null, actor2 = null;
		Pelicula peli = null;
		Protagonista prot1 = null, prot2 = null;
		try {
			manager = factoria.createEntityManager();
			
			actor1 = new Actor("Santiago Segura", new Direccion("Calle sinfin", 12));
			actor2 = new Actor("Toni Acosta", new Direccion("Avenida del final", 7));
			
			peli = new Pelicula("Padre no hay mas que uno", LocalDate.of(2019, 8, 1));
			
			ClaveProtagonista claveProt1 = new ClaveProtagonista(peli.getCodigo_pelicula(), actor1.getCodigo_actor());
			
			prot1 = new Protagonista();
			prot1.setClave(claveProt1);
			prot1.setActor(actor1);
			prot1.setPelicula(peli);
			prot1.setEsProta(Boolean.TRUE);
			
			ClaveProtagonista claveProt2 = new ClaveProtagonista(peli.getCodigo_pelicula(), actor2.getCodigo_actor());
			
			prot2 = new Protagonista();
			prot1.setClave(claveProt2);
			prot2.setActor(actor2);
			prot2.setPelicula(peli);
			prot2.setEsProta(Boolean.TRUE);
			
			actor1.addColaboracion(prot1);
			peli.addColaboraciones(prot1);
			
			actor2.addColaboracion(prot2);
			peli.addColaboraciones(prot2);
			
			transaction = manager.getTransaction();
			
			transaction.begin();
			
			manager.persist(actor1);
			manager.persist(actor2);
			manager.persist(peli);
			manager.persist(prot1);
			manager.persist(prot2);
			
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
