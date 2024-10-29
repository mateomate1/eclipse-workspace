package es.ciudadescolar;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	
	private static final Logger LOG = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		LOG.info("JSON!");
		
		JSONObject alumnoJSON;
		
		alumnoJSON = new JSONObject();
		
		alumnoJSON.put("nombre", "Manolo");
		alumnoJSON.put("edad",20);
		
		double[] notas = {8.5, 7, 5.75};
		
		alumnoJSON.put("notas", notas);
		
		LOG.debug(alumnoJSON.toString());
//		System.out.println(alumnoJSON.toString(2)); //prety print
		
		System.out.println("El nombre del alumno es "+alumnoJSON.getString("nombre"));
		
		JSONObject alumnoJSON2;
		alumnoJSON2 = new JSONObject();
		alumnoJSON2.put("nombre", "Fermin");
		alumnoJSON2.put("edad", 19);
		double[] notas2 = {2,4.5,5};
		alumnoJSON2.put("notas", notas2);
		
		JSONArray arrayDeAlumnos = new JSONArray();
		arrayDeAlumnos.put(alumnoJSON);
		arrayDeAlumnos.put(alumnoJSON2);
		
		for (int i = 0; i < notas2.length; i++) {
			LOG.debug(arrayDeAlumnos.getJSONObject(i).toString());
		}
		
//		for (Object object : arrayDeAlumnos) {
//			JSONObject obj = (JSONObject) object;
//		}
		
	}

}
