/*
 * File: Fort.java
 * Author: Brian Bizon
 * Date: 4/17/2024
 * Version: 1.0.0
 */
package MP4_Package;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

import javax.swing.*;

public class Fort extends Landmark{

	private Wagon wagon;
	
	private JFrame shopFrame;
	private JFrame basicFrame;
	private JFrame oxenFrame;
	private JFrame wagonFrame;
	
	private JPanel shopPanel;
	private JPanel basicPanel;
	private JPanel oxenPanel;
	private JPanel wagonPanel;
	
	private JLabel waterCostLabel;
	private JLabel flourCostLabel;
	private JLabel starterCostLabel;
	private JLabel clothingCostLabel;
	private JLabel wagonCostLabel;
	private JLabel oxenCostLabel;
	
	private JLabel waterLabel;
	private JLabel flourLabel;
	private JLabel starterLabel;
	private JLabel clothingLabel; 
	private JLabel wagonLabel;
	private JLabel moneyLabel;
	private JLabel oxenLabel;
	
	private int basicAmount = 0;
	private int oxenAmount = 0;
	private int yokeAmount = 0;
	private int wheelAmount = 0;
	private int axelAmount = 0;
	private int tongueAmount = 0;
	private double waterCost = 0.15;
	private double flourCost = .05;
	private double starterCost = .15;
	private int clothingCost = 10; // YHDOD
	private int wagonCost = 10; //YHDOD
	private int yokeCost = 40; //YHDOD
	private int oxenCost = 60;
	private int fortStops = 0;
	private double tempCost = 0;
	private double costChecker = 0;
	
	DecimalFormat df = new DecimalFormat("#.##");
	
