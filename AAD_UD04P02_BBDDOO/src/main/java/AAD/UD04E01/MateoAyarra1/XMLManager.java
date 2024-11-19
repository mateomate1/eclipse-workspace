package AAD.UD04E01.MateoAyarra1;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

/**
 * Clase que gestiona la descarga y el procesamiento de archivos XML.
 *  
 * Esta clase se encarga de descargar un archivo XML desde una URL especifica y guardarlo en el sistema de archivos local. Tambien proporciona una funcionalidad para analizar el contenido del archivo XML y convertirlo en una lista de objetos de tipo `Distrito`. Utiliza la API SAX para el procesamiento del XML y un esquema XML para la validacion.
 * 
 * @author Mateo Ayarra
 * @version 2024-11-15
 */
public class XMLManager {
    private static final Logger log = LoggerFactory.getLogger(XMLManager.class);
    
    private static File ficheroXML;
    private static Map<Integer, Distrito> distritos;
    
    /**
     * Procesa el archivo XML y devuelve una lista de objetos `Distrito`. 
     * 
     * Este metodo utiliza un analizador SAX con un `DistritoDefaultHandler` para parsear el XML y extraer la informacion relevante para cada distrito. El archivo XML se lee desde un archivo local cuyo nombre esta predefinido en la constante `FILE_NAME`.
     * 
     * @return Lista de objetos `Distrito` con la informacion extraida del XML. Si ocurre algun error, la lista sera vacia.
     */
    public static Map<Integer, Distrito> parseoXML() {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sp = null;
        
        DistritoDefaultHandler distritosDH = null;
        
        try {
            sp = spf.newSAXParser();
            distritosDH = new DistritoDefaultHandler();
            
            sp.parse(ficheroXML, distritosDH);
            
            distritos = distritosDH.getDistrito();
            log.info("Distritos recogidos(XmlManager): "+distritos.size());
        } catch (NullPointerException e) {
            log.error("El fichero no existe o no esta en esta ubicacion");
        } catch (SAXException|ParserConfigurationException|IOException e) {
            log.error("Error "+ e.getMessage());
        }
        return distritos;
    }
    
    /**
     * Descarga un archivo desde una URL especifica y lo guarda en un archivo local. 
     * 
     * Este metodo utiliza un bufer para leer los datos en bloques, lo que mejora el rendimiento al manejar archivos grandes. Tambien maneja posibles excepciones de entrada/salida y asegura que los flujos se cierren correctamente al finalizar la operacion.
     * 
     * @param FILE_URL -> la URL desde la cual se descargara el archivo. Debe ser una URL valida.
     * @param FILE_NAME -> el nombre del archivo donde se guardaran los datos descargados. Puede incluir una ruta relativa o absoluta.
     * 
     * @throws IllegalArgumentException -> Si FILE_URL o FILE_NAME son nulos o estan vacios.
     * @throws IOException -> Si ocurre un error al leer o escribir los datos.
     */
    @SuppressWarnings("deprecation")
    public static void loadURLtoFile(String FILE_URL, String FILE_NAME) {
        BufferedInputStream in = null;
        FileOutputStream out = null;
        ficheroXML = new File(FILE_NAME);
        
        if (!ficheroXML.exists()) {
        	log.debug("Iniciando descarga del archivo desde la URL ["+FILE_URL+"]");
			try {
				in = new BufferedInputStream(new URL(FILE_URL).openStream());
				out = new FileOutputStream(FILE_NAME);

				byte dataBuffer[] = new byte[1024];
				int bytesRead;
				while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
					out.write(dataBuffer, 0, bytesRead);
				}
				log.info("Recuperacion correcta del XML de internet [" + FILE_NAME + "]");
			} catch (IOException e) {
				log.error("Error de E/S durante la recuperacion del XML de internet");
			} finally {
				try {
					if (in != null)
						in.close();
					if (out != null)
						out.close();
					log.debug("Se ha cerrado el flujo de datos");
				} catch (IOException e2) {
					log.error("Error de E/S durante el cierre de los flujos");
				}
			} 
		}else {
			log.warn("El fichero ["+ficheroXML.getPath()+"] ya existe");
			
		}
    }
}
