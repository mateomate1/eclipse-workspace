package main;

import java.util.ArrayList;

import usable.*;
import usable.Object;
import utils.ClassFeatures;
import utils.Dice;

public class Character {
	
	protected String 
				name,
				personality_traits,
				ideals,
				bonds,
				flaws;
	protected Integer 
				level,//Varias cosas dependen del nivel(MaxHitPoints/MaxHitDice)
				age,
				armor,//Cambio con algunos hechizos 
				initiative,
				speed,
				inspiration,
				weight=0,//Empieza con 0, se cambia al añadir cualquier objeto/arma...
				proficiencyBonus;// Este se suma cuando tienes competencia con un arma o habilidad
	private ArrayList<Weapon> weapons;
	private ArrayList<ClassFeatures> features;
	private Dice dice;
	private ArrayList<Object> objetos;//Necesario sumar el peso

}
