package AAD.UD02E07.MateoAyarra;

import java.io.File;
import java.io.PrintWriter;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GestorJSON {
	
	private static Logger log = LoggerFactory.getLogger(GestorJSON.class);
	
	private String equipo = "Ath Madrid";
	
	public static boolean parsearJsonFile(File ficheroJson) {
		
		
		
		return true;
	}
	
	public static boolean generarJSON(String equipo) {
		File ficheroJSON = new File("AAD_UD02P07E01_JSON_"+equipo+"_MateoAyarra.json");
		PrintWriter pw = null;
		JSONArray arrayReporte = null;
		JSONObject objetoReporte = null;
		
		return true;
	}
	
}
