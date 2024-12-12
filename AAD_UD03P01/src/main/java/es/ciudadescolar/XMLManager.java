package es.ciudadescolar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
	
	private static final String XMLPath = "peliculas.xml", XSDPath = "peliculas.xsd";
	
	
	public static List<Pelicula> procesarXML(){
		List<Pelicula> pelis = new ArrayList<Pelicula>();
		
		Pelicula p = new Pelicula();
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		dbf.setValidating(false);// No validamos contra DTD
		
		//Validacion contra Schema (XSD)
		dbf.setNamespaceAware(true);
		
		File ficheroXML = new File(XMLPath);
		File ficheroXSD = new File(XSDPath);
		
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		
		Schema schema = null;
		
		dbf.setIgnoringElementContentWhitespace(true);
		
		try {
			schema = sf.newSchema(ficheroXSD);
			dbf.setSchema(schema);
			
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document documento = db.parse(ficheroXML);
			
			Element raiz = documento.getDocumentElement();
			
			NodeList NodosPelis = raiz.getChildNodes();
			
			for (int i = 0; i < NodosPelis.getLength(); i++) {
				Node nodoPeli = NodosPelis.item(i);
				
				Pelicula peli = new Pelicula();
				
				if(nodoPeli.getNodeType() == Node.ELEMENT_NODE) {
					Element elementPeli = (Element) nodoPeli;
					Node nodeTitulo = elementPeli.getFirstChild();
					Node nodeAnio = nodeTitulo.getNextSibling();
					Node NodosActores = nodeAnio.getNextSibling();
					
					
					peli.setTitulo(nodeTitulo.getTextContent());
					peli.setAnio((int)Integer.parseInt(nodeAnio.getTextContent()));
					
					Map<Boolean, Actor> actores = new TreeMap<Boolean, Actor>();
					
					if (NodosActores.getNodeType() == Node.ELEMENT_NODE) {
						Element elemetActores = (Element) NodosActores.getChildNodes();
						NodeList nodosActores = elemetActores.getChildNodes();
						for (int j = 0; j < nodosActores.getLength(); j++) {
							Node nodoActor = nodosActores.item(j);
							if (nodoActor.getNodeType() == Node.ELEMENT_NODE) {
								Element elementoActor = (Element) nodoActor;
								elementoActor.getNamespaceURI();
								
								Boolean protagonista = Boolean.parseBoolean(elementoActor.getAttribute("protagonista"));
								Actor actor = new Actor();
								
								Node nodoNombre = elementoActor.getFirstChild();
								Node nodoApellido = nodoNombre.getNextSibling();
								
								actor.setNombre(nodoNombre.getTextContent());
								actor.setApellido(nodoApellido.getTextContent());
								
								actores.put(protagonista, actor);
							}
						}
					}
					peli.setActores(actores);
				}
				pelis.add(peli);
				System.out.println(peli);
			}
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 */
		return pelis;
	}
}
