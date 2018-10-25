/**
 * 
 */
package edu.ncsu.csc216.simulation.actor;

import java.awt.Color;

import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.utils.Location;

/**
 * Creates an animal of lowest rank
 * @author John Ruisi
 *
 */
public class PurePrey extends Animal {
	/** field age of animal*/
	private int age;
	
	/**
	 * Creates animal of prey type and sets age to 0
	 * @param c the symbol of the animal
	 */
	public PurePrey(char c) {
		super(c);
		this.age = 0;
	}
	
	/**
	 * gets the color of the animal
	 * @return the color of the animal
	 */
	public Color getColor() {
		return Configs.getPreyColor();
	}
	
	/**
	 * Prey tries to act by breeding then moving
	 * @param l location of the animal
	 * @param e grid of ecosystem
	 */
	public void act(Location l, EcoGrid e) {
		if (canAct()) {
			age++;
 			if (getTimeSinceLastBreed() >= Configs.getPreyBreedTime()) {
				breed(l, e);
			} else {
				move(l, e);
				incrementTimeSinceLastBreed();
			}
		
			if (age >= Configs.getPreyStarveTime()) {
				die();
			}
		
			disable();
		}
	}
	
	/**
	 * calculates if animal is past its breed time
	 * @param i int time since last bred
	 * @return false if not time to breed
	 */
	protected boolean pastBreedTime(int i) {
		if (i >= Configs.getPreyBreedTime()) {
			return true;
		}
		return false;
	}
	
	/**
	 * tcreates new baby of prey type
	 * @return a the baby
	 */
	protected Animal makeNewBaby() {
		Animal a = new PurePrey(this.getSymbol());
		a.disable();
		return a;
	}
	
	/**
	 * Gets rank of prey
	 * @return the rank of the prey
	 */
	protected int getFoodChainRank() {
		return Configs.getPreyFoodChainRank();
	}

}