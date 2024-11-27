package es.ciudadescolar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBManager {
	private static final Logger log = LoggerFactory.getLogger(DBManager.class);
	
	private final String USUARIO = "dam2";
	private final String CONTRASENA = "dam2";
	
	private Connection conexion = null;
	//private final String QUERY = "SELECT expediente, nombre, nota_raices FROM alumnos";
	private String QUERY = SQL.QUERY2;
	private final String EXPEDIENTE = "expediente", NOMBRE ="nombre", NOTA = "nota_raices";
	
	
	private final String DRIVERNAME = "com.mysql.cj.jdbc.Driver";
	private final String dbURL = "jdbc:mysql://192.168.202.36:3306/dam2_2425 ";
	
	public DBManager() {
		try {
			// PRIMERO REGISTRAMOS EL DRIVER
			Class.forName(DRIVERNAME);
			log.debug("Se ha registrado correctamente el driver: "+DRIVERNAME);
			conexion =  DriverManager.getConnection(dbURL, USUARIO, CONTRASENA);
			log.debug("Se ha creado correctamente la conexion");
		} catch (ClassNotFoundException e) {
			log.error("Error durante registrando el driver: " + DRIVERNAME);
		} catch (SQLException e) {
			log.error("Error estableciendo conexion con la BDD: " + e.getMessage());
		}
	}
	
	public void cerrarDB() {
		if (conexion!=null) {
			try {
				conexion.close();
				log.debug("Se ha cerrado la BDD");
			} catch (SQLException e) {
				log.error("Error cerrando la BDD");
			}
		}
	}
	
	public Collection<Alumno> getAlumnos(){
		List<Alumno> alumnos = null;
		
		Statement stAlumno = null;
		ResultSet rstAlumno = null;
		if(conexion != null) {
			try {
				log.debug("Se va a ejecutar la query: ["+QUERY+"]");
				stAlumno = conexion.createStatement();
				rstAlumno = stAlumno.executeQuery(QUERY);
				log.debug("Se ha ejecutado la query previa");
				
				if (rstAlumno.next()) {
					alumnos = new ArrayList<Alumno>();
					do {
						Alumno alumno = new Alumno();
						
						/* Opcion 1
						String exp = rstAlumno.getString(1); //Mas eficiente por indice pero menos intuitivo
						String nom = rstAlumno.getString(2);
						int nota = rstAlumno.getInt(3);
						*/
						
						// Opcion 2
						String exp = rstAlumno.getString(EXPEDIENTE); //Mas eficiente por indice pero menos intuitivo
						String nom = rstAlumno.getString(NOMBRE);
						int nota = rstAlumno.getInt(NOTA);
						
						alumno.setExpediente(exp);
						alumno.setNombre(nom);
						alumno.setNota(nota);
						
						alumnos.add(alumno);
						
					} while (rstAlumno.next()); // Next nos desplaza de 1 en 1 y devuelve falso cuando nos movemos fuera del rango
				}else {
					log.warn("No se ha encontrado ningun alumno");
				}
				
			} catch (SQLException e) {
				log.error("Error durante la consulta de alumnos ["+e.getMessage()+"]");
			} finally {
				try {
					rstAlumno.close();
					stAlumno.close();
				} catch (Exception e2) {
					log.error("Error durante el cierre del ResultSet o PrepareStatement ["+e2.getMessage()+"]");
				}
			}
		}else {
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
				log.warn("No se ha encontrado el alumno buscado["+expediente+"]");
			}
			
		} catch (SQLException e) {
			log.error("Error durante la consulta de alumnos ["+e.getMessage()+"]");
		} finally {
			try {
				rstAlumnoNota.close();
				pstAlumnoNota.close();
			} catch (Exception e2) {
				log.error("Error durante el cierre del ResultSet o PrepareStatement ["+e2.getMessage()+"]");
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
//					log.info("Alumno ["+rstAlumno.getString(1)+"]");
					log.info("Alumno ["+rstAlumno.getString(1)+"]");
				} while (rstAlumno.next());
			} else {
				log.warn("No se ha encontrado alumnos con esas condiciones");
			}
			
		} catch (SQLException e) {
			log.error("Error durante la consulta de alumnos ["+e.getMessage()+"]");
		} finally {
			try {
				rstAlumno.close();
				pstAlumno.close();
			} catch (Exception e2) {
				log.error("Error durante el cierre del ResultSet o PrepareStatement ["+e2.getMessage()+"]");
			}
		}
		
		return nombreAlumnos;
	}
	
}
