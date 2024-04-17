/*
 * File: Events.java
 * Author: Anthony Bellino
 * Date: 4/17/2024
 * Version: 1.0.0
 */
package MP4_Package;

import java.util.Random;

public class Events {
	/** 
	 *
	 * Gets the river length
	 *
	 * @return the river length
	 */
	//Returns a random River Depth to be used
	public int getRiverDepth() 
	{
		Random rand = new Random();
		int depth= rand.nextInt(15)+1;
		return depth;
	}

	//Returns a random River Length to be used
	public int getRiverLength() 
	{
		Random rand = new Random();
		int length= rand.nextInt(200)+20;
		return length;
	}
	/** 
	 *
	 * Gets the river length
	 *
	 * @return the river length
	 */
	//Gives a random number which determines flow
	public int getRiverFlow() 
	{
		Random rand = new Random();
		int flow = rand.nextInt(15) + 1; //Arbitrary Level
		return flow;
		
	}

	/** 
	 *
	 * River ford
	 *
	 * @param riverDepth  the river depth. 
	 * @return boolean
	 */
	// returns whether or not attempt to cross the river was safe for failed by walking
	public boolean riverFord(int riverDepth) {
		boolean safeCross;
		Random rand = new Random();
		int chance = rand.nextInt(100);
		if((riverDepth)<= 3) {
			if(chance>20) {
				safeCross=true;
			}
			else {
				safeCross=false;
			}
		}
		else if(riverDepth > 3 && riverDepth < 5) {
			if(chance>60) {
				safeCross=true;
			}
			else {
			safeCross=false;
			}
		}
		else {
			safeCross=false;
		}
		return safeCross;
	}

	/** 
	 *
	 * River float
	 *
	 * @param riverDepth  the river depth. 
	 * @return boolean
	 */
	// returns whether the attempt to Float the wagon was successful or not
	public boolean riverFloat(int riverDepth) {
		boolean safeCross=true;
		Random rand = new Random();
		int chance = rand.nextInt(100);
		if(riverDepth<5) {
			if(chance>15) {
				safeCross=true;
			}
			else {
				safeCross=false;
			}
			
			if(riverDepth>=5) 
			{
				if(chance>75) 
				{
					safeCross=true;
				}
				else 
				{
					safeCross=false;
				}
			}
		}
		return safeCross;
	}

	/** 
	 *
	 * River ferry
	 *
	 * @return boolean
	 */
	//returns whether the attempt to cross by ferry had an accident or not
	public boolean riverFerry() {
		boolean safeCross;
		Random rand = new Random();
		int chance = rand.nextInt(100);
		if(chance>15) {
			safeCross=true;
		}
		else {
			safeCross=false;
		}
		return safeCross;
	}
}