package es.ciudadescolar;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase que permite manejar fichewros Json y ficheros XML
 * 
 * @author Mateo Ayarra
 * @version 20/11/2024
 */
public class FileManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileManager.class);
	
	public static List<Hacker> parsearJson(File ficheroJson){
		if (ficheroJson.exists())
			try {
				FileReader reader = new FileReader(ficheroJson);
				BufferedReader in = new BufferedReader(reader);
				StringBuilder sb = new StringBuilder();
				JSONObject jsonOnject = new JSONObject(null, null);
				while(in.readLine()!=null) {
					sb.append(in.toString());
				}
				System.out.println(sb);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				LOGGER.error("Error: "+e.getMessage());
			} catch (EOFException e) {
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else 
			LOGGER.warn("El fichero Json no existe o no esta en la ubicacion indicada");
		return null;
	}
	
	public static void volcadoXML(File ficheroXML) {
		
	}
	
}
