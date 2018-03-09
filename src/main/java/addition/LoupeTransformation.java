package addition;

import coordinates.Coordinates;
import coordinates.IntegerCoordinates;

public class LoupeTransformation {
	/*
	    Loupe coordinates is order to add creatures relative to one's position.
	 */
	private final IntegerCoordinates loupe;
	
	public LoupeTransformation(IntegerCoordinates loupe) {
		this.loupe = loupe;
	}


	private int addX(int x){
		return x + loupe.getX();
	}
	private int addY(int y){
		return y + loupe.getY();
	}

	public IntegerCoordinates transformCoordinates(int x, int y){
		return new Coordinates(addX(x), addY(y));
	}
}
