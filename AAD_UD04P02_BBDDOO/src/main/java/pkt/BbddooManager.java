package pkt;

import java.io.File;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

/**
 * La clase BbddooManager se encarga de gestionar una base de datos orientada a objetos (en este caso distritos) 
 * @author ISC
 * @version 2024.11.15
 */

public class BbddooManager {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BbddooManager.class);
	
	private ObjectContainer db = null;
	
	private File ficheroDb;
	
	/**
	 * Crea la base de datos, si no existe y la sobreescribe si el parametro es true
	 * @param sobrescribir
	 */
	
	public BbddooManager(boolean sobrescribir) {
		
		ficheroDb = new File("inigo_deSilva.db4o");
		
		
		if(sobrescribir) {
			if(ficheroDb.exists()) {
				LOGGER.warn("BD existente. Se procede a borrar...");
				ficheroDb.delete();
			}
		}
		
		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),ficheroDb.getName()); 
		LOGGER.info("BD abierta... ["+ficheroDb.getAbsolutePath()+"]");
	}
	
	/**
	 * Cierra la base de datos si estaba abierta
	 * @return boolean
	 */
	
	public boolean cerrar() {
		
		if(db != null) {
			db.close();
			LOGGER.info("BD cerrada correctamente... ["+ficheroDb.getAbsolutePath()+"]");
			return true;
		}
		LOGGER.warn("No de puede cerrar la BD por no estar inicializada");
		return false;	
	}
	
	/**
	 * Introduce objetos distrito en la base de datos
	 * @param distritos
	 */
	
	public void introducirDistritos(TreeMap<String, Distrito> distritos) {
		try {
			for(Entry<String, Distrito> entry : distritos.entrySet()) {
				db.store(entry.getValue());
				LOGGER.debug("Se ha guardado correctamente el alumno ["+entry.getValue()+"]");
			}
			
		}
		catch(Exception e) {
			LOGGER.error("Error guardando el alumno ["+e.getMessage()+"]");
		}
	}
	
	/**
	 * Busca el distrito con el mismo codigo que se ha pasado por parametro y lo devuelve si lo encuetra
	 * @param codigo
	 * @return Distrito
	 */
	
	public Distrito leerCodigo(String codigo) {
		for(Object obj : db.queryByExample(Distrito.class)) {
			Distrito dis = (Distrito) obj;
			if(dis.getCod_distrito().equals(codigo)) {
				return dis;
			}
		}
		return null;
	}
	
}
