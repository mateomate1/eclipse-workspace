package main;

import java.util.ArrayList;

import usable.Object;
import usable.Weapon;
import utils.ClassFeatures;
import utils.Dice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Character {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Character.class);
	
	protected String 
				name,
				personality_traits,
				ideals,
				bonds,
				flaws;
	protected Integer 
				level, //Varias cosas dependen del nivel(MaxHitPoints/MaxHitDice)
				xp,
				age,
				armor, //Cambio con algunos hechizos 
				initiative,
				speed,
				inspiration,
				weight=0, //Empieza con 0, se cambia al añadir cualquier objeto/arma...
				proficiencyBonus; // Este se suma cuando tienes competencia con un arma o habilidad
	
	private ArrayList<ClassFeatures> features;
	private Dice dice;
	private ArrayList<Object> objetos;//Necesario sumar el peso
	
	public ArrayList<Weapon> getWeapons(){
		ArrayList<Weapon> weapons = new ArrayList<Weapon>();
		for (Object objeto : objetos) {
			if (objeto instanceof Weapon)
				weapons.add((Weapon)objeto);
		}
		return weapons;
	}
	
	
	
}
