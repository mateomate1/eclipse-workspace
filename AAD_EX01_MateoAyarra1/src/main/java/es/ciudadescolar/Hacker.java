package es.ciudadescolar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase que representa a un hacker, incluye el Id, Nombre, Apellido y nota
 * 
 * @author Mateo Ayarra
 * @version 20/11/2024
 */
public class Hacker {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Hacker.class);
	
	private String id, nombre, apellido;
	private double nota;
	/**
	 * @return the logger
	 */
	public static Logger getLogger() {
		return LOGGER;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}
	/**
	 * @return the nota
	 */
	public double getNota() {
		return nota;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	/**
	 * @param nota the nota to set
	 */
	public void setNota(double nota) {
		this.nota = nota;
	}
	
	/**
	 * Metodo que representa a un objeto de la clase Hacker como texto
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return id+"|"+nombre+"|"+apellido+"|"+nota;
	}
	
}
