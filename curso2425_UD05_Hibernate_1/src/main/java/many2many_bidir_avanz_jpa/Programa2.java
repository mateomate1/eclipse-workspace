package many2many_bidir_avanz_jpa;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;

/**
 * Esta clase es la solucion al problema del orden de persistencia en hibernate
 */
public class Programa2 {
	private static final Logger log = LoggerFactory.getLogger(Programa2.class);
	
	private static EntityManagerFactory factoria = Persistence.createEntityManagerFactory("PersistenciaPeliculas");
	
	public static void main(String[] args) {
		
		EntityManager manager = null;
		EntityTransaction transaction = null;
		Actor actor1 = null, actor2 = null;
		Pelicula peli = null;
		Protagonista prot1 = null, prot2 = null;
		try {
			manager = factoria.createEntityManager();
			transaction = manager.getTransaction();
			
			transaction.begin();
			
			actor1 = new Actor("Santiago Segura", new Direccion("Calle sinfin", 12));
			manager.persist(actor1);
			
			actor2 = new Actor("Toni Acosta", new Direccion("Avenida del final", 7));
			manager.persist(actor2);
			
			peli = new Pelicula("Padre no hay mas que uno", LocalDate.of(2019, 8, 1));
			manager.persist(peli);
			
			ClaveProtagonista claveProt1 = new ClaveProtagonista(peli.getCodigo_pelicula(), actor1.getCodigo_actor());
			
			prot1 = new Protagonista();
			prot1.setClave(claveProt1);
			prot1.setActor(actor1);
			prot1.setPelicula(peli);
			prot1.setEsProta(Boolean.TRUE);
			manager.persist(prot1);
			
			ClaveProtagonista claveProt2 = new ClaveProtagonista(peli.getCodigo_pelicula(), actor2.getCodigo_actor());
			
			prot2 = new Protagonista();
			prot1.setClave(claveProt2);
			prot2.setActor(actor2);
			prot2.setPelicula(peli);
			prot2.setEsProta(Boolean.TRUE);
			manager.persist(prot2);
			
			actor1.addColaboracion(prot1);
			peli.addColaboraciones(prot1);
			
			actor2.addColaboracion(prot2);
			peli.addColaboraciones(prot2);
			
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
