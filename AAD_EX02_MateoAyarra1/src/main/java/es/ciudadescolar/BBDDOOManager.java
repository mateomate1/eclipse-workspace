package es.ciudadescolar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

/**
 * Clase BBDDOOManager del proyecto, encargada de manejar las operaciones de la BDD
 * 
 * @author Mateo Ayarra
 * @version 20/11/2024
 */
public class BBDDOOManager {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BBDDOOManager.class);
	
	private File ficheroDB = new File("hackers.db4o");
	private ObjectContainer db;
	
	/**
	 * Metodo de la clase que inicializa un objeto BBDDOOManager, recive un parametro Overwrite, cuando este es true, la BDD inicia completamente vacia
	 * 
	 * @param overwrite
	 */
	public BBDDOOManager (boolean overwrite) {
		if (overwrite) {
			if(ficheroDB.exists()) {
				LOGGER.debug("Se procede a reiniciar la BD");
				ficheroDB.delete();
			}
		}
		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), ficheroDB.getPath());
	}
	
	/**
	 * Metodo que cierra la BDD
	 */
	public void close() {
		db.close();
	}
	
	/**
	 * Metodo que guarda lo realizado en la BDD (commit)
	 */
	public void commit() {
		if(db!=null) {
			db.commit();
			LOGGER.debug("Commit realizado con exito");
		}
	}
	
	/**
	 * Metodo que desace lo realizado en la base de datos hasta el ultimo commit
	 */
	public void rollback(){
		db.rollback();
		LOGGER.debug("Rollback realizado con exito");
	}
	
	/**
	 * Metodo que recupera todos los hackers de la BDD
	 * 
	 * @return
	 */
	public List<Hacker> getAll(){
		Hacker example = new Hacker();
		ObjectSet<Hacker> recuperados = db.queryByExample(example);
		return recuperados;
	}
	
	/**
	 * Metodo que recupero los alumnos con nota mayor a la indicada por parametro, tambien notifica los hacker encontrados inicialmente en la BDD
	 * 
	 * @param nota
	 * @return
	 */
	public List<Hacker> getByNota(double nota){
		Hacker example = new Hacker();
		List<Hacker> recuperados = db.queryByExample(example);
		LOGGER.info("Inicialmente hay ["+recuperados.size()+"] hackers registrados");
		List<Hacker> salida = new ArrayList<Hacker>();
		for (int i = 0; i < recuperados.size(); i++) {
			Hacker hacker = recuperados.get(i);
			if (hacker.getNota() > nota) {
				salida.add(hacker);
			}
		}
		return salida;
	}
	
	/**
	 * Metodo que borra los hacker incluidos en la lista recibida por parametro
	 * 
	 * @param borrar
	 */
	public void borrarHackers(List<Hacker> borrar) {
		for (Hacker hacker : borrar) {
			db.delete(hacker);
		}
	}
	
}
