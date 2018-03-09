package loader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import loader.exception.LoaderTexturesException;

public class TexturesLoader {
	private final LoaderTexturesNatureAndConstraction NATURE_AND_CONSTRACTION = new LoaderTexturesNatureAndConstraction();
	private final UnitTextureLoader UNIT_TEXTURE = new UnitTextureLoader();
	
	public LoaderTexturesNatureAndConstraction getNatureAndContruction(){
		return NATURE_AND_CONSTRACTION;
	}
	public UnitTextureLoader getUnit(){
		return UNIT_TEXTURE;
	}
	public class LoaderTexturesNatureAndConstraction{
		/**
		 * Текстура травы в игре
		 */
		private BufferedImage grass;
		/**
		 * Текстура стены в игре
		 */
		private BufferedImage wall;
		/**
		 * Текстура воды
		 */
		private BufferedImage water;
		/**
		 * Текстура песка
		 */
		private BufferedImage sand;
		/**
		 * Текстура пола
		 */
		private BufferedImage floor;
		/**
		 * Загружаем текстуры в игру
		 * @throws IOException - ошибка загрузки текстуры
		 */
		public void loadTextures() throws LoaderTexturesException{
			try {
				grass = ImageIO.read(new File("textures\\grass.png"));
				wall = ImageIO.read(new File("textures\\wall.png"));
				water = ImageIO.read(new File("textures\\water.png"));
				sand = ImageIO.read(new File("textures\\sand.png"));
				floor = ImageIO.read(new File("textures\\floor.png"));
			} catch (IOException e) {
				throw new LoaderTexturesException("Ошибка загрузки текстур");
			}
			
		}
		public BufferedImage getGrass(){
			return grass;
		}
		public BufferedImage getWall(){
			return wall;
		}
		public BufferedImage getSand(){
			return sand;
		}
		public BufferedImage getFloor(){
			return floor;
		}
		public BufferedImage getWater(){
			return water;
		}
	}
}
