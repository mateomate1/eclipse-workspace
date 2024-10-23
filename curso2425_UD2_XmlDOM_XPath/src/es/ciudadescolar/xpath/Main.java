package es.ciudadescolar.xpath;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		LOGGER.trace("Comienzo de la app");
		if(args.length!=2) {
//			System.out.println("Error de parametros, esperados [2], pasados ["+args.length+"]");
			LOGGER.error("Error de parametros: [xlm] [xsd]");
			System.exit(1);
		}
		
		File xml = new File(args[0]);
		LOGGER.trace("El fichero XML pasado como parametro es ["+xml.getAbsolutePath()+"]");
		File xsd = new File(args[1]);
		LOGGER.trace("El fichero XSD pasado como parametro es ["+xsd.getAbsolutePath()+"]");
		
		if(XMLManager.parsearXmlDomXsd(xml, xsd)) {
			LOGGER.info("Se ha parseado correctamente el xml");
			LOGGER.trace("Se va a ejecutar por XPath el alumno con expediente [333333]");
			LOGGER.info(XMLManager.getAlumnoExpediente("33333").toString());
			LOGGER.trace("Se ha consultado por XPath el alumno con expediente [333333]");
			LOGGER.info(XMLManager.getAlumnoExpediente("11111").toString());
			LOGGER.info(XMLManager.getAlumnoExpediente("22222").toString());
			
			Alumno al = XMLManager.getAlumnoExpediente("555555");
			
			if(al != null) 
				LOGGER.info(al.toString());
			else
				LOGGER.warn("No se ha encontrao alumno con expediente [555555]");
			
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
