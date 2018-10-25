/**
 * 
 */
package edu.ncsu.csc216.simulation.actor;

import java.awt.Color;

import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.utils.Location;

/**
 * Creates animal of highest rank
 * @author John Ruisi
 *
 */
public class PurePredator extends Animal {

	/**
	 * Creates animal of PurePredator type
	 * @param c char the symbol of the animal
	 */
	public PurePredator(char c) {
		super(c);
	}
	
	/**
	 * gets the color of the animal
	 * @return the color of the animal
	 */
	public Color getColor() {
		return Configs.getPredatorColor();
	}
	
	/**
	 * Predator acts tring to eat breed then move
	 * @param l location location of the animal
	 * @param e grid of ecosystem
	 */
	public void act(Location l, EcoGrid e) {
		if (canAct()) {

			if (eat(l, e)) {
				incrementTimeSinceLastBreed();
			} else if (getTimeSinceLastBreed() >= Configs.getPredatorBreedTime()) {
				breed(l, e);
				incrementTimeSinceLastMeal();
			} else {
				move(l, e);
				incrementTimeSinceLastBreed();
				incrementTimeSinceLastMeal();
			}
			
//			if (!eat(l, e)) {
//				if (getTimeSinceLastBreed() >= Configs.getPredatorBreedTime()) {
//					breed(l, e);
//				} else {
//					move(l, e);
//					incrementTimeSinceLastBreed();
//				}
//				incrementTimeSinceLastMeal();
//				
//			}
		
			if (getTimeSinceLastMeal() >= Configs.getPredatorStarveTime()) {
				die();
			}

			disable();
		}
	}
	
	/**
	 * Calculates if animal is past its breed time
	 * @param i time since last bred
	 * @return false if its not past breed time
	 */
	protected boolean pastBreedTime(int i) {
		if (i >= Configs.getPredatorBreedTime()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Makes baby
	 * @return a the baby
	 */
	protected Animal makeNewBaby() {
		Animal a = new PurePredator(this.getSymbol());
		a.disable();
		return a;
	}
	
	/**
	 * gets rank of the animal
	 * @return the rank of the animal
	 */
	protected int getFoodChainRank() {
		return Configs.getPredatorFoodChainRank();
	}

}
