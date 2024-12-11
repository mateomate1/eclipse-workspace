package es.ciudadescolar;

public class SQLScripts {
	public static final String 
	GETPIZZAS =
			"SELECT i.nombre_ingrediente " 
			+ "FROM ingrediente as i, pizza as p, pizza_ingrediente as pi "
			+ "WHERE p.cod_pizza = pi.pizzaId AND pi.ingredienteId = i.cod_ingrediente AND p.nombre_pizza=?;",
	GETPRECIOPIZZA = 
			"SELECT getPrecioPizza(cod_pizza) AS 'precio pizza' from pizza WHERE nombre_pizza=?;",
	ADDINGREDIENTEPIZZA = 
			"CALL addIngredientePizza(?,?,175);";
}
