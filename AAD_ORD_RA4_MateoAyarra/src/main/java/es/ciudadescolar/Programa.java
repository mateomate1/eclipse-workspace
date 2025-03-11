package es.ciudadescolar;

import java.util.ArrayList;
import java.util.List;

public class Programa {
	public static void main(String[] args) {
		DBOOManager db = new DBOOManager();
		Participante p1 = new Participante("Juan Díaz", "España", 22);
		Participante p2 = new Participante("Diego Sanz", "Alemania", 28);
		List<Participante> participantes = new ArrayList<Participante>();
		db.altaTransaccional(participantes);
		db.cerrar();
	}
}
