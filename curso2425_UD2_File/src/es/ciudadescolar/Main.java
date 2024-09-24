package es.ciudadescolar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		String NombreFichero = null;
		File fichero = null;
		RandomAccessFile ficheroAD = null;
		Scanner sc = null;
		int posicion = -1;
		
		if (args.length == 1) {
			NombreFichero = args[0];
			fichero = new File(NombreFichero);
			
			if (fichero.exists()) {
				System.out.println("Fichero ["+NombreFichero+"] ya existe. Borrándolo...");
				fichero.delete();
			}
			
			try {
				ficheroAD = new RandomAccessFile(fichero, "rw");
				
				for(int i=1; i<=100; i++){
					ficheroAD.writeByte(i);
				}
				
				//Desplazamos el puntero a la posicion inicial del fichero
				ficheroAD.seek(0);
				sc = new Scanner(System.in);
				
				do {
					try {
						System.out.println("Introduzca la posicion a leer del fichero [1-" + ficheroAD.length() + "]");
						posicion = sc.nextInt();
					} catch (InputMismatchException e) {
						sc.nextLine();
					}
				} while (!(posicion <1 || posicion > ficheroAD.length()));
				
				ficheroAD.seek(posicion-1);
				System.out.println("El puntero esta en la posicion ["+ficheroAD.getFilePointer()+"]");
				System.out.println("El byte leido es ["+ficheroAD.readByte()+"]");
				System.out.println("El puntero esta en la posicion ["+ficheroAD.getFilePointer()+"]");
				
			} catch (FileNotFoundException e) {
				System.out.println("El fichero ["+NombreFichero+"] no se ha encontrado"+e.getMessage());
			}
			catch (IOException e) {
				System.out.println("Error durante la escritura del fichero ["+NombreFichero+"] "+e.getMessage());
			}
			
		}
		else {
			System.out.println("Error se esperaba un parametro con el nombre de un fichero");
		}

	}

}
