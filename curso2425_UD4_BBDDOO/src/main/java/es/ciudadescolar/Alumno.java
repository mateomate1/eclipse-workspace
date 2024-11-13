package es.ciudadescolar;

import java.util.Objects;

public class Alumno {

	private String expediente;
	private String nombre;
	private int edad;
	private String curso;
	private String instituto; // IES es opcional, puede ser null

	public Alumno() {
	}

	public Alumno(String expediente, String nombre, int edad, String curso, String instituto) {
		super();
		this.expediente = expediente;
		this.nombre = nombre;
		this.edad = edad;
		this.curso = curso;
		this.instituto = instituto;
	}

	public String getExpediente() {
		return expediente;
	}

	public String getNombre() {
		return nombre;
	}

	public int getEdad() {
		return edad;
	}

	public String getCurso() {
		return curso;
	}

	public String getInstituto() {
		return instituto;
	}

	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public void setInstituto(String instituto) {
		this.instituto = instituto;
	}

	public String toString() {
		return "Alumno [expediente=" + expediente + ", nombre=" + nombre + ", edad=" + edad + ", curso=" + curso
				+ (instituto != null ? (", instituto=" + instituto) : "") + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(curso, edad, expediente, instituto, nombre);
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
		return Objects.equals(expediente, other.expediente);
	}

}
