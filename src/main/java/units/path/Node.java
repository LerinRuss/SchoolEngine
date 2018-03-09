package units.path;

import coordinates.Coordinates;
import coordinates.IntegerCoordinates;

/**
 * This class represents node for algorithm A*.
 * Where is f = g + h;
 * 
 * @author Viteker
 * @version 0.0
 */
public class Node implements IntegerCoordinates,Comparable<Node>{
	//heuristic function to the target 
	private int f;
	//distance to the starting point
	private int g;
	//approximate distance to the end point
	private int h;
	//coordiante node on map
	private int x,y;
	//it's pointer to node parent   
	private Node parent;
	
	public Node(int x,int y){
		this.x = x; 
		this.y = y;
	}
	/**
	 * calculate heuristic function F for Node with parameters
	 * @param g - distance to the starting point multiplied by 10 in order to result был целым
	 */
	public void calculateF(int g, IntegerCoordinates t){
		this.g=g;
		this.h=calculateH(t.getX(), t.getY());
		f = g + h;
	}
	//this method calculates H for A* and multiplies result by 10
	private int calculateH(int x,int y){
		int h = Math.abs(y-this.y)+Math.abs(x-this.x); 
		return h*10;
	}
	public int getF() {
		return f;
	}
	
	public int getG() {
		return g;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public int getH() {
		// TODO Auto-generated method stub
		return h;
	}
	public IntegerCoordinates getCoordinates(){
		return new Coordinates(x, y);
		
	}
	@Override
	public int compareTo(Node anotherNode) {
		return (f < anotherNode.f) ? -1 : ((f == anotherNode.f) ? 0 : 1);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + f;
		result = prime * result + g;
		result = prime * result + h;
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (f != other.f)
			return false;
		if (g != other.g)
			return false;
		if (h != other.h)
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
}
