package textures1.covering;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Main;
import map.SizeField;
/**
 * Класс представляющий собой воду
 * @author Viteker
 * @version 0.2
 */
public class Water extends Covering{
	/**
	 * Конструктор создает воду в заданных координатах
	 * и с заданным размером
	 * @param x
	 * @param y
	 * @param size
	 */
	public Water(int x, int y, BufferedImage texture) {
		super(x, y, texture);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Конструктор по умалчанию,
	 * указывает нулевые координаты и нулевой размер,
	 * а текстуру загружает сам, соответствующую для воды
	 */
	public Water(){
		this(0,0,Main.textures.getNatureAndContruction().getWater());
	}
	/**
	 * Рисуем текстуру травы в своих координатах 
	 * @param g - контекст в котором риуем
	 */
	public void paint(Graphics g){
		g.drawImage(texture, x-SizeField.SIZE_PART, y - SizeField.SIZE_PART, null);
	}

	@Override
	public void setDefaultTexture() {
		texture = Main.textures.getNatureAndContruction().getWater();
	}

}
