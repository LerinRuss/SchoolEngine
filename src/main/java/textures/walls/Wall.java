package textures.walls;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import loader.LoaderTextures;
import main.Main;
import map.SizeField;
import textures.Cell;
/**
 * Класс представляет собой стену со своими координатами
 * и своей текстурой
 * @author Viteker
 * @version 1.2
 */
public class Wall extends Cell implements Serializable{
	
	/**
	 * Базовый конструктор со всеми параметрами
	 * 
	 * @param x - координата
	 * @param y - координата
	 * @param size - размер стены
	 * @param texture - текстура стены
	 */
	public Wall(int x, int y, BufferedImage texture) {
		super(x, y, texture);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Конструктор по умалчанию, вызваемый при создании стены в движке.
	 * Указывает нулевые координаты и нулевой размер,
	 * а текстуру загружает сам, соответствующую для стены
	 */
	public Wall(){
		this(0,0,Main.textures.getNatureAndContruction().getWall());
	}
	/**
	 * Рисуем текстуру стены в заданом контексте
	 * в своих координатах.
	 * @param g - контекст на котором рисуем стену
	 */
	public void paint(Graphics g){
		g.drawImage(texture, x-SizeField.SIZE_PART, y - SizeField.SIZE_PART, null);
	}
	@Override
	public void setDefaultTexture() {
		texture = Main.textures.getNatureAndContruction().getWall();
	}
	
}
