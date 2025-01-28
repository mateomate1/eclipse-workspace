package many2many_bidir_avanz_jpa;

import java.io.Serializable;
import java.util.Objects;

public class ClaveProtagonista implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int codigoPelicula;
	private int codigoActor;
	
	public ClaveProtagonista() {
	}
	
	public ClaveProtagonista(int codigoPelicula, int codigoActor) {
		this.codigoPelicula = codigoPelicula;
		this.codigoActor = codigoActor;
	}
	
	/**
	 * @return the codigoPelicula
	 */
	public int getCodigoPelicula() {
		return codigoPelicula;
	}
	/**
	 * @param codigoPelicula the codigoPelicula to set
	 */
	public void setCodigoPelicula(int codigoPelicula) {
		this.codigoPelicula = codigoPelicula;
	}
	/**
	 * @return the codigoActor
	 */
	public int getCodigoActor() {
		return codigoActor;
	}
	/**
	 * @param codigoActor the codigoActor to set
	 */
	public void setCodigoActor(int codigoActor) {
		this.codigoActor = codigoActor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigoActor, codigoPelicula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClaveProtagonista other = (ClaveProtagonista) obj;
		return codigoActor == other.codigoActor && codigoPelicula == other.codigoPelicula;
	}
	
}
