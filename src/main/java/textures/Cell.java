package textures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import coordinates.Coordinates;
import coordinates.IntegerCoordinates;
/**
 * Класс представляет собой объекты, типа травы, стен, воды и т.д.
 * Тоесть то, на что можно поставить другие объекты.
 * 
 * @author Viteker
 * @version 1.1
 */
public abstract class Cell implements Serializable,Cloneable,IntegerCoordinates{
	/**
	 * Размер клетки обекта
	 */
	//protected int size;
	/**
	 * Координаты клетки объекта
	 */
	protected int x,y;
	/**
	 * Текстура клетки
	 */
	protected transient BufferedImage texture;
	
	/**
	 * Базовый конструктор для создания клетки, 
	 * в заданных координатах,
	 * с указанным размером,
	 * текстурой клетки
	 * 
	 * @param x - координата
	 * @param y - координата
	 * @param texture - текстура клетки
	 */
	public Cell(int x,int y,BufferedImage texture){
		this.x=x;this.y=y;
		this.texture = texture;
	}
	/*public Cell(int x,int y,int size){
		this(x,y,size,getDefaultTexture(size));
	}
	*/
	/**
	 * Устанавливает полю {@link Cell#texture} стандартную
	 * картинку
	 */
	public abstract void setDefaultTexture();
	/**
	 *  Устанавливает значение координат и размера, когда ставим
	 *  в редакторе
	 * @param x - координата
	 * @param y - координата
	 * @param size - размер
	 */
	public void setValues(int x,int y){
		this.x=x;this.y=y;
	}
	/**
	 * Клонируем ячейку поля со своими св-ами
	 * @throws CloneNotSupportedException 
	 */
	public Cell clone() throws CloneNotSupportedException{
		return (Cell)super.clone();
	}
	public abstract void paint(Graphics g);
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public IntegerCoordinates getCoordinates(){
		return new Coordinates(x, y);
	}
}
