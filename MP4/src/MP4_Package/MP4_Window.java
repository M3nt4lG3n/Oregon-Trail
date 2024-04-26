/*
 * File: MP4_Window.java
 * Author: Brian Bizon
 * Date: 4/26/2024
 * Version: 3.0.0 
 */

package MP4_Package;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;

/**
* The class  MP4_Window
*/ 
public class MP4_Window {

	private JFrame mainFrame;
	private JFrame stopFrame;
	private JFrame suppliesFrame;
	private JFrame paceFrame;
	private JFrame consumptionFrame;
	private JFrame restFrame;
	private JFrame riverFrame;
	private JFrame tradeFrame;
	private JFrame conversationFrame;
	private JFrame bakingFrame;
	
	private JLayeredPane imagePanel;
	private JPanel wagonImagePanel;
	private JPanel stopPanel;
	private JPanel suppliesPanel;
	private JPanel pacePanel;
	private JPanel consumptionPanel;
	private JPanel restPanel;
	private JPanel riverPanel;
	private JPanel tradePanel;
	
	private JOptionPane popUP;
	
	private JLabel dateLabel;
	private JLabel foodLabel;
	private JLabel weatherLabel;
	
	Random random = new Random();
	Wagon wagon = new Wagon();
	Events events = new Events();
	Trader trader = new Trader();
	Weather weather = new Weather();
	
	private String currentMonth = "May";
	private String nameSeed = "";
	private int landmarkCount = 0;
	private int currentDay = 10;
	private int currentYear = 1854;
	private int travelRate = 12;
	private int index = 0;
	private int restDays = 1;
	private int currentDistance = 0;
	private int consumptionRate = 3; //YHDOD
	private int conversationSeed = 0;
	private boolean atLandmark = false;
	private boolean atOregon = false;
	private boolean atShop = false;
	private boolean atTrade = false;
	private boolean tradeCompleted = false;
	
	private final int MISSOURIINDEX = 1;
	private final int ELKHORNINDEX = 4;
	private final int MAINX = 700;
	private final int MAINY = 500;
	
	ArrayList<Landmark> landmarks = new ArrayList<Landmark>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			/** 
			 *
			 * Run
			 *
			 */
			public void run() {
				try {
					MP4_Window window = new MP4_Window();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MP4_Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setLandmarks();
		currentDistance = landmarks.get(index).getDistance();
		createDisplay();
	}
	
	/** 
	 *
	 * Creates the main WindowDisplay
	 * Contains the temporary image frame and stats with buttons
	 *
	 */
	private void createDisplay() {
		mainFrame = new JFrame("Main Game");
		mainFrame.setBounds(100, 100, 450, 300);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setPreferredSize(new Dimension(MAINX, MAINY));

		//Panel Setups
		JPanel statsPanel = new JPanel(new GridLayout(7, 2));
		
		imagePanel = new JLayeredPane();
		imagePanel.setPreferredSize(new Dimension(450, 300));
		
		createWagonGUI();
		
		//Stats Labels which are commonly interacted with
		dateLabel = new JLabel("" + currentMonth + " " + currentDay + ", " + currentYear);
		dateLabel.setHorizontalAlignment(SwingConstants.LEADING);
		
		JLabel milesLabel = new JLabel("" + landmarks.get(index).getDistance());
		milesLabel.setHorizontalAlignment(SwingConstants.LEADING);
		
		JLabel landmarkLabel = new JLabel(landmarks.get(index).getName());
		landmarkLabel.setHorizontalAlignment(SwingConstants.LEADING);
		
		//Interactions Setups
		JButton travelButton = new JButton("Travel");
		travelButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * Action performed
			 *
			 * @param e the e.
			 * 
			 * 	Move the player their set amount of distance
			 *  Check if the player has traveled to the landmark
			 *  If so, Notify them and get the information for the next landmark
			 */
			public void actionPerformed(ActionEvent e) {
				System.out.println("Travel Button Pressed");
				atShop = false;
				atTrade = false;
				//Updates the amount of resources
				calculateConsumptionAmount();
				milesLabel.setText("" + calculateTravelDistance());
				weatherLabel.setText("" + weather.pickRandomWeather());
				//Check if the player has reached a landmark
				if(atLandmark) {
					landmarkCount++;
					popUP = new JOptionPane();
					popUP.showMessageDialog(null, "You have reached a landmark", "Nice", JOptionPane.PLAIN_MESSAGE);
					//Check if the landmark is a river or not
					if(landmarkCount == MISSOURIINDEX || landmarkCount == ELKHORNINDEX) {
						createRiverDisplay();
					}
					//Update the miles and next landmark labels to the next values in the CSV
					milesLabel.setText("" + landmarks.get(index).getDistance());
					landmarkLabel.setText(landmarks.get(index).getName());
					//Checks for a game win
					//Will repeat the last leg of the trip
					//Some form of game end screen will be implemented later
					if(atOregon) {
						popUP = new JOptionPane();
						popUP.showMessageDialog(null, "You have reached Oregon, please exit", "Congratulations", JOptionPane.PLAIN_MESSAGE);
					}
				}
				atLandmark = false;
				calculateTravelDate(1);
				tradeCompleted = false;
				wagon.updateInventory(0, 0);
			}
		});
		statsPanel.add(travelButton);
		
