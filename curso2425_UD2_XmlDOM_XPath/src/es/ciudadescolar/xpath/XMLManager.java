package es.ciudadescolar.xpath;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLManager {
	private static Document documento;
	
	public static boolean parsearXmlDomXsd(File xml, File xsd) {
		
		DocumentBuilderFactory dbf = null;
		DocumentBuilder db = null;
		
		SchemaFactory sf = null;
		Schema schema = null;
		
		dbf = DocumentBuilderFactory.newInstance();
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		
		sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		
		try {
			schema = sf.newSchema(xsd);
			dbf.setSchema(schema);
			db = dbf.newDocumentBuilder();
			
			documento = db.parse(xml);
			
		} catch (SAXException | ParserConfigurationException | IOException e) {
			System.out.println("Error: "+e.getMessage());
			return false;
		}
		
		return true;
	}
	
	public static Alumno getAlumnoExpediente(String expediente) {
		XPathFactory xf = XPathFactory.newInstance();
		XPath xpath = xf.newXPath();
		
		Alumno alumno = null;
		
		String expresion = "/alumnos/alumno[expediente='"+expediente+"']";
		
		try {
			Node nodoAlumno = (Node) xpath.evaluate(expresion, documento, XPathConstants.NODE);
			Element elementAlumno = (Element) nodoAlumno;
			
			String exp = nodoAlumno.getChildNodes().item(0).getTextContent();
			String nom = nodoAlumno.getChildNodes().item(1).getTextContent();
			int edad = Integer.parseInt(nodoAlumno.getChildNodes().item(2).getTextContent());
			String curso = elementAlumno.getAttribute("curso");
			String instituto = elementAlumno.getAttribute("IES");
			System.out.println(instituto);
			
			alumno = new Alumno(expediente, nom, curso, instituto, edad);
			
		} catch (XPathExpressionException e) {
			System.out.println("Error: "+e.getMessage());
		}
		
		return alumno;
	}
	
	public static String getAlumnoNombre(String expediente) {
		
		XPathFactory xf = XPathFactory.newInstance();
		XPath xpath = xf.newXPath();
		
		Alumno alumno = null;
		
		String expresion = "/alumnos/alumno[expediente='"+expediente+"']";
		String nombreAlumno;
		
		try {
			Node nodoNombre = (Node) xpath.evaluate(expresion, documento, XPathConstants.NODE);
			
			if(nodoNombre != null) {
				nombreAlumno = nodoNombre.getTextContent();
			}
			
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static List<Alumno> getAlumnosCurso(String curso){
		
		XPathFactory xf = XPathFactory.newInstance();
		XPath xpath = xf.newXPath();
		
		List<Alumno> alumnos = new ArrayList<Alumno>();
		
		String expresion = "/alumnos/alumno[expediente='"+curso+"']";
		
		try {
			NodeList listaNodos = (NodeList) xpath.evaluate(expresion, documento, XPathConstants.NODESET);
			
			for(int i=0; i<listaNodos.getLength(); i++) {
				Node nodoAlumno = listaNodos.item(i);
				
				if(nodoAlumno.getNodeType() == Node.ELEMENT_NODE) {
					Element elementAlumno = (Element) nodoAlumno;
					
					String exp = nodoAlumno.getChildNodes().item(0).getTextContent();
					String nom = nodoAlumno.getChildNodes().item(1).getTextContent();
					int edad = Integer.parseInt(nodoAlumno.getChildNodes().item(2).getTextContent());
					String instituto = elementAlumno.getAttribute("IES");
					System.out.println(instituto);
					
					Alumno alumno = new Alumno(exp, nom, curso, instituto, edad);
					alumnos.add(alumno);
				}
			}
			
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return alumnos;
	}
	
}
