package one2one_uni;

import java.io.Serializable;
import java.util.Objects;

public class Direccion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int codigo_direccion;
	
	private String calle;
	
	private int num;
	
	public Direccion() {
		
	}
	
	public Direccion(String calle, int num) {
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
		Direccion other = (Direccion) obj;
		return codigo_direccion == other.codigo_direccion;
	}

	@Override
	public String toString() {
		return "Direccion [codigo_direccion=" + codigo_direccion + ", calle=" + calle + ", num=" + num + "]";
	}
	
	
}
