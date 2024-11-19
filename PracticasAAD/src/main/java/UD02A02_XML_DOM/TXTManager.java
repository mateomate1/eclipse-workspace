package UD02A02_XML_DOM;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TXTManager {
	
	private static final Logger log = LoggerFactory.getLogger(TXTManager.class);
	
	public static void writter(Collection<Item> items, File salida) {
		BufferedWriter bw = null;
		try {
			log.debug("Iniciando volcado a fichero:");
			bw = new BufferedWriter(new FileWriter(salida));
			for (Item item : items) {
				bw.append(item.toString());
				bw.newLine();
			}
			log.debug("Fin de volcado a fichero");
		} catch (IOException e) {
			log.error("Error volcando a fichero");
		}
	}
}
