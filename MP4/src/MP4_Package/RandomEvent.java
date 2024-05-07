package MP4_Package;

import java.util.Random;

public class RandomEvent {
	
	private boolean fire;
	private boolean raid;
	private boolean snakeBite;
	private boolean sickness;
	private boolean brokenLimb;
	public void RandomEventsChance()
	{
		Random rand = new Random();
		int chance = rand.nextInt(100);
		
		if(chance<=2) {
			System.out.println("raid");
			raid=true;
		}
		else if(chance<=7) {
			System.out.println("snake");
			snakeBite=true;
		}
		else if(chance<=8) {
			System.out.println("fire");
			fire=true;
		}
		else if(chance<=14) {
			System.out.println("broken limb");
			brokenLimb=true;
		}
		else if(chance<=20) {
			System.out.println("sick");
			sickness=true;
		}
		else {
			sickness=false;
			snakeBite=false;
			fire=false;
			brokenLimb=false;
			raid=false;
		}
	}
	public boolean getSickness() {
		return sickness;
	}
	public boolean getSnakeBite() {
		return snakeBite;
	}
	public boolean getFire() {
		return fire ;
	}
	public boolean getBrokenLimb() {
		return brokenLimb;
	}
	public boolean getRaid() {
		return raid;
	}
}
