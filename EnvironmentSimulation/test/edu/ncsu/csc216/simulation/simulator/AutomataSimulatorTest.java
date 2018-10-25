package edu.ncsu.csc216.simulation.simulator;
import org.junit.Test;

import edu.ncsu.csc216.simulation.actor.Configs;
import edu.ncsu.csc216.simulation.environment.utils.PaintedLocation;

import static org.junit.Assert.*;

import java.awt.Color;

/**
 * Tests automata simulator class
 * @author John Ruisi
 *
 */
public class AutomataSimulatorTest {
	
	/**
	 * Test default config constructor
	 */
	@Test
	public void testAutomataSimulator() {
		//Test valid
		AutomataSimulator sim = new AutomataSimulator("test-files/ecosystem.txt");
		String names[] = {"O: Great Gray Owl", "M: Mouse", "F: Frog", "I: Insect"};
		assertEquals(sim.getNames()[0], names[0]);
		assertEquals(sim.getNames()[1], names[1]);
		assertEquals(sim.getNames()[2], names[2]);
		assertEquals(sim.getNames()[3], names[3]);
	}
	
	/**
	 * Test custom config constructor
	 */
	@Test
	public void testAutomataSimulatorConfig() {
		AutomataSimulator sim = new AutomataSimulator("test-files/ecosystem.txt", "test-files/config.txt");
		String names[] = {"O: Great Gray Owl", "M: Mouse", "F: Frog", "I: Insect"};
		assertEquals(sim.getNames()[0], names[0]);
		assertEquals(sim.getNames()[1], names[1]);
		assertEquals(sim.getNames()[2], names[2]);
		assertEquals(sim.getNames()[3], names[3]);
		
		assertEquals(Configs.getPreyStarveTime(), 8);
		assertEquals(Configs.getMiddleStarveTime(), 7);
		assertEquals(Configs.getPredatorStarveTime(), 4);
		assertEquals(Configs.getPreyBreedTime(), 2);
		assertEquals(Configs.getMiddleBreedTime(), 6);
		assertEquals(Configs.getPredatorBreedTime(), 10);
	}
	
	/**
	 * Test step method
	 */
	@Test
	public void testStep() {
		AutomataSimulator sim = new AutomataSimulator("test-files/ecosystem.txt");
		sim.step();
		assertEquals(sim.getNames()[0], "O: Great Gray Owl");
	}
	
	/**
	 * Test painted location method
	 */
	@Test
	public void testPaintedLocation() {
		AutomataSimulator sim = new AutomataSimulator("test-files/ecosystem.txt");
		PaintedLocation[][] view = sim.getView();
		
		assertEquals(view[0][0].getColor(), Color.black);
	}

}
