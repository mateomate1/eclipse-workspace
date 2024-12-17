package es.aad;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.city.ies.RequisitosPractica2;

public class DbManager implements RequisitosPractica2 {
	private static final Logger log = LoggerFactory.getLogger(DbManager.class);

	private final String USER = "root", PASSWORD = "Root2425", DRIVERNAME = "com.mysql.cj.jdbc.Driver",
			DBURL = "jdbc:mysql://localhost:3306/navidad24";
	private Connection conexion = null;

	public DbManager() {
		try {
			// PRIMERO REGISTRAMOS EL DRIVER
			Class.forName(DRIVERNAME);
			log.debug("Se ha registrado correctamente el driver: " + DRIVERNAME);
			conexion = DriverManager.getConnection(DBURL, USER, PASSWORD);
			log.debug("Se ha creado correctamente la conexion");
		} catch (ClassNotFoundException e) {
			log.error("Error durante registrando el driver: " + DRIVERNAME);
		} catch (SQLException e) {
			log.error("Error estableciendo conexion con la BDD: " + e.getMessage());
		}
	}

	public boolean addTrans(List<Persona> personas) {
		boolean status = true;

		if (conexion != null) {
			try {
				conexion.setAutoCommit(false);
				log.debug("Iniciando transaccion de las personas");
				for (int i = 0; i < personas.size(); i++) {
					Persona persona = personas.get(i);
					String nomPersona = persona.getNombre(), emailPersonaString = persona.getEmail();
					LocalDate fechaNac = persona.getFechaNac();
					if (!aniadirPersona(nomPersona, emailPersonaString, fechaNac)) {
						status = false;
					}
				}
				conexion.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error("Error durante la insercion transaccional de personas.");
				status = false;
			} finally {
				if (!status) {
					try {
						log.debug("Se procede a iniciar rollback de la transaccion");
						conexion.rollback();
						log.debug("Rollback realizado con exito");
					} catch (SQLException e1) {
						log.error("Error durante el rollback");
					}
				} else
					log.debug("Se ha completado satisfactoriamente la transaccion");
				try {
					conexion.setAutoCommit(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return status;
	}

	public void getRegaloPorEmail(String email) {
		CallableStatement csFunRegaloXEmail = null;
		String regalo;
		
		if (conexion != null) {
			try {
				csFunRegaloXEmail = conexion.prepareCall(QUERRYS.OBTENERREGALOXEMAIL);
				csFunRegaloXEmail.registerOutParameter(1, Types.VARCHAR);
				csFunRegaloXEmail.setString(2, email);
				
				log.debug("Se procede a ejecuitar la funcion");
				csFunRegaloXEmail.execute();
				log.debug("Se ha ejecutado correctamente la funcion");
				regalo = csFunRegaloXEmail.getString(1);
				if (regalo == null)
					log.warn("No se encontraron regalos asociados al email");
				else 
					log.info("La persona con email "+email+" a recibido el regalo "+regalo);
			} catch (SQLException e) {
				log.error("Error durante la consulta mediante una funcion del regalo");
			} finally {
				if (csFunRegaloXEmail != null) {
					try {
						csFunRegaloXEmail.close();
					} catch (SQLException e2) {
						log.debug("Error durante el cierre del CallableStatement");
					}
				}
			}
		}
	}
	
	public List<String> getAllEmails(){
		return null;
	}
	
	@Override
	public String getRegalo(String email) {
		CallableStatement csFunRegaloXEmail = null;
		String regalo = null;
		
		if (conexion != null) {
			try {
				csFunRegaloXEmail = conexion.prepareCall(QUERRYS.OBTENERREGALOXEMAIL);
				csFunRegaloXEmail.registerOutParameter(1, Types.VARCHAR);
				csFunRegaloXEmail.setString(2, email);
				
				log.debug("Se procede a ejecuitar la funcion");
				csFunRegaloXEmail.execute();
				log.debug("Se ha ejecutado correctamente la funcion");
				regalo = csFunRegaloXEmail.getString(1);
				if (regalo == null)
					log.warn("No se encontraron regalos asociados al email");
				else 
					log.info("Persona [email=" + email + "] con el regalo ["+regalo+"]");
			} catch (SQLException e) {
				log.error("Error durante la consulta mediante una funcion del regalo");
			} finally {
				if (csFunRegaloXEmail != null) {
					try {
						csFunRegaloXEmail.close();
					} catch (SQLException e2) {
						log.debug("Error durante el cierre del CallableStatement");
					}
				}
			}
		}
		
		return regalo;
	}

	@Override
	public boolean aniadirPersona(String nomPersona, String emailPersona, LocalDate fechaNac) {
		// TODO Auto-generated method stub
		PreparedStatement pstAddPersona = null;
		boolean exito = false;

		if (conexion != null) {
			try {
				exito = true;
				pstAddPersona = conexion.prepareStatement(QUERRYS.INSERTPERSONA);
				pstAddPersona.setString(1, nomPersona);
				pstAddPersona.setString(2, emailPersona);
				pstAddPersona.setString(3, fechaNac.toString());

				if (pstAddPersona.executeUpdate() == 1) {
					log.debug("Se ha dado de alta la persona con nombre [" + nomPersona + "], email [" + emailPersona
							+ "] y nacido el dia [" + fechaNac.toString() + "]");
				}
			} catch (SQLException e) {
				log.error("Error durante la insercion de la persona");
				exito = false;
			}
			if (pstAddPersona != null) {
				try {
					pstAddPersona.close();
				} catch (SQLException e) {
					log.error("Error durante el cierre del prepared statement de inserccion de la persona");
				}
			}
		}

		return exito;
	}

	@Override
	public void actualizarTotales() {
		// TODO Auto-generated method stub

	}

}
