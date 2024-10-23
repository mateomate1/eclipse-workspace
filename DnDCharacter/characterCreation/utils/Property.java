package utils;

import java.util.ArrayList;

public class Property {
	private String nombre;
	private ArrayList<String> descripcion; // Not neded
	private int normal, max; // Not neded
	private Damage damage;
	private boolean silver = false, especial = false; // Default false
	
	public Damage getDamage() {
		return damage;
	}
	public ArrayList<String> getDescripcion() {
		return descripcion;
	}
	public int getMax() {
		return max;
	}
	public String getNombre() {
		return nombre;
	}
	public int getNormal() {
		return normal;
	}
	
	public boolean isEspecial() {
		return especial;
	}
	public boolean isSilver() {
		return silver;
	}
	
	public void setDamage(Damage damage) {
		this.damage = damage;
	}
	public void setDescripcion(ArrayList<String> descripcion) {
		this.descripcion = descripcion;
	}
	public void setEspecial(boolean especial) {
		this.especial = especial;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setNormal(int normal) {
		this.normal = normal;
	}
	public void setSilver(boolean silver) {
		this.silver = silver;
	}
	
	
	
}
