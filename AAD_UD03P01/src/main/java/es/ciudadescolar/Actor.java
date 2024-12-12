package es.ciudadescolar;

import java.util.Objects;

public class Actor {
	private int codAct;
	private String nombre, apellido;
	/**
	 * @return the codAct
	 */
	public int getCodAct() {
		return codAct;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}
	/**
	 * @param codAct the codAct to set
	 */
	public void setCodAct(int codAct) {
		this.codAct = codAct;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	@Override
	public int hashCode() {
		return Objects.hash(apellido, nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		return Objects.equals(apellido, other.apellido) && Objects.equals(nombre, other.nombre);
	}
	@Override
	public int compareTo(Actor obj) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String toString() {
		return "Actor [codAct=" + codAct + ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}
	
}
