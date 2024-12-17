package es.aad;

import java.time.LocalDate;

public class Regalo {
	private int id, personaID;
	private String descripcion;
	private double precio;
	private LocalDate fechaRegistro;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the personaID
	 */
	public int getPersonaID() {
		return personaID;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @return the precio
	 */
	public double getPrecio() {
		return precio;
	}
	/**
	 * @return the fechaRegistro
	 */
	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @param personaID the personaID to set
	 */
	public void setPersonaID(int personaID) {
		this.personaID = personaID;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	/**
	 * @param fechaRegistro the fechaRegistro to set
	 */
	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	@Override
	public String toString() {
		return "Regalo [id=" + id + ", personaID=" + personaID + ", descripcion=" + descripcion + ", precio=" + precio
				+ ", fechaRegistro=" + fechaRegistro + "]";
	}
	
	
}
