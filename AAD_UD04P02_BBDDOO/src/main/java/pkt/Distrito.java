package pkt;
/**
 * La clase distrito para guardar los distritos sacados del xml
 * @author ISC
 * @version 2024.11.15
 * 
 */
public class Distrito {
	private String cod_distrito, desc_distrito;
	private Integer num_mujeres, num_hombres;
	
	public Distrito(String cod_distrito, String desc_distrito, int num_mujeres, int num_hombres) {
		this.cod_distrito = cod_distrito;
		this.desc_distrito = desc_distrito;
		this.num_mujeres = num_mujeres;
		this.num_hombres = num_hombres;
	}
	public Distrito() {
		this.cod_distrito = "";
		this.desc_distrito = "";
		this.num_mujeres = 0;
		this.num_hombres = 0;
	}
	
	public String getCod_distrito() {
		return cod_distrito;
	}
	public String getDesc_distrito() {
		return desc_distrito;
	}
	public int getNum_mujeres() {
		return num_mujeres;
	}
	public int getNum_hombres() {
		return num_hombres;
	}
	public void setCod_distrito(String cod_distrito) {
		this.cod_distrito = cod_distrito;
	}
	public void setDesc_distrito(String desc_distrito) {
		this.desc_distrito = desc_distrito;
	}
	public void setNum_mujeres(int num_mujeres) {
		this.num_mujeres = num_mujeres;
	}
	public void setNum_hombres(int num_hombres) {
		this.num_hombres = num_hombres;
	}

}
