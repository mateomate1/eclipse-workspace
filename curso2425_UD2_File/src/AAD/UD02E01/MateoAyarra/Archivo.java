package AAD.UD02E01.MateoAyarra;

import java.io.File;

public class Archivo {
	
	public String absolutePath;
	public String tipo;
	public boolean read;
	public boolean write;
	public boolean execute;
	public boolean hidden;
	
	public Archivo(String absolutePath, String tipo) {
		super();
		this.absolutePath = absolutePath;
		this.tipo = tipo;
	}
	
}
