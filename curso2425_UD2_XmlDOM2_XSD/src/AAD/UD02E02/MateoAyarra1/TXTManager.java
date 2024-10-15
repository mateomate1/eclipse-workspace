package AAD.UD02E02.MateoAyarra1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TXTManager {
	public static void volcado(File fichero, ArrayList<Vulnerabilidad> vulnerabilidades) throws IOException {
//		try (BufferedWriter ficheroWriter = new BufferedWriter(new FileWriter(fichero))){
//			for (Vulnerabilidad vulnerabilidad : vulnerabilidades) {
//				ficheroWriter.write(vulnerabilidad.toString());
//				ficheroWriter.newLine();
//			}
//		} catch (IOException e) {
//			System.out.println(e.getMessage());
//			
//		}
		BufferedWriter ficheroWriter = new BufferedWriter(new FileWriter(fichero));
		for (Vulnerabilidad vulnerabilidad : vulnerabilidades) {
			ficheroWriter.write(vulnerabilidad.toString());
			ficheroWriter.newLine();
		}
	}
}
