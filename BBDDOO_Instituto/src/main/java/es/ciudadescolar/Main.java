package es.ciudadescolar;

public class Main {
	
	public static void main(String[] args) {
		Instituto insti = new Instituto("0000");
		Alumno alumno1 = new Alumno(101, "Ana Martínez", "1º ESO", "Instituto Central", 12);
        Alumno alumno2 = new Alumno(102, "Carlos López", "2º ESO", "Instituto Norte", 13);
        Alumno alumno3 = new Alumno(103, "Beatriz Sánchez", "3º ESO", "Instituto Sur", 14);
        Alumno alumno4 = new Alumno(104, "David Gómez", 15);
        Alumno alumno5 = new Alumno(105, "Elena Fernández", 16);
        Alumno alumno6 = new Alumno(101, "Ana Martínez", "1º ESO", "Instituto Central", 12);
        insti.matricularAlumno(alumno1);
        insti.matricularAlumno(alumno2);
        insti.matricularAlumno(alumno3);
        insti.matricularAlumno(alumno4);
        insti.matricularAlumno(alumno5);
        insti.matricularAlumno(alumno6);
        
        insti.expulsarAlumno(alumno1);
        
        System.out.println(insti);
        
	}

}