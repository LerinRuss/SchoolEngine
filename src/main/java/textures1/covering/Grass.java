package textures1.covering;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import main.Main;
import map.SizeField;

/**
 * Класс представляющий собой просто зеленую траву
 * @author Viteker
 * @version 1.2
 */
public class Grass extends Covering implements Serializable{
	private OnGrass on;
	/**
	 * Конструктор создает траву в заданных координатах
	 * и с заданным размером
	 * @param x
	 * @param y
	 * @param size
	 */
	public Grass(int x, int y,BufferedImage texture) {
		super(x, y,texture);
	}
	/**
	 * Конструктор по умалчанию,
	 * указывает нулевые координаты и нулевой размер,
	 * а текстуру загружает сам, соответствующую для стены
	 */
	public Grass(){
		this(0,0,Main.textures.getNatureAndContruction().getGrass());
	}
	/**
	 * Рисуем текстуру травы в своих координатах 
	 * @param g - контекст в котором риуем
	 */
	public void paint(Graphics g){
		g.drawImage(texture, x-SizeField.SIZE_PART, y - SizeField.SIZE_PART, null);
		if(on != null ) on.paint(g);
	}
	public void setOn(OnGrass on){
		this.on = on;
	}
	public boolean checkOn(){
		return on == null ? true : false;
	}
	@Override
	public void setDefaultTexture() {
		texture = Main.textures.getNatureAndContruction().getGrass();
	}
	
}
