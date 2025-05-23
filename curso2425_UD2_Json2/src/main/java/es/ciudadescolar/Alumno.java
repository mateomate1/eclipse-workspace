package es.ciudadescolar;

public class Alumno {
	
	private String nombre, curso, instituto;
	private int expediente, edad;
	
	public Alumno(int expediente, String nombre, String curso, String instituto, int edad) {
		super();
		this.expediente = expediente;
		this.nombre = nombre;
		this.curso = curso;
		this.instituto = instituto;
		this.edad = edad;
	}
	
	
	public Alumno(int expediente, String nombre, int edad) {
		super();
		this.expediente = expediente;
		this.nombre = nombre;
		this.edad = edad;
	}
	
	public Alumno() {
	}

	public int getExpediente() {
		return expediente;
	}
	public String getNombre() {
		return nombre;
	}
	public int getEdad() {
		return edad;
	}
	
	public void setExpediente(int expediente) {
		this.expediente = expediente;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}


	@Override
	public String toString() {
		return "Alumno [expediente=" + expediente + ", nombre=" + nombre + ", curso=" + curso + ", instituto="
				+ instituto + ", edad=" + edad + "]";
	}


	public String getCurso() {
		return curso;
	}


	public String getInstituto() {
		return instituto;
	}


	public void setCurso(String curso) {
		this.curso = curso;
	}


	public void setInstituto(String instituto) {
		this.instituto = instituto;
	}
	

	
	
}
