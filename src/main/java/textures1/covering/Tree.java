package textures1.covering;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ListIterator;
import java.util.Random;

import coordinates.Coordinates;
import main.Main;
import map.SizeField;
import other.TreeAction;

public class Tree implements OnGrass,TreeAction,Cloneable{
	private int x,y;
	private long time;

	public void paint(Graphics g){
		Color c = g.getColor();
		g.setColor(Color.GREEN);
		
		int xpoints[] = {x - SizeField.SIZE_PART,x - (SizeField.SIZE_PART>>2), x , x + (SizeField.SIZE_PART>>2), x + SizeField.SIZE_PART, x + (SizeField.SIZE_PART>>2), x, x - (SizeField.SIZE_PART>>2),x - SizeField.SIZE_PART};
		int ypoints[] = {y,y - (SizeField.SIZE_PART>>2), y - SizeField.SIZE_PART , y - (SizeField.SIZE_PART>>2), y, y + (SizeField.SIZE_PART>>2), y + SizeField.SIZE_PART, y + (SizeField.SIZE_PART>>2),y};
		Polygon poly = new Polygon(xpoints, ypoints, xpoints.length);
		g.fillPolygon(poly);
		
		g.setColor(c);
	}
	/**
	 * Клонируем ячейку поля со своими св-ами
	 * @throws CloneNotSupportedException 
	 */
	public Tree clone() throws CloneNotSupportedException{
		return (Tree)super.clone();
	}
	/**
	 * При добавлении на карту дерева, указываем координаты и время создания
	 * @param x
	 * @param y
	 */
	public void setValues(int x, int y) {
		this.x = x;
		this.y = y;
		time = System.currentTimeMillis(); // Время создания
	}
	@Override
	public void action(ListIterator<Tree> it) {
		grow(it);
	}
	private void grow(ListIterator<Tree> it) {
		long now = System.currentTimeMillis();
		if(now - time > 10000){
			time = now;
			
			int direction = new Random().nextInt(5);
			
			System.out.println("direction = " + direction);
			Tree tree = new Tree();
			
			switch(direction){
			case 1:
				Main.mp.disseminateObjects(it,new Coordinates(x + SizeField.SIZE_FIELD, y),tree);
				break;
			case 2:
				Main.mp.disseminateObjects(it,new Coordinates(x, y - SizeField.SIZE_FIELD),tree);
				break;
			case 3:
				Main.mp.disseminateObjects(it,new Coordinates(x - SizeField.SIZE_FIELD, y),tree);
				break;
			case 4:
				Main.mp.disseminateObjects(it,new Coordinates(x, y + SizeField.SIZE_FIELD),tree);
			break;
			}
			
		}
	}
}
