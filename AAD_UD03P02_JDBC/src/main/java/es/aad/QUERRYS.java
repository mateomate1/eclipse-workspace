package es.aad;

public class QUERRYS {
	public static final String 
			INSERTPERSONA = "INSERT INTO Personas (Nombre, Email, FechaNacimiento) "
							+ "VALUES (?, ?, ?)",
			OBTENERREGALOXEMAIL = "{? = CALL ObtenerRegaloPorEmail(?) }";
}
