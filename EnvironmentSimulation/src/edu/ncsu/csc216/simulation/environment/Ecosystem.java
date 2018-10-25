/**
 * 
 */
package edu.ncsu.csc216.simulation.environment;

import edu.ncsu.csc216.simulation.actor.Animal;
import edu.ncsu.csc216.simulation.environment.utils.Location;

/**
 * Ecosystem creates an grid that hold animal and facilitates
 * the addition of animal to the grid map.
 * @author John Ruisi
 *
 */
public class Ecosystem implements EcoGrid {

	/** field max rows of grid */
	private int maxRows;
	/** field max cols of grid*/
	private int maxCols;
	/** field map used to add animals*/
	private Animal[][] map;
	
	/**
	 * Creates object of type Ecosystem and declares rows and columns
	 * @param i the max rows
	 * @param j the max cols
	 */
	public Ecosystem(int i, int j) {
		this.maxRows = i;
		this.maxCols = j;
		this.map = new Animal[maxRows][maxCols];
	}


	/**
	 * Is the grid cell empty
	 * @param l location of animal
	 * @return true if no animal in cell
	 */
	public boolean isEmpty(Location l) {
		if (map[l.getRow()][l.getCol()] == null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Gets animal at cell
	 * @param l location of animal
	 * @return a the animal at grid cell
	 */
	public Animal getItemAt(Location l) {
		
		if (map[l.getRow()][l.getCol()] == null) {
			return null;
		}
		
		Animal a = map[l.getRow()][l.getCol()];
		return a;
	}
	
	/**
	 * Removes animal from given grid cell
	 * @param l location of the animal
	 */
	public void remove(Location l) {
		map[l.getRow()][l.getCol()] = null;
	}
	
	/**
	 * Adds animal to given location
	 * @param a animal being added
	 * @param l location of animal
	 */
	public void add (Animal a, Location l) {
		if (a == null) {
			remove(l);
		}
		
		map[l.getRow()][l.getCol()] = a;
	}
	
	/**
	 * Finds the first available space starting at different directions
	 * @param l location of teh animal
	 * @param startDirection the starting direction to search
	 * @return location the location of emprty neighbor
	 */
	public Location findFirstEmptyNeighbor(Location l, int startDirection) {
		if (startDirection == 0) {
			if (isEmpty(dueWest(l))) {
				return dueWest(l);
			} else if (isEmpty(dueNorth(l))) {
				return dueNorth(l);
			} else if (isEmpty(dueEast(l))) {
				return dueEast(l);
			} else if (isEmpty(dueSouth(l))) {
				return dueSouth(l);
			}
		} else if (startDirection == 1) {
			if (isEmpty(dueNorth(l))) {
				return dueNorth(l);
			} else if (isEmpty(dueEast(l))) {
				return dueEast(l);
			} else if (isEmpty(dueSouth(l))) {
				return dueSouth(l);
			} else if (isEmpty(dueWest(l))) {
				return dueWest(l);
			}
		} else if (startDirection == 2) {
			if (isEmpty(dueEast(l))) {
				return dueEast(l);
			} else if (isEmpty(dueSouth(l))) {
				return dueSouth(l);
			} else if (isEmpty(dueWest(l))) {
				return dueWest(l);
			} else if (isEmpty(dueNorth(l))) {
				return dueNorth(l);
			}
		} else if (startDirection == 3) {
			if (isEmpty(dueSouth(l))) {
				return dueSouth(l);
			} else if (isEmpty(dueWest(l))) {
				return dueWest(l);
			} else if (isEmpty(dueNorth(l))) {
				return dueNorth(l);
			} else if (isEmpty(dueEast(l))) {
				return dueEast(l);
			}
		}
		return null;
	}
	
	/**
	 * cell above given cell
	 * @param l location 
	 * @return north location above given cell
	 */
	public Location dueNorth(Location l) {
		int row = l.getRow() - 1;
		int col = l.getCol();
		
		if (row < 0) {
			row = maxRows - 1;
		}
		
		Location north = new Location(row, col);
		return north;
	}
	
	/**
	 * cell below given cell
	 * @param l location
	 * @return south location below given cell
	 */
	public Location dueSouth(Location l) {
		int row = l.getRow() + 1;
		int col = l.getCol();
		
		if (row > maxRows - 1) {
			row = 0;
		}
		
		Location south = new Location(row, col);
		return south;
	}
	
	/**
	 * cell to the right of given cell
	 * @param l location
	 * @return east cell to the right
	 */
	public Location dueEast(Location l) {
		int row = l.getRow();
		int col = l.getCol() + 1;
		
		if (col > maxRows - 1) {
			col = 0;
		}
		
		Location east = new Location(row, col);
		return east;
	}
	
	/**
	 * cell to the left of given cell
	 * @param l location
	 * @return west cell to the left
	 */
	public Location dueWest(Location l) {
		int row = l.getRow();
		int col = l.getCol() - 1;
		
		if (col < 0) {
			col = maxCols - 1;
		}
		Location west = new Location(row, col);
		return west;
	}
	
	/**
	 * Gets the map of animal objects
	 * @return map the array of animal objects
	 */
	public Animal[][] getMap() {
		return map;
	}
	
	/**
	 * Enables all the living animals on the map
	 */
	public void enableTheLiving() {
		for (int i = 0 ; i < maxRows; i++) {
			for (int j = 0; j < maxCols; j++) {
				Animal a = map[i][j];
				if (a == null) {
					continue;
				}
				if (a.isAlive()) {
					a.enable();
				} 
			}
		}
	}
	
	/**
	 * removes all the dead animals on the map
	 */
	public void buryTheDead() {
		for (int i = 0 ; i < maxRows; i++) {
			for (int j = 0; j < maxCols; j++) {
				Location l = new Location(i, j);
				Animal a = map[i][j];
				if (a == null) {
					continue;
				}
				if (!a.isAlive()) {
					remove(l);
				}
			}
		}
	}
}
