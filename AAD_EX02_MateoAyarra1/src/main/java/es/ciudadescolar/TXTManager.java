package es.ciudadescolar;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase que maneja un fichero de texto
 * 
 * @author Mateo Ayarra
 * @version 20/11/2024
 */
public class TXTManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(TXTManager.class);
	
	/**
	 * Metodo de la clkase que recibe un parametro tipo File, que sera donde se vuelque el segundo parametro, una lista de hackers
	 * 
	 * @param fichero
	 * @param hackers
	 */
	public static void volcadoTXT(File fichero, List<Hacker> hackers) {
		if (hackers.size() != 0) {
			LOGGER.debug("Iniciando volcado a fichero");
			FileWriter writer;
			PrintWriter pt = null;
			try {
				writer = new FileWriter(fichero);
				pt = new PrintWriter(writer);
				pt.println("Id | Nombre | Apellido | Nota");
				for (Hacker hacker : hackers) {
					pt.append(hacker.toString()+"\n");
				}
				LOGGER.debug("Volcado a fichero finalizado con exito.");
			} catch (IOException e) {
				LOGGER.error("Error: "+e.getMessage());
			}finally {
				pt.flush();
			}

		} else
			LOGGER.warn("La lista de hackers esta vacia asi que no se creara el fichero");
	}

}
