package es.ciudadescolar;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase Programa que actua como Main del proyecto, demostrando las utilidades del proyecto de manera simple
 * 
 * @author Mateo Ayarra
 * @version 20/11/2024
 */
public class Programa {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Programa.class);
	
	private static double nota = 9;
	
	public static void main(String[] args) {
		BBDDOOManager db = new BBDDOOManager(false);
		File ficheroTXT = new File("hackers_superior_"+nota+"_MateoAyarra1.txt");
		List<Hacker> hackers = db.getByNota(nota);
		for (Hacker hacker : hackers) {
			LOGGER.info("Hacker con nota mayor a ["+nota+"] encontrado"+hacker.toString());
		}
		TXTManager.volcadoTXT(ficheroTXT, hackers);
		db.borrarHackers(hackers);
//		db.rollback();
		db.commit();
		LOGGER.debug("Hackers con nota mayor a ["+nota+"] borrados");
		List<Hacker> restantes = db.getAll();
		LOGGER.info("Quedan ["+restantes.size()+"] hackers");
		for (Hacker hacker : restantes) {
			LOGGER.trace(hacker.toString());
		}
		db.close();
		
	}
}
