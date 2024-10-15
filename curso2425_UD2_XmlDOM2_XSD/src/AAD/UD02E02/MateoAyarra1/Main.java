package AAD.UD02E02.MateoAyarra1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		long start,end, current;
		start = System.currentTimeMillis();
		File ficheroXML = new File("allitems.xml");
		File ficheroTXT = new File("CVEs_20231025.txt");
		ArrayList<Vulnerabilidad> vulnerabilidades = XMLManager.traductor(ficheroXML);
		try {
			TXTManager.volcado(ficheroTXT, vulnerabilidades);
		} catch (IOException e) {
			System.out.println("Error");
		}
		
		end = System.currentTimeMillis();
		current = (end-start)/1000;
		System.out.println("Current time: "+current+" segundos");
	}
}
