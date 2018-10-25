package edu.ncsu.csc216.simulation.actor;
import org.junit.Test;

import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.Ecosystem;
import edu.ncsu.csc216.simulation.environment.utils.Location;

import static org.junit.Assert.*;

import java.awt.Color;
/**
 * Test PredatorPrey class
 * @author John
 *
 */
public class PredatorPreyTest {

	/** field default colors*/
	private static final Color[] DEFAULT_COLORS = {Color.green, Color.orange, Color.red};
	
	/**
	 * Test constructor
	 */
	@Test
	public void testPredatorPrey() {
		char c = 'M';
		Animal a = new PredatorPrey(c);
		
		assertEquals(a.getSymbol(), c);
	}
	
	/**
	 * Test getting color
	 */
	@Test
	public void testGetColor() {
		char c = 'M';
		Animal a = new PredatorPrey(c);
		Configs.setToDefaults();
		
		assertEquals(a.getColor(), DEFAULT_COLORS[1]);
		
	}
	
	/**
	 * Test getting past breed time
	 */
	@Test
	public void testPastBreedTime() {
		char c = 'M';
		Animal a = new PredatorPrey(c);
		Configs.setToDefaults();
		
		assertTrue(a.pastBreedTime(8));
		assertFalse(a.pastBreedTime(6));
	}
	
	/**
	 * Test making new baby
	 */
	@Test
	public void testMakeNewBaby() {
		char c = 'M';
		Animal a = new PredatorPrey(c);
		
		Animal b = a.makeNewBaby();
		
		assertEquals(b.getSymbol(), 'M');
		assertFalse(b.canAct());
	}
	
	/**
	 * Test getting the food chain rank
	 */
	@Test
	public void testGetFoodChainRank() {
		char c = 'M';
		Animal a = new PredatorPrey(c);
		
		assertEquals(a.getFoodChainRank(), 10);
	}
	
	/**
	 * Test act method
	 */
	@Test
	public void testAct() {
		//Test breed
		EcoGrid e = new Ecosystem(3, 3);
		Animal a = new PredatorPrey('M');
		Location l = new Location(1, 1);
		
		e.add(a, l);
		for (int i = 0; i < 8; i++) {
			a.incrementTimeSinceLastBreed();
		}
		a.enable();
		a.act(l, e);
		Location l1 = new Location(1, 0);
		assertFalse(e.isEmpty(l1));
		assertFalse(e.isEmpty(l));
		
		//Test eat
		EcoGrid e1 = new Ecosystem(3, 3);
		Animal b = new PredatorPrey('M');
		Animal c = new PurePrey('C');
		Location l2 = new Location(1, 1);
		Location l3 = new Location(0, 1);
		e1.add(b, l2);
		e1.add(c, l3);
		
		b.enable();
		b.act(l2, e1);
		assertTrue(e1.isEmpty(l2));
		assertEquals(e1.getItemAt(l3).getSymbol(), 'M');
		
		//Test Move
		EcoGrid e2 = new Ecosystem(3, 3);
		Animal d = new PredatorPrey('M');
		Location l4 = new Location(1, 1);
		
		d.enable();
		d.act(l4, e2);
		assertTrue(e2.isEmpty(l4));
		
		//Test die
		for (int i = 0; i < 6; i++) {
			d.enable();
			d.act(l4, e2);
		}
		
		assertFalse(d.isAlive());
	}
}


























