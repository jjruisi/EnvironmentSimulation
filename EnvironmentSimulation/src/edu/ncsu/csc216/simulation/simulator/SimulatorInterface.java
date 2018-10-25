package edu.ncsu.csc216.simulation.simulator;

import edu.ncsu.csc216.simulation.environment.utils.PaintedLocation;

/**
 * Behavior of the simulator
 * @author John Ruisi
 *
 */
public interface SimulatorInterface {

	/**
	 * Steps through the simulation once, enabling the animals, 
	 * acting on each animal, and then finally removing the dead animals
	 */
	public void step();
	
	/**
	 * Gets the colored view of the grid used by the GUI to show the map of
	 * animal objects in color
	 * @return pL the array of painted location objects
	 */
	public PaintedLocation[][] getView();
	
	/**
	 * gets the names of the animals
	 * @return names the names of the animals
	 */
	public String[] getNames();
}
