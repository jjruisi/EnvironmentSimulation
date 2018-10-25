/**
 * 
 */
package edu.ncsu.csc216.simulation.environment.utils;

import java.awt.Color;

/**
 * Painted location extends location and adds a symbol and color to the location
 * @author John Ruisi
 *
 */
public class PaintedLocation extends Location {

	/** field color of location*/
	private Color tint;
	/** field symbol of location*/
	private char symbol;
	
	/**
	 * Creates Location object along with color and symbol
	 * @param row row of location
	 * @param col column of location 
	 * @param color color of location
	 * @param c symbol of location
	 */
	public PaintedLocation(int row, int col, Color color, char c) {
		super(row, col);
		this.tint = color;
		this.symbol = c;
	}
	
	/**
	 * gets the color of the location
	 * @return tint the color of the location
	 */
	public Color getColor() {
		return tint;
	}
	
	/**
	 * returns the symbol of the location
	 * @return symbol the character of the location
	 */
	public char getSymbol() {
		return symbol;
	}

}
