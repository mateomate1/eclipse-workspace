package hibernate_api_nativa;

import java.time.LocalDate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Programa {

	private static final Logger log = LoggerFactory.getLogger(Programa.class);

	public static void main(String[] args) {
		SessionFactory factoria = null; // Analogo a EntityManagerFactory en JPA
		Session sesion = null; // Analogo a EntityManager en JPA
		Transaction transaction = null; // Analogo a EntityTransaction en JPA

		Director director = new Director();
		director.setCodigo_director(103); // No es autoincremental en la BD
		director.setNombre_director("Chris Wedge");

		Pelicula pelicula = new Pelicula("Ice Age", LocalDate.of(2000, 8, 30));
		pelicula.setDir_pelicula(director);

		try {
			factoria = new Configuration().configure().buildSessionFactory();
			sesion = factoria.openSession();

			transaction = sesion.beginTransaction();

			sesion.persist(pelicula);

			transaction.commit();

		} catch (HibernateException e) {
			log.error("Error durante la gestion de persistencia usando API nativa: " + e.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			if (sesion != null && sesion.isOpen())
				sesion.close();
			if (factoria != null && factoria.isClosed()) {
				factoria.close();
			}
		}
	}
}
