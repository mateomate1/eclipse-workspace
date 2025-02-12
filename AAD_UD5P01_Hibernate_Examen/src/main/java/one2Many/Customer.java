package one2Many;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name = "one2Many.Customer")
@Table(name = "customers", schema = "empresa_orm_2324")
public class Customer implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private int customerID;
	
	@Column(name = "first_name")
	private String fistName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private Set<Pago> pagos = new HashSet<Pago>();
	
	// Con el constructor por defecto es suficiente pues no tiene campos de relleno obligatorio
	/**
	 * Constructor de la clase Customer que inicializa una instancia sin datos.
	 */
	public Customer() {
	}
	
	/**
	 * Constructor de la clase Customer que inicializa una instancia con todos los datos excepto pagos
	 * @param customerID
	 * @param fistName
	 * @param lastName
	 * @param email
	 */
	public Customer(int customerID, String fistName, String lastName, String email) {
		this.customerID = customerID;
		this.fistName = fistName;
		this.lastName = lastName;
		this.email = email;
	}



	/**
	 * Constructor de la clase Customer que inicializa una instancia con todos los datos del mismo
	 * @param customerID
	 * @param fistName
	 * @param lastName
	 * @param email
	 * @param pagos
	 */
	public Customer(int customerID, String fistName, String lastName, String email, Set<Pago> pagos) {
		this.customerID = customerID;
		this.fistName = fistName;
		this.lastName = lastName;
		this.email = email;
		this.pagos = pagos;
	}



	/**
	 * @return the customerID
	 */
	public int getCustomerID() {
		return customerID;
	}

	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	/**
	 * @return the fistName
	 */
	public String getFistName() {
		return fistName;
	}

	/**
	 * @param fistName the fistName to set
	 */
	public void setFistName(String fistName) {
		this.fistName = fistName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	 * @return the pagos
	 */
	public Set<Pago> getPagos() {
		return pagos;
	}

	/**
	 * @param pagos the pagos to set
	 */
	public void setPagos(Set<Pago> pagos) {
		this.pagos = pagos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return customerID == other.customerID;
	}

	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", fistName=" + fistName + ", lastName=" + lastName + ", email="
				+ email + "]"; // No tendria sentido imprimir todos los pagos cada vez que recuperas un cliente
	}
	
	public boolean addPago(Pago pago) {
		pago.setCliente(this);
		return pagos.add(pago);
	}
	
	public boolean removePago(Pago pago) {
		pago.setCliente(null);
		return pagos.remove(pago);
	}
	
}
