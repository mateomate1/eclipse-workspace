package es.ciudadescolar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBManager implements Serializable{
	private static final long serialVersionUID = 1L;

	private final static Logger log = LoggerFactory.getLogger(DBManager.class);
	
	private final File fichero = new File("BD.properties");
	
	private final String DRIVER, URL, USER, PASSWORD;
	private Connection conexion;
	
	public DBManager() {
			DRIVER = getProperties("driver");
			URL = getProperties("url");
			USER = getProperties("user");
			PASSWORD = getProperties("password");
			try {
				conexion = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch (SQLException e) {
				
			}
	}
	public String getProperties(String propiedad) {
		Properties propiedades = new Properties();
		try {
			propiedades.load(new FileInputStream(fichero));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return propiedades.getProperty(propiedad);
	}
}
