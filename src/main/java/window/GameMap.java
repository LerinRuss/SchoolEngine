package window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import control.Control;
import control.KeyControlled;
import coordinates.Coordinates;
import coordinates.IntegerCoordinates;
import main.Main;
import map.SizeField;
import textures1.covering.Grass;
/**
 * 
 * @author Viteker
 * @version 0.4
 */
public class GameMap extends Canvas {
	/*

	 */
	private BufferedImage background;//Фон игрового поля
	private final Loupe loupe;
	private final Secretation cursor; 
	public GameMap(int xLoupe,int yLoupe,int speedLoupe) {
		super();
		loupe = new Loupe(xLoupe, yLoupe, speedLoupe);
		cursor = new Secretation();
		
		setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
	}
	/*
	 * Устанавливаем фон игрового поля
	 */
	public void setBackground(int width,int height){
		background=new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}
	public Graphics getBackgroundGraphics(){
		return background.getGraphics();
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.Component#repaint()
	 * Текущим графическим компонентом становится фон "background"
	 * и минуя метод upgrade как это должно быть по умолчанию
	 * сразу мередаем это графический объект в метод paint
	 */
	public void repaint(){
		Graphics g = background.getGraphics();
		upgrade(g);
	}
	/*
	 * (non-Javadoc)
	 * @see interfaces.Draw#upgrade(java.awt.Graphics)
	 */
	public void upgrade(Graphics g){
		Main.mp.paint(g);
	}
	/*
	 * (non-Javadoc)
	 * @see java.awt.Canvas#paint(java.awt.Graphics)
	 * Отрисовываем все ячейки карты Main.mp
	 * вызывая его метод paint
	 * И гр объектом нашего компонента отрисовываем фон background
	 */
	public void paint(Graphics g){
		int width=background.getWidth(),
				height=background.getHeight();
		cursor.drawCursor(Control.x+loupe.x, Control.y+loupe.y,g);
		//System.out.println("Control.x = " + Control.x + " Control.y = " + Control.y + " loupe.x = " + loupe.x + " loupe.y = " + loupe.y);
		loupe.paintByLoupe(background, width, height);//Отрисовываем фон, с учетом позиции лупы

	}
	
	public Loupe getLoupe(){
		return loupe;
	}
	public Secretation getGameMapCursor(){
		return cursor;
	}
	public class Loupe implements KeyControlled,IntegerCoordinates{
		private int x,y;
		private int speed;
		public Loupe(int x,int y,int speed){
			this.x = x;
			this.y = y;
			this.speed = speed;
		}
		public void moving(int offset) {
			boolean up = false;
			boolean down = false;
			boolean right = false;
			boolean left = false;
			if(Control.x < offset) left = true;
			if(Control.x > getWidth() - offset) right = true;
			if(Control.y < offset) up = true;
			if(Control.y > getHeight() - offset) down = true;
			
			move(up, down, left, right);
		}
		public void move(boolean up,boolean down,boolean left,boolean right){
			if(up && y>=speed)y-=speed;
			if(down && y+getHeight()<background.getHeight()-speed)y+=speed;
			if(left && x>=speed)x-=speed;
			if(right && x+getWidth()<background.getWidth()-speed)x+=speed;
		}
		
		public void paintByLoupe(BufferedImage background,int width,int height){
			getGraphics().drawImage(background, 0,0,width,height,x,y,x+width,y+height,null);

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
		public IntegerCoordinates getCoordinates(){
			return new Coordinates(x, y);
		}

		

	}
	private class Secretation implements SizeField{
		/**
		 * Рисуем фокус вокруг поля d заданных координатах и пишем стены если на них указаны
		 * @param x 
		 * @param y
		 * @param g - на чем рисуем(В игре на карте), объект типа {@link Graphics} 
		 */
		public void drawCursor(int x,int y,Graphics g){
			x/=SIZE_FIELD;y/=SIZE_FIELD;
			Main.mp.paintStringCell(g, x, y);
			if(Main.mp.getCell()[y][x] instanceof Grass && !((Grass)Main.mp.getCell()[y][x]).checkOn()){
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				BufferedImage image = null;
				try {
					final File parent = new File("").getAbsoluteFile();
					image = ImageIO.read(new File(parent,"/textures/cursor/chop.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Cursor cursor = toolkit.createCustomCursor(image, new Point(0, 0), "Chop");
				
				GameMap.this.setCursor(cursor);
				
			}else
				GameMap.this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			
			x*=SIZE_FIELD;y*=SIZE_FIELD;
			
			g.setColor(Color.BLACK);
			g.drawRect(x, y, SIZE_FIELD, SIZE_FIELD);
		}
	}
}
