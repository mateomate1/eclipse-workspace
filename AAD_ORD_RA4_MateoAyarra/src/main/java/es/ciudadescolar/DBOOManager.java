package es.ciudadescolar;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.db4o.*;
import com.db4o.activation.*;
import com.db4o.cluster.*;
import com.db4o.collections.*;
import com.db4o.config.*;
import com.db4o.config.annotations.*;
import com.db4o.config.annotations.reflect.*;
import com.db4o.config.encoding.*;
import com.db4o.consistency.*;
import com.db4o.constraints.*;
import com.db4o.cs.*;
import com.db4o.cs.caching.*;
import com.db4o.cs.config.*;
import com.db4o.cs.foundation.*;
import com.db4o.cs.internal.*;
import com.db4o.cs.internal.caching.*;
import com.db4o.cs.internal.config.*;
import com.db4o.cs.internal.events.*;
import com.db4o.cs.internal.messages.*;
import com.db4o.cs.internal.objectexchange.*;
import com.db4o.cs.monitoring.*;
import com.db4o.cs.ssl.*;
import com.db4o.defragment.*;
import com.db4o.diagnostic.*;
import com.db4o.drs.*;
import com.db4o.drs.db4o.*;
import com.db4o.enhance.*;
import com.db4o.events.*;
import com.db4o.ext.*;
import com.db4o.filestats.*;
import com.db4o.foundation.*;
import com.db4o.instrumentation.*;
import com.db4o.instrumentation.ant.*;
import com.db4o.instrumentation.api.*;
import com.db4o.instrumentation.bloat.*;
import com.db4o.instrumentation.classfilter.*;
import com.db4o.instrumentation.core.*;
import com.db4o.instrumentation.file.*;
import com.db4o.instrumentation.main.*;
import com.db4o.instrumentation.util.*;
import com.db4o.internal.*;
import com.db4o.internal.activation.*;
import com.db4o.internal.btree.*;
import com.db4o.internal.btree.algebra.*;
import com.db4o.internal.caching.*;
import com.db4o.internal.callbacks.*;
import com.db4o.internal.classindex.*;
import com.db4o.internal.cluster.*;
import com.db4o.internal.collections.*;
import com.db4o.internal.config.*;
import com.db4o.internal.convert.*;
import com.db4o.internal.convert.conversions.*;
import com.db4o.internal.delete.*;
import com.db4o.internal.diagnostic.*;


public class DBOOManager implements Serializable{
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(DBOOManager.class);
	
	private final String file = "competiciones.db4o";
	private final File fichero = new File(file);
	private ObjectContainer db;
	
	public DBOOManager() {
		if(!fichero.exists())
			try {
				fichero.createNewFile();
				log.warn("No existe archivo de la BDOO creando archivo");
			} catch (IOException e) {
				log.error("Error creando el archivo");
			}
		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), file);
	}
	
	public boolean altaTransaccional(List<Participante> participantes) {
		Participante example = new Participante();
		ObjectSet<?> participantesBD = db.queryByExample(example);
		System.out.println(participantesBD.hasNext());
		while (participantesBD.hasNext()) {
			Participante match = (Participante) participantesBD.next();
			for (Participante participante : participantes) {
				if(participante.equals(match))
					return false;
			}
		}
//		for (int i=0; i<participantesBD.size();i++) {
//			System.out.println(participantesBD.get(i).toString());
//			Participante match = (Participante) participantesBD.get(i);
//			for (Participante participante : participantes) {
//				if(participante.equals(match))
//					return false;
//			}		
//		}
		db.store(participantes.get(0));
		db.commit();
		log.info("Transaccion realizada con exito se procede a hacer commit");
		return true;
	}
	
	public void cerrar() {
		db.close();
	}
}
