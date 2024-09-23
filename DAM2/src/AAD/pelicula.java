package AAD;

public class pelicula {
	String titulo;
	String genero;
	int duracion;
	
	public pelicula(String titulo, String genero, int duracion) {
		this.titulo=titulo;
		this.genero=genero;
		this.duracion=duracion;
	}
	public String toString() {
		return "/nTitulo:"+titulo+"/nGenero"+genero+"/nDuracion:"+duracion;
	}
}
