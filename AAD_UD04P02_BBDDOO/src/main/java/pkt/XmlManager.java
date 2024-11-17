package pkt;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.TreeMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
/**
 * La clase XmlManager, se encarga de gestionar el xml
 * @author ISC
 * @version 2024.11.15
 */
public class XmlManager {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
	protected static File ficheroXml;
	private static TreeMap<String,Distrito> distritos;
	
	/**
	 * El metodo descargarUsarXml, se encarga de descargar un fichero xml pasado por parametro y coloca dicho fichero como fichero a gestionar
	 * @param FILE_URL
	 * @param FILE_NAME
	 */
	
	@SuppressWarnings("deprecation")
	public static void descargarUsarXml(String FILE_URL,String FILE_NAME) {
				
		BufferedInputStream in =null;
		FileOutputStream out= null;
		try {
			in = new BufferedInputStream(new URL(FILE_URL).openStream());
		
			out = new FileOutputStream(FILE_NAME);
			
			byte dataBuffer[] = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
				out.write(dataBuffer, 0, bytesRead);
			}
			ficheroXml = new File(FILE_NAME);
			LOGGER.debug("Recuperación correcta del XML de internet ["+FILE_NAME+"]");
		} 
		catch (IOException e){
			LOGGER.error("Error de E/S durante la recuperación del XML de internet");
		}
		finally{
			try{
				if(in != null)
					in.close();
				if(out != null)
					out.close();
				LOGGER.debug("Se ha cerrado el flujo de descarga correctamente");
			}
			catch(IOException e) {
				LOGGER.error("Error de E/S durante el cierre de los flujos");
			}
		}	
	}
	
	/**
	 * El metodo setFicheroXml, se encarga de colocar el fichero pasado por parametro como fichero a usar
	 * @param fichero
	 */
	
	public static void setFicheroXml(File fichero) {
		ficheroXml = fichero;
	}
	
	/**
	 * El metodo parsearXmlSax, se encarga de procesar el xml. Crea y devuelve un mapa de distritos (con su codigo como clave), sacados del fichero xml
	 * @return TreeMap<String,Distrito>
	 */
	
	public static TreeMap<String,Distrito> parsearXmlSax() {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = null;
		DistritosDefaultHandler distritosDH = null;
		
		try {

			sp = spf.newSAXParser();
			distritosDH = new DistritosDefaultHandler();
			
			sp.parse(ficheroXml, distritosDH);
			
			distritos = distritosDH.getDistritos();
			LOGGER.info("Distritos recogidos(XmlManager): "+distritos.size());
			
		}
		catch (SAXException | ParserConfigurationException | IOException e) {
			LOGGER.error("Error durante el procesamiento del XML");
			e.printStackTrace();
		}
		
		return distritos;
	}
}
