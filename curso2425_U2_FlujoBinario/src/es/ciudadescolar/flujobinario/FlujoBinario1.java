package es.ciudadescolar.flujobinario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FlujoBinario1 {
	private String nombreFichero;
	private String abecedario = "abcdefghijklmnopqrstuvwxyz";
	
//------------------------------------Constructores-------------------------------------
	
	public FlujoBinario1(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}
	
//-----------------------------------Metodos propios de la clase-------------------------
	
	public String getNombreFichero() {
		return nombreFichero;
	}
	public String getAbecedario() {
		return abecedario;
	}
	public void setNombreFichero(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}
	public void setAbecedario(String abecedario) {
		this.abecedario = abecedario;
	}
	
	
//----------------------------------Metodos-------------------------------------------
	
	public void crearFichero() {
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		DataOutputStream dos = null;
		
		try {
			fos = new FileOutputStream(this.nombreFichero);
			bos = new BufferedOutputStream(fos);
			dos = new DataOutputStream(bos);
			
			for (int i=0; i<abecedario.length(); i++) {
				dos.writeChar(abecedario.charAt(i));
			}
			
		}
		catch (FileNotFoundException e) {
			System.out.println("Error 404 (File not found)");
		} catch (IOException e) {
			System.out.println("Error. Problemas durante el acceso a disco");
		}
		finally {
			try {
				dos.flush();
				dos.close();
			} catch (IOException e) {
				System.out.println("Error durante el cierre del fichero");
			}
		}
		
	}
	
	public void leerFichero() {
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		
		try {
			fis = new FileInputStream(this.nombreFichero);
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);
			
			while(true) {
				char caracter = dis.readChar();
				System.out.print(caracter + " ");
			}
			
		}
		catch (EOFException e) {
			//Excepcion copntrolada, no es error, solo el final del fichero
		}
		catch (FileNotFoundException e) {
			System.out.println("Error 404 (File not found)");
		} 
		catch (IOException e) {
			System.out.println("Error acceciendo al fichero. Imposible leer");
		}
		finally {
			try {
				dis.close();
			} catch (IOException e) {
				System.out.println("Error al cerrar el fichero");
			}
		}
	}
}
