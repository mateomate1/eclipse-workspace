package es.ciudadescolar.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XMLManager {
	
	public static List<Alumno> procesarXML(File fichero){
		List<Alumno> alumnos = new ArrayList<>();
		
		Alumno alumno = null;
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(true);// El XML se validara contra un DTD
		
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document documento = db.parse(fichero);// Importar el Document de w3c.dom
			
			Element raiz = documento.getDocumentElement();// Importar el Element de w3c.dom
			//Recuperamos el elemento raiz del XML que se llama alumnos
			
		} catch (ParserConfigurationException e) {
			System.out.println("Error: "+e.getMessage());
		} catch (SAXException e) {
			System.out.println("Error: "+e.getMessage());
		} catch (IOException e) {
			System.out.println("Error: "+e.getMessage());
		}
		
		return null;
	}
}
