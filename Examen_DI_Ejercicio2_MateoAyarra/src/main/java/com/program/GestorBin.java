package com.program;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorBin {
    // Añadimos un método para verificar o crear el archivo
    private static void verificarOCrearArchivo(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        try {
            if (!archivo.exists()) {
                archivo.createNewFile(); // Crear un archivo vacío si no existe
                System.out.println("Archivo creado: " + rutaArchivo);
            }
        } catch (IOException e) {
            System.err.println("Error al crear el archivo: " + e.getMessage());
        }
    }

    public static void add(String rutaArchivo, Usuario user) {
        verificarOCrearArchivo(rutaArchivo); // Verificar y crear el archivo si no existe
        List<Usuario> users = leer(rutaArchivo);
        users.add(user);
        escribir(rutaArchivo, users);
    }

    public static List<Usuario> leer(String rutaArchivo) {
        verificarOCrearArchivo(rutaArchivo); // Verificar y crear el archivo si no existe
        List<Usuario> users = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(rutaArchivo); 
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            try {
                while (true) {
                    Usuario user = (Usuario) ois.readObject();
                    users.add(user);
                }
            } catch (EOFException e) {
                // Fin del archivo alcanzado
            } catch (ClassNotFoundException e) {
                System.err.println("Error al deserializar el objeto: " + e.getMessage());
            }
        } catch (FileNotFoundException e) {
            System.err.println("El archivo no existe: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return users;
    }

    public static void escribir(String rutaArchivo, List<Usuario> users) {
        verificarOCrearArchivo(rutaArchivo); // Verificar y crear el archivo si no existe
        try (FileOutputStream fos = new FileOutputStream(rutaArchivo); 
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for (Usuario user : users) {
                oos.writeObject(user);
            }
            System.out.println("Usuarios escritos en el archivo correctamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public static boolean eliminarPorPosicion(String rutaArchivo, int posicion) {
        List<Usuario> users = leer(rutaArchivo);

        if (posicion < 0 || posicion >= users.size()) {
            System.err.println("Posición fuera de rango: " + posicion);
            return false;
        }

        users.remove(posicion);
        escribir(rutaArchivo, users);
        return true;
    }

    public static void vaciar(String rutaArchivo) {
        verificarOCrearArchivo(rutaArchivo); // Verificar y crear el archivo si no existe
        try (FileOutputStream fos = new FileOutputStream(rutaArchivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            System.out.println("El archivo ha sido vaciado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al vaciar el archivo: " + e.getMessage());
        }
    }
}
