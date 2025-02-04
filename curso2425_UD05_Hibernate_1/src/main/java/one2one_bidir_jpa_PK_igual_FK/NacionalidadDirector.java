package one2one_bidir_jpa_PK_igual_FK;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity(name = "one2one_bidir_jpa_PK_igual_FK.NacionalidadDirector")
@Table(name = "nacionalidad_directores",schema = "peliculas_orm_2425")
public class NacionalidadDirector implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "cod_dire")
	private int codigoDirector;
	
	@Column(name = "nacionalidad")
	private String nacionalidadDirector;
	
	@OneToOne
	@MapsId //Esta FK debe coincidir con nuestra PK
	@JoinColumn(name = "cod_dire")
	private Director dir;

	public NacionalidadDirector() {

	}

	public NacionalidadDirector(String nacionalidadDirector) {
		this.nacionalidadDirector = nacionalidadDirector;
	}

	/**
	 * @return the codigoDirector
	 */
	public int getCodigoDirector() {
		return codigoDirector;
	}

	/**
	 * @param codigoDirector the codigoDirector to set
	 */
	public void setCodigoDirector(int codigoDirector) {
		this.codigoDirector = codigoDirector;
	}

	/**
	 * @return the nacionalidadDirector
	 */
	public String getNacionalidadDirector() {
		return nacionalidadDirector;
	}

	/**
	 * @param nacionalidadDirector the nacionalidadDirector to set
	 */
	public void setNacionalidadDirector(String nacionalidadDirector) {
		this.nacionalidadDirector = nacionalidadDirector;
	}

	/**
	 * @return the dir
	 */
	public Director getDir() {
		return dir;
	}

	/**
	 * @param dir the dir to set
	 */
	public void setDir(Director dir) {
		this.dir = dir;
		if (dir != null && dir.getNacionalidad() != this)
			dir.setNacionalidad(this);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigoDirector, nacionalidadDirector);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NacionalidadDirector other = (NacionalidadDirector) obj;
		return codigoDirector == other.codigoDirector
				&& Objects.equals(nacionalidadDirector, other.nacionalidadDirector);
	}

	@Override
	public String toString() {
		return "NacionalidadDirector [codigoDirector=" + codigoDirector + ", nacionalidadDirector="
				+ nacionalidadDirector + "]";
	}

}
