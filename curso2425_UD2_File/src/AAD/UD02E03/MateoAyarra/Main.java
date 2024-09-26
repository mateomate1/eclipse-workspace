package AAD.UD02E03.MateoAyarra;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		File fichero = new File(System.getProperty("user.dir")+System.getProperty("file.separator")+"abecedario.txt");
		RandomAccessFile ficheroAD = null;
		int posicion=-1;
		
		if (fichero.exists()) {
			System.out.println("Fichero ["+fichero.getAbsolutePath()+"] ya existe. Borrándolo...");
			fichero.delete();
		}
		
		try {
			ficheroAD = new RandomAccessFile(fichero, "rw");
			for (char c = 'a'; c <= 'z'; c++) {
				ficheroAD.writeChar(c);
			}
			
			ficheroAD.seek(0);
			System.out.println("El fichero ocupa "+ficheroAD.length()+" bytes.");
			System.out.println("Antes de posicionar el puntero estamos en la posicion "+ficheroAD.getFilePointer());
			do {
				try {
					System.out.println("Introduzca el numero de letra del abecedario a leer [1-" + (ficheroAD.length()/2) + "]");
					posicion = sc.nextInt()-1;
				} catch (InputMismatchException e) {
					sc.nextLine();
				}
			} while ((posicion<0)||(posicion*2>=ficheroAD.length()));
			
			ficheroAD.seek(posicion*2);
			System.out.println("Despues de posicionar el puntero en la posicion indicada, estamos en la posicion "+ficheroAD.getFilePointer()+"="+ficheroAD.readChar());
			System.out.println("Despues de leer los bytes del Char, estamos en la posicion "+ficheroAD.getFilePointer());
			
		} 
		catch (SecurityException e) {
			// TODO: handle exception
			System.out.println("No tienes permisos");
		}
		catch (IOException e) {
			// TODO: handle exception
		}
		sc.close();
	}

}
