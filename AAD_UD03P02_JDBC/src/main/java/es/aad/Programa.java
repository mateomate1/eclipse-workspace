package es.aad;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Programa {
	public static void main(String[] args) {
		DbManager db = new DbManager();
		List<Persona> personas = new ArrayList<Persona>();
		Persona pedro = new Persona();
		pedro.setNombre("Pedro");
		pedro.setEmail("pedro@gmail.com");
		pedro.setFechaNac(LocalDate.parse("2001-01-20"));
		Persona andrea = new Persona();
		andrea.setNombre("Andrea");
		andrea.setEmail("andrea@gmail.com");
		andrea.setFechaNac(LocalDate.parse("2007-02-22"));
//		for (int i = 0; i < 2; i++) {
//			Persona p = new Persona();
//			p.setEmail("persona"+i+"@gmail.com");
//			p.setNombre("Persona"+i);
//			p.setFechaNac(LocalDate.parse("2001-01-20"));
//		}
//		db.addTrans(personas);
		db.aniadirPersona(pedro.getNombre(), pedro.getEmail(), pedro.getFechaNac());
		db.getRegaloPorEmail("carlos.garcia@example.com");
	}
}
