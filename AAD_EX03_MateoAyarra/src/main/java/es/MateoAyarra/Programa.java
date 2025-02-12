package es.MateoAyarra;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Programa {
	
	private static final Logger log = LoggerFactory.getLogger(Programa.class);
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenciaFCTs");
	
	public static void main(String[] args) {
		EntityManager em = null;
		EntityTransaction et = null;
		
		try {
			em = emf.createEntityManager();
			et = em.getTransaction();
			
			et.begin();
			
			Alumno alumno1 = new Alumno(101, "Dario Pazos", LocalDate.of(2005, 6, 15), null);
			Alumno alumno2 = new Alumno(102, "Fermin Sanz", LocalDate.of(2002, 9, 21), null);
			
			Contacto c1 = new Contacto("dario.pazos@instituto.es", "912443213", alumno1);
			Contacto c2 = new Contacto("fermin.sanz@instituto.es", "6660006666", alumno2);

			em.persist(alumno1);
			em.persist(alumno2);
			em.persist(c1);
			em.persist(c2);
			
			et.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
