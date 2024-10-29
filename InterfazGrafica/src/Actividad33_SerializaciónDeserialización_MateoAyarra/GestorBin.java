package Actividad33_SerializaciónDeserialización_MateoAyarra;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class GestorBin {
	// Cambiamos el tipo de Object a Alumno
	public static void add(String rutaArchivo, Alumno alumno) {
		List<Alumno> alumnos = leer(rutaArchivo);
		alumnos.add(alumno);
		escribir(rutaArchivo, alumnos);
	}

	public static List<Alumno> leer(String rutaArchivo) {
		List<Alumno> alumnos = new ArrayList<>();

		// Leer los objetos existentes del archivo
		try {
			FileInputStream fis = new FileInputStream(rutaArchivo);
			ObjectInputStream ois = new ObjectInputStream(fis);
			try {
				while (true) {
					Alumno alumno = (Alumno) ois.readObject(); // Cambiar a Alumno
					alumnos.add(alumno); // Agregar alumno a la lista
				}
			} catch (EOFException e) {
				// Fin del archivo alcanzado
			} catch (ClassNotFoundException e) {
				System.err.println("Error al deserializar el objeto: " + e.getMessage());
			} finally {
				ois.close();
			}
		} catch (FileNotFoundException e) {
			System.err.println("El archivo no existe: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Error al leer el archivo: " + e.getMessage());
		}

		return alumnos;
	}

	public static void escribir(String ruta, List<Alumno> alumnos) {
		try (FileOutputStream fos = new FileOutputStream(ruta); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			for (Alumno alumno : alumnos) {
				oos.writeObject(alumno); // Escribir cada alumno en el archivo
			}
			System.out.println("Alumnos escritos en el archivo correctamente.");
		} catch (IOException e) {
			System.err.println("Error al escribir en el archivo: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Error inesperado: " + e.getMessage());
		}
	}

	public static boolean eliminarPorPosicion(String rutaArchivo, int posicion) {
		List<Alumno> alumnos = leer(rutaArchivo); // Leer la lista existente de alumnos

		if (posicion < 0 || posicion >= alumnos.size()) {
			System.err.println("Posición fuera de rango: " + posicion);
			return false; // Retorna false si la posición es inválida
		}

		alumnos.remove(posicion); // Eliminar el alumno en la posición especificada
		escribir(rutaArchivo, alumnos); // Escribir la lista actualizada en el archivo
		return true; // Retorna true si la operación fue exitosa
	}

	public static void vaciar(String rutaArchivo) {
		try (FileOutputStream fos = new FileOutputStream(rutaArchivo);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			// No se escribe nada en el archivo, simplemente se abre y cierra para vaciarlo
			System.out.println("El archivo ha sido vaciado correctamente.");
		} catch (IOException e) {
			System.err.println("Error al vaciar el archivo: " + e.getMessage());
		}
	}

}
