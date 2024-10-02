package es.ciudadescolar.cliente;

import java.io.Serializable;

public class Alumno implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String expediente;
	private String nombre;
	private transient String password;//transient es para cuando no queremos que se serialice el atributo
	
	public Alumno(String expediente, String nombre, String password) {
		super();
		this.expediente = expediente;
		this.nombre = nombre;
		this.password = password;
	}

	public String getExpediente() {
		return expediente;
	}
	public String getNombre() {
		return nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Alumno [expediente=" + expediente + ", nombre=" + nombre + ", password=" + password + "]";
	}
	
	
	
}