		JButton statsButton = new JButton("Stop");
		statsButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * Action performed
			 *
			 * @param e the e. 
			 * 
			 * Stop the player and open up the stop menu with proper funtions
			 */
			public void actionPerformed(ActionEvent e) {
				System.out.println("Stats Button Pressed");
				//GUI Setup
				stopFrame = new JFrame("You have stopped");
				stopFrame.setBounds(800, 100, 450, 300);
				stopFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				createStopDisplay(atShop, atTrade);
				stopFrame.getContentPane().add(stopPanel);
//				mainFrame.setEnabled(false);
				stopFrame.pack();
				stopFrame.setVisible(true);
			}
		});
		statsPanel.add(statsButton);
		
		//statsPanel Label Setups
		JLabel dateTextLabel = new JLabel("Date: ");
		dateTextLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		statsPanel.add(dateTextLabel);
		
		statsPanel.add(dateLabel);
		
		JLabel weatherTextLabel = new JLabel("Weather: ");
		weatherTextLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		statsPanel.add(weatherTextLabel);
		
		weatherLabel = new JLabel("" + weather.pickRandomWeather());
		weatherLabel.setHorizontalAlignment(SwingConstants.LEADING);
		statsPanel.add(weatherLabel);
		
		JLabel healthTextLabel = new JLabel("Health: ");
		healthTextLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		statsPanel.add(healthTextLabel);
		
		JLabel healthLabel = new JLabel("Healthy (Placeholder)");
		healthLabel.setHorizontalAlignment(SwingConstants.LEADING);
		statsPanel.add(healthLabel);
		
		JLabel foodTextLabel = new JLabel("Food: ");
		foodTextLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		statsPanel.add(foodTextLabel);
		
		foodLabel = new JLabel("" + wagon.getFoodWeight());
		foodLabel.setHorizontalAlignment(SwingConstants.LEADING);
		statsPanel.add(foodLabel);
		
		JLabel landmarkTextLabel = new JLabel("Next Landmark: ");
		landmarkTextLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		statsPanel.add(landmarkTextLabel);
		
		statsPanel.add(landmarkLabel);
		
		JLabel milesTextLabel = new JLabel("Miles: ");
		milesTextLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		statsPanel.add(milesTextLabel);
		
		statsPanel.add(milesLabel);
		
		mainFrame.getContentPane().add(statsPanel, BorderLayout.SOUTH);
		mainFrame.getContentPane().add(imagePanel);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	/** 
	 *
	 * Create stop display
	 *
	 * @param shopEnabled  the shop enabled. 
	 * @param tradeEnabled  the trade enabled. 
	 * 
	 * Create a display which houses the needed buttons depending on the location
	 * Display changes depending on if they are on the road, at a landmark, or staying in a fort
	 */
	private void createStopDisplay(boolean shopEnabled, boolean tradeEnabled) {
		mainFrame.setEnabled(false);
		stopPanel = new JPanel(new GridLayout(9, 1));
		JButton proceedButton = new JButton("Proceed on the trail");
		proceedButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * Action performed
			 *
			 * @param e  the e.
			 * 
			 *  Closes the window
			 */
			public void actionPerformed(ActionEvent e) {
					System.out.println("Proceeding...");
					mainFrame.setEnabled(true);
					stopFrame.dispatchEvent(new WindowEvent(stopFrame, WindowEvent.WINDOW_CLOSING));
				}
			});
		stopPanel.add(proceedButton);
		JButton suppliesButton = new JButton("Check Supplies");
		suppliesButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * Action performed
			 *
			 * @param e the e. 
			 * 
			 * Opens the supplies window and displays the current contents of the wagon
			 */
			public void actionPerformed(ActionEvent e) {
					System.out.println("Opening supplies Window");
					createSuppliesDisplay();
				}
			});
		stopPanel.add(suppliesButton);
		JButton mapButton = new JButton("Check Map");
		mapButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * Action performed
			 *
			 * @param e  the e. 
			 * 
			 * Not yet implemented, will show the player's location on their total journey
			 */
			public void actionPerformed(ActionEvent e) {
					popUP = new JOptionPane();
					popUP.showMessageDialog(null, "This feature is not yet implemented", "MVP Gaming", JOptionPane.ERROR_MESSAGE);
				}
			});
		stopPanel.add(mapButton);
		JButton paceButton = new JButton("Set the Pace");
		paceButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * Action performed
			 *
			 * @param e the e. 
			 * 
			 * Sets the pace based off of the three buttons which are displayed (either 12, 16, 20)
			 */
			public void actionPerformed(ActionEvent e) {
					System.out.println("Setting the Pace");
					createPaceWindow();
				}
			});
		stopPanel.add(paceButton);
		JButton rationsButton = new JButton("Change the Rations");
		rationsButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * Action performed
			 *
			 * @param e  the e. 
			 * 
			 * Adjusts the consumption rate of each person currently alive in the wagon
			 */
			public void actionPerformed(ActionEvent e) {
					createConsumptionWindow();
				}
			});
		stopPanel.add(rationsButton);
		
		JButton restButton = new JButton("Stop to Rest");
		restButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * Action performed
			 *
			 * @param e  the e. 
			 * 
			 * Allows the player to skip days for reasons, sets amount with two buttons
			 */
			public void actionPerformed(ActionEvent e) {
					createRestWindow();
				}
			});
		stopPanel.add(restButton);
		JButton huntButton = new JButton("Bake Bread");
		huntButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * Action performed
			 *
			 * @param e  the e. 
			 * 
			 * Not Implemented, will eventually have the player perform a quick time whack a mole mini-game for gathering berries
			 */
			public void actionPerformed(ActionEvent e) {
					popUP = new JOptionPane();
					popUP.showMessageDialog(null, "This feature is not yet implemented", "MVP Gaming", JOptionPane.ERROR_MESSAGE);
					//createBakingDisplay();
				}
			});
		stopPanel.add(huntButton);
		if(tradeEnabled) {
			JButton tradeButton = new JButton("Attempt to trade");
			tradeButton.addActionListener(new ActionListener() {
				/** 
				 *
				 * Action performed
				 *
				 * @param e  the e. 
				 * 
				 * Not implemented, will allow the player to trade with known/random people on the trail
				 */
				public void actionPerformed(ActionEvent e) {
						if(!tradeCompleted) {
							createTradeDisplay();
						}
						else {
							
						}
					}
				});
			stopPanel.add(tradeButton);
			if(!shopEnabled) {
				JButton conversationButton = new JButton("Stop to Talk");
				conversationButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						createConversationDisplay(landmarks.get(index - 1).getName());
					}
				});
				stopPanel.add(conversationButton);
			}
		}
		if(shopEnabled) {
			JButton shopButton = new JButton("Shop for Supplies");
			shopButton.addActionListener(new ActionListener() {
				/** 
				 *
				 * Action performed
				 *
				 * @param e  the e.
				 * 
				 *  Not Implemented, Will open up the shop window where the player can add items to their wagon
				 */
				public void actionPerformed(ActionEvent e) {
					Fort fort = new Fort(null, 0, false, false, wagon);	
					fort.createShopInterface();
					}
				});
			stopPanel.add(shopButton);
			
			JButton conversationButton = new JButton("Stop to Talk");
			conversationButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					createConversationDisplay(landmarks.get(index - 1).getName());
				}
			});
			stopPanel.add(conversationButton);
		}
	}
	
	/** 
	 *
	 * Calculate travel date
	 *
	 * @param daysSpent  the days spent. 
	 * 
	 * Changes the date label based off of the current date
	 * Iterates the months when maximum days are reached
	 * Allows for set amounts for days to be calculated, allows for resting
	 */
	private void calculateTravelDate(int daysSpent) {
		int maxDays = 0;
		if (currentMonth == "January" || currentMonth == "March" || currentMonth == "May" || currentMonth == "July" || currentMonth == "August" || currentMonth == "October" || currentMonth == "December" ) {
			maxDays = 31;
		}
		else if (currentMonth == "Febuary") {
			maxDays = 29;
		}
		else {
			maxDays = 30;
		}
		
		currentDay += daysSpent;
		System.out.println(currentDay);
		if(currentDay > maxDays) {
			currentDay = 1;
			switch(currentMonth) {
				case "January": currentMonth = "Febuary"; break;
				case "Febuary": currentMonth = "March"; break;
				case "March": currentMonth = "April"; break;
				case "April": currentMonth = "May"; break;
				case "May": currentMonth = "June"; break;
				case "June": currentMonth = "July"; break;
				case "July": currentMonth = "August"; break;
				case "August": currentMonth = "September"; break;
				case "September": currentMonth = "October"; break;
				case "October": currentMonth = "November"; break;
				case "November": currentMonth = "December"; break;
				case "December": currentMonth = "January"; currentYear++; break;
			}
		}
		dateLabel.setText("" + currentMonth + " " + currentDay + ", " + currentYear);
	}
	
	/** 
	 *
	 * Create supplies display
	 * Contains the amounts for: food, dry goods, and water
	 *
	 */
	private void createSuppliesDisplay() {
		System.out.println("Supplies Display Created");
		suppliesFrame = new JFrame("Current Supplies");
		suppliesFrame.setBounds(1000, 100, 450, 300);
		suppliesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		suppliesPanel = new JPanel(new GridLayout(13, 1));
		
		JLabel stopFoodLabel = new JLabel("Food: " + wagon.getFoodWeight());
		suppliesPanel.add(stopFoodLabel);
		
		JLabel stopFlourLabel = new JLabel("Flour: " + wagon.getFlourAmount());
		suppliesPanel.add(stopFlourLabel);
		
		JLabel stopStarterLabel = new JLabel("Starter: " + wagon.getStarterAmount());
		suppliesPanel.add(stopStarterLabel);
		
		JLabel stopDryLabel = new JLabel("Dry Goods: " + wagon.getDryGoodsWeight());
		suppliesPanel.add(stopDryLabel);
		
		JLabel stopWaterLabel = new JLabel("Water: " + wagon.getWaterAmount());
		suppliesPanel.add(stopWaterLabel);
		
		JLabel stopClothingLabel = new JLabel("Clothing: " + wagon.getClothingAmount());
		suppliesPanel.add(stopClothingLabel);
		
		JLabel stopMoneyLabel = new JLabel("Money: " + wagon.getMoneyAmount());
		suppliesPanel.add(stopMoneyLabel);
		
		JLabel stopWheelLabel = new JLabel("Wagon Wheel: " + wagon.getWheelAmount());
		suppliesPanel.add(stopWheelLabel);
		
		JLabel stopAxelLabel = new JLabel("Wagon Axel: " + wagon.getAxelAmount());
		suppliesPanel.add(stopAxelLabel);
		
		JLabel stopTongueLabel = new JLabel("Wagon Tongue: " + wagon.getStarterAmount());
		suppliesPanel.add(stopTongueLabel);
		
		JLabel stopOxenLabel = new JLabel("Oxen: " + wagon.getOxenAmount());
		suppliesPanel.add(stopOxenLabel);
		
		JLabel stopYokeLabel = new JLabel("Oxen Yoke: " + wagon.getYokeAmount());
		suppliesPanel.add(stopYokeLabel);
		
		JButton stopCloseButton = new JButton("Close Window");
		stopCloseButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * Action performed
			 *
			 * @param e  the e.
			 * 
			 *  Closes the window
			 */
			public void actionPerformed(ActionEvent e) {
					System.out.println("Closing Supplies Menu");
					suppliesFrame.dispatchEvent(new WindowEvent(stopFrame, WindowEvent.WINDOW_CLOSING));
					stopFrame.setEnabled(true);
				}
			});
		suppliesPanel.add(stopCloseButton);
		
		suppliesFrame.getContentPane().add(suppliesPanel);
		suppliesFrame.pack();
		suppliesFrame.setVisible(true);
		stopFrame.setEnabled(false);
	}
	
	/** 
	 *
	 * Create pace window
	 * 
	 * Contains a column of buttons which sets the pace to their respective amounts
	 *
	 */
	public void createPaceWindow() {
		System.out.println("Pace Display Created");
		paceFrame = new JFrame("Set Pace");
		paceFrame.setBounds(1000, 100, 450, 300);
		paceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		pacePanel = new JPanel(new GridLayout(3, 1));
		
		JButton steadyButton = new JButton("Steady");
		steadyButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * Action performed
			 *
			 * @param e  the e. 
			 * 
			 * Sets the pace to steady (12)
			 */
			public void actionPerformed(ActionEvent e) {
					System.out.println("Pace set to Steady");
					travelRate = 12;
					paceFrame.dispatchEvent(new WindowEvent(paceFrame, WindowEvent.WINDOW_CLOSING));
				}
			});
		pacePanel.add(steadyButton);
		
		JButton strenuousButton = new JButton("Strenuous");
		strenuousButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * Action performed
			 *
			 * @param e the e.
			 * 
			 * Sets pace to strenuous (16)
			 */
			public void actionPerformed(ActionEvent e) {
					System.out.println("Pace set to Strenuous");
					travelRate = 16;
					paceFrame.dispatchEvent(new WindowEvent(paceFrame, WindowEvent.WINDOW_CLOSING));
				}
			});
		pacePanel.add(strenuousButton);
		
		JButton greulingButton = new JButton("Greuling");
		greulingButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * Action performed
			 *
			 * @param e the e. 
			 * 
			 * Sets pace to greuling (20)
			 */
			public void actionPerformed(ActionEvent e) {
					System.out.println("Pace set to Greuling");
					travelRate = 20;
					paceFrame.dispatchEvent(new WindowEvent(paceFrame, WindowEvent.WINDOW_CLOSING));
				}
			});
		pacePanel.add(greulingButton);
		
		paceFrame.getContentPane().add(pacePanel);
		paceFrame.pack();
		paceFrame.setVisible(true);
	}
	
	/** 
	 *
	 * Calculate travel distance
	 *
	 * @return int distance
	 * 
	 * Takes the global currentDistance and travelRate and subtracts them
	 * Checks if the player has made it to their nearest landmark
	 * Also checks if the landmark type the player reaches 
	 */
	public int calculateTravelDistance(){
		//Travel Distances based off of YHDOD
		currentDistance -= travelRate;
		int distance = currentDistance;
		if(distance <= 0) {
			distance = 0;
			index++;
			if(index >= landmarks.size()) {
				index = landmarks.size() - 1;
				atOregon = true;
			}
			if(landmarks.get(index - 1).getShop()) {
				atShop = true;
			}
			if(landmarks.get(index - 1).getTrade()) {
				atTrade = true;
			}
			atLandmark = true;
			currentDistance = landmarks.get(index).getDistance();
			distance = currentDistance;
		}
		return distance;
	}
	
	/** 
	 *
	 * Sets the landmarks into the ArrayList
	 *
	 */
	public void setLandmarks() {
		//Opening the CSV File and setting up the input scanner
		InputStreamReader in = new InputStreamReader(this.getClass().getResourceAsStream("/MP4_Package/Landmarks.csv"));
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
			String tempDistance = reader.next();
			int distance = Integer.parseInt(tempDistance);
			String tempTrade = reader.next();
			boolean trade = Boolean.parseBoolean(tempTrade);
			String tempShop = reader.next();
			boolean shop = Boolean.parseBoolean(tempShop);
			Landmark landmark = new Landmark(name, distance, trade, shop);
			landmarks.add(landmark);
			reader.close();
		}
		System.out.println(landmarks);
	}
	
	/** 
	 *
	 * Create consumption window
	 *
	 *	Generates a new frame which has a column of buttons which allow the player to change the consumptionRate
	 *	When a button is pressed, close the window
	 */
	public void createConsumptionWindow() {
		System.out.println("Consumption Display Created");
		consumptionFrame = new JFrame("Set Consumption Rate");
		consumptionFrame.setBounds(1000, 100, 450, 300);
		consumptionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		consumptionPanel = new JPanel(new GridLayout(3, 1));
		
		JButton fillingButton = new JButton("Filling");
		fillingButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * Action performed
			 *
			 * @param e  the e. 
			 * 
			 * Sets the consumptionRate to 3
			 */
			public void actionPerformed(ActionEvent e) {
					consumptionRate = 3;
					System.out.println("Consumption Rate set to Filling");
					consumptionFrame.dispatchEvent(new WindowEvent(consumptionFrame, WindowEvent.WINDOW_CLOSING));
				}
			});
		consumptionPanel.add(fillingButton);
		
		JButton meagerButton = new JButton("Meager");
		meagerButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * Action performed
			 *
			 * @param e  the e. 
			 * 
			 * Sets the consumptionRate to 2
			 */
			public void actionPerformed(ActionEvent e) {
					consumptionRate = 2;
					System.out.println("Consumption Rate set to Meager");
					consumptionFrame.dispatchEvent(new WindowEvent(consumptionFrame, WindowEvent.WINDOW_CLOSING));
				}
			});
		consumptionPanel.add(meagerButton);
		
		JButton bareButton = new JButton("Bare Bones");
		bareButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * Action performed
			 *
			 * @param e  the e. 
			 * 
			 * Sets the consumptionRate to 1
			 */
			public void actionPerformed(ActionEvent e) {
					consumptionRate = 1;
					System.out.println("Consumption Rate set to Bare Bones");
					consumptionFrame.dispatchEvent(new WindowEvent(consumptionFrame, WindowEvent.WINDOW_CLOSING));
				}
			});
		consumptionPanel.add(bareButton);
		
		consumptionFrame.getContentPane().add(consumptionPanel);
		consumptionFrame.pack();
		consumptionFrame.setVisible(true);
	}
	
	/** 
	 *
	 * Create rest window
	 * Contains an up and down button, a label which is updated along with the amount of days, and a close window button
	 *
	 */
	public void createRestWindow() {
		System.out.println("Rest Display Created");
		restFrame = new JFrame("Rest for how many days?");
		restFrame.setBounds(1000, 100, 450, 300);
		restFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		restPanel = new JPanel(new GridLayout(4, 1));
		
		JLabel restAmountLabel = new JLabel("" + restDays);
		
		JButton restUpButton = new JButton("Day Amount UP");
		restUpButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * Action performed
			 *
			 * @param e  the e. 
			 * 
			 * Iterates the amount of days up
			 */
			public void actionPerformed(ActionEvent e) {
					System.out.println("Rest Days up");
					restDays++;
					if(restDays > 7) {
						restDays = 7;
					}
					restAmountLabel.setText("" + restDays);
				}
			});
		restPanel.add(restUpButton);
		
		restPanel.add(restAmountLabel);
		
		JButton restDownButton = new JButton("Day Amount DOWN");
		restDownButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * Action performed
			 *
			 * @param e  the e. 
			 * 
			 * Iterates the amount of days down
			 */
			public void actionPerformed(ActionEvent e) {
					System.out.println("Rest Days down");
					restDays--;
					if(restDays < 1) {
						restDays = 1;
					}
					restAmountLabel.setText("" + restDays);
				}
			});
		restPanel.add(restDownButton);
		
		JButton calculateRestButton = new JButton("Rest");
		calculateRestButton.addActionListener(new ActionListener() {
			/** 
			 *
			 * Action performed
			 *
			 * @param e  the e. 
			 * 
			 * Closes the window and changes the date
			 */
			public void actionPerformed(ActionEvent e) {
					System.out.println("Resting for " + restDays + " Days");
					calculateTravelDate(restDays);
				}
			});
		restPanel.add(calculateRestButton);
		
		restFrame.getContentPane().add(restPanel);
		restFrame.pack();
		restFrame.setVisible(true);
	}
	
	/** 
	 *
	 * Calculate consumption amount
	 * Set the values in their respective classes
	 *
	 */
	public void calculateConsumptionAmount() {
		//Food Consumption Rates based off of YHDOD
		wagon.setFoodWeight((wagon.getFoodWeight() - (CharacterClass.getAmountOfPeople() * consumptionRate)));
		wagon.setWaterAmount(wagon.getWaterAmount() - CharacterClass.getAmountOfPeople());
		foodLabel.setText("" + wagon.getFoodWeight());
	}
	/** 
	 *
	 * It is a constructor. 
	 *
	 * Give the player options for how to cross the river 
	 */
	public void createRiverDisplay() {
		//GUI Setup
		mainFrame.setEnabled(false);
		System.out.println("Creating River");
		riverFrame = new JFrame("River Crossing");
		riverFrame.setBounds(800, 375, 450, 300);
		riverFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		riverPanel = new JPanel(new GridLayout(4, 1));
		
		//Generate the flow, depth, and height
		//Turn the data into display ready info
		String flowWord = "";
		
		int riverFlow = events.getRiverFlow();
		int riverDepth = events.getRiverDepth();
		int riverLength = events.getRiverLength();
		if (riverFlow < 7) {
			flowWord = "slow";
		}
		else {
			flowWord = "fast";
		}
		JLabel riverLabel = new JLabel("You come across a " + flowWord + " river which is " + riverDepth + " ft deep and " + riverLength + " ft wide");
		riverPanel.add(riverLabel);
		
		JButton fordButton = new JButton("Ford the River");
		fordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					mainFrame.setEnabled(true);
					System.out.println("Fording the River...");
					riverFrame.dispatchEvent(new WindowEvent(riverFrame, WindowEvent.WINDOW_CLOSING));
					//Check if the player is successful based off of RNG 
					//Success
					if(events.riverFord(riverDepth)){
						popUP.showMessageDialog(null, "Successful River Crossing", "River Gaming", JOptionPane.PLAIN_MESSAGE);
						System.out.println("Successful");
					}
					//Fail
					//Item loss will be randomized at a later time
					else{
						popUP.showMessageDialog(null, "Unsuccessful River Crossing", "River Gaming", JOptionPane.ERROR_MESSAGE);
						wagon.wagonWheelAmount -= 2;
						wagon.wagonAxelAmount -= 2;
						wagon.wagonTongueAmount -= 2;
					}
				}
			});
		riverPanel.add(fordButton);
		
		JButton floatButton = new JButton("Float across the River");
		floatButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					mainFrame.setEnabled(true);
					//Check if the player is successful based off of RNG 
					//Success
					if(events.riverFloat(riverDepth)){
						popUP.showMessageDialog(null, "Successful River Crossing", "River Gaming", JOptionPane.PLAIN_MESSAGE);
						System.out.println("Successful");
					}
					//Fail
					//Item loss will be randomized at a later time
					else{
						popUP.showMessageDialog(null, "Unsuccessful River Crossing", "River Gaming", JOptionPane.ERROR_MESSAGE);
						wagon.wagonWheelAmount -= 2;
						wagon.wagonAxelAmount -= 2;
						wagon.wagonTongueAmount -= 2;
					}
				}
			});
		riverPanel.add(floatButton);
		
		JButton ferryButton = new JButton("Take the ferry");
		ferryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					mainFrame.setEnabled(true);
					//Check if the player is successful based off of RNG 
					//Success
					if(events.riverFerry()){
						popUP.showMessageDialog(null, "Successful River Crossing", "River Gaming", JOptionPane.PLAIN_MESSAGE);
						System.out.println("Successful");
						wagon.moneyAmount -= 5;
					}
					//Fail
					//Item loss will be randomized at a later time
					else{
						popUP.showMessageDialog(null, "Unsuccessful River Crossing", "River Gaming", JOptionPane.ERROR_MESSAGE);
						wagon.wagonWheelAmount -= 2;
						wagon.wagonAxelAmount -= 2;
						wagon.wagonTongueAmount -= 2;
					}
				}
			});
		riverPanel.add(ferryButton);
		
