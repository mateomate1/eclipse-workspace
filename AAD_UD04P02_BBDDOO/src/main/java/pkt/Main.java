package pkt;

import java.io.File;
import java.util.Scanner;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * La clase Main, donde carga un mapa con informacion descargada de un xml, la almacena en una base de datos
 * y luego te pide un codigo por consola donde se devuelve la info de dicho distrito
 * @author ISC
 * @version 2024.11.15
 */

public class Main {
	
	static Scanner sc = new Scanner(System.in);
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
		
	public static void main(String[] args) {
		
		if(args.length != 2) {
			LOGGER.error("Error, se esperaban dos parametros [URL del xml] [fichero.xml]");
			System.exit(1);
		}
		String FILE_URL = args[0];
		String FILE_NAME = args[1];
		File ficheroXml = new File(FILE_NAME);
		
		if(ficheroXml.exists()) {
			LOGGER.info("El fichero xml, ya existe");
			XmlManager.setFicheroXml(ficheroXml);
		}
		else {
			LOGGER.info("El fichero xml no existe, descargando...");
			XmlManager.descargarUsarXml(FILE_URL, FILE_NAME);
		}
		
		TreeMap<String,Distrito> distritos = XmlManager.parsearXmlSax();
		LOGGER.debug("Numero de distritos: "+distritos.size());
		
		BbddooManager bdManager = new BbddooManager(true);
		
		bdManager.introducirDistritos(distritos);
		
		System.out.print("Introduce el codigo de un distrito: ");
		String codigo = sc.next();
		Distrito dis = bdManager.leerCodigo(codigo);
		if(dis != null) {
			LOGGER.debug("Distrito leido correctamente");
			System.out.println("\nCodigo del distrito: "+dis.getCod_distrito()+"\nNombre del distrito: "+dis.getDesc_distrito()+"\nNumero de hombres: "+dis.getNum_hombres()+"\nNumero de mujeres: "+dis.getNum_mujeres()+"\n");
		}
		else {
			LOGGER.warn("No hay ningun cliente con ese codigo");
		}
		bdManager.cerrar();
		
		
		
		
			
	}
}
