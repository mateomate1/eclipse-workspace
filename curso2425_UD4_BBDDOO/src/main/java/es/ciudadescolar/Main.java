package es.ciudadescolar;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * La clase main permite guardar y recuperar instancias de la clase alumnbo en
 * una BBDD orientada a objetos (db4a)
 * 
 * @author Mateo Ayarra
 * @version 2024/11/06
 */
public class Main {

	private static final Logger log = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {

		BBDDOO bdo, bdo2, bdo3;
		Alumno alumno, alumno2, alumno3;

		if (args.length != 1) {
			log.error("Error de parametros. Se esperaba [nombre fichero BBDDOO]");
			System.exit(1);
		}

		File ficheroDB = new File(args[0]);

		bdo = new BBDDOO(ficheroDB, true);

		alumno = new Alumno("9999", "Paco", 20, "DAM2", "IES Ciudad Escolar");
		alumno2 = new Alumno("8888", "Manolo", 22, "DAM2", "IES Ciudad Escolar");
		alumno3 = new Alumno("7777", "Fermin", 30, "DAM2", "IES Ciudad Escolar");

		try {
			bdo.guardarAlumno(alumno);
			bdo.guardarAlumno(alumno2);
			if (true) throw new Exception();
			bdo.guardarAlumno(alumno3);
			// commit

			Alumno alumnoBuscado = bdo.getAlumnoByExpediente("9999");
			if (alumnoBuscado != null) {
				log.info("Alumno encontrado [" + alumnoBuscado + "]");
			} else {
				log.info("Alumno con expediente [9999] no encontrado]");
			}
		} catch (Exception e) {
			// rollback
			bdo.rollback();
		} finally {
			bdo.cerrar();
		}

		log.debug("Abriendo BDD abierta sin borrar...");
		bdo2 = new BBDDOO(ficheroDB, false);

		bdo2.visualizarTodosAlumnos();

		bdo2.cerrar();

		bdo3 = new BBDDOO(ficheroDB, false);

		String expedienteCambioEdad = "9999";

		log.debug("Se procede a cambiar la edad del alumno con expediente [" + expedienteCambioEdad + "] edad ["
				+ expedienteCambioEdad + "]");
		bdo3.cambiarEdadAlumno(new Alumno(expedienteCambioEdad, null, 0, null, null), 24);

		bdo3.visualizarTodosAlumnos();

		bdo3.deleteAllAlumnos();

		bdo3.visualizarTodosAlumnos();

		bdo3.cerrar();
	}

}
