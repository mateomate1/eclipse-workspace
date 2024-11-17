package AAD.UD04E01.MateoAyarra1;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

/**
 * Clase que gestiona la base de datos de objetos (BDD) utilizando Db4o.
 * 
 * Esta clase permite abrir, cerrar, guardar, recuperar y eliminar objetos de tipo `Distrito` en la base de datos, 
 * utilizando la base de datos embebida de Db4o. Además, proporciona métodos para realizar transacciones con commit y rollback.
 * 
 * @author Mateo Ayarra
 * @version 2024-11-15
 */
public class BBDDOOManager {
	private static final Logger log = LoggerFactory.getLogger(BBDDOOManager.class);
	
	private final String RutaficheroDB = "mateo_ayarra.db4o";
	private File ficheroDB;
	private ObjectContainer db;
	
	/**
     * Constructor que abre o crea la base de datos según el parámetro `overwrite`.
     * 
     * Si `overwrite` es verdadero, la base de datos se abre si ya existe, o se crea si no se encuentra. 
     * Si es falso, la base de datos se elimina y se reinicia desde cero.
     * 
     * @param overwrite Si se establece en verdadero, la base de datos existente se reutiliza. 
     *                  Si es falso, se eliminará la base de datos antes de abrirla.
     */
	public BBDDOOManager(boolean overwrite) {
		ficheroDB = new File(RutaficheroDB);
		if(overwrite) {
			if (ficheroDB.exists()) 
				log.warn("BDD existente. Abriendo");
			else 
				log.warn("BDD no encontrada. Creando BDD");
		}
		else { // En caso de querer reiniciar la BDD
			log.warn("Se procede a reiniciar la BDD");
			ficheroDB.delete();
		}
		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), ficheroDB.getName());
		log.info("BDD abierta... ["+ficheroDB.getPath()+"]");
	}
	
	/**
     * Cierra la base de datos de objetos, liberando los recursos asociados.
     * 
     * Si la base de datos no estaba inicializada, se emite una advertencia.
     * 
     * @return true si la base de datos se cierra correctamente, false si no estaba inicializada.
     */
	public boolean close() {
		if (db != null) {
			db.close();
			log.info("BDD cerrada correctamente... ["+ficheroDB.getAbsolutePath()+"]");
			return true;
		}
		log.warn("No se puede cerrar la BDD porque no estaba inicializada");
		return false;
	}
	
	/**
     * Guarda un objeto `Distrito` en la base de datos.
     * 
     * Si ocurre un error durante el proceso de almacenamiento, se lanza una excepción.
     * 
     * @param d El objeto `Distrito` que se desea guardar en la base de datos.
     */
	public void add(Distrito d) {
		try {
			db.store(d);
			log.debug("Se ha guardado correctamente el distrito ["+d+"]");
		} catch (Exception e) {
			log.error("Error guardando distrito. ["+e.getMessage()+"]");
			throw e;
		}
	}
	
	/**
     * Recupera todos los objetos `Distrito` almacenados en la base de datos.
     * 
     * @return Una lista de objetos `Distrito` recuperados de la base de datos.
     */
	public List<Distrito> getAllDistritos(){
		return db.queryByExample(Distrito.class);
	}
	
	/**
     * Recupera un objeto `Distrito` mediante su código de distrito.
     * 
     * Si no se encuentra el distrito, se emite una advertencia.
     * 
     * @param cod El código del distrito a recuperar.
     * @return El objeto `Distrito` correspondiente al código proporcionado, o null si no se encuentra.
     */
	public Distrito getDistritoByCodDistrito(String cod) {
		Distrito example = new Distrito();
		example.setCod_distrito(cod);
		
		ObjectSet<Distrito> recuperados = db.queryByExample(example);
		Distrito salida = null;
		
		if (recuperados.size() > 0) {
			salida = recuperados.next();
			log.info(salida.toString());
		} else {
			log.warn("No se encontraron Distritos con el código ["+cod+"]");
		}
		
		return salida;
	}
	
	/**
     * Elimina todos los objetos `Distrito` de la base de datos.
     * 
     * Si no se encuentran distritos, se emite una advertencia.
     */
	public void deleteAllDistritos() {
		List<Distrito> distritosRecuperados = this.getAllDistritos();
		
		if (distritosRecuperados.size() > 0) {
			for (Distrito dist : distritosRecuperados) {
				db.delete(dist);
				log.debug("Se ha borrado un distrito");
			}
		} else 
			log.warn("No se ha borrado ningún distrito porque no había ninguno guardado");
	}
	
	/**
     * Elimina un objeto `Distrito` específico de la base de datos.
     * 
     * Si el distrito no se encuentra en la base de datos, se emite una advertencia.
     * 
     * @param ref El objeto `Distrito` que se desea eliminar.
     */
	public void deleteDistrito(Distrito ref) {
		ObjectSet<Distrito> recuperados = db.queryByExample(ref);
		
		if (recuperados.size() > 0) {
			db.delete(recuperados.next());
			log.debug("Se ha eliminado correctamente el distrito.");
		} else {
			log.warn("No se encontraron coincidencias");
		}
	}
	
	/**
     * Realiza un commit en la base de datos, confirmando todas las modificaciones realizadas.
     * 
     * Si la base de datos es nula, no se realiza ninguna acción.
     */
	public void commit() {
		if (db != null) {
			db.commit();
			log.debug("Commit realizado en la BDD");
		}
	}
	
	/**
     * Realiza un rollback en la base de datos, deshaciendo todas las modificaciones realizadas.
     * 
     * Si la base de datos es nula, no se realiza ninguna acción.
     */
	public void rollback() {
		if (db != null) {
			db.rollback();
			log.debug("Rollback realizado en la BDD");
		}
	}
}
