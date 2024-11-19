package UD02A02_XML_DOM;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlManager {
	private static final Logger log = LoggerFactory.getLogger(XmlManager.class);
	
	private Document documento;
	
	public static Collection<Item> parseoXML(File XML, File XSD) {
		Collection<Item> items = null;
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		dbf.setIgnoringElementContentWhitespace(true);
		
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = null;
		
		try {
			schema = sf.newSchema(XSD);
			dbf.setSchema(schema);
			
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document documento = db.parse(XML);
			
			Element raiz = documento.getDocumentElement();//Elemento raiz
			NodeList nodos = raiz.getChildNodes(); //Elementos Item
			log.debug("Elemento raiz recuperado");
			
			for (int i = 0; i < nodos.getLength(); i++) {
				Node nodo = nodos.item(i); //Elemento item
				Item item = new Item();
				if(nodo.getNodeType() == Node.ELEMENT_NODE) {
					Element NodoItem = (Element) nodo;// Atributos del nodo
					NodoItem.getNamespaceURI();
					item.setNombre(NodoItem.getAttribute("name"));
					item.setSeq(NodoItem.getAttribute("seq"));
					item.setType(NodoItem.getAttribute("type"));
					
					NodeList subnodos = NodoItem.getChildNodes();
					
					for (int j = 0; j < nodos.getLength(); j++) {
						Node subnodo = nodos.item(j);
						if(subnodo.getNodeType() == Node.ELEMENT_NODE) {
							if(subnodo.getNodeName() == "status") {
								item.setStatus(subnodo.getTextContent());
							} else if (subnodo.getNodeName() == "desc") {
								item.setDesc(subnodo.getTextContent());
							}
						}
					}
					
				}
				items.add(item);
				log.debug("Nuevo item");
			}
			
		} catch (SAXException | ParserConfigurationException | IOException e) {
			log.error("Error linea "+e.getMessage());
		}
		
		return items;
	}
	
}
