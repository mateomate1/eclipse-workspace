package es.MateoAyarra;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity(name = "es.MateoAyarra.Alumno")
@Table(name = "alumnos", schema = "fcts")
public class Alumno {
	
	@Id
	@Column(name = "id_alumno")
	private Integer id;
	
	@Column(name = "nombreyapellido")
	private String NomApe;
	
	@Column(name = "fecha_nacimiento")
	private LocalDate fechaNac;
	
	@OneToOne(mappedBy = "alumno", cascade = CascadeType.ALL)
	private Contacto contacto;

	public Alumno(Integer id, String nomApe, LocalDate fechaNac, Contacto contacto) {
		this.id = id;
		NomApe = nomApe;
		this.fechaNac = fechaNac;
		this.contacto = contacto;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the nomApe
	 */
	public String getNomApe() {
		return NomApe;
	}

	/**
	 * @param nomApe the nomApe to set
	 */
	public void setNomApe(String nomApe) {
		NomApe = nomApe;
	}

	/**
	 * @return the fechaNac
	 */
	public LocalDate getFechaNac() {
		return fechaNac;
	}

	/**
	 * @param fechaNac the fechaNac to set
	 */
	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Alumno [id=" + id + ", NomApe=" + NomApe + ", fechaNac=" + fechaNac + "]";
	}
	
}
