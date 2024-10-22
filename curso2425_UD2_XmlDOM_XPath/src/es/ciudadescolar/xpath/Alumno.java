package es.ciudadescolar.xpath;

public class Alumno {
	
	private String expediente, nombre, curso, instituto;
	private int edad;
	
	public Alumno(String expediente, String nombre, String curso, String instituto, int edad) {
		super();
		this.expediente = expediente;
		this.nombre = nombre;
		this.curso = curso;
		this.instituto = instituto;
		this.edad = edad;
	}
	
	
	public Alumno(String expediente, String nombre, int edad) {
		super();
		this.expediente = expediente;
		this.nombre = nombre;
		this.edad = edad;
	}
	
	public Alumno() {
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
	
	public void setExpediente(String expediente) {
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
	

	
	
}
