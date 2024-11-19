package pkt;

import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * La clase DistritosDefaultHandler, se encarga de crear objetos distritos y meterlos en una lista, a partir del xml
 * @author ISC
 * @version 2024.11.15
 */

public class DistritosDefaultHandler extends DefaultHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
	
	private TreeMap<String,Distrito> distritos;
	private Distrito distrito;
	
	private StringBuilder sb; //la usaremos para guardar el texto recuperado con cada 

	private final String record = "record"; //conbstante que representa a Item en el xml
	private final String cod_distrito = "COD_DISTRITO";
	private final String desc_distrito = "DESC_DISTRITO";
	private final String num_mujeres_exp = "EXTRANJEROSMUJERES";
	private final String num_mujeres_esp = "ESPANOLESMUJERES";
	private final String num_hombres_exp = "EXTRANJEROSHOMBRES";
	private final String num_hombres_esp = "ESPANOLESHOMBRES";
	
	
	@Override
	public void startDocument() throws SAXException {
		LOGGER.info("Comenzando el procesamiento del XML: nuevo documento");
		distritos = new TreeMap<String,Distrito>();
	}
	
	@Override
	public void endDocument() throws SAXException {
		LOGGER.debug("Finalizando el procesamiento del XML: fin del documento");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		LOGGER.debug("Comienzo del elemento["+qName+"]");
		sb = new StringBuilder();
		if (record.equals(qName)) {
            distrito = new Distrito();
        }
		
		
		
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
		LOGGER.debug("Fin del elemento["+qName+"]");
		try {
			switch (qName) {
			
				case cod_distrito: //lo pilla
					distrito.setCod_distrito(sb.toString());
					break;
				case desc_distrito:
					distrito.setDesc_distrito(sb.toString()); //lo pilla
					break;
				case num_hombres_exp, num_hombres_esp: //lo pilla
					distrito.setNum_hombres(distrito.getNum_hombres() + Integer.valueOf(sb.toString()));
					break;
				case num_mujeres_exp, num_mujeres_esp: //lo pilla
					distrito.setNum_mujeres(distrito.getNum_mujeres() + Integer.valueOf(sb.toString()));
					break;
				case record:
					String key = distrito.getCod_distrito();
					if(distritos.containsKey(key)) {
						LOGGER.warn("Fusionando distrito");
						distritos.get(key).setNum_hombres(distritos.get(key).getNum_hombres()+distrito.getNum_hombres());
						distritos.get(key).setNum_mujeres(distritos.get(key).getNum_mujeres()+distrito.getNum_mujeres());
					}
					else {
						distritos.put(distrito.getCod_distrito(), distrito);
					}
					
				}
		} 
		catch (NumberFormatException e) {		}
	}
	
	@Override
	public void error(SAXParseException e) throws SAXException {
		LOGGER.error("Se ha detectado un error en el procesamiento del Xml: "+e.getMessage());
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		LOGGER.debug("Texto locacalizado["+String.valueOf(ch,start,length)+"]");
		sb.append(ch,start, length);
	}
	
	public TreeMap<String,Distrito> getDistritos(){
		return distritos;
	}
}
