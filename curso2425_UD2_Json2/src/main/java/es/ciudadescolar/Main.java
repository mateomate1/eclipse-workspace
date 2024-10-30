package es.ciudadescolar;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	
	private static Logger LOG = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		
		List<Alumno> alumnos;
		Alumno al;
		JSONArray alumnosJson;
		JSONObject alumnosJsonObject;
		
		if (args.length!=1) {
			LOG.error("Se esperaba un parametro [JSON]");
		}
		
		File ficheroJsonEntrada = new File(args[0]);
		LOG.debug("El fichero pasado como parametro es ["+ficheroJsonEntrada+"]");
		
		if(JsonManager.parsearJsonFile(ficheroJsonEntrada)) {
			JSONObject objetoRaiz = JsonManager.getObjetoJson();
			LOG.trace("El numero de pares dentro del Json es [" + objetoRaiz.length()+"]");
			alumnos = new ArrayList<Alumno>();
			alumnosJson = objetoRaiz.getJSONArray("alumnnos");
			for (int i = 0; i < alumnosJson.length(); i++) {
				alumnosJsonObject = alumnosJson.getJSONObject(i);
				al = new Alumno();
				al.setExpediente(alumnosJsonObject.getInt("expediente"));
				al.setNombre(alumnosJsonObject.getString("nombre"));
				al.setEdad(alumnosJsonObject.getInt("edad"));
				al.setInstituto(objetoRaiz.getString("centro"));
				al.setCurso(objetoRaiz.getString("curso"));
				
				
			}
			if (JsonManager.visualizarJsonFile()) {
				LOG.warn("No es posible visualizar el fichero JSON");
			}
		}
	}

}
