package es.ciudadescolar;

public class Programa {

	static String Margarita = "Margarita", Barbacoa = "Barbacoa";
	
	public static void main(String[] args) {
		DBManager db = new DBManager();
		db.getIngredientes(Barbacoa);
		db.getPrecio(Margarita);
	}

}
