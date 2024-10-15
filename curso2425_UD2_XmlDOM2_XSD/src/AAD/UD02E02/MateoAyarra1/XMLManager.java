package AAD.UD02E02.MateoAyarra1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLManager {
	public static void procesarXML(File fichero) {
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		dbf.setValidating(false);// No validamos contra DTD
		
		//Validacion contra Schema (XSD)
		dbf.setNamespaceAware(true);
		
		File ficheroXSD = new File("cve_1.0.xsd");
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		
		Schema schema = null;
		
		dbf.setIgnoringElementContentWhitespace(true);
		
		try {
			
			schema = sf.newSchema(ficheroXSD);
			
			dbf.setSchema(schema);
			
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document documento = db.parse(fichero);
			
			Element raiz = documento.getDocumentElement();
			
			System.out.println("Se dispone de "+ raiz.getChildNodes().getLength()+" nodos");
			
			NodeList Nodos = raiz.getChildNodes();
			
		} catch (SAXException | ParserConfigurationException| IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static ArrayList<Vulnerabilidad> traductor(File fichero) {
		ArrayList<Vulnerabilidad> vulnerabilidades = new ArrayList<Vulnerabilidad>();
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		dbf.setValidating(false);// No validamos contra DTD
		
		//Validacion contra Schema (XSD)
		dbf.setNamespaceAware(true);
		
		File ficheroXSD = new File("cve_1.0.xsd");
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		
		Schema schema = null;
		
		dbf.setIgnoringElementContentWhitespace(true);
		
		try {
			
			schema = sf.newSchema(ficheroXSD);
			
			dbf.setSchema(schema);
			
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document documento = db.parse(fichero);
			
			Element raiz = documento.getDocumentElement();
			
			System.out.println("Se dispone de "+ raiz.getChildNodes().getLength()+" nodos");
			
			NodeList Nodos = raiz.getChildNodes();
			
			for(int i=0; i<Nodos.getLength(); i++) {
				Node nodo = Nodos.item(i);
				Vulnerabilidad vulnerabilidad = new Vulnerabilidad();
				if (nodo.getNodeType() == Node.ELEMENT_NODE) {
					Element Evulnerabilidad = (Element) nodo;
					Evulnerabilidad.getNamespaceURI();
					vulnerabilidad.setName(Evulnerabilidad.getAttribute("name"));
					vulnerabilidad.setSeq(Evulnerabilidad.getAttribute("seq"));
					vulnerabilidad.setType(Evulnerabilidad.getAttribute("type"));
					
					NodeList subnodos = Evulnerabilidad.getChildNodes();
					
					for(int j=0; j<subnodos.getLength(); j++) {
						Node subnodo = subnodos.item(j);
						if (subnodo.getNodeType() == Node.ELEMENT_NODE ) {
							if (subnodo.getNodeName() == "status") {
								vulnerabilidad.setStatus(subnodo.getTextContent());
							} else if (subnodo.getNodeName() == "desc") {
								vulnerabilidad.setDesc(subnodo.getTextContent());
							} 
						}
					}
					
				}
				vulnerabilidades.add(vulnerabilidad);
			}
			
		} catch (SAXException | ParserConfigurationException| IOException e) {
			System.out.println(e.getMessage());
		}
		return vulnerabilidades;
	}
}
