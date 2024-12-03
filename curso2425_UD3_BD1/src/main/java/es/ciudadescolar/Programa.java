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
			log.warn("No se ha encontrado ningun alumno con expediete ["+expBuscado+"]");
		else
			log.info("La nota del alumno con expediente ["+expBuscado+"]"+db);
		
		System.out.println("---------------");
		db.getAlumnos();
		System.out.println("---------------");
		// db.cambiarNota("1", 10);
		// db.cambiarNombre("1", "Francisco");
		// Alumno alumno = new Alumno("6","Mael", 0);
		// db.insertarAlumno(alumno);
		// db.borrarAlumno(alumno);

		db.muestraInfoAlumno("1");
		log.info("La nota de raices del alumno con expediente 2:" + db.getNotaAlumno("2"));
		
		log.info("Alta de 3 alumnos harcodeados en una transaccion");
		
		if(db.altaAlumnosTrans()) {
			log.debug("Se han dado de alta correctamente los alumnos en la transaccion");
		} else {
			log.error("No se han dado de alta correctamentre los alumnos en la transaccion");
		}
		
		db.cerrarDB();
	}
}

