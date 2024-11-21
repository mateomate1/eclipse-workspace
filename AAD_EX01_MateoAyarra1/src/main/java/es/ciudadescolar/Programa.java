package es.ciudadescolar;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Programa {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Programa.class);
	
	private static File ficheroJson = new File("hackers.json");
	
	public static void main(String[] args) {
		FileManager.parsearJson(ficheroJson);
	}
}
