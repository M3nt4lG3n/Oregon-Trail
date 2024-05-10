package MP4_Package;

import java.util.Random;

import javax.swing.JOptionPane;

public class RandomEvent {
	
	private boolean fire;
	private boolean raid;
	private boolean snakeBite;
	private boolean sickness;
	private boolean brokenLimb;
	
	private JOptionPane popUP;
	
	public void RandomEventsChance()
	{
		Random rand = new Random();
		int chance = rand.nextInt(100);
		
		if(chance<=2) {
			popUP.showMessageDialog(null, "A group of indians raid your wagon, check supplies", "Random Event", JOptionPane.PLAIN_MESSAGE);
			System.out.println("raid");
			raid=true;
		}
		else if(chance<=7) {
			popUP.showMessageDialog(null, "One of your passengers was bit by a snake", "Random Event", JOptionPane.PLAIN_MESSAGE);
			System.out.println("snake");
			snakeBite=true;
		}
		else if(chance<=8) {
			popUP.showMessageDialog(null, "Your wagon caught fire, check supplies", "Random Event", JOptionPane.PLAIN_MESSAGE);
			System.out.println("fire");
			fire=true;
		}
		else if(chance<=14) {
			popUP.showMessageDialog(null, "One of your passengers broke a limb", "Random Event", JOptionPane.PLAIN_MESSAGE);
			System.out.println("broken limb");
			brokenLimb=true;
		}
		else if(chance<=20) {
			popUP.showMessageDialog(null, "One of your passengers is sick", "Random Event", JOptionPane.PLAIN_MESSAGE);
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
