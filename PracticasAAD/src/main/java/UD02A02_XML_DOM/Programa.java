package UD02A02_XML_DOM;

import java.io.File;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Programa {
	private static final Logger log = LoggerFactory.getLogger(Programa.class);
	
	private String xml = "https://cve.mitre.org/data/downloads/allitems.xml";
	private String xsd = "https://cve.mitre.org/schema/cve/cve_1.0.xsd";
	
	private static File ficheroXML = new File("cve_list_20240625.xml");
	private static File dicheroXSD = new File("cve_1.0.xsd");
	private static File ficheroTXT = new File("CVEs_20231025.txt");
	
	public static void main(String[] args) {
		Collection<Item> items;
		items = XmlManager.parseoXML(ficheroXML, ficheroTXT);
		TXTManager.writter(items, ficheroTXT);
	}
}
