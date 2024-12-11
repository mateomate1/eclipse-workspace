package es.ciudadescolar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBManager {
	private static final Logger log = LoggerFactory.getLogger(DBManager.class);

	private final String USER = "root", PASSWORD = "Root2425";
	private final String DBURL = "jdbc:mysql://localhost:3306/pizzas";
	private final String DRIVERNAME = "com.mysql.cj.jdbc.Driver";
	private Connection conexion = null;

	public DBManager() {
		try {
			// PRIMERO REGISTRAMOS EL DRIVER
			log.debug("Se procede a intentar conectar a la BD");
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

	public List<String> getIngredientes(String pizza) {
		List<String> ingredientes = null;
		PreparedStatement pstIngredientes = null;
		ResultSet rstIngredientes = null;

		if (conexion != null) {
			try {
				log.debug("Se va a ejecutar la busqueda de los ingredientes [{}]",SQLScripts.GETPIZZAS);
				pstIngredientes = conexion.prepareStatement(SQLScripts.GETPIZZAS);
				pstIngredientes.setString(1, pizza);
				rstIngredientes = pstIngredientes.executeQuery();
				log.debug("Se ha ejecutado la query.");
				
				ingredientes = new ArrayList<String>();
				
				if (rstIngredientes.next()) {
					do {
						String ingrediente = null;

						ingrediente = rstIngredientes.getString(1); //Solo hay una columna que recuperar
						log.info(ingrediente);
						ingredientes.add(ingrediente);
					} while (rstIngredientes.next());
				}
				else 
					log.warn("No se han encontrado ingredientes para la pizza con nombre '{}'",pizza);
				
			} catch (SQLException e) {
				log.error("Error durante la consulta de alumnos [" + e.getMessage() + "]");
			}finally {
				try {
					rstIngredientes.close();
					pstIngredientes.close();
				} catch (Exception e2) {
					log.error("Error durante el cierre del ResultSet o PrepareStatement [" + e2.getMessage() + "]");
				}
			}
		}

		return ingredientes;
	}
	
	public double getPrecio(String pizza) {
		PreparedStatement pstPrecio = null;
		ResultSet rstPrecio = null;
		
		double precio = -1;
		
		if (conexion != null) {
			try {
				log.debug("Se va a ejecutar la busqueda de los ingredientes [{}]",SQLScripts.GETPRECIOPIZZA);
				pstPrecio = conexion.prepareStatement(SQLScripts.GETPRECIOPIZZA);
				pstPrecio.setString(1, pizza);
				rstPrecio = pstPrecio.executeQuery();
				log.debug("Se ha ejecutado la query.");
				if (rstPrecio.next()) {
					precio = rstPrecio.getDouble(1);
					log.info(precio+"");
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally {
				try {
					rstPrecio.close();
					pstPrecio.close();
				} catch (Exception e2) {
					log.error("Error durante el cierre del ResultSet o PrepareStatement [{}]",e2.getMessage());
				}
			}
		}
		return precio;
	}
	
	public void addIngredientePizza(String pizza, String ingrediente, int cantidad) {
		
	}
	
	public void dormirSegundos(int segundos) {
		try {
			TimeUnit.SECONDS.sleep(segundos);
		} catch (InterruptedException e) {
			log.warn("Se ha interrumpido el sueño");
		}
	}

}
