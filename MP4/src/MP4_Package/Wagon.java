/*
 * File: Wagon.java
 * Authors: Mason Greenawalt and Brian Bizon(Baking Stuff)
 * Date: 5/4/2024
 * Version: 3.0.0 
 */

package MP4_Package;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Wagon { 
	//Initialized as arbitrary values
	int foodWeight = 1000;
	int flourAmount = 500; 
	int starterAmount = 200;
	int dryGoodsWeight = 50; 
	int waterAmount = 200; 
	int clothingAmount = 10;
	double moneyAmount = 800;
	int wagonWheelAmount = 3;
	int wagonAxelAmount = 2;
	int wagonTongueAmount = 2;
	int oxenAmount = 4;
	int oxenYokeAmount = 2;
	
	//Cooking Variables
	private JFrame bakingFrame;
	private JLayeredPane cookingPane;
	private JPanel selectionPanel;
	
	Random random = new Random();
	//Wagon wagon = new Wagon();
	
	private JOptionPane popUP;
	
	private int greenStart;
	private int greenSpace;
	private int currentLocation;
	
	private Timer clock;

	/** 
	 *
	 * Gets the food weight
	 *
	 * @return the food weight
	 */
    public int getFoodWeight() {
        return foodWeight;
    }

    /** 
    *
    * Sets the food weight
    *
    * @param foodWeight  the food weight. 
    */
    public void setFoodWeight(int foodWeight) {
        this.foodWeight = foodWeight;
    }

    /** 
    *
    * Gets the dry goods weight
    *
    * @return the dry goods weight
    */
    public int getDryGoodsWeight() {
        return dryGoodsWeight;
    }

    /** 
    *
    * Sets the dry goods weight
    *
    * @param dryGoodsWeight  the dry goods weight. 
    */
    public void setDryGoodsWeight(int dryGoodsWeight) {
        this.dryGoodsWeight = dryGoodsWeight;
    }

    /** 
	 *
	 * Gets the water amount
	 *
	 * @return the water amount
	 */
    public int getWaterAmount() {
        return waterAmount;
    }

    /** 
    *
    * Sets the water amount
    *
    * @param waterAmount  the water amount. 
    */
    public void setWaterAmount(int waterAmount) {
        this.waterAmount = waterAmount;
    }
    /** 
	 *
	 * Gets the flour amount
	 *
	 * @return the flour amount
	 */
    public int getFlourAmount() {
    	return flourAmount;
    }
    /** 
    *
    * Sets the flour amount
    *
    * @param flourAmount  the water amount. 
    */
    public void setFlourAmount(int flourAmount) {
    	this.flourAmount = flourAmount;
    }
    /** 
	 *
	 * Gets the starter amount
	 *
	 * @return the starter amount
	 */
    public int getStarterAmount() {
    	return starterAmount;
    }
    /** 
    *
    * Sets the starter amount
    *
    * @param starterAmount  the water amount. 
    */
    public void setStarterAmount(double starterAmount) {
    	this.starterAmount = (int) starterAmount;
    }
    /** 
	 *
	 * Gets the clothing amount
	 *
	 * @return the clothing amount
	 */
    public int getClothingAmount() {
    	return clothingAmount;
    }
    /** 
    *
    * Sets the clothing amount
    *
    * @param clothingAmount  the water amount. 
    */
    public void setClothingAmount(int clothingAmount) {
    	this.clothingAmount = clothingAmount;
    }
    /** 
	 *
	 * Gets the money amount
	 *
	 * @return the money amount
	 */
    public double getMoneyAmount() {
    	return moneyAmount;
    }
    /** 
    *
    * Sets the money amount
    *
    * @param moneyAmount  the water amount. 
    */
    public void setMoneyAmount(double moneyAmount) {
    	this.moneyAmount = moneyAmount;
    }
    /** 
	 *
	 * Gets the wheel amount
	 *
	 * @return the wheel amount
	 */
    public int getWheelAmount() {
    	return wagonWheelAmount;
    }
    /** 
    *
    * Sets the wheel amount
    *
    * @param wheelAmount  the water amount. 
    */
    public void setWheelAmount(int wheelAmount) {
    	this.wagonWheelAmount = wheelAmount;
    }
    /** 
	 *
	 * Gets the axel amount
	 *
	 * @return the axel amount
	 */
    public int getAxelAmount() {
    	return wagonAxelAmount;
    }
    /** 
    *
    * Sets the axel amount
    *
    * @param axelAmount  the water amount. 
    */
    public void setAxelAmount(int axelAmount) {
    	this.wagonAxelAmount = axelAmount;
    }
    /** 
	 *
	 * Gets the tongue amount
	 *
	 * @return the tongue amount
	 */
    public int getTongueAmount() {
    	return wagonTongueAmount;
    }
    /** 
    *
    * Sets the tongue amount
    *
    * @param tongueAmount  the water amount. 
    */
    public void setTongueAmount(int tongueAmount) {
    	this.wagonTongueAmount = tongueAmount;
    }
    /** 
	 *
	 * Gets the oxen amount
	 *
	 * @return the oxen amount
	 */
    public int getOxenAmount() {
    	return oxenAmount;
    }
    /** 
    *
    * Sets the oxen amount
    *
    * @param oxenAmount  the water amount. 
    */
    public void setOxenAmount(int oxenAmount) {
    	this.oxenAmount = oxenAmount;
    }
    /** 
	 *
	 * Gets the yoke amount
	 *
	 * @return the yoke amount
	 */
    public int getYokeAmount() {
    	return oxenYokeAmount;
    }
    /** 
    *
    * Sets the yoke amount
    *
    * @param yokeAmount  the water amount. 
    */
    public void setYokeAmount(int yokeAmount) {
    	this.oxenYokeAmount = yokeAmount;
    }

    /** 
    *
    * Update inventory
    *
    * @param foodUsed the food used. 
    * @param waterUsed the water used. 
    * 
    * Updates the contents of the wagon based off of circumstances
    */
    public void updateInventory(int foodUsed, int waterUsed) {
        System.out.println("Inventory Updated");
    	foodWeight -= foodUsed;
        waterAmount -= waterUsed;
    }
    /** 
    *
    * It is a constructor. 
    *
    * Checks if there is a random event
    * If there is, perform the appropriate actions and remove appropriate items
    */
    public void randomEventUpdate() {
    	Random rand = new Random();
		int num = rand.nextInt(8);
    	RandomEvent randomEvent = new RandomEvent();
		if(randomEvent.getRaid()==true) {
			if(num==0) {
				setYokeAmount((int) (getYokeAmount()*.75));
			}
			else if(num==1) {
				setOxenAmount((int) (getOxenAmount()*.75));
			}
			else if(num==2) {
				setTongueAmount((int) (getTongueAmount()*.75));
			}
			else if(num==3) {
				setAxelAmount((int) (getAxelAmount()*.75));
			}
			else if(num==4) {
				setWheelAmount((int) (getWheelAmount()*.75));
			}
			else if(num==5) {
				setClothingAmount((int) (getClothingAmount()*.75));
			}
			else if(num==6) {
				setWaterAmount((int) (getWaterAmount()*.75));
			}
			else if(num==7) {
				setFoodWeight((int) (getFoodWeight()*.75));
			}
		}
		if(randomEvent.getFire()==true) {
				setYokeAmount((int) (getYokeAmount()*.5));
				setOxenAmount((int) (getOxenAmount()*.5));
				setTongueAmount((int) (getTongueAmount()*.5));
				setAxelAmount((int) (getAxelAmount()*.5));
				setWheelAmount((int) (getWheelAmount()*.5));
				setClothingAmount((int) (getClothingAmount()*.5));
				setWaterAmount((int) (getWaterAmount()*.5));
				setFoodWeight((int) (getFoodWeight()*.5));
			}
		}
    /** 
    *
    * It is a constructor. 
    * 
    * Generates a red line with a green space
    * A black bar will be instantiated at the far left, will move later
    */
    public void createBakingDisplay() {
    	setStarterAmount(getStarterAmount() - 5);
		setFlourAmount(getFlourAmount() - 5);
		setWaterAmount(getWaterAmount() - 5);
		
		popUP.showMessageDialog(null, "Press the space bar on the green", "Cooking Mama Gaming", JOptionPane.PLAIN_MESSAGE);
		cookingPane = new JLayeredPane();
		cookingPane.setPreferredSize(new Dimension(600, 50));
		greenStart = random.nextInt(450) + 50;
		greenSpace = random.nextInt(200) + 5;
		currentLocation = 0;
		
		System.out.println("Creating Baking Minigame");
		bakingFrame = new JFrame("Bake");
		bakingFrame.setBounds(800, 400, 600, 50);
		bakingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		bakingFrame.addKeyListener(new KeyListener() {
			/** 
			 *
			 * It is a constructor. 
			 *
			 * @param e  the e. 
			 */
			public void keyPressed(KeyEvent e) {
				int vk = e.getKeyCode();
				onSpacePress(vk);
			}
			/** 
			 *
			 * It is a constructor. 
			 *
			 * @param e  the e. 
			 */
			public void keyReleased(KeyEvent e) {
			}
			/** 
			 *
			 * It is a constructor. 
			 *
			 * @param e  the e. 
			 * @Override
			 */
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JPanel redSpacePanel = new JPanel();
		redSpacePanel.setBounds(0, 0, 600, 50);
		redSpacePanel.setBackground(Color.RED);
		redSpacePanel.setVisible(true);
		cookingPane.add(redSpacePanel, 0, 0);
		
		JPanel greenSpacePanel = new JPanel();
		greenSpacePanel.setBounds(greenStart, 0, greenSpace, 50);
		greenSpacePanel.setBackground(Color.GREEN);
		greenSpacePanel.setVisible(true);
		cookingPane.add(greenSpacePanel, 1, 0);
		
		selectionPanel = new JPanel();
		selectionPanel.setBounds(0, 0, 10, 50);
		selectionPanel.setBackground(Color.BLACK);
		redSpacePanel.setVisible(true);
		cookingPane.add(selectionPanel, 2, 0);
		
		bakingFrame.add(cookingPane);
		bakingFrame.pack();
		bakingFrame.setVisible(true);
		
		moveSelectionBar();
	}
    /** 
    *
    * It is a constructor. 
    *
    * @param vk  the vk. 
    * 
    * Checks if the space bar was pressed
    * If yes, checks the location of the black selector bar
    * Allot resources accordingly
    */
	public void onSpacePress(int vk) {
		clock.stop();
		if(vk == KeyEvent.VK_SPACE) {
			System.out.println("Space Bar Pressed");
			if(currentLocation >= greenStart && currentLocation <= greenStart + greenSpace) {
				setFoodWeight(getFoodWeight() + 45);
				System.out.println(getFoodWeight());
				//main.foodLabel.setText("" + wagon.getFoodWeight());
				popUP.showMessageDialog(null, "You baked 45 food", "Cooking Mama Gaming", JOptionPane.PLAIN_MESSAGE);
				System.out.println("Bread Baked");
			}
			else {
				popUP.showMessageDialog(null, "You failed, no food rewarded", "Cooking Mama Gaming", JOptionPane.ERROR_MESSAGE);
				System.out.println("Baking Unsuccessful");
			}
		}
		bakingFrame.dispose();
	}
	/** 
	 *
	 * It is a constructor. 
	 * 
	 * Moves the black selection bar 5 units right every 10 milliseconds
	 */
	public void moveSelectionBar() {
		clock = new javax.swing.Timer(10, new ActionListener() {
		/** 
		 *
		 * It is a constructor. 
		 *
		 * @param evt  the evt. 
		 */
		public void actionPerformed(ActionEvent evt) {
				selectionPanel.setLocation(selectionPanel.getX() + 5, selectionPanel.getY());
				currentLocation = selectionPanel.getX();
			}
		});
		clock.start();
	}
}
