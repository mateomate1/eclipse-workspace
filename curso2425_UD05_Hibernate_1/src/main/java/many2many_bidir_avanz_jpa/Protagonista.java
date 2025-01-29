package many2many_bidir_avanz_jpa;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity(name="many2many_bidir_avanz_jpa.Protagonista")
@Table(name = "protagonistas")
public class Protagonista implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ClaveProtagonista clave;
	
	@Column(name = "es_protagonista")
	private boolean esProta;
	
	@ManyToOne
	@MapsId("codigoActor")//El codigo de actor de esta instancia Actor debe coincidir con el valor de ClaveProtagonista(PK)
	@JoinColumn(name = "cod_actor")
	private Actor actor;
	
	@ManyToOne
	@MapsId("codigoPelicula")
	@JoinColumn(name = "cod_pelicula")
	private Pelicula pelicula;

	/**
	 * @return the clave
	 */
	public ClaveProtagonista getClave() {
		return clave;
	}

	/**
	 * @param clave the clave to set
	 */
	public void setClave(ClaveProtagonista clave) {
		this.clave = clave;
	}

	/**
	 * @return the esProta
	 */
	public boolean isEsProta() {
		return esProta;
	}

	/**
	 * @param esProta the esProta to set
	 */
	public void setEsProta(boolean esProta) {
		this.esProta = esProta;
	}

	/**
	 * @return the actor
	 */
	public Actor getActor() {
		return actor;
	}

	/**
	 * @param actor the actor to set
	 */
	public void setActor(Actor actor) {
		this.actor = actor;
	}

	/**
	 * @return the pelicula
	 */
	public Pelicula getPelicula() {
		return pelicula;
	}

	/**
	 * @param pelicula the pelicula to set
	 */
	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	@Override
	public int hashCode() {
		return Objects.hash(actor, clave, esProta, pelicula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Protagonista other = (Protagonista) obj;
		return Objects.equals(actor, other.actor) && Objects.equals(clave, other.clave) && esProta == other.esProta
				&& Objects.equals(pelicula, other.pelicula);
	}

	@Override
	public String toString() {
		return "Protagonista [esProta=" + esProta + ", actor=" + actor.getNombre() + ", pelicula=" + pelicula.getTitulo_pelicula() + "]";
	}
}
