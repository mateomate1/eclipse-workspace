package AAD.UD04E01.MateoAyarra1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Clase que maneja el analisis del archivo XML para extraer los datos relevantes de los distritos.
 * 
 * Esta clase extiende de `DefaultHandler` y se utiliza con la API SAX para procesar el contenido de un archivo XML. Extrae los datos de cada distrito del XML y los almacena en una lista de objetos `Distrito`. Los datos extraidos incluyen el codigo y la descripcion del distrito, asi como el numero de hombres y mujeres (espanoles y extranjeros) en cada distrito.
 * 
 * @author Mateo Ayarra
 * @version 2024-11-15
 */
public class DistritoDefaultHandler extends DefaultHandler {
    private static final Logger log = LoggerFactory.getLogger(DistritoDefaultHandler.class);
    
    private Map<String, Distrito> distritos;
    private Distrito distrito;

    private StringBuilder sb;
    private String key;

    private final String Cod_distrito = "COD_DISTRITO";
    private final String desc_distrito = "DESC_DISTRITO";
    private final String num_mujeresEsp = "ESPANOLESMUJERES";
    private final String num_mujeresExt = "EXTRANJEROSMUJERES";
    private final String num_hombresEsp = "ESPANOLESHOMBRES";
    private final String num_hombresExt = "EXTRANJEROSHOMBRES";
    private final String DISTRITO = "record";

    /**
     * Metodo que se ejecuta al comenzar el procesamiento del archivo XML.
     * Inicializa la lista de distritos donde se almacenaran los objetos `Distrito` procesados.
     * 
     * @throws SAXException Si ocurre un error durante el procesamiento.
     */
    @Override
    public void startDocument() throws SAXException {
        log.debug("Comenzando procesamiento del fichero XML: Inicio documento");
        distritos = new TreeMap<String, Distrito>();
    }

    /**
     * Metodo que se ejecuta al finalizar el procesamiento del archivo XML.
     * 
     * @throws SAXException Si ocurre un error durante el procesamiento.
     */
    @Override
    public void endDocument() throws SAXException {
        log.debug("Finalizando procesamiento del fichero XML: Fin de documento");
    }

    /**
     * Metodo que se ejecuta al comenzar un elemento XML.
     * 
     * Si el elemento es un `record` (distrito), se crea una nueva instancia de `Distrito`.
     * 
     * @param uri El URI del espacio de nombres (si se usa).
     * @param localName El nombre local del elemento.
     * @param qName El nombre calificado del elemento.
     * @param attributes Los atributos del elemento.
     * @throws SAXException Si ocurre un error durante el procesamiento.
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        log.debug("Comienzo del elemento [" + qName + "]");
        sb = new StringBuilder();
        if (attributes.getLength() > 0)
            for (int i = 0; i < attributes.getLength(); i++)
                log.info("Atributo [" + attributes.getQName(i) + "] = " + attributes.getValue(i));
        if (qName.equalsIgnoreCase(DISTRITO)) {
            distrito = new Distrito();
        }
    }

    /**
     * Metodo que se ejecuta al finalizar un elemento XML.
     * 
     * Dependiendo del nombre del elemento, se asignan los valores leidos al objeto `Distrito` actual.
     * Al finalizar un elemento `record`, el distrito se agrega a la lista de distritos.
     * 
     * @param uri El URI del espacio de nombres (si se usa).
     * @param localName El nombre local del elemento.
     * @param qName El nombre calificado del elemento.
     * @throws SAXException Si ocurre un error durante el procesamiento.
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        log.debug("Final del elemento [" + qName + "]");

        switch (qName) {
            case Cod_distrito -> distrito.setCod_distrito(sb.toString());
            case desc_distrito -> distrito.setDesc_distrito(sb.toString());
            case num_mujeresEsp -> distrito.setMujeresEsp(sb.toString());
            case num_mujeresExt -> distrito.setMujeresExt(sb.toString());
            case num_hombresEsp -> distrito.setHombresEsp(sb.toString());
            case num_hombresExt -> distrito.setHombresExt(sb.toString());
            case DISTRITO -> {
            	key = distrito.getCod_distrito();
            	if(distritos.containsKey(key)) {
            		Distrito old = distritos.get(key);
            		old.fuse(distrito);
            	} else
            		log.warn("Nuevo distrito encontrado, anadiendo nuevo distrito.");
            		distritos.put(distrito.getCod_distrito(), distrito);
            }
        }
    }

    /**
     * Metodo que se ejecuta para obtener los caracteres de un elemento XML.
     * 
     * Los caracteres leidos se agregan a un `StringBuilder` para su posterior procesamiento.
     * Se eliminan los espacios en blanco al final del texto.
     * 
     * @param ch El arreglo de caracteres del elemento.
     * @param start La posicion inicial en el arreglo.
     * @param length La longitud del texto a procesar.
     * @throws SAXException Si ocurre un error durante el procesamiento.
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String texto = String.valueOf(ch, start, length).trim(); // Elimina los espacios en blanco

        if (!texto.isEmpty()) // Solo imprime si no esta vacio
            log.trace("Texto localizado [" + texto + "]");

        sb.append(ch, start, length);
    }

    /**
     * Metodo que maneja los errores durante el procesamiento del XML.
     * 
     * @param e La excepcion que se ha producido durante el procesamiento.
     * @throws SAXException Si ocurre un error durante el procesamiento.
     */
    @Override
    public void error(SAXParseException e) throws SAXException {
        // TODO Auto-generated method stub
        super.error(e);
    }

    /**
     * Devuelve la lista de distritos procesados.
     * 
     * @return La lista de objetos `Distrito` extraidos del XML.
     */
    public Map<String, Distrito> getDistrito() {
        return distritos;
    }
}
