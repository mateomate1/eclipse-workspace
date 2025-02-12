package es.MateoAyarra;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity(name = "es.MateoAyarra.Contacto")
@Table(name = "alumnoscontactos", schema = "fcts")
public class Contacto {
	
	@Id
	@Column(name = "id_contacto")
	private Integer id;
	
	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "telefono")
	private String telefono;
	
	@OneToOne
	@JoinColumn(name = "id_alumno")
	private Alumno alumno;

	public Contacto(String email, String telefono, Alumno alumno) {
		this.email = email;
		this.telefono = telefono;
		this.alumno = alumno;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the alumno
	 */
	public Alumno getAlumno() {
		return alumno;
	}

	/**
	 * @param alumno the alumno to set
	 */
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	@Override
	public int hashCode() {
		return Objects.hash(alumno, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contacto other = (Contacto) obj;
		return Objects.equals(alumno, other.alumno) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Contacto [id=" + id + ", email=" + email + ", telefono=" + telefono + ", alumno=" + alumno + "]";
	}
	
	
}
