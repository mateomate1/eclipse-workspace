package AAD.UD04E01.MateoAyarra1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Distrito {
	private static final Logger log = LoggerFactory.getLogger(Distrito.class);
	
	private Integer Cod_distrito;
	private String desc_distrito;
	private int hombresEsp, hombresExt;
	private int mujeresEsp, mujeresExt;
	private Integer num_mujeres, num_hombres;
	
	/**
	 * @return the cod_distrito
	 */
	public Integer getCod_distrito() {
		return Cod_distrito;
	}
	/**
	 * @return the desc_distrito
	 */
	public String getDesc_distrito() {
		return desc_distrito;
	}
	/**
	 * @return the hombresEsp
	 */
	public int getHombresEsp() {
		return hombresEsp;
	}
	/**
	 * @return the hombresExt
	 */
	public int getHombresExt() {
		return hombresExt;
	}
	/**
	 * @return the mujeresEsp
	 */
	public int getMujeresEsp() {
		return mujeresEsp;
	}
	/**
	 * @return the mujeresExt
	 */
	public int getMujeresExt() {
		return mujeresExt;
	}
	/**
	 * @return the num_mujeres
	 */
	public Integer getNum_mujeres() {
		return num_mujeres;
	}
	/**
	 * @return the num_hombres
	 */
	public Integer getNum_hombres() {
		return num_hombres;
	}
	/**
	 * @param cod_distrito the cod_distrito to set
	 */
	public void setCod_distrito(Integer cod_distrito) {
		Cod_distrito = cod_distrito;
	}
	/**
	 * @param desc_distrito the desc_distrito to set
	 */
	public void setDesc_distrito(String desc_distrito) {
		this.desc_distrito = desc_distrito;
	}
	/**
	 * @param hombresEsp the hombresEsp to set
	 */
	public void setHombresEsp(int hombresEsp) {
		this.hombresEsp = hombresEsp;
	}
	/**
	 * @param hombresExt the hombresExt to set
	 */
	public void setHombresExt(int hombresExt) {
		this.hombresExt = hombresExt;
	}
	/**
	 * @param mujeresEsp the mujeresEsp to set
	 */
	public void setMujeresEsp(int mujeresEsp) {
		this.mujeresEsp = mujeresEsp;
	}
	/**
	 * @param mujeresExt the mujeresExt to set
	 */
	public void setMujeresExt(int mujeresExt) {
		this.mujeresExt = mujeresExt;
	}
	/**
	 * @param num_mujeres the num_mujeres to set
	 */
	public void setNum_mujeres(Integer num_mujeres) {
		this.num_mujeres = num_mujeres;
	}
	/**
	 * @param num_hombres the num_hombres to set
	 */
	public void setNum_hombres(Integer num_hombres) {
		this.num_hombres = num_hombres;
	}
	
	public void addHombres(int otro) {
		this.num_hombres += otro;
	}
	public void addMujeres(int otro) {
		this.num_mujeres += otro;
	}
	
	public void sum() {
		num_hombres = hombresEsp+hombresExt;
		num_mujeres = mujeresEsp+mujeresExt;
	}

	@Override
	public String toString() {
		return "Distrito [Cod_distrito=" + Cod_distrito + ", num_mujeres="
				+ num_mujeres + ", num_hombres=" + num_hombres + "]";
	}
	
}

