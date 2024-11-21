package es.ciudadescolar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBManager {
	private final String USUARIO = "dam2";
	private final String CONTRASENA = "dam2";
	
	private static final Logger log = LoggerFactory.getLogger(DBManager.class);
	private Connection conexion = null;
	private final String QUERY = "SELECT expediente, nombre, nota_raices FROM alumnos";
	private final String propExp = "expediente", propNombre ="nombre";
	
	private final String DRIVERNAME = "com.mysql.cj.jdbc.Driver";
	private final String dbURL = "jdbc:mysql://192.168.202.36:3306/dam2_2425 ";
	
	public DBManager() {
		try {
			// pRIMERO REGISTRAMOS EL DRIVER
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
			} catch (SQLException e) {
				log.error("Error cerrando la BDD");
			}
		}
	}
	
}
