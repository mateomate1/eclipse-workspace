package es.ciudadescolar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase que representa un instituto, permitiendo gestionar la matricula y expulsion de alumnos,
 * y almacenar una lista de los mismos. Cada instancia de Instituto tiene un nombre, un identificador 
 * y una lista de objetos de tipo Alumno.
 * 
 * <p>Esta clase usa SLF4J para registrar acciones especificas, como la eliminacion de alumnos 
 * duplicados en la lista.
 * </p>
 * 
 * @author Mateo Ayarra
 * @version 2024/11/13
 */
public class Instituto {
    private static final Logger log = LoggerFactory.getLogger(Instituto.class);
    private String nombre;
    private String ID;
    private List<Alumno> alumnos;

    /**
     * Constructor de la clase Instituto. Inicializa la lista de alumnos como un ArrayList vacio.
     * Este constructor no requiere parametros.
     */
    public Instituto(String ID) {
    	this.ID = ID;
        alumnos = new ArrayList<Alumno>();
    }

    /**
     * Matricula un alumno en el instituto. Antes de agregar al alumno, se asegura de que no exista
     * una instancia previa del mismo en la lista de alumnos, llamando al metodo {@link #expulsarAlumno(Alumno)}.
     * 
     * @param al el alumno a matricular en el instituto
     */
    public void matricularAlumno(Alumno al) {
        expulsarAlumno(al); // Asegura que no haya coincidencias
        alumnos.add(al);
    }

    /**
     * Expulsa todas las instancias coincidentes de un alumno especifico de la lista de alumnos.
     * Si se encuentra una coincidencia, el metodo registra un mensaje de traza para indicar la eliminacion.
     * 
     * @param al el alumno a expulsar del instituto
     */
    public void expulsarAlumno(Alumno al) {
        while (alumnos.remove(al)) {
            log.trace("Coincidencia encontrada. Eliminando alumno...");
        }
    }

    /**
     * Cambia el identificador del instituto a un nuevo valor.
     * 
     * @param nuevoId el nuevo identificador para el instituto
     */
    public void cambiarIdInstituto(String nuevoId) {
        ID = nuevoId;
    }

    /**
     * Devuelve una representacion en cadena de todos los alumnos matriculados en el instituto.
     * 
     * @return una cadena que contiene la informacion de todos los alumnos, con un salto de linea entre cada uno
     */
    @Override
    public String toString() {
        String salida = "";
        for (Alumno alumno : alumnos) {
            salida += alumno + "\n";
        }
        return salida;
    }

	/**
	 * @return the log
	 */
	public static Logger getLog() {
		return log;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}

	/**
	 * @return the alumnos
	 */
	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @param iD the iD to set
	 * @deprecated
	 */
	public void setID(String iD) {
		ID = iD;
	}

	/**
	 * @param alumnos the alumnos to set
	 */
	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
}
