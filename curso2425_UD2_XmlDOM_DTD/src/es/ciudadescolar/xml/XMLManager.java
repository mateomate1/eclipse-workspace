package es.ciudadescolar.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class XMLManager {
	
	public static List<Alumno> procesarXML(File fichero){
		List<Alumno> alumnos = new ArrayList<>();
		
		Alumno alumno = null;
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(true);// El XML se validara contra un DTD
		dbf.setIgnoringElementContentWhitespace(true);// Ignorar nodos con espacios en blanco sin informacion util
		
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document documento = db.parse(fichero);// Importar el Document de w3c.dom
			
			Element raiz = documento.getDocumentElement();// Importar el Element de w3c.dom
			// Recuperamos el elemento raiz del XML: "alumnos"
			System.out.println("Se dispone de "+ raiz.getChildNodes().getLength()+" nodos");
			
			NodeList NodosAlumnos = raiz.getChildNodes();
			
			for (int i=0; i<NodosAlumnos.getLength(); i++) {
				Node nodoAlumno = NodosAlumnos.item(i);
				
				if (nodoAlumno.getNodeType() == Node.ELEMENT_NODE) {
					Element elementoAlumno = (Element) nodoAlumno;
					Node nodoExpediente = elementoAlumno.getFirstChild();//Esto devuelve el primer nodo del elemento Alumno: Expediente lo que seria (<expediente>11111</expediente>)
					Node nodoNombre = nodoExpediente.getNextSibling();//Esto nos devuelve el nodo contiguo
					Node nodoEdad = nodoAlumno.getLastChild();//Esto nos devuelve el ultimo nodo
					
					alumno = new Alumno();
					
					alumno.setNombre(nodoNombre.getFirstChild().getNodeValue());// Recoger el valor como el hijo del Nodo nombre
					alumno.setExpediente(nodoExpediente.getTextContent());// Recoger el contenido del nodo, solo funciona con elementos sin sub-elementos
					alumno.setEdad(Integer.parseInt(nodoEdad.getTextContent()));
					
					alumnos.add(alumno);
				}
			}
			
		} catch (ParserConfigurationException e) {
			System.out.println("Error: "+e.getMessage());
		} catch (SAXException e) {
			System.out.println("Error: "+e.getMessage());
		} catch (IOException e) {
			System.out.println("Error: "+e.getMessage());
		}
		
		return alumnos;
	}
	
	public static void generarNuevoXML(List<Alumno> alumnos) {
		//Lo primero es crear la estructura de arbol en la RAM (mem ppal)
		
		DocumentBuilderFactory dbf = null;
		DocumentBuilder db = null;
		Document documento = null;
		
		Element elementoAlumno = null;
		
		Attr atributoNombre,atributoExpediente,atributoEdad = null;
		
		try {
			
			dbf = DocumentBuilderFactory.newInstance();
			db = dbf.newDocumentBuilder();
			documento = db.newDocument();
			
			Element raiz = documento.createElement("estudiantes");
			Element edadAlumno = null;
			
			Text nodoTextoExpediente, nodoTextoEdad = null;
			
			documento.appendChild(raiz);
			
			for(Alumno al : alumnos) {
				elementoAlumno = documento.createElement("alumno");
				
				//Opcion 1 para anadir un atributo a un elemento
				atributoNombre = documento.createAttribute("nom");
				atributoNombre.setNodeValue(al.getNombre());
				elementoAlumno.setAttributeNode(atributoNombre);
				
				//Opcion 2
				atributoExpediente = documento.createAttribute("exp");
				nodoTextoExpediente = documento.createTextNode(al.getExpediente());
				atributoExpediente.appendChild(nodoTextoExpediente);
				elementoAlumno.setAttributeNode(atributoExpediente);
				
				//Opcion 3
				elementoAlumno.setAttribute("edad", String.valueOf(al.getEdad()));
				
				nodoTextoEdad = documento.createTextNode(String.valueOf(al.getEdad()));
				edadAlumno = documento.createElement("edad");
				
				edadAlumno.appendChild(nodoTextoEdad);
				elementoAlumno.appendChild(edadAlumno);
				
				raiz.appendChild(elementoAlumno);
			}
			
			// Volcado a fichero del arbol
			
			TransformerFactory tf = null;
			Transformer t = null;
			DOMSource ds = null;
			StreamResult sr = null;
			DOMImplementation domImp = null;
			DocumentType docType = null;
			
			try {
				tf = TransformerFactory.newInstance();
				t = tf.newTransformer();
				
				ds = new DOMSource(documento);
				
				// Opcion 1 volcado como fichero binario
				//sr = new StreamResult(new FileOutputStream("alumnos2.dtd"));//Creado de un Stream para el fichero binario
				
				//Opcion 2 volcado como fichero de texto
				sr = new StreamResult(new FileWriter("alumnos2.xml"));
				
				//Como nuestro XML de 
				domImp = documento.getImplementation();
				docType = domImp.createDocumentType("doctype", null, "alumnos2.dtd");
				
				//Se tiene que validar contra un dtd
				t.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, docType.getSystemId());
				t.setOutputProperty(OutputKeys.METHOD, "xml");
				t.setOutputProperty(OutputKeys.VERSION, "1.0");
				t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				t.setOutputProperty(OutputKeys.INDENT, "yes");
				
				t.transform(ds, sr);//Volcado en el fichero
				
			} catch (TransformerConfigurationException e) {
				System.out.println("Error: "+e.getMessage());
			} catch (FileNotFoundException e) {
				System.out.println("Error: "+e.getMessage());
			} catch (TransformerException e) {
				System.out.println("Error: "+e.getMessage());
			} catch (IOException e) {
				System.out.println("Error: "+e.getMessage());
			}
			
			
		} catch (ParserConfigurationException e) {
			System.out.println("Error: "+e.getMessage());
		}
		
	}
	
	public static List<String> recuperarExpedientes (File fichero){ // NodeList getElementByTagName(String)
		List<String> salida = new ArrayList<>();
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(true);// El XML se validara contra un DTD
		dbf.setIgnoringElementContentWhitespace(true);// Ignorar nodos con espacios en blanco sin informacion util
		
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document documento = db.parse(fichero);// Importar el Document de w3c.dom
			
			Element raiz = documento.getDocumentElement();// Importar el Element de w3c.dom
			// Recuperamos el elemento raiz del XML: "expediente"
			System.out.println("Se dispone de "+ raiz.getChildNodes().getLength()+" nodos");
			
			NodeList NodosAlumnos = raiz.getChildNodes();
			
		} catch (ParserConfigurationException e) {
			System.out.println("Error: "+e.getMessage());
		} catch (SAXException e) {
			System.out.println("Error: "+e.getMessage());
		} catch (IOException e) {
			System.out.println("Error: "+e.getMessage());
		}
		
		return salida;
	}
}
