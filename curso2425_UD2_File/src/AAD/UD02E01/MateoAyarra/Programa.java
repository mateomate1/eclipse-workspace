package AAD.UD02E01.MateoAyarra;

import java.io.File;

public class Programa {

	public static void main(String[] args) {
		
		if(args.length == 1) {
			if ((new File(args[0]).exists()))
				FicherosClase.recorrerDirectorio(args[0]);
			else
				System.out.println("El directorio o la ruta no existen.");
		}
		else {
			System.out.println("Numero de parametros incorrectos. Se esperaba 1 y se han pasado "+args.length);
		}
	}

}
