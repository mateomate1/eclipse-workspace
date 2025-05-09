package one2one_bidir_jpa;

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
		EntityTransaction trans = null;
		Actor actor = null;
		Direccion direccion = null;
		try {
			manager = factoria.createEntityManager();

			int idDireccion = 4;
			int idDireccionActor = 6;

			direccion = manager.find(Direccion.class, idDireccion);

			if (direccion != null) {
				log.info("Se ha localizado la direccion: " + idDireccion + "-----" + direccion);
			}

			direccion = manager.find(Direccion.class, idDireccionActor);

			if (direccion != null) {
				log.info("Se ha localizado la direccion: " + idDireccionActor + "-----" + direccion);
			}

			trans = manager.getTransaction();

			trans.begin();

			trans.commit();

		} catch (IllegalStateException e) {
			// TODO: handle exception
			log.error("Error durante la creacion del contexto de persistencia: " + e.getMessage());
		} catch (PersistenceException e) {
			// TODO: handle exception
			log.error("Error durante la persistencia de entidades: " + e.getMessage());
			if (trans.isActive()) {
				log.warn("Se procede a realizar el rollback de la transaccion");
				trans.rollback();
			}
		} finally {
			log.debug("Cerrando contexto de persistencia");
			if (manager.isOpen())
				manager.close();
			log.debug("Cerrando factoria de persistencia");
			if (factoria.isOpen())
				factoria.close();
		}
	}
}
