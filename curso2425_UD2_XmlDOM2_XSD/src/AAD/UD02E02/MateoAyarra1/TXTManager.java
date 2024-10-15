package AAD.UD02E02.MateoAyarra1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TXTManager {
	public static void volcado(File fichero, ArrayList<Vulnerabilidad> vulnerabilidades) {
		try (BufferedWriter ficheroWriter = new BufferedWriter(new FileWriter(fichero))){
			ficheroWriter.write("Hola");
			ficheroWriter.newLine();
			for (Vulnerabilidad vulnerabilidad : vulnerabilidades) {
				ficheroWriter.write(vulnerabilidad.toString());
				ficheroWriter.newLine();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			
		}
	}
}
