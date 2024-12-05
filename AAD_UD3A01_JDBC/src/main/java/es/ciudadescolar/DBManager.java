package es.ciudadescolar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBManager {
	private static final Logger log = LoggerFactory.getLogger(DBManager.class);
	
	private final String USER = "root", PASSWORD = "Root2425";
	private final String DBURL = "jdbc:mysql://localhost:3306/pizzas";
	private final String DRIVERNAME = "jdbc:mysql://localhost:3306/pizzas";
	private Connection conexion = null;
	
	public DBManager() {
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
	
	
	
}
