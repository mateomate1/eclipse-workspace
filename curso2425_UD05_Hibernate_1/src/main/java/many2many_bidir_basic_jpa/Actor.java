package many2many_bidir_basic_jpa;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity(name = "many2many_bidir_basic_jpa.Actor")
@Table (name = "actores", schema = "peliculas_orm_2425")
public class Actor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//Atributos de la clase:
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_act")
	private int codigo_actor;
	
	@Column(name = "nombre")
	private String nombre;
	
	@OneToOne (cascade = CascadeType.PERSIST)
	//@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name = "cod_direccion") // Esta etiqueta nos sirve tmb para identificar esta entidad como OWNER/PADRE/MASTER
	private Direccion direc;
	
	@ManyToMany(mappedBy = "elenco", cascade = CascadeType.PERSIST)
	private Set<Pelicula> peliculasActor;
	
	public Actor() {
		this.peliculasActor = new HashSet<Pelicula>();
	}
	
	public Actor(String nombre, Direccion direc) {
		this.nombre = nombre;
		this.direc = direc;
		this.peliculasActor = new HashSet<Pelicula>();
	}

	
	/**
	 * @return the codigo_actor
	 */
	public int getCodigo_actor() {
		return codigo_actor;
	}

	/**
	 * @param codigo_actor the codigo_actor to set
	 */
	public void setCodigo_actor(int codigo_actor) {
		this.codigo_actor = codigo_actor;
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
	 * @return the direc
	 */
	public Direccion getDirec() {
		return direc;
	}

	/**
	 * @param direc the direc to set
	 */
	public void setDirec(Direccion direc) {
		this.direc = direc;
	}

	/**
	 * @return the peliculasActor
	 */
	public Set<Pelicula> getPeliculasActor() {
		return peliculasActor;
	}

	/**
	 * @param peliculasActor the peliculasActor to set
	 */
	public void setPeliculasActor(Set<Pelicula> peliculasActor) {
		this.peliculasActor = peliculasActor;
	}
	
	public void addPelicula(Pelicula peli) {
		peliculasActor.add(peli);
	}
	
	public void removePelicula(Pelicula peli) {
		peliculasActor.remove(peli);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(codigo_actor, direc, nombre);
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
		return codigo_actor == other.codigo_actor && Objects.equals(direc, other.direc)
				&& Objects.equals(nombre, other.nombre);
	}
	@Override
	public String toString() {
		return "Actor [codigo_actor=" + codigo_actor + ", nombre=" + nombre + ", direc=" + direc.getCalle() + "]";
	}
}
