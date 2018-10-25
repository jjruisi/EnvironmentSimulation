package edu.ncsu.csc216.simulation.actor;

import java.awt.Color;

/**
 * Configs sets up the values for important values such as the time it takes for animals to breed, eat, and 
 * the colors of the animals. Can eaither have default or custom values.
 * @author John Ruisi
 *
 */
public class Configs {

	/** field defualt ranks */
	private static final int[] DEFAULT_FOOD_CHAIN_RANK = {0, 10, 20};
	/** field default colors */
	private static final Color[] DEFAULT_COLORS = {Color.green, Color.orange, Color.red};
	/** field  default starve times*/
	private static final int[] DEFAULT_STARVE_TIME = {10, 6, 5};
	/** field default breed times*/
	private static final int[] DEFAULT_BREED_TIME = {1, 7, 15};
	/** field  player colors*/
	private static Color[] playerColors;
	/** field starve times*/
	private static int[] starveTime;
	/** field breed times*/
	private static int[] breedTime;
	
	/**
	 * Initialize configs with custom values
	 * @param color color array of animals
	 * @param s int array of starve times
	 * @param b int array of breed times
	 */
	public static void initConfigs(Color[] color, int[] s, int[] b) {
		playerColors = color;
		starveTime = s;
		breedTime = b;
	}
	
	/**
	 * sets the configs to default values
	 */
	public static void setToDefaults() {
		initConfigs(DEFAULT_COLORS, DEFAULT_STARVE_TIME, DEFAULT_BREED_TIME);
	}
	
	/**
	 * gets the prey color
	 * @return the prey color
	 */
	public static Color getPreyColor() {
		return playerColors[0];
	}
	
	/**
	 * gets the middle color
	 * @return the middle color
	 */
	public static Color getMiddleColor() {
		return playerColors[1];
	}
	
	/**
	 * gets the predator color
	 * @return the predator color
	 */
	public static Color getPredatorColor() {
		return playerColors[2];
	}
	
	/**
	 * gets the prey rank
	 * @return the prey rank
	 */
	public static int getPreyFoodChainRank() {
		return DEFAULT_FOOD_CHAIN_RANK[0];
	}
	
	/**
	 * gets the middle rank
	 * @return the middle rank
	 */
	public static int getMiddleFoodChainRank() {
		return DEFAULT_FOOD_CHAIN_RANK[1];
	}
	
	/**
	 * gets the predator rank
	 * @return the predator rank
	 */
	public static int getPredatorFoodChainRank() {
		return DEFAULT_FOOD_CHAIN_RANK[2];
	}
	
	/**
	 * gets the prey starve time
	 * @return the prey starve time
	 */
	public static int getPreyStarveTime() {
		return starveTime[0];
	}
	
	/**
	 * gets the middle starve time
	 * @return the middle starve time
	 */
	public static int getMiddleStarveTime() {
		return starveTime[1];
	}
	
	/**
	 * gets the predator starve time
	 * @return the predator starve time
	 */
	public static int getPredatorStarveTime() {
		return starveTime[2];
	}
	
	/**
	 * gets the prey breed time
	 * @return the prey breed time
	 */
	public static int getPreyBreedTime() {
		return breedTime[0];
	}
	
	/**
	 * get the middle breed time
	 * @return the middle breed time
	 */
	public static int getMiddleBreedTime() {
		return breedTime[1];
	}
	
	/**
	 * gets the predator breed time
	 * @return the predator breed time
	 */
	public static int getPredatorBreedTime() {
		return breedTime[2];
	}
}