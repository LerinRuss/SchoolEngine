package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import log.Log;
import main.Main;
import main.Saving;
import main.Saving.SavingException;
import map.Map;
import textures1.covering.Floor;
import textures1.covering.Grass;
import textures1.covering.Sand;
import textures1.covering.Tree;
import textures1.covering.Water;
import textures1.walls.Wall;
import units.Creature;
import units.TestAlly;
import units.TestEnemy;
import units.TestUnit;
/**
 * !!!СОБИРАЮТ И ПОДАЮТ ИНФОРМАЦИЮ!!!
 * 
 * Управление клавишами в движке
 * @author Viteker
 * @version 1.5
 */
public class KeyControl extends Control implements KeyListener {
	/**
	 * Направления движения лупы
	 */
	private boolean up, down, left, right;
	
	private KeyControlled hero;
	/*
	 * Объект которым управляем через клавиатуру
	 */
	private KeyControlled controlled;
	
	public KeyControl(KeyControlled controlled) {
		super();
		this.controlled = controlled;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();
		checkMoveLoupeAndHero(keycode);
		checkChoicePuting(keycode);
		checkSavingOrLoading(keycode);
		
	}
	private void checkSavingOrLoading(int keycode) {
		switch(keycode){
				case KeyEvent.VK_F5:
					save();
					break;
				//Загрузка
				case KeyEvent.VK_F6:
					load();
					break;
		}
	}

	private void checkChoicePuting(int keycode){
		switch (keycode) {
		case KeyEvent.VK_1:
			obnull();
			cell = new Wall();
			Log.printCreated("Wall");
			break;
		case KeyEvent.VK_2:
			obnull();
			cell = new Grass();
			Log.printCreated("Grass");
			break;
		case KeyEvent.VK_3:
			obnull();
			cell = new Water();
			Log.printCreated("Water");
			break;
		case KeyEvent.VK_4:
			obnull();
			cell = new Sand();
			Log.printCreated("Sand");
			break;
		case KeyEvent.VK_5:
			obnull();
			cell = new Floor();
			Log.printCreated("Floor");
			break;
		case KeyEvent.VK_6:
			obnull();
			creature = new TestUnit();
			Log.printCreated("Test_Unit");
			break;
		case KeyEvent.VK_7:
			obnull();
			creature = new TestEnemy();
			Log.printCreated("Test_Enemy");
			break;
		case KeyEvent.VK_8:
			obnull();
			creature = new TestAlly();
			Log.printCreated("Test_Ally");
			break;
		case KeyEvent.VK_9:
			obnull();
			tree = new Tree();
			Log.printCreated("Дерево");
			break;
		}
	}
	private void checkMoveLoupeAndHero(int keycode) {
		switch (keycode) {
		//Движения лупы
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		}
		controlled.move(up, down, left, right);
		if(hero!=null)hero.move(up, down, left, right);
	}
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 * Просто отпускаем наши направления
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		}
		controlled.move(up, down, left, right);
		if(hero!=null)hero.move(up, down, left, right);
	}

	/*
	 * Сохраняем игру
	 */
	private void save() {
		try {
			Saving.save(Main.mp);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Сохранение завершено");
	}
	/*
	 * Загружаем игру
	 */
	private void load() {
		
			Object obj;
			try {
				obj = Saving.load();
				Main.mp= obj instanceof Map ? (Map)obj : null;
				System.out.println(Main.mp);
			} catch (SavingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}

	public void setKeyControlHero(Creature creature) {
		if(creature instanceof KeyControlled)
			this.hero = (KeyControlled)creature;
	}
}
