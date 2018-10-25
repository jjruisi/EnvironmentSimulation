package edu.ncsu.csc216.simulation.actor;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

/**
 * Tests the configs class
 * @author John Ruisi
 *
 */
public class ConfigsTest {

	/** field default colors*/
	private static final Color[] DEFAULT_COLORS = {Color.green, Color.orange, Color.red};
	/** field default starve times*/
	private static final int[] DEFAULT_STARVE_TIME = {10, 6, 5};
	/** field default breed times*/
	private static final int[] DEFAULT_BREED_TIME = {1, 7, 15};
	
	/**
	 * Testing custom configs
	 */
	@Test
	public void testinitConfigs() {
		Color[] color = {Color.red, Color.blue, Color.gray};
		int[] s = {3, 2, 1};
		int [] b = {1, 2, 3};
		
		Configs.initConfigs(color, s, b);
		
		assertEquals(Configs.getPreyColor(), color[0]);
		assertEquals(Configs.getMiddleColor(), color[1]);
		assertEquals(Configs.getPredatorColor(), color[2]);
		
		assertEquals(Configs.getPreyStarveTime(), s[0]);
		assertEquals(Configs.getMiddleStarveTime(), s[1]);
		assertEquals(Configs.getPredatorStarveTime(), s[2]);
		
		assertEquals(Configs.getPreyBreedTime(), b[0]);	
		assertEquals(Configs.getMiddleBreedTime(), b[1]);	
		assertEquals(Configs.getPredatorBreedTime(), b[2]);	
		
		assertEquals(Configs.getPreyFoodChainRank(), 0);
		assertEquals(Configs.getMiddleFoodChainRank(), 10);
		assertEquals(Configs.getPredatorFoodChainRank(), 20);
	}

	/**
	 * Testing default configs
	 */
	@Test
	public void testSetToDefaults() {
		Configs.initConfigs(DEFAULT_COLORS, DEFAULT_STARVE_TIME, DEFAULT_BREED_TIME);
		
		assertEquals(Configs.getPreyColor(), DEFAULT_COLORS[0]);
		assertEquals(Configs.getMiddleColor(), DEFAULT_COLORS[1]);
		assertEquals(Configs.getPredatorColor(), DEFAULT_COLORS[2]);
		
		assertEquals(Configs.getPreyStarveTime(), DEFAULT_STARVE_TIME[0]);
		assertEquals(Configs.getMiddleStarveTime(), DEFAULT_STARVE_TIME[1]);
		assertEquals(Configs.getPredatorStarveTime(), DEFAULT_STARVE_TIME[2]);
		
		assertEquals(Configs.getPreyBreedTime(), DEFAULT_BREED_TIME[0]);	
		assertEquals(Configs.getMiddleBreedTime(), DEFAULT_BREED_TIME[1]);	
		assertEquals(Configs.getPredatorBreedTime(), DEFAULT_BREED_TIME[2]);	
	}
}
