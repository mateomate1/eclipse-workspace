package es.ciudadescolar.xpath;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		if(args.length!=2) {
			System.out.println("Error de parametros, esperados [2], pasados ["+args.length+"]");
			System.exit(1);
		}
		
		File xml = new File(args[0]);
		File xsd = new File(args[1]);
		
		if(XMLManager.parsearXmlDomXsd(xml, xsd)) {
			System.out.println("Se ha parseado correctamente el xml");
			System.out.println(XMLManager.getAlumnoExpediente("33333"));
			System.out.println(XMLManager.getAlumnoExpediente("11111"));
			System.out.println(XMLManager.getAlumnoExpediente("22222"));
			
			Alumno al = XMLManager.getAlumnoExpediente("22222");
			
			if(al != null) 
				System.out.println(al);
			String nombreAl = XMLManager.getAlumnoNombre("11111");
			if(nombreAl != null)
				System.out.println("El nombre del alumno [11111] es "+nombreAl);
			for (Alumno alumno : XMLManager.getAlumnosCurso("24-25")) {
				System.out.println(alumno);
			}
		}
		else {
			System.out.println("Error durante el parseo del ");
		}
		
	}

}
