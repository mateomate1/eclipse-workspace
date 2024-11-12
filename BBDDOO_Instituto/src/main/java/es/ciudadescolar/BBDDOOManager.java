package es.ciudadescolar;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.db4o.ObjectContainer;

public class BBDDOOManager {
	
	private static final Logger log = LoggerFactory.getLogger(BBDDOOManager.class);
	private ObjectContainer db;
	private File ficheroDB;
}
