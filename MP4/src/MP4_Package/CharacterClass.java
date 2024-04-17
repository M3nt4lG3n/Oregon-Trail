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

/**
* The class Character class
*/ 
public class CharacterClass {
	
	String charName;
	boolean charAlive;
	int charHealth;
	boolean charSick;
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
	public boolean isALive() {
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
	 * Set Character Alive Satus
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
			if (characters.get(i).isALive()) {
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
}
