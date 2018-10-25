package edu.ncsu.csc216.simulation.actor;
import org.junit.Test;

import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.Ecosystem;
import edu.ncsu.csc216.simulation.environment.utils.Location;

import static org.junit.Assert.*;

import java.awt.Color;
/**
 * Test the PurePredator class
 * @author John Ruisi
 *
 */
public class PurePredatorTest {

	/** field defualt colors*/
	private static final Color[] DEFAULT_COLORS = {Color.green, Color.orange, Color.red};
	
	/**
	 * Test constructor
	 */
	@Test
	public void testPurePredator() {
		char c = 'P';
		Animal a = new PurePredator(c);
		
		assertEquals(a.getSymbol(), c);
	}

	/**
	 * Test get the color
	 */
	@Test
	public void testGetColor() {
		char c = 'P';
		Animal a = new PurePredator(c);
		Configs.setToDefaults();
		
		assertEquals(a.getColor(), DEFAULT_COLORS[2]);
		
	}
	
	/**
	 * Test getting past breed time
	 */
	@Test
	public void testPastBreedTime() {
		char c = 'P';
		Animal a = new PurePredator(c);
		Configs.setToDefaults();
		
		assertTrue(a.pastBreedTime(16));
		assertFalse(a.pastBreedTime(14));
	}
	
	/**
	 * Test making new baby
	 */
	@Test
	public void testMakeNewBaby() {
		char c = 'P';
		Animal a = new PurePredator(c);
		
		Animal b = a.makeNewBaby();
		
		assertEquals(b.getSymbol(), 'P');
		assertFalse(b.canAct());
	}
	
	/**
	 * Test getting food chain rank
	 */
	@Test
	public void testGetFoodChainRank() {
		char c = 'P';
		Animal a = new PurePredator(c);
		
		assertEquals(a.getFoodChainRank(), 20);
	}
	
	/**
	 * Test act method
	 */
	@Test
	public void testAct() {
		//Test eat
		EcoGrid e = new Ecosystem(3, 3);
		Animal a = new PurePredator('M');
		Animal a1 = new PurePrey('C');
		
		Location l = new Location(1, 1);
		Location l1 = new Location(1, 0);
		
		e.add(a, l);
		e.add(a1, l1);
		
		a.enable();
		a.act(l, e);
		assertTrue(e.isEmpty(l));
		assertEquals(e.getItemAt(l1).getSymbol(), 'M');
		assertEquals(a.getTimeSinceLastMeal(), 0);
		
		//Test breed
		EcoGrid e1 = new Ecosystem(3, 3);
		Animal b = new PurePredator('M');
		
		e1.add(b, l);
		for (int i = 0; i <= 15; i++) {
			b.incrementTimeSinceLastBreed();
		}
		b.enable();
		b.act(l, e1);
		assertFalse(e1.isEmpty(l1));
		assertEquals(b.getTimeSinceLastBreed(), 0);
		
		//Test Move
		e1.remove(l1);
		b.enable();
		b.act(l, e1);
		assertTrue(e1.isEmpty(l));
		
		//Test die
		for (int i = 0; i < 6; i++) {
			b.enable();
			b.act(l, e1);
		}
		
		assertFalse(b.isAlive());
		
	}
	
	/**
	 * Tests trying to eat in different directions
	 */
	@Test
	public void testEatDirections() {
		//Test eating to the east
		EcoGrid e = new Ecosystem(3, 3);
		Animal a = new PurePredator('M');
		Animal a1 = new PurePrey('C');
		
		Location l = new Location(1, 1);
		Location l1 = new Location(1, 2);
		
		e.add(a, l);
		e.add(a1, l1);
		
		a.enable();
		a.act(l, e);
		assertTrue(e.isEmpty(l));
		assertFalse(e.isEmpty(l1));
		
		//Test eating to the south
		EcoGrid e1 = new Ecosystem(3, 3);
		Animal b = new PurePredator('M');
		Animal b1 = new PurePrey('C');
		
		Location s = new Location(1, 1);
		Location s1 = new Location(2, 1);
		
		e1.add(b, s);
		e1.add(b1, s1);
		
		b.enable();
		b.act(s, e1);
		assertTrue(e1.isEmpty(s));
		assertFalse(e1.isEmpty(s1));
	}
}
