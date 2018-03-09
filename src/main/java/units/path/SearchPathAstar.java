package units.path;

import java.util.LinkedList;
import java.util.PriorityQueue;

import coordinates.Coordinates;
import coordinates.IntegerCoordinates;
import main.Main;
import map.SizeField;
import textures.Cell;
import textures.covering.Grass;
import textures.walls.Wall;
import units.IPassability;

/**
 * In this class search path and return Node to the target.
 * 
 * @author Viteker
 * @version 0.2
 */
public class SearchPathAstar implements ISearchPath{
	/**
	 * 
	 * @param cell
	 *            - array of map
	 * @param start
	 *            - cell of starting point
	 * @param goal
	 *            - cell of target
	 * @param passability - через что может и не может проходить
	 * @return Узел в котором координаты в пространстве массива карты. Возвращает узел с нулевыми координатами, если цель находится в непроходимой
	 * местности
	 */
	public Node searchPath(Cell cell[][], IntegerCoordinates start, IntegerCoordinates goal, IPassability passability) {
		int x = goal.getX()/SizeField.SIZE_FIELD;
		int y = goal.getY()/SizeField.SIZE_FIELD;
		if(!passability.checkPassability(cell[y][x])){
			return null;
		}
		
		// creates lists
		PriorityQueue<Node> open = new PriorityQueue<>(cell.length*cell.length);
		LinkedList<Node> close = new LinkedList<>();


		Node[][] nodes = new Node[cell.length][cell[0].length];
		

		nodes[y][x] = new Node(x, y);
		nodes[y][x].calculateF(0, start);
		open.add(nodes[y][x]);
		/*
		 * На счет округления проверяй
		 */
		y = floor(start.getY(),goal.getY())/SizeField.SIZE_FIELD;
		x = floor(start.getX(),goal.getX())/SizeField.SIZE_FIELD;
		start = new Coordinates(x, y);
		Node n;
		while ((n = open.poll()) != null && !checkTarget(n, start)) {

			close.add(n);
			for (y = n.getY() - 1; y <= n.getY() + 1; y++) {
				if(y<0 || y >= nodes.length)
					continue;
				for (x = n.getX() - 1; x <= n.getX() + 1; x++) {
					if(x<0 || x >= nodes[y].length)
						continue;

					if (passability.checkPassability(cell[y][x]) && !close.contains(nodes[y][x])) {
						if(nodes[y][x] == null){
							nodes[y][x] = new Node(x, y);
							nodes[y][x].setParent(n);
							int g = calculateG(n.getG(), n, nodes[y][x]);
							nodes[y][x].calculateF(g, start);
							open.add(nodes[y][x]);
						}else{
							int g = calculateG(n.getG(), n, nodes[y][x]);
							if(nodes[y][x].getG() > g){
								open.remove(nodes[y][x]);
								nodes[y][x].setParent(n);
								nodes[y][x].calculateF(g, start);
								open.add(nodes[y][x]);
							}
						}
					}
				}
			}
		}
		return n;
	}
	/*Округление целой координаты, если последняя цифра больше 5, значит округляем в большую сторону,
		иначе ничего не делаем, т.к. нет смысла, там все равно делится дальше
	*/
	private int floor(int num,int goal){
		int mod = num % SizeField.SIZE_FIELD;
		if(goal - num < 0){
			if(mod < SizeField.SIZE_PART)
				return num - SizeField.SIZE_PART - mod;
			return num;
		}
		if(mod > SizeField.SIZE_PART)
			return num + (SizeField.SIZE_FIELD - mod);
		return num;
	}
	private boolean checkTarget(Node n,IntegerCoordinates t){
		if(n.getX() == t.getX() && n.getY() == t.getY())
			return true;
		return false;
	}
	private int calculateG(int g,Node parent,Node current){
		if(Math.abs(parent.getY()-current.getY())==Math.abs(parent.getX()-current.getX())){
			return g+14;
		}
		return g+10;
	}
	// tests
	public static void main(String args[]) {
		testAlgorithmAstar();
	}
	private static Node testAlgorithmAstar(){
		Cell cells[][] = {
				{new Grass(0, 0, null),new Grass(1, 0, null),new Grass(2, 0, null),new Grass(3, 0, null),new Wall(4, 0, null),new Grass(5, 0, null),new Grass(6, 0,null),new Grass(7, 0, null)},
				{new Grass(0, 1, null),new Grass(1, 1, null),new Grass(2, 1, null),new Grass(3, 1, null),new Wall(4, 1, null),new Grass(5, 1, null),new Grass(6, 1, null),new Grass(7, 1, null)},
				{new Grass(0, 2, null),new Grass(1, 2, null),new Grass(2, 2, null),new Grass(3, 2, null),new Wall(4, 2, null),new Grass(5, 2, null),new Grass(6, 2, null),new Grass(7, 2, null)},
				{new Grass(0, 3, null),new Grass(1, 3, null),new Grass(2, 3, null),new Grass(3, 3, null),new Wall(4, 3, null),new Grass(5, 3, null),new Grass(6, 3, null),new Grass(7, 3, null)},
				{new Grass(0, 4, null),new Grass(1, 4, null),new Grass(2, 4, null),new Grass(3, 4, null),new Wall(4, 4, null),new Grass(5, 4, null),new Grass(6, 4, null),new Grass(7, 4, null)},
				{new Grass(0, 5, null),new Grass(1, 5, null),new Grass(2, 5, null),new Grass(3, 5, null),new Grass(4, 5, null),new Grass(5, 5, null),new Grass(6, 5, null),new Grass(7, 5, null)}
				};
		IPassability p = new IPassability() {
			
			@Override
			public boolean checkPassability(Cell cell) {
				if (cell instanceof Grass)
					return true;
				return false;
			}
		};
		Node path =  new SearchPathAstar().searchPath(cells, new Grass(2, 2, null), new Grass(5,3,null), p);
		while(path.getParent()!=null){
			IntegerCoordinates t = path.getParent();
			System.out.print("["+t.getX()+":"+t.getY()+"] ");
			path = path.getParent();
		}
		return path;
	}
	private static void testContainNodes(IntegerCoordinates start,IntegerCoordinates goal) {
		LinkedList<Node> close = new LinkedList<>();
		PriorityQueue<Node> open = new PriorityQueue<>();
		
		Node nodes[][] = new Node[3][3];
		int y = start.getY();
		int x = start.getX();
		nodes[y][x]=new Node(x, y);
		nodes[y][x].calculateF(0, goal);
		open.add(nodes[y][x]);
		Node n=open.poll();
		close.add(n);
		
		System.out.println(" result = " + close.contains(nodes[y][x]));
	}

