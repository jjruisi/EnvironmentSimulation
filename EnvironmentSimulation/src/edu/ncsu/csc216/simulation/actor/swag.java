package edu.ncsu.csc216.simulation.actor;

public class swag {

	public static void main(String [] args) {
		boolean b1 = true;
		boolean b2 = false;
		boolean b3 = true;
		
		if (b1 & b2 | b2 & b3 | b2)
			System.out.print("ok ");
		if ( b1 & b2 | b2 & b3 | b2 | b1) 
			System.out.print("dokey");
	}
}
