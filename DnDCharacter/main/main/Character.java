package main;

import java.util.ArrayList;

import usable.*;

public class Character {
	
	protected String 
				name,
				personality_traits,
				ideals,
				bonds,
				flaws;
	protected Integer 
				age,
				armor,
				initiative,
				speed,
				inspiration,
				proficiencyBonus;// Este se suma cuando tienes competencia con un arma o habilidad
	private ArrayList<Weapon> weapons;

}
