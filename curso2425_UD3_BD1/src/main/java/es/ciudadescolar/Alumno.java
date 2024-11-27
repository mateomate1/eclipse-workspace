package es.ciudadescolar;

import java.util.Objects;

public class Alumno {
	private String expediente, nombre;
	private int nota;
	
	public Alumno() {
		
	}
	
	public Alumno(String expediente, String nombre, int nota) {
		super();
		this.expediente = expediente;
		this.nombre = nombre;
		this.nota = nota;
	}
	
	/**
	 * @return the expediente
	 */
	public String getExpediente() {
		return expediente;
	}
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * @return the nota
	 */
	public int getNota() {
		return nota;
	}
	
	/**
	 * @param expediente the expediente to set
	 */
	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}
	
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * @param nota the nota to set
	 */
	public void setNota(int nota) {
		this.nota = nota;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(expediente, nombre, nota);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		return Objects.equals(expediente, other.expediente) && Objects.equals(nombre, other.nombre)
				&& nota == other.nota;
	}

	@Override
	public String toString() {
		return "Alumno [expediente=" + expediente + ", nombre=" + nombre + ", nota=" + nota + "]";
	}
}
