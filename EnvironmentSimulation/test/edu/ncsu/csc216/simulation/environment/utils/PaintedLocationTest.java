package edu.ncsu.csc216.simulation.environment.utils;
import org.junit.Test;

import static org.junit.Assert.*;

import java.awt.Color;
/**
 * Test painted location class
 * @author John Ruisi
 *
 */
public class PaintedLocationTest {

	/**
	 * Tests constructor and getters
	 */
	@Test
	public void testPaintedLocation() {
		PaintedLocation l = new PaintedLocation(3, 3, Color.red, 'M');
		assertEquals(l.getCol(), 3);
		assertEquals(l.getRow(), 3);
		assertEquals(l.getSymbol(), 'M');
		assertEquals(l.getColor(), Color.red);
	}

}
