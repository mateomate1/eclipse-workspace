package utils;

import java.util.ArrayList;

public class Property {
	private String nombre;
	private ArrayList<String> descripcion; // Not neded
	private int normal, max; // Not neded
	private Damage damage;
	private boolean silver = false, especial = false; // Default false
	
	public String getNombre() {
		return nombre;
	}
	public ArrayList<String> getDescripcion() {
		return descripcion;
	}
	public int getNormal() {
		return normal;
	}
	public int getMax() {
		return max;
	}
	public Damage getDamage() {
		return damage;
	}
	
	public boolean isSilver() {
		return silver;
	}
	public boolean isEspecial() {
		return especial;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setDescripcion(ArrayList<String> descripcion) {
		this.descripcion = descripcion;
	}
	public void setNormal(int normal) {
		this.normal = normal;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public void setDamage(Damage damage) {
		this.damage = damage;
	}
	public void setSilver(boolean silver) {
		this.silver = silver;
	}
	public void setEspecial(boolean especial) {
		this.especial = especial;
	}
	
	
	
}
