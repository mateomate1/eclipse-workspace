package es.ciudadescolar;

import java.io.Serializable;
import java.util.Objects;

public class Participante implements Serializable{
	private static final long serialVersionUID = 1L;
	private String nombre, pais;
	private Integer edad;
	
	public Participante() {
	}

	public Participante(String nombre, String pais, Integer edad) {
		this.nombre = nombre;
		this.pais = pais;
		this.edad = edad;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * @param pais the pais to set
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * @return the edad
	 */
	public Integer getEdad() {
		return edad;
	}

	/**
	 * @param edad the edad to set
	 */
	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	@Override
	public int hashCode() {
		return Objects.hash(edad, nombre, pais);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Participante other = (Participante) obj;
		return Objects.equals(edad, other.edad) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(pais, other.pais);
	}

	@Override
	public String toString() {
		return "Participante [nombre=" + nombre + ", pais=" + pais + ", edad=" + edad + "]";
	}
	
}
