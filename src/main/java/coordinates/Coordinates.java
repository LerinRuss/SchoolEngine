package coordinates;

public class Coordinates implements IntegerCoordinates{
	private final int x;
	private final int y;
	
	public Coordinates(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String toString() {
		return "Coordinates [x=" + x + ", y=" + y + "]";
	}

}
