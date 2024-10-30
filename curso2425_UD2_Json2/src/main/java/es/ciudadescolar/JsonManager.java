package es.ciudadescolar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.AccessDeniedException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonManager {

	private static Logger LOG = LoggerFactory.getLogger(JsonManager.class);

	private static JSONObject objetoJson;

	public static boolean parsearJsonFile(File ficheroJson) {

	    FileReader fr = null;
	    BufferedReader br = null;
	    String linea = null;
	    StringBuilder sb = new StringBuilder(); // Inicializamos el StringBuilder

	    try {
	        fr = new FileReader(ficheroJson);
	        br = new BufferedReader(fr);
	        LOG.debug("Comenzando a leer el fichero");
	        while ((linea = br.readLine()) != null) {
	            sb.append(linea);
	        }
	        LOG.debug("Finalizada lectura del fichero");
	    } catch (AccessDeniedException e) {
	        LOG.error("No se disponen de permisos para leer el fichero");
	        return false;
	    } catch (FileNotFoundException e) {
	        LOG.error("El fichero no existe");
	        return false;
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (br != null) {
	            try {
	                br.close();
	            } catch (IOException e) {
	                LOG.warn("Error cerrando el fichero");
	            }
	        }
	    }
	    
	    if (sb.length() > 0) {
	        objetoJson = new JSONObject(sb.toString());
	        return true;
	    } else {
	        return false;
	    }
	}


	public static JSONObject getObjetoJson() {
		return objetoJson;
	}

	public static void setObjetoJson(JSONObject objetoJson) {
		JsonManager.objetoJson = objetoJson;
	}
	public static boolean visualizarJsonFile() {
		if(objetoJson!=null) {
			LOG.debug(objetoJson.toString(2));
			return true;
		}else {
			LOG.warn("ImposibleVisualizar fichero Json sin parsear");
			return false;
		}
	}
	
	public static boolean visualizarJsonFile(JSONObject objetoJson) {
		if(objetoJson!=null) {
			LOG.debug(objetoJson.toString(2));
			return true;
		}else {
			LOG.warn("ImposibleVisualizar fichero Json sin parsear");
			return false;
		}
	}
	
	public static boolean escribirJsonFile(File ficheroJsonSalida, List<Alumno> listaAlumnos) {
		PrintWriter pw = null;
		JSONArray arrayAlumnos = null;
		JSONObject objetoAlumno = null;
		
		if(ficheroJsonSalida.exists()) {
			LOG.warn("El fichero de salida ya existe");
			return false;
		}
//		if(!ficheroJsonSalida.canWrite()) {
//			LOG.error("No hay permiso para escribir el fichero");
//			return false;
//		}
		try {
			pw = new PrintWriter(ficheroJsonSalida);
			arrayAlumnos = new JSONArray();
			for (Alumno al : listaAlumnos) {
				objetoAlumno = new JSONObject();
				objetoAlumno.put("expedient", al.getExpediente());
				objetoAlumno.put("name", al.getNombre());
				objetoAlumno.put("age", al.getEdad());
				objetoAlumno.put("high school", al.getInstituto());
				objetoAlumno.put("school year", al.getCurso());
				
				arrayAlumnos.put(objetoAlumno);
			}
			
			if (arrayAlumnos.length()>0) {
				pw.print(arrayAlumnos);
			}
			
		} catch (FileNotFoundException e) {
			LOG.warn(null);
		} finally {
			if (pw!=null) {
				pw.flush();
				pw.close();
			}
		}
		
		if (arrayAlumnos.isEmpty()) {
			LOG.debug(null);
		}
		
		return true;
	}
}
