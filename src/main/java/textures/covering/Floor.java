package textures.covering;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Main;
import map.SizeField;
/**
 * Класс представляющий собой пол
 * @author Viteker
 * @version 0.2
 */
public class Floor extends Covering{
	/**
	 * Конструктор создает пол в заданных координатах
	 * и с заданным размером
	 * @param x
	 * @param y
	 * @param size
	 */
	public Floor(int x, int y, BufferedImage texture) {
		super(x, y, texture);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Конструктор по умалчанию,
	 * указывает нулевые координаты и нулевой размер,
	 * а текстуру загружает сам, соответствующую для пола
	 */
	public Floor(){
		this(0,0,Main.textures.getNatureAndContruction().getFloor());
	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(texture, x-SizeField.SIZE_PART, y - SizeField.SIZE_PART, null);
	}

	@Override
	public void setDefaultTexture() {
		texture = Main.textures.getNatureAndContruction().getFloor();
	}


}
