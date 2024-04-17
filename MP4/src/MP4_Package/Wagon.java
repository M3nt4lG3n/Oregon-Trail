/*
 * File: Wagon.java
 * Author: Mason Greenawalt
 * Date: 4/9/2024
 * Version: 2.0.0 
 */

package MP4_Package;

import java.util.ArrayList;

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
}
