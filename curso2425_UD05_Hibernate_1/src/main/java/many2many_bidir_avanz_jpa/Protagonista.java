package many2many_bidir_avanz_jpa;

import java.io.Serializable;
import java.util.Objects;

public class Protagonista implements Serializable{
	
	
	private ClaveProtagonista clave;
	
	private boolean esProta;
	
	private Actor actor;
	
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
