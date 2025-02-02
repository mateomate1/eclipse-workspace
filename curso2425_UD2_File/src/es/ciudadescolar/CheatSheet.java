package es.ciudadescolar;

import java.io.File;
import java.io.IOException;

public class CheatSheet {

	public static void main(String[] args) {
		System.out.println(System.getProperty("user.name"));
		System.out.println(System.getProperty("user.dir"));
		System.out.println(System.getProperty("user.home"));
		System.out.println(System.getProperty("os.name"));
		System.out.println(System.getProperty("file.separator"));
		System.out.println(System.getProperty("java.version"));
		
		File fichero = new File(System.getProperty("user.dir")+System.getProperty("file.separator")+"mateo.txt");
		
		try {
			if (!fichero.createNewFile()) {
				System.out.println("El fichero ["+fichero.getPath()+"] ya existe");
			}
			else {
				System.out.println("Se ha creado fichero ["+fichero.getPath()+"]");
			}
		} catch (IOException e) {
			//aqui se liberan recursos, se vuelca informacion de error en un log.
			System.out.println(e.getMessage());
		}
		
		File directorio = new File(System.getProperty("user.dir")+System.getProperty("file.separator")+"carpeta");
		
		if (!directorio.mkdir()) {
			System.out.println("Imposible crear el directorio ["+directorio.getPath()+"]");
		} 
		else {
			System.out.println("Se ha creado el directorio ["+directorio.getPath()+"]");
		}
		if(!fichero.delete())
			System.out.println("Imposible borrar el fichero ["+fichero.getPath()+"]");
		else
			System.out.println("Se ha borrado el fichero ["+fichero.getPath()+"]");
		if(!directorio.delete())
			System.out.println("Imposible borrar el directorio ["+fichero.getPath()+"]");
		else
			System.out.println("Se ha borrado el directorio ["+fichero.getPath()+"]");
		
		/*
		 * Visualizar las propiedades de un fichero y el tamaño en bytes
		 */
		
		
	}

}
