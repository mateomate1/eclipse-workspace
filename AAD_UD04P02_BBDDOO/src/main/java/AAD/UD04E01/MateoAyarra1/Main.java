package AAD.UD04E01.MateoAyarra1;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	private static final Logger log = LoggerFactory.getLogger(Main.class);

	private static final String FILE_URL = "https://datos.madrid.es/egob/catalogo/200076-4-padron.xml";
	private static final String FILE_NAME = "padron_madrid_202410.xml";

	public static void main(String[] args) {
		XMLManager.loadURLtoFile(FILE_URL, FILE_NAME);
		Map<Integer, Distrito> distritos;
		distritos = XMLManager.parseoXML();
		BBDDOOManager db = new BBDDOOManager(false);
		try {
			db.deleteAllDistritos();
			for (Map.Entry<Integer, Distrito> entry : distritos.entrySet()) {
	            System.out.println(entry.getKey() + " -> " + entry.getValue());
	            db.add(entry.getValue());
	        }
		} catch (Exception e) {
			log.error("Cancelando operacion");
			db.rollback();
		}
		for (Distrito distrito : db.getAllDistritos()) {
			log.trace(distrito.toString());
		}
		db.close();
	}
}
