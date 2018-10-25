/**
 * 
 */
package edu.ncsu.csc216.simulation.simulator;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

import edu.ncsu.csc216.simulation.actor.Animal;
import edu.ncsu.csc216.simulation.actor.Configs;
import edu.ncsu.csc216.simulation.actor.PredatorPrey;
import edu.ncsu.csc216.simulation.actor.PurePredator;
import edu.ncsu.csc216.simulation.actor.PurePrey;
import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.Ecosystem;
import edu.ncsu.csc216.simulation.environment.utils.Location;
import edu.ncsu.csc216.simulation.environment.utils.PaintedLocation;

/**
 * AutomataSimulator is the class that is in charge of iterating the simulation.
 * This class reads inpout from file to create an initial grid and set configs if
 * necessary. The class tehn used that information to step through the simulation.
 * @author John Ruisi
 *
 */
public class AutomataSimulator implements SimulatorInterface {

	/** field max size of grid*/
	private static final int SIZE = 20;
	/** field threshold */
	private static final int THRESHOLD = 2;
	/** field size error message*/
	private static final String SIZE_ERROR_MESSAGE = "size error";
	/** field threshold error message*/
	private static final String THRESHOLD_ERROR_MESSAGE = "threshold error";
	/** field empty/null animal character*/
	private static final char EMPTY = '.';
	/** field names of animals*/
	private String[] names;
	/** field # of animals*/
	private int numberOfNames;
	/** field characters of animals*/
	private char[] symbol;
	/** field ecosystem grid used to add animals*/
	private EcoGrid simpleSystem;
	
	/**
	 * AutomataSimualtor creates grid with given file and default configuration
	 * @param init valid file to create simulator
	 */
	public AutomataSimulator(String init) {
		Configs.setToDefaults();
		simpleSystem = new Ecosystem(SIZE, SIZE);
		Scanner fileReader;
		
		try {
			fileReader = new Scanner(new File(init));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File Not Found.");
		}
		
		int counter = 0;
		boolean validAnimalChar;
		Animal a1 = null;
		Animal a2 = null;
		char aSymbol;
		char c;
		
		if (!fileReader.hasNextInt()) {
			throw new IllegalArgumentException("Incorrect format.");
		}
		
		this.numberOfNames = fileReader.nextInt();
		
		if (numberOfNames < THRESHOLD) {
			throw new IllegalArgumentException(THRESHOLD_ERROR_MESSAGE);
		}
		
		symbol = new char[numberOfNames];
		names = new String[numberOfNames];
		
		for (int i = 0; i < numberOfNames; i++) {
			c = fileReader.next().charAt(0);
			
			if (!Character.isAlphabetic(c)) {
				throw new IllegalArgumentException("Character must be a letter");
			}
			
			symbol[i] = c;
			names[i] = fileReader.nextLine().trim();
		}
		
		while (fileReader.hasNextLine()) {
			counter += 1;
			
			if (SIZE < counter) {
				throw new IllegalArgumentException(SIZE_ERROR_MESSAGE);
			}
			
			String gridLine = fileReader.next();
			
			if (gridLine.length() != SIZE) {
				throw new IllegalArgumentException(SIZE_ERROR_MESSAGE);
			}
			
			for (int i = 0; i < SIZE; i++) {
				validAnimalChar = false;
				aSymbol = gridLine.charAt(i);
				
				for (int j = 0; j < numberOfNames; j++) {
					if (aSymbol == EMPTY || aSymbol == symbol[j]) {
						validAnimalChar = true;
					}
				}
				
				if ((!Character.isAlphabetic(aSymbol) && aSymbol != EMPTY) || !validAnimalChar) {
					throw new IllegalArgumentException("Invalid character");
				}
				
				if (aSymbol == EMPTY) {
					a1 = a2;
				} else {
					if (aSymbol == symbol[0]) {
						a1 = new PurePredator(aSymbol);
					} else if (aSymbol == symbol[numberOfNames - 1]) {
						a1 = new PurePrey(aSymbol);
					} else if (aSymbol != EMPTY) {
						a1 = new PredatorPrey(aSymbol);
					}
				}
				
				Location l = new Location(counter - 1, i);
				simpleSystem.add(a1, l);
			}
		}
		if (counter < SIZE) {
			throw new IllegalArgumentException(SIZE_ERROR_MESSAGE);
		}
	}
	
