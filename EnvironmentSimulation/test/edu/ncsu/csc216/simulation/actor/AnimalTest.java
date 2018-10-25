package edu.ncsu.csc216.simulation.actor;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Test the animal class
 * @author John Ruisi
 *
 */
public class AnimalTest {

	/**
	 * Test animals constructor and protected methods
	 */
	@Test
	public void testAnimal() {
		Animal a = new PurePrey('M');
		assertTrue(a.isAlive());
		a.enable();
		assertEquals(a.getTimeSinceLastBreed(), 0);
		assertEquals(a.getTimeSinceLastMeal(), 0);
		a.incrementTimeSinceLastBreed();
		a.incrementTimeSinceLastMeal();
		assertEquals(a.getTimeSinceLastBreed(), 1);
		assertEquals(a.getTimeSinceLastMeal(), 1);
		a.die();
	}

}
