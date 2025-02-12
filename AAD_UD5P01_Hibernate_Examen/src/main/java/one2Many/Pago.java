package one2Many;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity(name = "one2Many.Pago")
@Table(name = "payments", schema = "empresa_orm_2324")
public class Pago implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private int paymentID;
	
	@Column(name = "amount")
	private double cantidad;
	
	@Column(name = "payment_date")
	private LocalDate fechaPago;
	
	@ManyToMany
	@JoinColumn(name = "customer_id")
	private Customer cliente;
	
	/**
	 * Constructor de la clase Pago genera una instancia con un cliente asociado a ella
	 * @param cliente
	 */
	public Pago(Customer cliente) {
		
	}
	
	
	
	/**
	 * Constructor de la clase Pago que inicializa una instancia con todos los datos
	 * @param paymentID
	 * @param cantidad
	 * @param fechaPago
	 * @param cliente
	 */
	public Pago(int paymentID, double cantidad, LocalDate fechaPago, Customer cliente) {
		this.paymentID = paymentID;
		this.cantidad = cantidad;
		this.fechaPago = fechaPago;
		this.cliente = cliente;
	}



	/**
	 * @return the paymentID
	 */
	public int getPaymentID() {
		return paymentID;
	}

	/**
	 * @param paymentID the paymentID to set
	 */
	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}

	/**
	 * @return the cantidad
	 */
	public double getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the fechaPago
	 */
	public LocalDate getFechaPago() {
		return fechaPago;
	}

	/**
	 * @param fechaPago the fechaPago to set
	 */
	public void setFechaPago(LocalDate fechaPago) {
		this.fechaPago = fechaPago;
	}

	/**
	 * @return the cliente
	 */
	public Customer getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Customer cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(paymentID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pago other = (Pago) obj;
		return paymentID == other.paymentID;
	}

	@Override
	public String toString() {
		return "Pago [paymentID=" + paymentID + ", cantidad=" + cantidad + ", fechaPago=" + fechaPago + ", cliente="
				+ cliente + "]";
	}
	
	
	
}
