package es.ciudadescolar.xml;

import java.io.File;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("Error de parametros, falta la ruta del fichero xml");
			System.exit(1);
		}
		
		File ficheroXML = new File(args[0]);
		
		if(!ficheroXML.exists()) {
			System.out.println("Error de acceso al fichero xml");
			System.exit(2);
		}
		
		List<Alumno> alumnos = XMLManager.procesarXML(ficheroXML);
		
		for (Alumno alumno : alumnos) {
			System.out.println(alumno);
		}
		
		XMLManager.generarNuevoXML(alumnos);
		
//		
//		if(alumnos.isEmpty()) {
//			System.out.println("No se ha recuperado ningun alumno");
//		}
//		
//		for (Alumno alumno : alumnos) {
//			System.out.println(alumno);
//		}
//		
//		XMLManager.generarNuevoXML(alumnos);
//		for (String exp : XMLManager.recuperarExpedientes(ficheroXML)) {
//			System.out.println(exp);
//		}
		
	}

}
