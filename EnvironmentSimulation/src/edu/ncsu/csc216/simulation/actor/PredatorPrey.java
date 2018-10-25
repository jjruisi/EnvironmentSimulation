/**
 * 
 */
package edu.ncsu.csc216.simulation.actor;

import java.awt.Color;

import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.utils.Location;

/**
 * Created a animal of middle rank
 * @author John Ruisi
 *
 */
public class PredatorPrey extends Animal {

	/**
	 * Created animal of PredatorPrey type
	 * @param c char the symbol of the animal
	 */
	public PredatorPrey(char c) {
		super(c);
	}

	/**
	 * gets the color of the animal
	 * @return the color of the animal
	 */
	public Color getColor() {
		return Configs.getMiddleColor();
	}
	
	/**
	 * Predator prey acts trying to breed, eat, then move
	 * @param l location of the animal
	 * @param e grid of ecosystem
	 */
	public void act(Location l, EcoGrid e) {
		if (canAct()) {

			if (getTimeSinceLastBreed() >= Configs.getMiddleBreedTime()) {
				breed(l, e);
				incrementTimeSinceLastMeal();
			} else if (eat(l, e)) {
				incrementTimeSinceLastBreed();
			} else {
				move(l, e);
				incrementTimeSinceLastBreed();
				incrementTimeSinceLastMeal();
				
			}
		
			if (getTimeSinceLastMeal() >= Configs.getMiddleStarveTime()) {
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
		if (i >= Configs.getMiddleBreedTime()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Makes new PredatorPrey baby
	 * @return a the baby
	 */
	protected Animal makeNewBaby() {
		Animal a = new PredatorPrey(this.getSymbol());
		a.disable();
		return a;
	}
	
	/**
	 * gets rank of PredatorPrey
	 * @return animals rank
	 */
	protected int getFoodChainRank() {
		return Configs.getMiddleFoodChainRank();
	}
}