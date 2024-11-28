import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class COPY {
	private static final Logger log = LoggerFactory.getLogger(COPY.class);
	private Connection conexion = null;

	private final String EXPEDIENTE = "expediente";
	private final String NOMBRE = "nombre";
	private final String NOTA_RAICES = "nota_raices";
	private final String FICHERO_PROPIEDADES = "DAM2_DB.properties";
	private final String DRIVER = "driver";
	private final String URL = "url";
	private final String USUARIO = "user";
	private final String CONTRASENA = "password";

	public COPY() {

		Properties propiedades = new Properties();

		try {
			propiedades.load(new FileInputStream(FICHERO_PROPIEDADES));

//primero registramos el driver
			Class.forName(propiedades.getProperty(DRIVER));
			log.debug("Se ha registrado correctamente el driver: " + propiedades.getProperty(DRIVER));
			conexion = DriverManager.getConnection(propiedades.getProperty(URL), propiedades.getProperty(USUARIO),
					propiedades.getProperty(CONTRASENA));
			log.debug("Se ha creado la conexion correctamente");
		} catch (ClassNotFoundException e) {
			log.error("Error registrando el driver: " + propiedades.getProperty(DRIVER));
		} catch (SQLException e) {
			log.error("Error estableciendo conexion con la base de datos: " + e.getMessage());
		} catch (FileNotFoundException e) {
			log.error("Error cargando el fichero properties " + FICHERO_PROPIEDADES);
		} catch (IOException e) {
			log.error("Error cargando el fichero " + FICHERO_PROPIEDADES);
		}
	}

	public void cerrarDb() {
		if (conexion != null) {
			try {
				conexion.close();
				log.debug("Se ha cerrado correctamente la base de datos");
			} catch (SQLException e) {

				log.error("Error al cerrar la base de datos");
			}
		}
	}

	/**
	 * Recorre la coleccion del principio al final
	 * 
	 * @return todos los alumnos
	 */

	public Collection<Alumno> getAlumnos() {

		List<Alumno> alumnos = null;
		Statement stAlumno = null;
		ResultSet rstAlumno = null;
		Alumno alumno = null;

		if (conexion != null) {
			try {
				stAlumno = conexion.createStatement();
				log.debug("Se va a ejecutar la query: [" + SQL.QUERY2 + "]");
				rstAlumno = stAlumno.executeQuery(SQL.QUERY2);
				log.debug("Se ha ejecutado la query previa");

				if (rstAlumno.next()) { // next nos desplaza de uno en uno y devuelve falso cuando nos movemos al
										// siguiente del ultimo
					alumnos = new ArrayList<Alumno>();
					do {
						alumno = new Alumno();

						/*
						 * opcion 1 String exp = rstAlumno.getString(1); //mas eficiente por indice
						 * String nom = rstAlumno.getString(2); int nota = rstAlumno.getInt(3);
						 */

//opcion 2
						String exp = rstAlumno.getString(EXPEDIENTE); // menos eficiente por indice
						String nom = rstAlumno.getString(NOMBRE);
						int nota = rstAlumno.getInt(NOTA_RAICES);

						alumno.setExpediente(exp);
						alumno.setNombre(nom);
						alumno.setNota(nota);

						alumnos.add(alumno);

					} while (rstAlumno.next());
				} else {
					log.warn("No se ha recuperado ningun alumno");
				}
			} catch (SQLException e) {
				log.error("Error durante la consulta de alumnos [" + e.getMessage() + "]");
			} finally {
				try {
					if (rstAlumno != null) {
						rstAlumno.close();
					}
					if (stAlumno != null) {
						stAlumno.close();
					}
				} catch (SQLException e) {
					log.error("Error al cerrar el resultSet o Statement [" + e.getMessage() + "]");
				}
			}
		} else {
			log.warn("No se ha consultado alumnos porque la conexion esta sin inicializar");
		}

		return alumnos;
	}

	public int getNota(String exp) {
		PreparedStatement pstAlumnoNota = null;
		ResultSet rstAlumnoNota = null;

		int notaObtenida = -1;

		try {
			pstAlumnoNota = conexion.prepareStatement(SQL.QUERY_NOTA_ALUMNO);
			pstAlumnoNota.setString(1, exp);

			rstAlumnoNota = pstAlumnoNota.executeQuery();

			if (rstAlumnoNota.next()) {
				notaObtenida = rstAlumnoNota.getInt(1);
			} else {
				log.warn("No se ha encontrado el alumno buscado [" + exp + "]");
			}
		} catch (SQLException e) {
			log.error("Error durante la consulta de nota del alumno con expediente [" + exp + "] [" + e.getMessage()
					+ "]");

		} finally {
			try {
				if (rstAlumnoNota != null) {
					rstAlumnoNota.close();
				}
				if (pstAlumnoNota != null) {
					pstAlumnoNota.close();
				}
			} catch (SQLException e) {
				log.error("Error al cerrar el ResultSet o Statement [" + e.getMessage() + "]");
			}
		}
		return notaObtenida;
	}

	public List<String> getNombrePorNotaySexo(int nota, String sexo) {
		PreparedStatement pstAlumno = null;
		ResultSet rstAlumno = null;
		ArrayList<String> nombres = null;
		String nombre = null;

		try {
			pstAlumno = conexion.prepareStatement(SQL.QUERY_NOTA_ALUMNO);
			pstAlumno.setString(1, sexo);
			pstAlumno.setInt(2, nota);

			rstAlumno = pstAlumno.executeQuery();

			if (rstAlumno.next()) {
				nombres = new ArrayList<String>();
				do {
					nombre = rstAlumno.getString(1);
					log.info("Alumno [" + nombre + "]");
					nombres.add(nombre);
				} while (rstAlumno.next());

			} else {
				log.warn("No se ha encontrado el alumno con estas condiciones [" + sexo + "] [" + nota + "]");
			}
		} catch (SQLException e) {
			log.error("Error durante la consulta de nota del alumno con sexo [" + sexo + "] y con con nota [" + nota
					+ "] [" + e.getMessage() + "]");

		} finally {
			try {
				if (rstAlumno != null) {
					rstAlumno.close();
				}
				if (pstAlumno != null) {
					pstAlumno.close();
				}
			} catch (SQLException e) {
				log.error("Error al cerrar el ResultSet o Statement [" + e.getMessage() + "]");
			}
		}
		return nombres;
	}

	/**
	 * Recorre la coleccion del final al principio y te dice cuantos alumnos ha
	 * recuperado
	 * 
	 * @return todos los alumnos
	 */

	public Collection<Alumno> mostrarAlumnos() {

		List<Alumno> alumnos = null;
		Statement stAlumno = null;
		ResultSet rstAlumno = null;
		Alumno alumno = null;

		if (conexion != null) {
			try {
				stAlumno = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				log.debug("Se va a ejecutar la query: [" + SQL.QUERY1 + "]");

				rstAlumno = stAlumno.executeQuery(SQL.QUERY1);
				log.debug("Se ha ejecutado la query previa");
//dormirSegundos(20);
				if ((rstAlumno.getType() == ResultSet.TYPE_SCROLL_SENSITIVE)
						&& (rstAlumno.getConcurrency() == ResultSet.CONCUR_UPDATABLE)) {
					rstAlumno.last();
					log.debug("El numero de alumnos recuperados es [" + rstAlumno.getRow() + "]");
					alumnos = new ArrayList<Alumno>();
					do {
						alumno = new Alumno();

						String exp = rstAlumno.getString(EXPEDIENTE); // menos eficiente por indice
						String nom = rstAlumno.getString(NOMBRE);
						int nota = rstAlumno.getInt(NOTA_RAICES);

						alumno.setExpediente(exp);
						alumno.setNombre(nom);
						alumno.setNota(nota);

						alumnos.add(alumno);
						log.info(alumno.toString());
					} while (rstAlumno.previous());
				}

				else {
					log.warn("No se ha recuperado ningun alumno");
				}
			} catch (SQLException e) {
				log.error("Error durante la consulta de alumnos [" + e.getMessage() + "]");
			} finally {
				try {
					if (rstAlumno != null) {
						rstAlumno.close();
					}
					if (stAlumno != null) {
						stAlumno.close();
					}
				} catch (SQLException e) {
					log.error("Error al cerrar el resultSet o Statement [" + e.getMessage() + "]");
				}
			}
		} else {
			log.warn("No se ha consultado alumnos porque la conexion esta sin inicializar");
		}

		return alumnos;
	}

	/**
	 * Cambia la nota del alumno con expediente pasado por parametro por la nota
	 * pasada por parametro
	 * 
	 * @param expC
	 * @param notaC
	 * @return todos los alumnos
	 */

	public Collection<Alumno> cambiarNota(String expC, int notaC) {

		List<Alumno> alumnos = null;
		Statement stAlumno = null;
		ResultSet rstAlumno = null;
		Alumno alumno = null;

		if (conexion != null) {
			try {
				stAlumno = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				log.debug("Se va a ejecutar la query: [" + SQL.QUERY1 + "]");

				rstAlumno = stAlumno.executeQuery(SQL.QUERY1);
				log.debug("Se ha ejecutado la query previa");
				if ((rstAlumno.getType() == ResultSet.TYPE_SCROLL_SENSITIVE)
						&& (rstAlumno.getConcurrency() == ResultSet.CONCUR_UPDATABLE)) {
					rstAlumno.last();
					log.debug("El numero de alumnos recuperados es [" + rstAlumno.getRow() + "]");
					alumnos = new ArrayList<Alumno>();
					do {
						if (rstAlumno.getString(EXPEDIENTE).equals(expC)) {
							rstAlumno.updateInt(NOTA_RAICES, notaC);
							rstAlumno.updateRow();
						}
						alumno = new Alumno();

						String exp = rstAlumno.getString(EXPEDIENTE); // menos eficiente por indice
						String nom = rstAlumno.getString(NOMBRE);
						int nota = rstAlumno.getInt(NOTA_RAICES);

						alumno.setExpediente(exp);
						alumno.setNombre(nom);
						alumno.setNota(nota);

						alumnos.add(alumno);
						log.info(alumno.toString());
					} while (rstAlumno.previous());
				}

				else {
					log.warn("No se ha recuperado ningun alumno");
				}
			} catch (SQLException e) {
				log.error("Error durante la consulta de alumnos [" + e.getMessage() + "]");
			} finally {
				try {
					if (rstAlumno != null) {
						rstAlumno.close();
					}
					if (stAlumno != null) {
						stAlumno.close();
					}
				} catch (SQLException e) {
					log.error("Error al cerrar el resultSet o Statement [" + e.getMessage() + "]");
				}
			}
		} else {
			log.warn("No se ha consultado alumnos porque la conexion esta sin inicializar");
		}

		return alumnos;
	}

	public Collection<Alumno> cambiarNombre(String expC, String nombre) {

		List<Alumno> alumnos = null;
		Statement stAlumno = null;
		ResultSet rstAlumno = null;
		Alumno alumno = null;

		if (conexion != null) {
			try {
				stAlumno = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				log.debug("Se va a ejecutar la query: [" + SQL.QUERY1 + "]");

				rstAlumno = stAlumno.executeQuery(SQL.QUERY1);
				log.debug("Se ha ejecutado la query previa");
				if ((rstAlumno.getType() == ResultSet.TYPE_SCROLL_SENSITIVE)
						&& (rstAlumno.getConcurrency() == ResultSet.CONCUR_UPDATABLE)) {
					rstAlumno.last();
					log.debug("El numero de alumnos recuperados es [" + rstAlumno.getRow() + "]");
					alumnos = new ArrayList<Alumno>();
					do {
						if (rstAlumno.getString(EXPEDIENTE).equals(expC)) {
							rstAlumno.updateString(NOMBRE, nombre);
							rstAlumno.updateRow();
						}
						alumno = new Alumno();

						String exp = rstAlumno.getString(EXPEDIENTE); // menos eficiente por indice
						String nom = rstAlumno.getString(NOMBRE);
						int nota = rstAlumno.getInt(NOTA_RAICES);

						alumno.setExpediente(exp);
						alumno.setNombre(nom);
						alumno.setNota(nota);

						alumnos.add(alumno);
						log.info(alumno.toString());
					} while (rstAlumno.previous());
				}

				else {
					log.warn("No se ha recuperado ningun alumno");
				}
			} catch (SQLException e) {
				log.error("Error durante la consulta de alumnos [" + e.getMessage() + "]");
			} finally {
				try {
					if (rstAlumno != null) {
						rstAlumno.close();
					}
					if (stAlumno != null) {
						stAlumno.close();
					}
				} catch (SQLException e) {
					log.error("Error al cerrar el resultSet o Statement [" + e.getMessage() + "]");
				}
			}
		} else {
			log.warn("No se ha consultado alumnos porque la conexion esta sin inicializar");
		}

		return alumnos;
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

	public void borrarAlumno(Alumno alumno) {
		PreparedStatement pstBajaAlumno = null;

		if (conexion != null) {
			try {
				pstBajaAlumno = conexion.prepareStatement(SQL.DELETE_ALUMNO);
				pstBajaAlumno.setString(1, alumno.getExpediente());

				if (pstBajaAlumno.executeUpdate() == 1) { // al ser un insert de un registro solo comparamos con uno
					log.debug("Se ha borrado el alumno " + alumno.getExpediente());
				}
			} catch (SQLException e) {
				log.error("Error durante la eliminacion del alumno " + alumno.getExpediente());
			}
			if (pstBajaAlumno != null) {
				try {
					pstBajaAlumno.close();
				} catch (SQLException e) {
					log.error("Error durante el cierre del prepared statement de eliminacion de alumno");
				}
			}
		}
	}

	public void cambiarNotaAlumno(String expC, int notaC) {

		PreparedStatement pstBajaAlumno = null;

		if (conexion != null) {
			try {
				pstBajaAlumno = conexion.prepareStatement(SQL.UPDATE_NOTA_ALUMNO);
				pstBajaAlumno.setInt(1, notaC);

				if (pstBajaAlumno.executeUpdate() == 1) { // al ser un Update por PK, solo comparamos con uno
					log.debug("Se ha actualizado la nota del alumno " + expC);
				} else {
					log.warn("No se ha encontrado el alumno a modificar su nota " + expC);
				}
			} catch (SQLException e) {
				log.error(
						"Error durante la actualizacion de nota del alumno " + expC + " excepcion: " + e.getMessage());
			}
			if (pstBajaAlumno != null) {
				try {
					pstBajaAlumno.close();
				} catch (SQLException e) {
					log.error(
							"Error durante el cierre del prepared statement de actualizacion de la nota del alumno "
									+ expC);
				}
			}
		}
	}

// public void verPermisos() {
// PreparedStatement pstPermisos = null;
// ResultSet rsPermisos = null;
// Properties propiedades = new Properties();
//
//
// if(conexion != null) {
// try {
// propiedades.load(new FileInputStream(FICHERO_PROPIEDADES));
// pstPermisos = conexion.prepareStatement(SQL.QUERY_PERMISOS);
// pstPermisos.setString(1, propiedades.getProperty(USUARIO));
// pstPermisos.setString(2, propiedades.getProperty(URL));
//
//
// }
// catch (SQLException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// } catch (FileNotFoundException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// } catch (IOException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// }
// }
}