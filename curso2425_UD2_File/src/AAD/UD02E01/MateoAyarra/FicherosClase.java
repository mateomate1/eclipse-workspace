package AAD.UD02E01.MateoAyarra;

import java.io.File;

public class FicherosClase {
	
	static File fichero;
	static String salida;
	
	public FicherosClase() {
	}
	
	public static void recorrerDirectorio(String ruta) {
		fichero = new File(ruta);
		String salida="";
		
		salida+=fichero.isDirectory()?"D":"F";
		salida+="|"+fichero.getAbsolutePath()+"|";
		salida+=fichero.canRead()?"R|":"";
		salida+=fichero.canWrite()?"W|":"";
		salida+=fichero.isHidden()?"H|":"";
		
		System.out.println(salida);
		
		if (fichero.isDirectory()) 
			for (File hijo : fichero.listFiles()) {
				recorrerDirectorio(hijo.getAbsolutePath());
			}
	}
	
}
