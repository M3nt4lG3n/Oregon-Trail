/*
 * File: Trader.java
 * Author: Mason Greenawalt
 * Date: 4/17/2024
 * Version: 1.0.0
 */
package MP4_Package;

import java.util.Random;



public class Trader {

    private String[] items = {"Oxen", "Yoke", "Wagon_Wheel", "Wagon_Axel", "Wagon_Tongue", "Water", "Flour", "Starter", "Clothing"};
    private Random random = new Random();
    private String traderItem;
    private int traderQuantity;
    private String computerItem;
    private int computerQuantity;

    public void initializeTrade() {
        // Select a random item for the trader
        traderItem = items[random.nextInt(items.length)];
        
        // Generate a random quantity (1-50)
        traderQuantity = random.nextInt(50) + 1;
        
        // Select a random item for the computer
        computerItem = items[random.nextInt(items.length)];

        // Generate a random quantity (1-50)
        computerQuantity = random.nextInt(50) + 1;
        System.out.println(traderItem);
        System.out.println(traderQuantity);
        System.out.println(computerItem);
        System.out.println(computerQuantity);
    }
    
    public String getTraderItem() {
    	return traderItem;
    }
    
    public int getTraderQuantity() {
    	return traderQuantity;
    }
    
    public String getComputerItem() {
    	return computerItem;
    }
    
    public int getComputerQuantity() {
    	return computerQuantity;
    }
    
    public boolean checkInventory(){
    	boolean inInventory = true;
    	return inInventory;
    }
}