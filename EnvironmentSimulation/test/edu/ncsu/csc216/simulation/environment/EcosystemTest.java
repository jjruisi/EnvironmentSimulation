package edu.ncsu.csc216.simulation.environment;
import org.junit.Test;

import edu.ncsu.csc216.simulation.actor.Animal;
import edu.ncsu.csc216.simulation.actor.PurePrey;
import edu.ncsu.csc216.simulation.environment.utils.Location;

import static org.junit.Assert.*;
/**
 * Test Ecosystem class
 * @author John Ruisi
 *
 */
public class EcosystemTest {

	/**
	 * Test add method
	 */
	@Test
	public void testAdd() {
		Ecosystem e = new Ecosystem(3, 3);
		Animal a = null;
		Location l = new Location(0, 0);
		
		//Add null element
		e.add(a, l);
		assertNull(e.getItemAt(l));
		
		//Add valid animal
		Location l1 = new Location(0, 1);
		Animal a1 = new PurePrey('M');
		e.add(a1, l1);
		assertEquals(e.getItemAt(l1).getSymbol(), 'M');
	}
	
	/**
	 * Test isEmpty method
	 */
	@Test
	public void testIsEmpty() {
		Ecosystem e = new Ecosystem(3, 3);
		Animal a = null;
		Location l = new Location(0, 0);
		
		//Test true is empty
		e.add(a, l);
		assertTrue(e.isEmpty(l));
		
		//Test false is empty
		Location l1 = new Location(0, 1);
		Animal a1 = new PurePrey('M');
		e.add(a1, l1);
		assertFalse(e.isEmpty(l1));
	}
	
	/**
	 * Test getItemAt method
	 */
	@Test
	public void testGetItemAt() {
		Ecosystem e = new Ecosystem(3, 3);
		Animal a = new PurePrey('M');
		Location l = new Location(0, 0);
		e.add(a, l);
		
		assertEquals(e.getItemAt(l).getSymbol(), 'M');
	}
	
	/**
	 * Test remove method
	 */
	@Test
	public void testRemove() {
		Ecosystem e = new Ecosystem(3, 3);
		Animal a = new PurePrey('M');
		Location l = new Location(0, 0);
		e.add(a, l);
		
		assertEquals(e.getItemAt(l).getSymbol(), 'M');
		
		e.remove(l);
		assertNull(e.getItemAt(l));
		
	}
	
	/**
	 * Test find empty neighbor method
	 */
	@Test
	public void testFindFirstEmptyNeighbor() {
		//Test return null
		Ecosystem e = new Ecosystem(3, 3);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Animal a = new PurePrey('M');
				Location l = new Location(i, j);
				e.add(a, l);
			}
		}
		Location l = new Location(1, 1);
		assertNull(e.findFirstEmptyNeighbor(l, 0));
		
		//Test west open
		Ecosystem e1 = new Ecosystem(3, 3);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Animal a = new PurePrey('M');
				Location l1 = new Location(i, j);
				e1.add(a, l1);
			}
		}
		Location west = new Location(1, 0);
		e1.remove(west);
		
		//Starting at 0
		Location getWest = e1.findFirstEmptyNeighbor(l, 0);
		assertEquals(west.getCol(), getWest.getCol());
		assertEquals(west.getRow(), getWest.getRow());
		//Starting at 1
		Location getWest1 = e1.findFirstEmptyNeighbor(l, 1);
		assertEquals(west.getCol(), getWest1.getCol());
		assertEquals(west.getRow(), getWest1.getRow());
		//Starting at 2
		Location getWest2 = e1.findFirstEmptyNeighbor(l, 2);
		assertEquals(west.getCol(), getWest2.getCol());
		assertEquals(west.getRow(), getWest2.getRow());
		//Starting at 3
		Location getWest3 = e1.findFirstEmptyNeighbor(l, 3);
		assertEquals(west.getCol(), getWest3.getCol());
		assertEquals(west.getRow(), getWest3.getRow());
		
		//Test north open
		Ecosystem e2 = new Ecosystem(3, 3);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Animal a = new PurePrey('M');
				Location l2 = new Location(i, j);
				e2.add(a, l2);
			}
		}
		Location north = new Location(0, 1);
		e2.remove(north);
		
		//Starting at 0
		Location getNorth = e2.findFirstEmptyNeighbor(l, 0);
		assertEquals(north.getCol(), getNorth.getCol());
		assertEquals(north.getRow(), getNorth.getRow());
		//Starting at 1
		Location getNorth1 = e2.findFirstEmptyNeighbor(l, 1);
		assertEquals(north.getCol(), getNorth1.getCol());
		assertEquals(north.getRow(), getNorth1.getRow());
		//Starting at 2
		Location getNorth2 = e2.findFirstEmptyNeighbor(l, 2);
		assertEquals(north.getCol(), getNorth2.getCol());
		assertEquals(north.getRow(), getNorth2.getRow());
		//Starting at 3
		Location getNorth3 = e2.findFirstEmptyNeighbor(l, 3);
		assertEquals(north.getCol(), getNorth3.getCol());
		assertEquals(north.getRow(), getNorth3.getRow());
		
		//Test east open
		Ecosystem e3 = new Ecosystem(3, 3);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Animal a = new PurePrey('M');
				Location l3 = new Location(i, j);
				e3.add(a, l3);
			}
		}
		Location east = new Location(1, 2);
		e3.remove(east);
		
		//Starting at 0
		Location getEast = e3.findFirstEmptyNeighbor(l, 0);
		assertEquals(east.getCol(), getEast.getCol());
		assertEquals(east.getRow(), getEast.getRow());
		//Starting at 1
		Location getEast1 = e3.findFirstEmptyNeighbor(l, 1);
		assertEquals(east.getCol(), getEast1.getCol());
		assertEquals(east.getRow(), getEast1.getRow());
		//Starting at 2
		Location getEast2 = e3.findFirstEmptyNeighbor(l, 2);
		assertEquals(east.getCol(), getEast2.getCol());
		assertEquals(east.getRow(), getEast2.getRow());
		//Starting at 3
		Location getEast3 = e3.findFirstEmptyNeighbor(l, 3);
		assertEquals(east.getCol(), getEast3.getCol());
		assertEquals(east.getRow(), getEast3.getRow());
		
		//Test south open
		Ecosystem e4 = new Ecosystem(3, 3);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Animal a = new PurePrey('M');
				Location l4 = new Location(i, j);
				e4.add(a, l4);
			}
		}
		Location south = new Location(2, 1);
		e4.remove(south);
		
		//Starting at 0
		Location getSouth = e4.findFirstEmptyNeighbor(l, 0);
		assertEquals(south.getCol(), getSouth.getCol());
		assertEquals(south.getRow(), getSouth.getRow());
		//Starting at 1
		Location getSouth1 = e4.findFirstEmptyNeighbor(l, 1);
		assertEquals(south.getCol(), getSouth1.getCol());
		assertEquals(south.getRow(), getSouth1.getRow());
		//Starting at 2
		Location getSouth2 = e4.findFirstEmptyNeighbor(l, 2);
		assertEquals(south.getCol(), getSouth2.getCol());
		assertEquals(south.getRow(), getSouth2.getRow());
		//Starting at 3
		Location getSouth3 = e4.findFirstEmptyNeighbor(l, 3);
		assertEquals(south.getCol(), getSouth3.getCol());
		assertEquals(south.getRow(), getSouth3.getRow());
	}

}






















