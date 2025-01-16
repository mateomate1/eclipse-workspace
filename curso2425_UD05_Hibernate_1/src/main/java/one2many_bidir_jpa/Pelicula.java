package one2many_bidir_jpa;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "peliculas", schema = "peliculas_orm_2425")
public class Pelicula implements Serializable{
	private static final long serialVersionUID = 1L;

	private int codigo_pelicula;
	
	private String titulo_pelicula;
	
	private LocalDate fecha_grab;
	
	private Director dir_pelicula;

	public Pelicula(int codigo_pelicula, String titulo_pelicula, LocalDate fecha_grab, Director dir_pelicula) {
		this.codigo_pelicula = codigo_pelicula;
		this.titulo_pelicula = titulo_pelicula;
		this.fecha_grab = fecha_grab;
		this.dir_pelicula = dir_pelicula;
	}

	/**
	 * @return the codigo_pelicula
	 */
	public int getCodigo_pelicula() {
		return codigo_pelicula;
	}

	/**
	 * @param codigo_pelicula the codigo_pelicula to set
	 */
	public void setCodigo_pelicula(int codigo_pelicula) {
		this.codigo_pelicula = codigo_pelicula;
	}

	/**
	 * @return the titulo_pelicula
	 */
	public String getTitulo_pelicula() {
		return titulo_pelicula;
	}

	/**
	 * @param titulo_pelicula the titulo_pelicula to set
	 */
	public void setTitulo_pelicula(String titulo_pelicula) {
		this.titulo_pelicula = titulo_pelicula;
	}

	/**
	 * @return the fecha_grab
	 */
	public LocalDate getFecha_grab() {
		return fecha_grab;
	}

	/**
	 * @param fecha_grab the fecha_grab to set
	 */
	public void setFecha_grab(LocalDate fecha_grab) {
		this.fecha_grab = fecha_grab;
	}

	/**
	 * @return the dir_pelicula
	 */
	public Director getDir_pelicula() {
		return dir_pelicula;
	}

	/**
	 * @param dir_pelicula the dir_pelicula to set
	 */
	public void setDir_pelicula(Director dir_pelicula) {
		this.dir_pelicula = dir_pelicula;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo_pelicula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pelicula other = (Pelicula) obj;
		return codigo_pelicula == other.codigo_pelicula;
	}

	@Override
	public String toString() {
		String director = "No hay director asignado";
		if(dir_pelicula!=null)
			director = dir_pelicula.getNombre_director();
		
		return "Pelicula [codigo_pelicula=" + codigo_pelicula + ", titulo_pelicula=" + titulo_pelicula + ", fecha_grab="
				+ fecha_grab + ", dir_pelicula=" + director + "]";
	}
	
	
}
