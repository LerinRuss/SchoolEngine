package coordinates;

public class Coordinates implements IntegerCoordinates{
	private int x,y;
	
	public Coordinates(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public String toString() {
		return "Coordinates [x=" + x + ", y=" + y + "]";
	}

	@Override
	public IntegerCoordinates getCoordinates() {
		// TODO Auto-generated method stub
		return this;
	}
	
	
	
}
