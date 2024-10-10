package es.ciudadescolar.xml;

public class Alumno {
	
	private String expediente, nombre ;
	private int edad;
	
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
		return "Alumnos [expediente=" + expediente + ", nombre=" + nombre + ", edad=" + edad + "]";
	}
	
}
