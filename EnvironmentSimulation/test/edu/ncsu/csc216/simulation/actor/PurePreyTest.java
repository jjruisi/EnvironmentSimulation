package edu.ncsu.csc216.simulation.actor;
import org.junit.Test;

import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.Ecosystem;
import edu.ncsu.csc216.simulation.environment.utils.Location;

import static org.junit.Assert.*;

import java.awt.Color;
/**
 * Test the PurePrey class
 * @author John Ruisi
 *
 */
public class PurePreyTest {

	/** field defualt colors*/
	private static final Color[] DEFAULT_COLORS = {Color.green, Color.orange, Color.red};

	
	/**
	 * Test constructor
	 */
	public void testPurePrey() {
		char c = 'F';
		Animal a = new PurePrey(c);
		
		assertEquals(a.getSymbol(), c);
	}
	
	/**
	 * Test get the color of animal
	 */
	@Test
	public void testGetColor() {
		char c = 'F';
		Animal a = new PurePrey(c);
		Configs.setToDefaults();
		
		assertEquals(a.getColor(), DEFAULT_COLORS[0]);
		
	}

	/**
	 * Test get past breed time
	 */
	@Test
	public void testPastBreedTime() {
		char c = 'F';
		Animal a = new PurePrey(c);
		Configs.setToDefaults();
		
		assertTrue(a.pastBreedTime(2));
		assertFalse(a.pastBreedTime(0));
	}
	
	/**
	 * Test making new baby
	 */
	@Test
	public void testMakeNewBaby() {
		char c = 'F';
		Animal a = new PurePrey(c);
		
		Animal b = a.makeNewBaby();
		
		assertEquals(b.getSymbol(), 'F');
		assertFalse(b.canAct());
	}
	
	/**
	 * Test getting animal food chain rank
	 */
	@Test
	public void testGetFoodChainRank() {
		char c = 'F';
		Animal a = new PurePrey(c);
		
		assertEquals(a.getFoodChainRank(), 0);
	}
	
	/**
	 * Test act method
	 */
	@Test
	public void testAct() {
		
		//Test breeding
		EcoGrid e = new Ecosystem(3, 3);
		Animal a = new PurePrey('M');
		
		for (int i = 0; i < 4; i++) {
			a.incrementTimeSinceLastBreed();
		}
	
		assertEquals(a.getTimeSinceLastBreed(), 4);
		
		Location middle = new Location(1, 1);
		e.add(a, middle);
		a.enable();
		a.act(middle, e);
		
		Location west = new Location(1, 0);
		
		assertEquals(a.getTimeSinceLastBreed(), 0);
		assertFalse(e.isEmpty(west));
		
		//Test move
		a.enable();
		a.act(middle, e);
		assertTrue(e.isEmpty(middle));
		
		//Test die
		EcoGrid e1 = new Ecosystem(3, 3);
		Animal a1 = new PurePrey('M');
		Location l = new Location(1, 1);
		for (int i = 0; i < 20; i++) {
			a1.enable();
			a1.act(l, e1);
		}
		
		assertFalse(a1.isAlive());
		
		
	}
}