	/** 
	 *
	 * It is a constructor. 
	 *
	 * @param name  the name. 
	 * @param distanceFromLast  the distance from last. 
	 * @param tradeEnabled  the trade enabled. 
	 * @param shopEnabled  the shop enabled. 
	 * @param wagon  the wagon. 
	 */
	public Fort(String name, int distanceFromLast, boolean tradeEnabled, boolean shopEnabled, Wagon wagon) {
		super(name, distanceFromLast, tradeEnabled, shopEnabled);
		this.wagon = wagon;
	}
	/** 
	 *
	 * It is a constructor. 
	 *
	 */
	public void createShopInterface() {
		System.out.println("Creating Shop");
		shopFrame = new JFrame("Shopping");
		shopFrame.setBounds(800, 375, 450, 300);
		shopFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		shopPanel = new JPanel(new GridLayout(7, 3));
		
		JButton buyWater = new JButton("Buy Water");
		buyWater.addActionListener(new ActionListener(){
			/** 
			 *
			 * It is a constructor. 
			 *
			 * @param e  the e. 
			 */
			public void actionPerformed(ActionEvent e) {
				createBasicShop("water");
				basicAmount = 0;
			}
		});
		
		JButton buyFlour = new JButton("Buy Flour");
		buyFlour.addActionListener(new ActionListener(){
			/** 
			 *
			 * It is a constructor. 
			 *
			 * @param e  the e. 
			 */
			public void actionPerformed(ActionEvent e) {
				createBasicShop("flour");
				basicAmount = 0;
			}
		});
		
		JButton buyStarter = new JButton("Buy Starter");
		buyStarter.addActionListener(new ActionListener(){
			/** 
			 *
			 * It is a constructor. 
			 *
			 * @param e  the e. 
			 */
			public void actionPerformed(ActionEvent e) {
				createBasicShop("starter");
				basicAmount = 0;
			}
		});
		
		JButton buyClothing = new JButton("Buy Clothing");
		buyClothing.addActionListener(new ActionListener(){
			/** 
			 *
			 * It is a constructor. 
			 *
			 * @param e  the e. 
			 */
			public void actionPerformed(ActionEvent e) {
				createBasicShop("clothing");
				basicAmount = 0;
			}
		});
		
		JButton buyWagonSupplies = new JButton("Buy Wagon Supplies");
		buyWagonSupplies.addActionListener(new ActionListener(){
			/** 
			 *
			 * It is a constructor. 
			 *
			 * @param e  the e. 
			 */
			public void actionPerformed(ActionEvent e) {
				createWagonDisplay();
			}
		});
		
		JButton buyOxenSupplies = new JButton("Buy Oxen Supplies");
		buyOxenSupplies.addActionListener(new ActionListener(){
			/** 
			 *
			 * It is a constructor. 
			 *
			 * @param e  the e. 
			 */
			public void actionPerformed(ActionEvent e) {
				createOxenShop();
			}
		});
		
		JButton resumeButton = new JButton("Leave Shop");
		resumeButton.addActionListener(new ActionListener(){
			/** 
			 *
			 * It is a constructor. 
			 *
			 * @param e  the e. 
			 */
			public void actionPerformed(ActionEvent e) {
				System.out.println("Leaving Shop...");
				shopFrame.dispatchEvent(new WindowEvent(shopFrame, WindowEvent.WINDOW_CLOSING));
			}
		});
		
		waterLabel = new JLabel("Current Water: " + wagon.getWaterAmount());
		flourLabel = new JLabel("Current Flour: " + wagon.getFlourAmount());
		starterLabel = new JLabel("Current Starter: " + wagon.getStarterAmount());
		clothingLabel = new JLabel("Current Clothing: " + wagon.getClothingAmount());
		wagonLabel = new JLabel("Current Wheel: " + wagon.getWheelAmount() + " Axel: " + wagon.getAxelAmount() + " Tounge: " + wagon.getTongueAmount() + "    ");
		oxenLabel = new JLabel("Current Oxen: " + wagon.getOxenAmount() + " Yoke: " + wagon.getYokeAmount());
		moneyLabel = new JLabel("Current Money: " + wagon.getMoneyAmount());
		
		waterCostLabel = new JLabel("Water: " + df.format((waterCost + (waterCost * (.05 * fortStops)))));
		flourCostLabel = new JLabel("Flour: " + df.format((flourCost + (flourCost * (.05 * fortStops)))));
		starterCostLabel = new JLabel("Starter: " + df.format((starterCost + (starterCost * (.05 * fortStops)))));
		clothingCostLabel = new JLabel("Clothing: " + df.format((clothingCost + (clothingCost * (.05 * fortStops)))));
		wagonCostLabel = new JLabel("Wagon Wheels/Axels/Tongues: " + df.format((wagonCost + (wagonCost * (.05 * fortStops)))));
		oxenCostLabel = new JLabel("Oxen: " + df.format((oxenCost + (oxenCost * (.05 * fortStops)))) + " Yoke: " + df.format((yokeCost + (yokeCost * (.05 * fortStops)))));
		
		shopPanel.add(buyWater);
		shopPanel.add(waterLabel);
		shopPanel.add(waterCostLabel);
		shopPanel.add(buyFlour);
		shopPanel.add(flourLabel);
		shopPanel.add(flourCostLabel);
		shopPanel.add(buyStarter);
		shopPanel.add(starterLabel);
		shopPanel.add(starterCostLabel);
		shopPanel.add(buyClothing);
		shopPanel.add(clothingLabel);
		shopPanel.add(clothingCostLabel);
		shopPanel.add(buyWagonSupplies);
		shopPanel.add(wagonLabel);
		shopPanel.add(wagonCostLabel);
		shopPanel.add(buyOxenSupplies);
		shopPanel.add(oxenLabel);
		shopPanel.add(oxenCostLabel);
		shopPanel.add(resumeButton);
		shopPanel.add(moneyLabel);
		shopFrame.add(shopPanel);
		shopFrame.pack();
		shopFrame.setVisible(true);
	}
	/** 
	 *
	 * It is a constructor. 
	 *
	 */
	public void createOxenShop() {
		System.out.println("Creating Oxen Shop");
		oxenFrame = new JFrame("Shopping");
		oxenFrame.setBounds(1050, 100, 450, 300);
		oxenFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel oxenAmountLabel = new JLabel("Oxen Amount: " + oxenAmount);
		JLabel yokeAmountLabel = new JLabel("Yoke Amount: " + yokeAmount);
		
		oxenPanel = new JPanel(new GridLayout(3, 3));
		
		JButton upOxenButton = new JButton("Amount up");
		upOxenButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * It is a constructor. 
			 *
			 * @param e  the e. 
			 */
			public void actionPerformed(ActionEvent e) {
				costChecker += (oxenCost + (oxenCost * (.05 * fortStops)));
				System.out.println(costChecker);
				if(costChecker <= wagon.moneyAmount) {
					oxenAmount++;
				}
				oxenAmountLabel.setText("Oxen Amount: " + oxenAmount);
			}
		});
		
		JButton downOxenButton = new JButton("Amount down");
		downOxenButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * It is a constructor. 
			 *
			 * @param e  the e. 
			 */
			public void actionPerformed(ActionEvent e) {
				costChecker -= (oxenCost + (oxenCost * (.05 * fortStops)));
				if(oxenAmount > 0) {
					oxenAmount--;
				}
				else {
					costChecker = 0;
				}
				System.out.println(costChecker);
				oxenAmountLabel.setText("Oxen Amount: " + oxenAmount);
			}
		});
		
		JButton upYokeButton = new JButton("Amount up");
		upYokeButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * It is a constructor. 
			 *
			 * @param e  the e. 
			 */
			public void actionPerformed(ActionEvent e) {
				costChecker += (yokeCost + (yokeCost * (.05 * fortStops)));
				System.out.println(costChecker);
				if(costChecker <= wagon.moneyAmount) {
					yokeAmount++;
				}
				yokeAmountLabel.setText("Yoke Amount: " + yokeAmount);
			}
		});
		
		JButton downYokeButton = new JButton("Amount down");
		downYokeButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * It is a constructor. 
			 *
			 * @param e  the e. 
			 */
			public void actionPerformed(ActionEvent e) {
				costChecker -= (yokeCost + (yokeCost * (.05 * fortStops)));
				if(yokeAmount > 0) {
					yokeAmount--;
				}
				else {
					costChecker = 0;
				}
				System.out.println(costChecker);
				yokeAmountLabel.setText("Yoke Amount: " + yokeAmount);
			}
		});
		
		JButton buyOxenButton = new JButton("Buy Amount");
		buyOxenButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * It is a constructor. 
			 *
			 * @param e  the e. 
			 */
			public void actionPerformed(ActionEvent e) {
				System.out.println("Items Bought");
				wagon.oxenAmount += oxenAmount;
				wagon.oxenYokeAmount += yokeAmount;
				wagon.setMoneyAmount(wagon.getMoneyAmount() - (oxenAmount * (oxenCost + (oxenCost * (.05 * fortStops)))));
				wagon.setMoneyAmount(wagon.getMoneyAmount() - (yokeAmount * (yokeCost + (yokeCost * (.05 * fortStops)))));
				oxenLabel.setText("Current Oxen: " + wagon.getOxenAmount() + " Yoke: " + wagon.getYokeAmount());
				moneyLabel.setText("Current Money: " + df.format(wagon.getMoneyAmount()));
				oxenAmount = 0;
				costChecker = 0;
				oxenFrame.dispatchEvent(new WindowEvent(oxenFrame, WindowEvent.WINDOW_CLOSING));
			}
		});
		
		oxenPanel.add(upOxenButton);
		oxenPanel.add(oxenAmountLabel);
		oxenPanel.add(downOxenButton);
		oxenPanel.add(upYokeButton);
		oxenPanel.add(yokeAmountLabel);
		oxenPanel.add(downYokeButton);
		oxenPanel.add(buyOxenButton);
		oxenFrame.add(oxenPanel);
		oxenFrame.setVisible(true);
	}
	
	/** 
	 *
	 * It is a constructor. 
	 *
	 */
	public void createWagonDisplay() {
		
		System.out.println("Creating Wagon Shop");
		wagonFrame = new JFrame("Shopping");
		wagonFrame.setBounds(1050, 100, 450, 300);
		wagonFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel wheelAmountLabel = new JLabel("Wheel Amount: " + wheelAmount);
		JLabel axelAmountLabel = new JLabel("Axel Amount: " + axelAmount);
		JLabel tongueAmountLabel = new JLabel("Tongue Amount: " + tongueAmount);
		
		wagonPanel = new JPanel(new GridLayout(4, 3));
		
		JButton upWheelButton = new JButton("Amount up");
		upWheelButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * It is a constructor. 
			 *
			 * @param e  the e. 
			 */
			public void actionPerformed(ActionEvent e) {
				costChecker += (wagonCost + (wagonCost * (.05 * fortStops)));
				System.out.println(costChecker);
				if(costChecker <= wagon.moneyAmount) {
					wheelAmount++;
				}
				wheelAmountLabel.setText("Wheel Amount: " + wheelAmount);
			}
		});
		
		JButton downWheelButton = new JButton("Amount down");
		downWheelButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * It is a constructor. 
			 *
			 * @param e  the e. 
			 */
			public void actionPerformed(ActionEvent e) {
				costChecker -= (wagonCost + (wagonCost * (.05 * fortStops)));
				if(wheelAmount > 0) {
					wheelAmount--;
				}
				else {
					costChecker = 0;
				}
				System.out.println(costChecker);
				wheelAmountLabel.setText("Wheel Amount: " + wheelAmount);
			}
		});
		
		JButton upAxelButton = new JButton("Amount up");
		upAxelButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * It is a constructor. 
			 *
			 * @param e  the e. 
			 */
			public void actionPerformed(ActionEvent e) {
				costChecker += (wagonCost + (wagonCost * (.05 * fortStops)));
				System.out.println(costChecker);
				if(costChecker <= wagon.moneyAmount) {
					axelAmount++;
				}
				axelAmountLabel.setText("Axel Amount: " + axelAmount);
			}
		});
		
		JButton downAxelButton = new JButton("Amount down");
		downAxelButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * It is a constructor. 
			 *
			 * @param e  the e. 
			 */
			public void actionPerformed(ActionEvent e) {
				costChecker -= (wagonCost + (wagonCost * (.05 * fortStops)));
				if(axelAmount > 0) {
					axelAmount--;
				}
				else {
					costChecker = 0;
				}
				System.out.println(costChecker);
				axelAmountLabel.setText("Axel Amount: " + axelAmount);
			}
		});
		
		JButton upTongueButton = new JButton("Amount up");
		upTongueButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * It is a constructor. 
			 *
			 * @param e  the e. 
			 */
			public void actionPerformed(ActionEvent e) {
				costChecker += (wagonCost + (wagonCost * (.05 * fortStops)));
				System.out.println(costChecker);
				if(costChecker <= wagon.moneyAmount) {
					tongueAmount++;
				}
				tongueAmountLabel.setText("Tongue Amount: " + tongueAmount);
			}
		});
		
		JButton downTongueButton = new JButton("Amount down");
		downTongueButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * It is a constructor. 
			 *
			 * @param e  the e. 
			 */
			public void actionPerformed(ActionEvent e) {
				costChecker -= (wagonCost + (wagonCost * (.05 * fortStops)));
				if(tongueAmount > 0) {
					tongueAmount--;
				}
				else {
					costChecker = 0;
				}
				System.out.println(costChecker);
				tongueAmountLabel.setText("Tongue Amount: " + tongueAmount);
			}
		});
		
		JButton buyWagonButton = new JButton("Buy Amount");
		buyWagonButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * It is a constructor. 
			 *
			 * @param e  the e. 
			 */
			public void actionPerformed(ActionEvent e) {
				System.out.println("Items Bought");
				wagon.wagonWheelAmount += wheelAmount;
				wagon.wagonAxelAmount += axelAmount;
				wagon.wagonTongueAmount += tongueAmount;
				wagon.setMoneyAmount(wagon.getMoneyAmount() - (wheelAmount * (wagonCost + (wagonCost * (.05 * fortStops)))));
				wagon.setMoneyAmount(wagon.getMoneyAmount() - (axelAmount * (wagonCost + (wagonCost * (.05 * fortStops)))));
				wagon.setMoneyAmount(wagon.getMoneyAmount() - (tongueAmount * (wagonCost + (wagonCost * (.05 * fortStops)))));
				wagonLabel.setText("Current Wheel: " + wagon.getWheelAmount() + " Axel: " + wagon.getAxelAmount() + " Tongue: " + wagon.getTongueAmount() + "    ");
				moneyLabel.setText("Current Money: " + df.format(wagon.getMoneyAmount()));
				wheelAmount = 0;
				axelAmount = 0;
				tongueAmount = 0;
				costChecker = 0;
				wagonFrame.dispatchEvent(new WindowEvent(wagonFrame, WindowEvent.WINDOW_CLOSING));
			}
		});
		
		wagonPanel.add(upWheelButton);
		wagonPanel.add(wheelAmountLabel);
		wagonPanel.add(downWheelButton);
		wagonPanel.add(upAxelButton);
		wagonPanel.add(axelAmountLabel);
		wagonPanel.add(downAxelButton);
		wagonPanel.add(upTongueButton);
		wagonPanel.add(tongueAmountLabel);
		wagonPanel.add(downTongueButton);
		wagonPanel.add(buyWagonButton);
		wagonFrame.add(wagonPanel);
		wagonFrame.pack();
		wagonFrame.setVisible(true);
	}
	/** 
	 *
	 * It is a constructor. 
	 *
	 * @param type  the type. 
	 */
	public void createBasicShop(String type) {
		switch(type) {
			case "water": tempCost = waterCost; break;
			case "flour": tempCost = flourCost; break;
			case "starter": tempCost = starterCost; break;
			case "clothing": tempCost = clothingCost; break;
		}
		
		System.out.println("Creating Basic Shop");
		basicFrame = new JFrame("Shopping");
		basicFrame.setBounds(1050, 100, 450, 300);
		basicFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		basicPanel = new JPanel(new GridLayout(2, 3));
		
		JLabel basicAmountLabel = new JLabel("Amount: " + basicAmount);
		
		JButton upBasicButton = new JButton("Amount up");
		upBasicButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * It is a constructor. 
			 *
			 * @param e  the e. 
			 */
			public void actionPerformed(ActionEvent e) {
				costChecker += (tempCost + (tempCost * (.05 * fortStops)));
				System.out.println(costChecker);
				if(tempCost <= wagon.moneyAmount) {
					basicAmount++;
				}
				basicAmountLabel.setText("Amount: " + basicAmount);
			}
		});
		
		JButton downBasicButton = new JButton("Amount down");
		downBasicButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * It is a constructor. 
			 *
			 * @param e  the e. 
			 */
			public void actionPerformed(ActionEvent e) {
				costChecker -= (tempCost + (tempCost * (.05 * fortStops)));
				System.out.println(costChecker);
				if(basicAmount > 0) {
					basicAmount--;
				}
				basicAmountLabel.setText("Amount: " + basicAmount);
			}
		});
		
		JButton buyBasicButton = new JButton("Buy Amount");
		buyBasicButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * It is a constructor. 
			 *
			 * @param e  the e. 
			 */
			public void actionPerformed(ActionEvent e) {
				System.out.println("Items Bought");
				switch(type) {
					case "water": wagon.waterAmount += basicAmount; wagon.setMoneyAmount(wagon.getMoneyAmount() - (basicAmount * (waterCost + (waterCost * (.05 * fortStops))))); waterLabel.setText("Current Water: " + wagon.getWaterAmount()); moneyLabel.setText("Current Money: " + df.format(wagon.getMoneyAmount())); break;
					case "flour": wagon.flourAmount += basicAmount; wagon.setMoneyAmount(wagon.getMoneyAmount() - (basicAmount * (flourCost + (flourCost * (.05 * fortStops))))); flourLabel.setText("Current Flour: " + wagon.getFlourAmount()); moneyLabel.setText("Current Money: " + df.format(wagon.getMoneyAmount())); break;
					case "starter": wagon.starterAmount += basicAmount; wagon.setMoneyAmount(wagon.getMoneyAmount() - (basicAmount * (starterCost + (starterCost * (.05 * fortStops))))); starterLabel.setText("Current Starter: " + wagon.getStarterAmount()); moneyLabel.setText("Current Money: " + df.format(wagon.getMoneyAmount())); break;
					case "clothing": wagon.clothingAmount += basicAmount; wagon.setMoneyAmount(wagon.getMoneyAmount() - (basicAmount * (clothingCost + (clothingCost * (.05 * fortStops))))); clothingLabel.setText("Current Clothing: " + wagon.getClothingAmount()); moneyLabel.setText("Current Money: " + df.format(wagon.getMoneyAmount())); break;
				}
				basicFrame.dispatchEvent(new WindowEvent(basicFrame, WindowEvent.WINDOW_CLOSING));
				costChecker = 0;
			}
		});
		
		basicPanel.add(upBasicButton);
		basicPanel.add(basicAmountLabel);
		basicPanel.add(downBasicButton);
		basicPanel.add(buyBasicButton);
		basicFrame.add(basicPanel);
		basicFrame.pack();
		basicFrame.setVisible(true);
	}
}
