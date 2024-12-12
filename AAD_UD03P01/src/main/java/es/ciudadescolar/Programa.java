package es.ciudadescolar;

import java.util.List;

public class Programa {
	public static void main(String[] args) {
		XMLManager XMLM = new XMLManager();
		List<Pelicula> pelis = XMLM.procesarXML();
	}
}
