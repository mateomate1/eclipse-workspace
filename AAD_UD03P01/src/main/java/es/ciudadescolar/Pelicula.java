package es.ciudadescolar;

import java.util.Map;

public class Pelicula {
	private String titulo;
	private int codPeli, anio;
	private Map<Boolean, Actor> actores;
	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}
	/**
	 * @return the codPeli
	 */
	public int getCodPeli() {
		return codPeli;
	}
	/**
	 * @return the anio
	 */
	public int getAnio() {
		return anio;
	}
	/**
	 * @return the actores
	 */
	public Map<Boolean, Actor> getActores() {
		return actores;
	}
	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	/**
	 * @param codPeli the codPeli to set
	 */
	public void setCodPeli(int codPeli) {
		this.codPeli = codPeli;
	}
	/**
	 * @param anio the anio to set
	 */
	public void setAnio(int anio) {
		this.anio = anio;
	}
	/**
	 * @param actores the actores to set
	 */
	public void setActores(Map<Boolean, Actor> actores) {
		this.actores = actores;
	}
	@Override
	public String toString() {
		return "Pelicula [titulo=" + titulo + ", codPeli=" + codPeli + ", anio=" + anio + ", actores=" + actores + "]";
	}
	
	
}
