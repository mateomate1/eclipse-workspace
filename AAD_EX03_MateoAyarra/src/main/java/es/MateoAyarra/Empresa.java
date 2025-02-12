package es.MateoAyarra;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity(name = "es.MateoAyarra.Empresa")
@Table(name = "empresas", schema = "fcts")
public class Empresa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empresas")
	private Integer id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "sector")
	private String sector;
	
	@Column(name = "direccion")
	private String direccion;
	
	@Column(name = "email_contacto")
	private String email;
	
	@Column(name = "telefono_contacto")
	private String telefono;
	
	@OneToMany(mappedBy = "empresa")
	private Set<Tutor> tutores = new HashSet<Tutor>();
	
	
}
