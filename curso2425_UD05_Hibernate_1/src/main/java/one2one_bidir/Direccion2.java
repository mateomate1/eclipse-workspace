package one2one_bidir;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "direcciones", schema = "peliculas_orm_2425")
public class Direccion2 implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_dir")
	private int codigo_direccion;
	
	@Column(name = "calle")
	private String calle;

	@Column(name = "numero")
	private int num;
	
	//Anadir el atributo mapedby para indicar que esta entidad no es el owner, e indicamos el nombre del atributo de la clase OWNER (Actor2) que se corresponde con la clase FK
	@OneToOne(mappedBy = "direc")
	private Actor2 actor;
	
	public Direccion2() {
		
	}
	
	public Direccion2(String calle, int num) {
		this.calle = calle;
		this.num = num;
	}

	/**
	 * @return the codigo_direccion
	 */
	public int getCodigo_direccion() {
		return codigo_direccion;
	}

	/**
	 * @return the calle
	 */
	public String getCalle() {
		return calle;
	}

	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}
	
	/**
	 * @return the actor
	 */
	public Actor2 getActor() {
		return actor;
	}

	/**
	 * @param codigo_direccion the codigo_direccion to set
	 */
	public void setCodigo_direccion(int codigo_direccion) {
		this.codigo_direccion = codigo_direccion;
	}

	/**
	 * @param calle the calle to set
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * @param actor the actor to set
	 */
	public void setActor(Actor2 actor) {
		this.actor = actor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo_direccion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Direccion2 other = (Direccion2) obj;
		return codigo_direccion == other.codigo_direccion;
	}

	@Override
	public String toString() {
		return "Direccion2 [codigo_direccion=" + codigo_direccion + ", calle=" + calle + ", num=" + num + actor!=null?(", actor=" + actor.getCodigo_actor()):"" + "]";
	}
	
	
}
