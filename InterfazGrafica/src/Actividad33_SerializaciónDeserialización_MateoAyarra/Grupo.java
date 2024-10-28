package Actividad33_SerializaciónDeserialización_MateoAyarra;

import java.io.Serializable;
import java.util.ArrayList;

public class Grupo implements Serializable{
	private String nombre;
	private ArrayList<Alumno> alumnos;
	
	public Grupo(String nombre) {
		this.nombre = nombre;
		alumnos = new ArrayList<Alumno>();
	}

	public void addAlumno(Alumno al) {
		alumnos.add(al);
	}
	
	public void buscarAlumno(String nia) {
		
	}
	
}
