package AAD.UD02E02.MateoAyarra1;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLManager {
	public static void procesarXML(File fichero) {
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		dbf.setValidating(false);// No validamos contra DTD
		
		//Validacion contra Schema (XSD)
		dbf.setNamespaceAware(true);
		
		File ficheroXSD = new File("https://cve.mitre.org/schema/cve/cve_1.0.xsd");
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
			
			NodeList NodosAlumnos = raiz.getChildNodes();
			
		} catch (SAXException | ParserConfigurationException| IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
