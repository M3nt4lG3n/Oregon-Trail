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
			raid=true;
		}
		else if(chance<=7) {
			snakeBite=true;
		}
		else if(chance<=8) {
			fire=true;
		}
		else if(chance<=14) {
			brokenLimb=true;
		}
		else if(chance<=20) {
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
