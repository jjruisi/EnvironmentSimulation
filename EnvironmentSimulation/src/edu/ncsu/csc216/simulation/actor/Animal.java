package edu.ncsu.csc216.simulation.actor;

import java.awt.Color;
import java.util.Random;

import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.utils.Location;

/**
 * Animal class creates animals that are used to populate the ecosystem
 * grid. The animals keep track of their survival fields including how long it
 * has been since they have bred or eaten.
 * @author John Ruisi
 *
 */
public abstract class Animal {
	/** field time since animal last ate */
	private int timeSinceLastMeal;
	/** field time since animal last bred*/
	private int timeSinceLastBreed;
	/** field if animal is able to act this step*/
	private boolean canActThisStep;
	/** field symbol of the animal*/
	private char symbol;
	/** field the state of being alive */
	private boolean alive;
	/** field seed for testing random*/
	private static int seed = 500;
	/** field random generator for move method*/
	private static Random randomGenerator = new Random(seed);

	/**
	 * Constructs animal with a specific symbol
	 * @param c char of the animal
	 */
	public Animal(char c) {
		this.symbol = c;
		this.timeSinceLastBreed = 0;
		this.timeSinceLastMeal = 0;
		this.alive = true;
		this.canActThisStep = false;
	}
	
	/**
	 * temporary sets random seed for generator
	 * @param i int
	 */
	public static void setRandomSeed(int i) {
		seed = randomGenerator.nextInt(i);
	}
	
	/**
	 * gets the symbol of the animal
	 * @return symbol the symbol of the animal
	 */
	public char getSymbol() {
		return symbol;
	}
	
	/**
	 * returns whether the animal is alive or not
	 * @return alive the state of living
	 */
	public boolean isAlive() {
		return alive;
	}
	
	/**
	 * enables the animal to act
	 */
	public void enable() {
		canActThisStep = true;
	}
	
	/**
	 * disaables the animal, not allowing it to act
	 */
	public void disable() {
		canActThisStep = false;
	}
	
	/**
	 * sets the animal to be dead
	 */
	protected void die() {
		this.alive = false;
	}
	
	/**
	 * decides if the animal can act
	 * @return false if the animal cannot act
	 */
	protected boolean canAct() {
		if (canActThisStep && this != null) {
			return true;
		} 
		return false;
	}
	
	/**
	 * gets the time since animal has last bred
	 * @return timeSinceLastBreed the time since last breed
	 */
	protected int getTimeSinceLastBreed() {
		return timeSinceLastBreed;
	}
	
	/**
	 * gets the time since animal has last ate
	 * @return timeSinceLastMeal the time since last eating
	 */
	protected int getTimeSinceLastMeal() {
		return timeSinceLastMeal;
	}
	
	/**
	 * increments the time the animal has last ate
	 */
	protected void incrementTimeSinceLastMeal() {
		timeSinceLastMeal++;
	}
	
	/**
	 * increments the time the animal has last bred
	 */
	protected void incrementTimeSinceLastBreed() {
		timeSinceLastBreed++;
	}
	
	/**
	 * Breeds an animal if it is able to breed depending on its fields
	 * @param l location of the animal
	 * @param e the Ecosystem grid
	 * @return true if the animal is able to breed
	 */
	protected boolean breed(Location l, EcoGrid e) {
		
		if (pastBreedTime(timeSinceLastBreed) && e.findFirstEmptyNeighbor(l, 0) != null) {
			e.add(this.makeNewBaby(), e.findFirstEmptyNeighbor(l, 0));
			timeSinceLastBreed = 0;
			return true;
			
		}
		return false;
	}
	
	/**
	 * Moves the animal if their is an open space to move
	 * @param l location of the animal
	 * @param e Ecosystem grid
	 */
	protected void move(Location l, EcoGrid e) {
		
		int start = randomGenerator.nextInt(3);
		if (e.findFirstEmptyNeighbor(l, start) != null) {
			e.add(this, e.findFirstEmptyNeighbor(l, start));
			e.remove(l);
		}
		
		
	}
	
	/**
	 * The animal eats an adjacent animal if it is higher in rank
	 * @param l location of the animal
	 * @param e Ecosystem grid
	 * @return false if animal cannot eat
	 */
	protected boolean eat(Location l, EcoGrid e) {
	
		if (!e.isEmpty(e.dueWest(l)) && this.getFoodChainRank() > e.getItemAt(e.dueWest(l)).getFoodChainRank()) {

			e.remove(e.dueWest(l));
			e.add(this, e.dueWest(l));
			e.remove(l);
			this.timeSinceLastMeal = 0;
			return true;
			
		} else if (!e.isEmpty(e.dueNorth(l)) && this.getFoodChainRank() > e.getItemAt(e.dueNorth(l)).getFoodChainRank()) {
			e.remove(e.dueNorth(l));
			e.add(this, e.dueNorth(l));
			e.remove(l);
			this.timeSinceLastMeal = 0;
			return true;
			
		} else if (!e.isEmpty(e.dueEast(l)) && this.getFoodChainRank() > e.getItemAt(e.dueEast(l)).getFoodChainRank()) {

			e.remove(e.dueEast(l));
			e.add(this, e.dueEast(l));
			e.remove(l);
			this.timeSinceLastMeal = 0;
			return true;
			
		} else if (!e.isEmpty(e.dueSouth(l)) && this.getFoodChainRank() > e.getItemAt(e.dueSouth(l)).getFoodChainRank()) {
			e.remove(e.dueSouth(l));
			e.add(this, e.dueSouth(l));
			e.remove(l);
			this.timeSinceLastMeal = 0;
			return true;
			
		}
		return false;
	}
	
	/**
	 * gets color of animal
	 * @return Color color of animal
	 */
	public abstract Color getColor();
	
	/**
	 * Animal acts based off its rank
	 * @param l location of animal
	 * @param e grid of ecosystem
	 */
	public abstract void act(Location l, EcoGrid e);
	
	/**
	 * determines if animal is past its breed time
	 * @param i time since breed
	 * @return true if its past the breed time
	 */
	protected abstract boolean pastBreedTime(int i);
	
	/**
	 * Animal makes new baby of its specific type
	 * @return Animal baby of specific type
	 */
	protected abstract Animal makeNewBaby();
	
	/**
	 * Gets rank of animal
	 * @return int rank of animal
	 */
	protected abstract int getFoodChainRank();

}