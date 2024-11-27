package es.ciudadescolar;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Programa {
	
	private static final Logger log = LoggerFactory.getLogger(Programa.class);
	
	
	public static void main(String[] args) {
		DBManager db = new DBManager();
		
		Collection<Alumno> listaAlumnos = db.getAlumnos();
		
		if (listaAlumnos == null) {
			log.warn("No se ha recuperado ningun alumno");
		} else {
			for (Alumno a : listaAlumnos) {
				log.info(a.toString());
			}
		}
		
		String expBuscado = "2";
		int nota = db.getNota(expBuscado);
		if (nota<0)
			log.warn("No hay ninguna nota");
		else
			log.info("La nota del alumno con expediente ["+expBuscado+"]"+db);
		db.cerrarDB();
	}
}