	// this test cheks that queue takes min value = f of node
	private static void testOpenQueue() {
		PriorityQueue<Node> open = new PriorityQueue<>();
		Node target = new Node(5, 5);

		Node n = new Node(2, 3);
		n.calculateF(38, target);
		System.out.println("f = " + n.getF() + " g = " + n.getG() + " h = " + n.getH());
		open.add(n);

		n = new Node(1, 1);
		n.calculateF(14, target);
		System.out.println("f = " + n.getF() + " g = " + n.getG() + " h = " + n.getH());
		open.add(n);

		n = new Node(2, 2);
		n.calculateF(29, target);
		System.out.println("f = " + n.getF() + " g = " + n.getG() + " h = " + n.getH());
		open.add(n);

		for (Node node : open) {
			System.out.println(" node f = " + node.getF());
		}

		System.out.println("After queue");
		/*while ((n = open.poll()) != null) {
			System.out.println("n.f = " + n.getF());
		}*/
		System.out.println("after changes");
		n = new Node(2, 3);
		n.calculateF(38, target);
		System.out.println("remove = "+open.remove(n));
		n.calculateF(40, target);
		open.add(n);
		while ((n = open.poll()) != null) {
			System.out.println("n.f = " + n.getF());
		}
	}

	private static void testCloseQueue() {
		PriorityQueue<Node> open = new PriorityQueue<>();
		LinkedList<Node> close = new LinkedList<>();

		Node target = new Node(5, 5);
		Node n = new Node(2, 3);
		n.calculateF(38, target);
		open.add(n);

		n = open.poll();
		close.add(n);

		Node temp = new Node(n.getX(), n.getY());

		System.out.println("result = " + close.contains(temp));

	}
}
