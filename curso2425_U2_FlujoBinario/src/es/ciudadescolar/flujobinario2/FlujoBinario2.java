package es.ciudadescolar.flujobinario2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FlujoBinario2 {
	
	private File ficheroBinario2 = null;

	public FlujoBinario2(File ficheroBinario2) {
		
		this.ficheroBinario2 = ficheroBinario2;
	}
	
	public void escribirAlumnos(Alumno[] alumnos) {
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream(ficheroBinario2);
			bos = new BufferedOutputStream(fos);
			oos = new ObjectOutputStream(bos);
			
			for(Alumno al : alumnos) {
				oos.writeObject(al);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Error 404 (File not found)");
		} catch (IOException e) {
			System.out.println("Error: "+e.getMessage());
		}
		finally {
			try {
				oos.flush();
				oos.close();
			} catch (IOException e) {
				System.out.println("Error al cerrar el fichero");
			}
		}
	}
	
	public void leerAlumnos() {
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ObjectInputStream ois = null;
		
		try {
			fis = new FileInputStream(ficheroBinario2);
			bis = new BufferedInputStream(fis);
			ois = new ObjectInputStream(bis);
			
			while (true) {
				Alumno al = (Alumno) ois.readObject();
				System.out.println(al);
			}
			
		} 
		catch (EOFException e) {
			// TODO: handle exception
		}
		catch (ClassNotFoundException e) {
		} 
		catch (IOException e) {
		}
		finally {
			try {
				ois.close();
			} catch (IOException e) {
			}
		}
	}
	
}
