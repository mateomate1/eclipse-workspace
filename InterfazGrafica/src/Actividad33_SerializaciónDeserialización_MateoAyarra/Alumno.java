package Actividad33_SerializaciónDeserialización_MateoAyarra;

import java.io.Serializable;

public class Alumno implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombre, nia;
	private int edad;

	public Alumno() {
	}

	public Alumno(String nombre, String nia, int edad) {
		this.nombre = nombre;
		this.nia = nia;
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public String getNia() {
		return nia;
	}

	public int getEdad() {
		return edad;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNia(String nia) {
		this.nia = nia;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	@Override
	public String toString() {
		return "Alumno [nombre=" + nombre + ", nia=" + nia + ", edad=" + edad + "]";
	}

}