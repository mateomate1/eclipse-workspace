package es.ciudadescolar;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBManager {
	private static final Logger log = LoggerFactory.getLogger(DBManager.class);

	private final String USUARIO = "dam2";
	private final String CONTRASENA = "dam2";

	private Connection conexion = null;
	// private final String QUERY = "SELECT expediente, nombre, nota_raices FROM
	// alumnos";
	private String QUERY = SQL.QUERY2;
	private final String EXPEDIENTE = "expediente", NOMBRE = "nombre", NOTA = "nota_raices";

	private final String DRIVERNAME = "com.mysql.cj.jdbc.Driver";
	private final String dbURL = "jdbc:mysql://192.168.202.36:3306/dam2_2425 ";

	public DBManager() {
		try {
			// PRIMERO REGISTRAMOS EL DRIVER
			Class.forName(DRIVERNAME);
			log.debug("Se ha registrado correctamente el driver: " + DRIVERNAME);
			conexion = DriverManager.getConnection(dbURL, USUARIO, CONTRASENA);
			log.debug("Se ha creado correctamente la conexion");
		} catch (ClassNotFoundException e) {
			log.error("Error durante registrando el driver: " + DRIVERNAME);
		} catch (SQLException e) {
			log.error("Error estableciendo conexion con la BDD: " + e.getMessage());
		}
	}

	public void cerrarDB() {
		if (conexion != null) {
			try {
				conexion.close();
				log.debug("Se ha cerrado la BDD");
			} catch (SQLException e) {
				log.error("Error cerrando la BDD");
			}
		}
	}

	public Collection<Alumno> getAlumnos() {
		List<Alumno> alumnos = null;

		Statement stAlumno = null;
		ResultSet rstAlumno = null;
		if (conexion != null) {
			try {
				log.debug("Se va a ejecutar la query: [" + QUERY + "]");
				stAlumno = conexion.createStatement();
				rstAlumno = stAlumno.executeQuery(QUERY);
				log.debug("Se ha ejecutado la query previa");

				if (rstAlumno.next()) {
					alumnos = new ArrayList<Alumno>();
					do {
						Alumno alumno = new Alumno();

						/*
						 * Opcion 1 String exp = rstAlumno.getString(1); //Mas eficiente por indice pero
						 * menos intuitivo String nom = rstAlumno.getString(2); int nota =
						 * rstAlumno.getInt(3);
						 */

						// Opcion 2
						String exp = rstAlumno.getString(EXPEDIENTE); // Mas eficiente por indice pero menos intuitivo
						String nom = rstAlumno.getString(NOMBRE);
						int nota = rstAlumno.getInt(NOTA);

						alumno.setExpediente(exp);
						alumno.setNombre(nom);
						alumno.setNota(nota);

						alumnos.add(alumno);

					} while (rstAlumno.next()); // Next nos desplaza de 1 en 1 y devuelve falso cuando nos movemos fuera
												// del rango
				} else {
					log.warn("No se ha encontrado ningun alumno");
				}

			} catch (SQLException e) {
				log.error("Error durante la consulta de alumnos [" + e.getMessage() + "]");
			} finally {
				try {
					rstAlumno.close();
					stAlumno.close();
				} catch (Exception e2) {
					log.error("Error durante el cierre del ResultSet o PrepareStatement [" + e2.getMessage() + "]");
				}
			}
		} else {
			log.warn("No se han consultado alumnos porque no se encuentra conectado a la DB");
		}

		return alumnos;
	}

	public int getNota(String expediente) {
		PreparedStatement pstAlumnoNota = null;

		ResultSet rstAlumnoNota = null;

		int notaObtenida = -1;

		try {
			pstAlumnoNota = conexion.prepareStatement(SQL.QUERY_NOTA_ALUMNO);
			pstAlumnoNota.setString(1, expediente);

			rstAlumnoNota = pstAlumnoNota.executeQuery();

			if (rstAlumnoNota.next()) {
				notaObtenida = rstAlumnoNota.getInt(1);
			} else {
				log.warn("No se ha encontrado el alumno buscado[" + expediente + "]");
			}

		} catch (SQLException e) {
			log.error("Error durante la consulta de alumnos [" + e.getMessage() + "]");
		} finally {
			try {
				rstAlumnoNota.close();
				pstAlumnoNota.close();
			} catch (Exception e2) {
				log.error("Error durante el cierre del ResultSet o PrepareStatement [" + e2.getMessage() + "]");
			}
		}

		return notaObtenida;
	}

	public List<String> getNombrePorNotaySexo(int notaCorte, String sexo) {
		PreparedStatement pstAlumno = null;

		ResultSet rstAlumno = null;

		List<String> nombreAlumnos = null;

		try {
			pstAlumno = conexion.prepareStatement(SQL.QUERY_SOBRESALIENTE_ALUMNO);
			pstAlumno.setString(1, sexo);
			pstAlumno.setInt(2, notaCorte);

			rstAlumno = pstAlumno.executeQuery();

			if (rstAlumno.next()) {
				nombreAlumnos = new ArrayList<String>();
				do {
					log.info("Alumno [" + rstAlumno.getString(1) + "]");
				} while (rstAlumno.next());
			} else {
				log.warn("No se ha encontrado alumnos con esas condiciones");
			}

		} catch (SQLException e) {
			log.error("Error durante la consulta de alumnos [" + e.getMessage() + "]");
		} finally {
			try {
				rstAlumno.close();
				pstAlumno.close();
			} catch (Exception e2) {
				log.error("Error durante el cierre del ResultSet o PrepareStatement [" + e2.getMessage() + "]");
			}
		}

		return nombreAlumnos;
	}

	public void dormirSegundos(int segundos) {
		try {
			TimeUnit.SECONDS.sleep(segundos);
		} catch (InterruptedException e) {
			log.warn("Se ha interrumpido el sueño");
		}
	}

	public void insertarAlumno(Alumno alumno) {
		PreparedStatement pstBajaAlumno = null;

		if (conexion != null) {
			try {
				pstBajaAlumno = conexion.prepareStatement(SQL.INSERT_ALUMNO);
				pstBajaAlumno.setString(1, alumno.getExpediente());
				pstBajaAlumno.setString(2, alumno.getNombre());
				pstBajaAlumno.setInt(3, alumno.getNota());

				if (pstBajaAlumno.executeUpdate() == 1) { // al ser un insert de un registro solo comparamos con uno
					log.debug("Se ha dado de alta el alumno " + alumno.getExpediente());
				}
			} catch (SQLException e) {
				log.error("Error durante la insercion del alumno " + alumno.getExpediente());
			}
			if (pstBajaAlumno != null) {
				try {
					pstBajaAlumno.close();
				} catch (SQLException e) {
					log.error("Error durante el cierre del prepared statement de inserccion de alumno");
				}
			}
		}
	}

	public void cambiarNotaAlumno(String expe, int nuevaNota) {
		PreparedStatement pstCambiaNotaAlumno = null;
		if (conexion != null) {
			try {

				pstCambiaNotaAlumno = conexion.prepareStatement(SQL.UPDATE_NOTA_ALUMNO);
				pstCambiaNotaAlumno.setInt(1, nuevaNota);
				pstCambiaNotaAlumno.setInt(2, Integer.parseInt(expe));

				if (pstCambiaNotaAlumno.executeUpdate() == 1) { // al fer un update por PK, solo comparamos con 1.
//					log.debug("Se ha dado de baja el alumno "+alumno.getExpediente());
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public void muestraInfoAlumno(String exp) {
		CallableStatement csSPINFO = null;
		try {
			if (conexion != null) {
				csSPINFO = conexion.prepareCall(SQL.INVOCACION_SP_INFO_ALUMNO);
				csSPINFO.setInt(1, Integer.parseInt(exp));

				log.debug("Antes de ejecutar el procedimiento: " + SQL.INVOCACION_SP_INFO_ALUMNO + " con expediente ["
						+ exp + "]");
//				csSPINFO.executeUpdate();
				csSPINFO.execute();
				log.debug("Se ha ejecutado correctamente el procedimiento");
			}
		} catch (NumberFormatException e) {
			log.error("Error durante la muestra de informacion del alumno con sxpediente [" + exp + "]");
		} catch (SQLException e) {
			log.error("Error durante la muestra de informacion del alumno con sxpediente [" + exp + "]");
		} finally {
			if (csSPINFO != null) {
				try {
					csSPINFO.close();
				} catch (SQLException e) {
					log.debug("");
				}
			} else {
				log.debug("");
			}
		}
	}

	public int getNotaAlumno(String expediente) {
		CallableStatement csFunNotaAlumno = null;
		int notaAlumno = -1;

		if (conexion != null) {
			try {
				csFunNotaAlumno = conexion.prepareCall(SQL.INVOCACION_FUN_NOTA_ALUMNO);
				// Dado que se trata de una funcion, debe devolver un valor por tanto tendre un
				// parametyro de salida que debo registrar
				csFunNotaAlumno.registerOutParameter(1, Types.INTEGER); // Parametro de salida
				csFunNotaAlumno.setInt(2, Integer.parseInt(expediente)); // Parametro de entrada

				log.debug("Antes de ejecutar la funcion: " + SQL.INVOCACION_FUN_NOTA_ALUMNO + " con expediente ["
						+ expediente + "]");
				csFunNotaAlumno.execute();
				log.debug("Se ha ejecutado correctamente la funcion");

				notaAlumno = csFunNotaAlumno.getInt(1);

			} catch (SQLException e) {
				log.error("Error durante la consulta mediante una funcion de la nota de un alumno. Excepcion "
						+ e.getMessage());
			} finally {
				if (csFunNotaAlumno != null) {
					try {
						csFunNotaAlumno.close();
					} catch (SQLException e) {
						log.debug("Error durante el cierre del CallableStatement");
					}
				} else {
					log.debug("");
				}
			}
		}

		return notaAlumno;
	}

	public boolean altaAlumnosTrans() {
		PreparedStatement pstInsertarAlumno = null;
		boolean status = false;

		Alumno al1 = new Alumno("1001", "Ramón", 9);
		Alumno al2 = new Alumno("1002", "María", 10);
		Alumno al3 = new Alumno("1003", "Elena", 6);

		List<Alumno> nuevosAlumnos = new ArrayList<Alumno>();
		nuevosAlumnos.add(al1);
		nuevosAlumnos.add(al2);
		nuevosAlumnos.add(al3);

		if (conexion != null) {
			try {
				pstInsertarAlumno = conexion.prepareStatement(SQL.INSERT_ALUMNO);
				conexion.setAutoCommit(false);
				/*
				 * La insercion de todos los alumnos
				 */

				for (Alumno al : nuevosAlumnos) {
					pstInsertarAlumno.setString(1, al.getExpediente());
					pstInsertarAlumno.setString(2, al.getNombre());
					pstInsertarAlumno.setInt(3, al.getNota());
					pstInsertarAlumno.executeUpdate();
					pstInsertarAlumno.clearParameters();// Como voy a reutilizar el statement, entre ejecucion y
														// ejecucion quiero borrar los valores de los params
				}
				conexion.commit();
				log.debug("Se ha completado satisfactoriamente la transaccion");
				status = true;

			} catch (SQLException e) {
				log.error("Error durante la inserciopn transaccional de alumnos. Excepcion: " + e.getMessage());
				try {
					log.debug("Se procede a iniciar rollback de la transaccion");
					conexion.rollback();
					log.debug("Rollback realizado con exito");
				} catch (SQLException e1) {
					log.error("Error durante el rollback");
				}
			} finally {
				if (pstInsertarAlumno != null) {
					try {
						pstInsertarAlumno.close();
						conexion.setAutoCommit(true);
					} catch (SQLException e) {
					}
				}
			}

		}
		return true;
	}

}