	/**
	 * Creates simulator with custom configuration file
	 * @param init custom intialization file
	 * @param config custom configuration file
	 */
	public AutomataSimulator(String init, String config) {
		this(init);
		processConfig(config);
	}
	
	/**
	 * Processes the configuration file
	 * @param config the config file
	 */
	private void processConfig(String config) {
		Scanner configReader;
		
		try {
			configReader = new Scanner(new File(config));
		} catch (FileNotFoundException e){ 
			throw new IllegalArgumentException("File not found");
		}
		
		String colorLow = ""; String colorMid = ""; String colorHigh = "";
		int starveLow = 0; int starveMid = 0; int starveHigh = 0;
		int breedLow = 0; int breedMid = 0; int breedHigh = 0;

		try {
			if (configReader.hasNextLine()) {
				colorLow = configReader.next();
				colorMid = configReader.next();
				colorHigh = configReader.next();
				
			}
			
			configReader.nextLine();
			if (configReader.hasNextLine()) {

				starveLow = configReader.nextInt();
				starveMid = configReader.nextInt();
				starveHigh = configReader.nextInt();

			configReader.nextLine();
			} if (configReader.hasNextLine()) {
				breedLow = configReader.nextInt();
				breedMid = configReader.nextInt();
				breedHigh = configReader.nextInt();

			}
		} catch (InputMismatchException e) {
			throw new IllegalArgumentException("Mismatch");
		}
		
		Color lowColor = Color.decode("0x" + colorLow);
		Color midColor = Color.decode("0x" + colorMid);
		Color highColor = Color.decode("0x" + colorHigh);

		int[] starve = {starveLow, starveMid, starveHigh};
		int[] breed = {breedLow, breedMid, breedHigh};
		Color[] colors = {lowColor, midColor, highColor};
		
		Configs.initConfigs(colors, starve, breed);
		configReader.close();
	}
	
	/**
	 * Steps through the simulation once, enabling the animals, 
	 * acting on each animal, and then finally removing the dead animals
	 */
	public void step() {
		simpleSystem.enableTheLiving();
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				Location l = new Location(i, j);
				if (simpleSystem.getItemAt(l) == null) {
					continue;
				} else {
					simpleSystem.getItemAt(l).act(l, simpleSystem);
				}
			}
		}
		simpleSystem.buryTheDead();
	}

	/**
	 * Gets the colored view of the grid used by the GUI to show the map of
	 * animal objects in color
	 * @return pL the array of painted location objects
	 */
	public PaintedLocation[][] getView() {
		PaintedLocation[][] pL = new PaintedLocation[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				Location l = new Location(i, j);
				Animal a = simpleSystem.getItemAt(l);
				Color color;
				if (a == null) {
					color = Color.black;
				} else {
					color = a.getColor();
				}
				char aSymbol;
				if (a == null) {
					aSymbol = EMPTY;
				} else {
					aSymbol = a.getSymbol();
				}
				PaintedLocation p = new PaintedLocation(i, j, color, aSymbol);
				pL[i][j] = p;
			}
		}
		return pL;
	}
	
	/**
	 * gets the names of the animals
	 * @return names the names of the animals
	 */
	public String[] getNames() {
		String[] getNames = new String[numberOfNames];
		for (int i = 0; i < numberOfNames; i++) {
			getNames[i] = "" + symbol[i] + ": " + names[i];
		}
		return getNames;
	}
}
