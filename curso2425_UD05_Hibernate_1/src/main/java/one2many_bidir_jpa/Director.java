package one2many_bidir_jpa;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Director implements Serializable{
	private static long serialVersionUID = 1L;
	
	private int codigo_director;
	
	private String nombre_director;
	
	private Set<Pelicula> pelis_director;
	
	public Director() {
		pelis_director = new HashSet<Pelicula>();
	}
	
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @param serialversionuid the serialversionuid to set
	 */
	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}

	/**
	 * @return the codigo_director
	 */
	public int getCodigo_director() {
		return codigo_director;
	}

	/**
	 * @param codigo_director the codigo_director to set
	 */
	public void setCodigo_director(int codigo_director) {
		this.codigo_director = codigo_director;
	}

	/**
	 * @return the nombre_director
	 */
	public String getNombre_director() {
		return nombre_director;
	}

	/**
	 * @param nombre_director the nombre_director to set
	 */
	public void setNombre_director(String nombre_director) {
		this.nombre_director = nombre_director;
	}

	/**
	 * @return the pelis_director
	 */
	public Set<Pelicula> getPelis_director() {
		return pelis_director;
	}

	/**
	 * @param pelis_director the pelis_director to set
	 */
	public void setPelis_director(Set<Pelicula> pelis_director) {
		this.pelis_director = pelis_director;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo_director);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Director other = (Director) obj;
		return codigo_director == other.codigo_director;
	}

	@Override
	public String toString() {
		return "Director [codigo_director=" + codigo_director + ", nombre_director=" + nombre_director
				+ ", pelis_director=" + pelis_director + "]";
	}
	
	public boolean addPelicula(Pelicula peli) {
		peli.setDir_pelicula(this);
		return pelis_director.add(peli);
	}
	
	public boolean removePelicula(Pelicula peli) {
		peli.setDir_pelicula(null);
		return pelis_director.remove(peli);
	}
}
