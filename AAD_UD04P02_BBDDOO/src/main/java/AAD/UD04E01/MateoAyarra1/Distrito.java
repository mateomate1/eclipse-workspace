package AAD.UD04E01.MateoAyarra1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Distrito {
	private static final Logger log = LoggerFactory.getLogger(Distrito.class);
	
	private String Cod_distrito, desc_distrito;
	private Integer num_mujeres=0, num_hombres=0;
	/**
	 * @return the cod_distrito
	 */
	public String getCod_distrito() {
		return Cod_distrito;
	}
	/**
	 * @return the desc_distrito
	 */
	public String getDesc_distrito() {
		return desc_distrito;
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
	public void setCod_distrito(String cod_distrito) {
		Cod_distrito = cod_distrito;
	}
	/**
	 * @param desc_distrito the desc_distrito to set
	 */
	public void setDesc_distrito(String desc_distrito) {
		this.desc_distrito = desc_distrito;
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
	public void setHombresEsp(String hombresEsp) {
		num_hombres+=Integer.valueOf(hombresEsp);
	}
	public void setHombresExt(String hombresExt) {
		num_hombres+=Integer.valueOf(hombresExt);
	}
	public void setMujeresExt(String mujeresExt) {
		num_mujeres+=Integer.valueOf(mujeresExt);
	}
	public void setMujeresEsp(String mujeresEsp) {
		num_mujeres+=Integer.valueOf(mujeresEsp);
	}
	public void fuse(Distrito other) {
		num_hombres+=other.getNum_hombres();
		num_mujeres+=other.getNum_mujeres();
	}
	@Override
	public String toString() {
		return "Distrito [Cod_distrito=" + Cod_distrito + ", desc_distrito=" + desc_distrito + ", num_mujeres="
				+ num_mujeres + ", num_hombres=" + num_hombres + "]";
	}
	
}
