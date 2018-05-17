package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import map.Map;
import textures1.Cell;
/**
 * Класс отвечающий за сохранение КАРТЫ в игре {@link map.Map}
 * Сохрание и загрузка происходит в корневую папку в файле "game.sav"
 * @author Lerin
 * @version 1.1
 */
public class Saving {
	/**
	 * Статический метод в котором передаваемый объект (в этой игре карта {@link map.Map})
	 * сохраняется в файл "game.sav"
	 */
	public static void save(Object saved) throws FileNotFoundException, IOException{
		File f=new File("game.sav");
		ObjectOutputStream obj=new ObjectOutputStream(new FileOutputStream(f));
		obj.writeObject(saved);
		obj.flush();
	}
	/**
	 * В этом методе из файла "game.sav" загружается карту
	 * @return загруженная карта {@link map.Map} 
	 * @throws FileNotFoundException - Нет файла
	 * @throws IOException - Ошибка записи
	 * @throws ClassNotFoundException - Класс должен быть
	 * @throws SavingException 
	 */
	public static Object load() throws SavingException{
		File f=new File("game.sav");
		ObjectInputStream inputObject;
		Object obj = null;
		
		try {
			inputObject = new ObjectInputStream(new FileInputStream(f));
			obj = inputObject.readObject();
			inputObject.close();
		} catch (IOException e) {
			throw new SavingException("Загрузка карты не удалась, ошибка чтения файла",e);
		} catch (ClassNotFoundException e) {
			throw new SavingException("Загрузка карты не удалась, ошибка такого класса нет",e);
		}
		
		
		Map mp= obj instanceof Map ? (Map)obj : null;
		if(mp == null)
			throw new SavingException("Загрузка карты не удалась, возможно файл не относится к карте");
		
		Cell[][] cells = mp.getCell();
		
		for(int i = 0; i < cells.length;i++){
			for(int j = 0; j < cells[i].length; j++){
				cells[i][j].setDefaultTexture();
			}
		}
		return mp;
	}
	public static class SavingException extends Exception{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SavingException(String s){
			super(s);
		}
		public SavingException(String s,Throwable cause){
			super(s,cause);
		}
	}
}
