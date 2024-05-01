/*
 * File: CharacterClass.java
 * Author: Anthony Bellino
 * Date: 4/9/2024
 * Version: 1.0.0 
 */
package MP4_Package;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

/**
* The class Character class
*/ 
public class CharacterClass {
	
	String charName;
	boolean charAlive;
	int charHealth;
	boolean charSick;
	private int consumptionRate=3;
	public static int numberOfPeople = 4;
	static ArrayList<CharacterClass> characters = new ArrayList<CharacterClass>();

	/** 
	 *
	 * It is a constructor. 
	 *
	 * @param name  the name. 
	 * @param isAlive  the is alive. 
	 * @param health  the health. 
	 * @param isSick  the is sick. 
	 */
	public CharacterClass(String name, boolean isAlive, int health, boolean isSick) {
		charName = name;
		charAlive = isAlive;
		charHealth = health;
		charSick = isSick;
	}

	/** 
	 *
	 * Is Character Alive
	 *
	 * @return boolean charAlive
	 */
	public boolean isAlive() {
		return charAlive;
	}

	/** 
	 *
	 * Is Character Sick
	 *
	 * @return boolean charSick
	 */
	public boolean isSick() {
		return charSick;
	}

	/** 
	 *
	 * Character Health Value
	 *
	 * @return int charHealth
	 */
	public int returnHealth() {
		return charHealth;
	}

	/** 
	 *
	 * Character Name
	 *
	 * @return int charName
	 */
	public String returnName() {
		return charName;
	}

	/** 
	 *
	 * Set Character Alive Status
	 *
	 */
	public void setAlive(boolean isAlive) {
		charAlive = isAlive;
	}

	/** 
	 *
	 * Set Character Sick Status
	 *
	 */
	public void setSick(boolean isSick) {
		charSick = isSick;
	}

	/** 
	 *
	 * Set Character Health number
	 *
	 */
	public void setHealth(int health) {
		charHealth = health;
	}

	/** 
	 *
	 * Set Character Name
	 *
	 */
	public void setName(String name) {
		charName = name;
	}
	
	/** 
	 *
	 * Gets the amount of people
	 *
	 * @return the amount of people
	 */
	public static int getAmountOfPeople() {
		for(int i = 0; i < characters.size(); i++) {
			if (characters.get(i).isAlive()) {
				numberOfPeople++;
			}
		}
		return numberOfPeople;
	}
	
	/** 
	 *
	 * Sets the characters
	 *
	 */
	public void setCharacters() {
		//Opening the CSV File and setting up the input scanner
		InputStreamReader in = new InputStreamReader(this.getClass().getResourceAsStream("/MP4_Package/People.csv"));
		Scanner scr = new Scanner(in);
		//Loop through all of the items in the csv file
		//Get each value without the commas and put them into a object variable
		//set that full object into an arrayList
		while(scr.hasNext()) {
			System.out.println("Reading in");
			Scanner reader = new Scanner(scr.next());
			//scr.nextLine();
			reader.useDelimiter(",");
			String name = reader.next();
			String tempIsAlive = reader.next();
			boolean IsAlive = Boolean.parseBoolean(tempIsAlive);
			String tempHealth = reader.next();
			int health = Integer.parseInt(tempHealth);
			String tempIsSick = reader.next();
			boolean isSick = Boolean.parseBoolean(tempIsSick);
			CharacterClass character = new CharacterClass(name, IsAlive, health, isSick);
			characters.add(character);
			reader.close();
		}
		System.out.println(characters);
	}
	public void charDeath(int health) {
		if(health<=0) {
			setAlive(false);
		}
	}
	public void cureSickness() {
		
		Random rand = new Random();
		int chance = rand.nextInt(5)+1;
		if(isSick()==true) {
			if(chance==1) {
				setSick(false);
			}
		}
	}
	
	public int healthChange(int health) {
		if(isSick()==true) {
			health--;
		}
		if(consumptionRate==3) {
			health++;
		}
		if(consumptionRate==1) {
			health--;
		}
		return health;
	}
	
	public void randomHealthEvents() {
		RandomEvent randomEvent = new RandomEvent();
		if(randomEvent.getSickness()==true) {
			setSick(true);
		}
		if(randomEvent.getSnakeBite()==true) {
			charHealth=charHealth-3;
		}
		if(randomEvent.getBrokenLimb()==true) {
			charHealth=charHealth-3;
		}
	}
	public void runCharClass() {
		Random rand = new Random();
		int num = rand.nextInt(getAmountOfPeople());
		characters.get(num).randomHealthEvents();
		for(int i=0;i<getAmountOfPeople();i++) {
			characters.get(i).healthChange(characters.get(i).returnHealth());
			characters.get(i).cureSickness();
			characters.get(i).charDeath(characters.get(i).returnHealth());
		}
	}
}
