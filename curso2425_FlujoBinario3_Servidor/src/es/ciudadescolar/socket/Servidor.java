package es.ciudadescolar.socket;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	private final int PUERTO = 4444;
	private ServerSocket serversocket = null;
	private Socket clientesocket = null;
	
	public Servidor() {
		try {
			serversocket = new ServerSocket(PUERTO);//socket servidor
			clientesocket = new Socket();//socket cliente
		} catch (IOException e) {
			System.out.println("Error en el establecimiento de la conexion");
		}
	}
	
	public void arrancarServidor() {
		System.out.println("Esperando peticiones del cliente...");
		try {
			
			clientesocket = serversocket.accept();
			System.out.println("Cliente en linea...");
			
			Alumno[] alumnosRecibidos = obtenerAlumnos();
			
			for (Alumno alumno : alumnosRecibidos) {
				System.out.println(alumno);
			}
			
			System.out.println("Finalizando comunicacion con el cliente");
			clientesocket.close();
			serversocket.close();
			
		} catch (IOException e) {
			System.out.println("Error estableciendo comunicacion con el servidor...");
		}
	}

	public Alumno[] obtenerAlumnos() {
		
		InputStream is = null;
		BufferedInputStream bis = null;
		ObjectInputStream ois = null;
		
		Alumno[] alumnos = null;
		
		try {
			is = clientesocket.getInputStream();
			bis = new BufferedInputStream(is);
			ois = new ObjectInputStream(bis);
			
			Object objeto = ois.readObject();
			alumnos = (Alumno[])objeto;
			
			ois.close();
			
		} catch (ClassNotFoundException e) {
			System.out.println("Error en el formato de la informacion...");
		} catch (IOException e) {
			System.out.println("Error recibiendo el listado de alumnos...");
		} 
		
		return alumnos;
	}
	
}
