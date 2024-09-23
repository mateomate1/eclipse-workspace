package AAD;

import java.util.ArrayList;
import java.util.Scanner;

public class main {
	
	static Scanner sc =new Scanner(System.in);
	
	public static void main(String[] args) {
		int opc;
		ArrayList<pelicula> pelis = new ArrayList<>();
		do {
			switch (opc) {
			case 1 -> alta();
			case 2 -> show();
			case 3 -> System.out.println("Adios...");
			
			default ->System.out.println("Comando invalido");
			}
		} while (opc!=3);
	}

}
