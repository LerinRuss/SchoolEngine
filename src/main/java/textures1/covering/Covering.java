package textures1.covering;

import java.awt.image.BufferedImage;

import textures1.Cell;
/**
 * Абстрактный класс разветвляющий иерархию {@link Cell}
 * @author Viteker
 * @version 1.3
 * @see Grass
 * @see Sand
 * @see Water
 */
public abstract class Covering extends Cell{
	
	public Covering(int x, int y,BufferedImage texture) {
		super(x, y,texture);
	}

}
