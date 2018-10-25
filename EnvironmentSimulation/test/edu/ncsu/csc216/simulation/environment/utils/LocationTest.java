package edu.ncsu.csc216.simulation.environment.utils;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Test location class
 * @author John Ruisi
 *
 */
public class LocationTest {

	/**
	 * Test constructor and getters
	 */
	@Test
	public void testLocation() {
		Location l = new Location(3, 3);
		assertEquals(l.getCol(), 3);
		assertEquals(l.getRow(), 3);
	}

}
