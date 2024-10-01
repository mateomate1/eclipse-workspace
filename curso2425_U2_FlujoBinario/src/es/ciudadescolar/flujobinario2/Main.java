package es.ciudadescolar.flujobinario2;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		Alumno[] alumnos = {new Alumno("1", "Paco", "aaa"), new Alumno("2", "Manolo", "bbb"), new Alumno("3",  "Fermin", "ccc")};
		File fichero = new File("alumnos.dat");
		FlujoBinario2 fb = new FlujoBinario2(fichero);
		
		fb.escribirAlumnos(alumnos);//serializamos objetos de la clase alumnos
		
		fb.leerAlumnos();//deserializamos objetos de la clase alumno
		
	}

}
