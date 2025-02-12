package one2Many;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Programa {
	
	private static EntityManagerFactory factoria = Persistence.createEntityManagerFactory("PersistenciaExamen");
	private static final Logger log = LoggerFactory.getLogger(Programa.class);
	
	
	public static void main(String[] args) {
		
		Customer cliente = new Customer();
		cliente.setFistName("Joaquín");
		cliente.setLastName("Román");
		cliente.setEmail("joaqrom@tocomocho.net");
		
		Pago p1 = new Pago(cliente);
		p1.setCantidad(234.45);
		p1.setFechaPago(LocalDate.of(2024, 1, 7));
		Pago p2 = new Pago(cliente);
		p2.setCantidad(100.25);
		p2.setFechaPago(LocalDate.of(2024, 1, 12));
		Pago p3 = new Pago(cliente);
		p3.setCantidad(655.99);
		p3.setFechaPago(LocalDate.of(2024, 1, 17));
		
		cliente.addPago(p1);
		cliente.addPago(p2);
		cliente.addPago(p3);
		
		EntityManager manager = null;
		EntityTransaction transaction = null;
		
		try {
			manager = factoria.createEntityManager();
			transaction = manager.getTransaction();
			
			transaction.begin();
			
			manager.persist(cliente);
			
			transaction.commit();
			manager.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
