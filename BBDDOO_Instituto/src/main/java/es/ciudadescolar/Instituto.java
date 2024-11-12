package es.ciudadescolar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que contiene los metodos propios de un instituto
 */
public class Instituto {
	
	private String nombre;
	private Integer ID;
	private List<Alumno> alumnos;
	
	public Instituto() {
		alumnos = new ArrayList<Alumno>();
	}

	public void matricularAlumno(Alumno al) {
		alumnos.add(al);
	}
	
	public void expulsarAlumno(Alumno al) {
		for(int i=0; i<alumnos.size(); i++) {
			if(al.equals(alumnos.get(i))) {
				alumnos.remove(i);
				i--;
			}
		}
	}

	@Override
	public String toString() {
		String salida = "";
		for (Alumno alumno : alumnos) {
			salida+=alumno;
		}
		return "Instituto [nombre=" + nombre + ", ID=" + ID + ", alumnos=" + alumnos + "]";
	}
	
}
