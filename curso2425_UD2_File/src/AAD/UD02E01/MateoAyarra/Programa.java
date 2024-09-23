package AAD.UD02E01.MateoAyarra;

import java.io.File;
import java.io.IOException;

public class Programa {

	public static void main(String[] args) {
		File fichero = new File(System.getProperty("user.dir")+System.getProperty("file.separator")+"mateo.txt");
		try {
			if (!fichero.createNewFile()) {
				
			}
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}
