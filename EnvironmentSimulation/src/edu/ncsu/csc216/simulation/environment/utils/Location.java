package edu.ncsu.csc216.simulation.environment.utils;

/**
 * Location gives grid coordinates used to find positions of animals
 * @author John Ruisi
 *
 */
public class Location {

	/** field row of cell */
	private int row;
	/** field column of cell*/
	private int column;
	
	/**
	 * Creates a location given a row and column
	 * @param row the row of the cell
	 * @param col the column of the cell
	 */
	public Location(int row, int col) {
		this.row = row;
		this.column = col;
	}
	
	/**
	 * gets the locations row
	 * @return row the locations row
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * gets the locations column
	 * @return column the locations column
	 */
	public int getCol() {
		return column;
	}

}
