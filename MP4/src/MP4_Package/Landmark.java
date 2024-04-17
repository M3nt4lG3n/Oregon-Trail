/*
 * File: Landmark.java
 * Author: Brian Bizon
 * Date: 4/9/2024
 * Version: 1.0.0 
 */

package MP4_Package;

public class Landmark {
	private String landmarkName = "";
	private int landmarkDistance = 0;
	private boolean landmarkTradeEnabled = false;
	private boolean landmarkShopEnabled = false;
	
	/** 
	 *
	 * It is a constructor. 
	 *
	 * @param name  the name. 
	 * @param distanceFromLast  the distance from last. 
	 * @param tradeEnabled  the trade enabled. 
	 * @param shopEnabled  the shop enabled. 
	 */
	public Landmark(String name, int distanceFromLast, boolean tradeEnabled, boolean shopEnabled) {		
		landmarkName = name;
		landmarkDistance = distanceFromLast;
		landmarkTradeEnabled = tradeEnabled;
		landmarkShopEnabled = shopEnabled;
	}
	
	/** 
	 *
	 * Sets the name
	 *
	 * @param name  the name. 
	 */
	public void setName(String name) {
		landmarkName = name;
	}
	
	/** 
	 *
	 * Gets the name
	 *
	 * @return the name
	 */
	public String getName() {
		return landmarkName;
	}
	
	/** 
	 *
	 * Sets the distance
	 *
	 * @param distance  the distance. 
	 */
	public void setDistance(int distance) {
		landmarkDistance = distance;
	}
	
	/** 
	 *
	 * Gets the distance
	 *
	 * @return the distance
	 */
	public int getDistance() {
		return landmarkDistance;
	}
	
	/** 
	 *
	 * Sets the trade
	 *
	 * @param trade  the trade. 
	 */
	public void setTrade(boolean trade) {
		landmarkTradeEnabled = trade;
	}
	
	/** 
	 *
	 * Gets the trade
	 *
	 * @return the trade
	 */
	public boolean getTrade() {
		return landmarkTradeEnabled;
	}
	
	/** 
	 *
	 * Sets the enabled
	 *
	 * @param shop  the shop. 
	 */
	public void setEnabled(boolean shop) {
		landmarkShopEnabled = shop;
	}
	
	/** 
	 *
	 * Gets the shop
	 *
	 * @return the shop
	 */
	public boolean getShop() {
		return landmarkShopEnabled;
	}
}