//		GUI
		riverFrame.getContentPane().add(riverPanel);
		riverFrame.pack();
		riverFrame.setVisible(true);
	}
	/** 
	 *
	 * It is a constructor. 
	 * 
	 * Generates a trade at random on button press
	 */
	public void createTradeDisplay() {
		System.out.println("Creating Trade");
		//GUI setup
		tradeFrame = new JFrame("Trading");
		tradeFrame.setBounds(800, 375, 450, 300);
		tradeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		tradePanel = new JPanel(new GridLayout(1, 3));
		
		//Generate the trade
		trader.initializeTrade();
		
		//Display what the trader wants
		JLabel tradeLabel = new JLabel("A trader offers " + trader.getTraderQuantity() + " " + trader.getTraderItem() + ", they want " + trader.getComputerQuantity() + " " + trader.getComputerItem());
		
		//When trade is accepted
		JButton acceptTrade = new JButton("DEAL");
		acceptTrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int currentComputerItemAmount = 0;
				System.out.println("Trade accepted");
				//Get the current amount of the desired item in the users inventory
				//Will be used as a check later
				switch(trader.getComputerItem()) {
					case "Oxen": currentComputerItemAmount = wagon.getOxenAmount(); break;
					case "Yoke": currentComputerItemAmount = wagon.getYokeAmount(); break;
					case "Wagon_Wheel": currentComputerItemAmount = wagon.getWheelAmount(); break;
					case "Wagon_Axel": currentComputerItemAmount = wagon.getAxelAmount(); break;
					case "Wagon_Tongue": currentComputerItemAmount = wagon.getTongueAmount(); break;
					case "Water": currentComputerItemAmount = wagon.getWaterAmount(); break;
					case "Flour": currentComputerItemAmount = wagon.getFlourAmount(); break;
					case "Starter": currentComputerItemAmount = wagon.getStarterAmount(); break;
					case "Clothing": currentComputerItemAmount = wagon.getClothingAmount(); break;
				}
				//Later is now, check if the user has enough
				if(currentComputerItemAmount > trader.getTraderQuantity()) {
					//If yes perform an exchange for the trader's items and the users
					//Adding the items from the trader
					switch(trader.getTraderItem()) {
						case "Oxen": wagon.setOxenAmount(wagon.getOxenAmount() + trader.getTraderQuantity()); break;
						case "Yoke": wagon.setYokeAmount(wagon.getYokeAmount() + trader.getTraderQuantity()); break;
						case "Wagon_Wheel": wagon.setWheelAmount(wagon.getWheelAmount() + trader.getTraderQuantity()); break;
						case "Wagon_Axel": wagon.setAxelAmount(wagon.getAxelAmount() + trader.getTraderQuantity()); break;
						case "Wagon_Tongue": wagon.setTongueAmount(wagon.getTongueAmount() + trader.getTraderQuantity()); break;
						case "Water": wagon.setWaterAmount(wagon.getWaterAmount() + trader.getTraderQuantity()); break;
						case "Flour": wagon.setFlourAmount(wagon.getFlourAmount() + trader.getTraderQuantity()); break;
						case "Starter": wagon.setStarterAmount(wagon.getStarterAmount() + trader.getTraderQuantity()); break;
						case "Clothing": wagon.setClothingAmount(wagon.getClothingAmount() + trader.getTraderQuantity()); break;
					}
					//Taking the items from the player
					switch(trader.getComputerItem()) {
						case "Oxen": wagon.setOxenAmount(wagon.getOxenAmount() - trader.getComputerQuantity()); break;
						case "Yoke": wagon.setYokeAmount(wagon.getYokeAmount() - trader.getComputerQuantity()); break;
						case "Wagon_Wheel": wagon.setWheelAmount(wagon.getWheelAmount() - trader.getComputerQuantity()); break;
						case "Wagon_Axel": wagon.setAxelAmount(wagon.getAxelAmount() - trader.getComputerQuantity()); break;
						case "Wagon_Tongue": wagon.setTongueAmount(wagon.getTongueAmount() - trader.getComputerQuantity()); break;
						case "Water": wagon.setWaterAmount(wagon.getWaterAmount() - trader.getComputerQuantity()); break;
						case "Flour": wagon.setFlourAmount(wagon.getFlourAmount() - trader.getComputerQuantity()); break;
						case "Starter": wagon.setStarterAmount(wagon.getStarterAmount() - trader.getComputerQuantity()); break;
						case "Clothing": wagon.setClothingAmount(wagon.getClothingAmount() - trader.getComputerQuantity()); break;
					}	
					popUP.showMessageDialog(null, "Pleasure Doing Bussiness", "Trader Gaming", JOptionPane.PLAIN_MESSAGE);
					tradeCompleted = true;
					tradeFrame.dispatchEvent(new WindowEvent(tradeFrame, WindowEvent.WINDOW_CLOSING));
				}
				else {
					popUP.showMessageDialog(null, "Sorry but you don't have enough", "Trader Gaming", JOptionPane.PLAIN_MESSAGE);
					tradeFrame.dispatchEvent(new WindowEvent(tradeFrame, WindowEvent.WINDOW_CLOSING));
				}
			}
		});
		
		//When trade is denied, do nothing
		JButton denyTrade = new JButton("NO DEAL");
		denyTrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Trade denied");
				popUP.showMessageDialog(null, "That's a shame", "Trader Gaming", JOptionPane.PLAIN_MESSAGE);
				tradeFrame.dispatchEvent(new WindowEvent(tradeFrame, WindowEvent.WINDOW_CLOSING));
			}
		});
		
		//GUI
		tradePanel.add(acceptTrade);
		tradePanel.add(tradeLabel);
		tradePanel.add(denyTrade);
		tradeFrame.getContentPane().add(tradePanel);
		tradeFrame.pack();
		tradeFrame.setVisible(true);
	}
	/** 
	 *
	 * It is a constructor. 
	 *
	 * @param location  the location. 
	 * 
	 * Randomizes the author then uses the location to get the conversation
	 */
	public void createConversationDisplay(String location) {
		String conversationText = "";
		conversationSeed = random.nextInt(3) + 1;
		
		//Author selection
		switch (conversationSeed) {
			case 1: nameSeed = "Brian"; System.out.println("Brian Conversation"); break;
			case 2: nameSeed = "Mason"; System.out.println("Mason Conversation"); break;
			case 3: nameSeed = "Anthony"; System.out.println("Anthony Conversation"); break;
		}
		if(nameSeed.isEmpty()) {
			System.out.println("Houston we have a problem");
		}
		
		System.out.println("Creating Conversation");
		conversationFrame = new JFrame("You stop to talk");
		conversationFrame.setBounds(800, 375, 300, 300);
		conversationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//Get the conversation txt file
		System.out.println("/Conversations_" + nameSeed + "/" + location + ".txt");
		InputStreamReader in = new InputStreamReader(this.getClass().getResourceAsStream("/Conversations_" + nameSeed + "/" + location + ".txt"));
		Scanner scr = new Scanner(in);
		//Formatting is a pain but here it is
		//HTML was the easiest way to do this
		conversationText += "<html>";
		while (scr.hasNextLine()) {
			String tempText = scr.nextLine();
			conversationText += tempText + "<br>";
		}
		conversationText += "</html>";
		
		//Make a label and add the conversation from above to it
		JLabel conversationLabel = new JLabel(conversationText);
		conversationFrame.getContentPane().add(conversationLabel);
		
		conversationFrame.pack();
		conversationFrame.setVisible(true);
	}
	
	//Not yet implemented, please ignore for the time being
	//Will eventually become a baking QTE like Cooking Mama
	public void createBakingDisplay() {
		popUP.showMessageDialog(null, "Press the space bar on the green", "Cooking Mama Gaming", JOptionPane.PLAIN_MESSAGE);
		int greenStart = random.nextInt(30) + 50;
		int greenSpace = random.nextInt(5) + 5;
		int currentLocation = 0;
		
		System.out.println("Creating Baking Minigame");
		bakingFrame = new JFrame("Bake");
		bakingFrame.setBounds(800, 375, 100, 100);
		bakingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		bakingFrame.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				int vk = e.getKeyCode();
				if(vk == KeyEvent.VK_SPACE) {
					System.out.println("Space Bar Pressed");
					if(currentLocation >= greenStart && currentLocation <= greenStart + greenSpace) {
						System.out.println("Bread Baked");
					}
					else {
						System.out.println("Baking Unsuccessful");
					}
				}
			}
			public void keyReleased(KeyEvent e) {
			}
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		JLabel redSpaceLabel = new JLabel("");
		redSpaceLabel.setBounds(0, 0, 100, 50);
		bakingFrame.getContentPane().add(redSpaceLabel);
		JLabel greenSpaceLabel = new JLabel("");
		greenSpaceLabel.setBounds(greenStart, 0, greenSpace, 50);
		bakingFrame.getContentPane().add(greenSpaceLabel);
		
		bakingFrame.pack();
		bakingFrame.setVisible(true);
	}
	/** 
	 *
	 * It is a constructor. 
	 *
	 *	Creates the graphics seen in the main frame
	 */
	public void createWagonGUI() {
		ImageIcon backgroundGraphic = new ImageIcon(this.getClass().getResource("/Assets/Bliss_(Windows_XP).png"));
		Image backImg = backgroundGraphic.getImage().getScaledInstance(750, 350, Image.SCALE_SMOOTH);
		
		//Puts an inconspicuous background image for the wagon to travel on
		JPanel backgroundPanel = new JPanel();
		backgroundPanel.setBounds(0, 0, 750, 350);
		JLabel backgroundLabel = new JLabel();
		backgroundLabel.setIcon(new ImageIcon(backImg));
		backgroundPanel.add(backgroundLabel);
		imagePanel.add(backgroundPanel, 0, 0);
		
		//Puts a wagon graphic in a panel then puts the panel in the layered frame
		System.out.println("Creating Wagon Graphic");
		wagonImagePanel = new JPanel();
		wagonImagePanel.setOpaque(false);
		wagonImagePanel.setBounds(200, 200, 254, 94);
		wagonImagePanel.setLocation(450, 200);
		
		JLabel wagonGraphicLabel = new JLabel();
		wagonGraphicLabel.setBounds(0, 0, 127, 47);
		
		//Scale the wagon to a usable size
		ImageIcon wagonGraphic = new ImageIcon(this.getClass().getResource("/Assets/Oregon_Trail_Wagon.png"));
		Image newImg = wagonGraphic.getImage().getScaledInstance(254 / 2, 94 / 2, Image.SCALE_SMOOTH);
		wagonGraphicLabel.setIcon(new ImageIcon(newImg));
		//ImageIcon landmarkGraphic = new ImageIcon();
		
		wagonImagePanel.add(wagonGraphicLabel);
		imagePanel.add(wagonImagePanel, 1, 0);
	}
}