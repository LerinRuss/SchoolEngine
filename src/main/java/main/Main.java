package main;

import java.awt.Dimension;

import addition_in_engine.AdditionCreatures;
import addition_in_engine.LoupeTransformation;
import control.KeyControl;
import control.MouseControl;
import control.MouseMotionControl;
import controler.Controler;
import loader.LoaderTextures;
import loader.LoaderTextures.LoaderTexturesException;
import log.Log;
import map.Map;
import structs.ListActionCreatures;
import structs.ListActionObjects;
import window.GameMap;
import window.Window;
/**
 * Отсюда происходит запуск программы
 * 
 * @author Viteker
 * @version 2.1
 */
public class Main {
	/**
	 * Игровая карта
	 */
	public static Map mp;
	/**
	 * Текстуры в игре
	 */
	public static LoaderTextures textures;
	/**
	 * Живые существа в игре
	 */
	public static ListActionCreatures creatures;
	/**
	 * Деревья в игре
	 */
	public static ListActionObjects trees;
	
	public static KeyControl keyControl;
	
	
	/**
	 * Запсук игры, создаем окно и карту, ставим управление
	 * @param args
	 */
	public static void main(String[] args){
		loadTextures();
		initCreatures(20);
		GameMap gmap = initWindow();//показываем уже игру
		prepareMap(gmap);
		Controler c=new Controler(gmap);
		
	}
	/*
	 * Инициализирем начальное кол-во существ в игре, 
	 * с заданным кол-ом
	 */
	private static void initCreatures(int capacity) {
		creatures = new ListActionCreatures();
		trees = new ListActionObjects();
	}
	private static void prepareMap(GameMap gmap) {
		Dimension size=getSizeForMap();
		mp=new Map(size);
		gmap.setBackground((int)size.getWidth(),(int) size.getHeight());
	}
	/**
	 * загружаем текстуры
	 */
	private static void loadTextures(){
		textures = new LoaderTextures();
		try {
			textures.getNatureAndContruction().loadTextures();
			textures.getUnit().loadTextures();
		} catch (LoaderTexturesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Метод для установки размера игровой карты
	 * @return возвращает размер карты
	 */
	private static Dimension getSizeForMap(){
		int width=2400,height=1360;
		return new Dimension(width, height);
	}
	
	private static GameMap initWindow(){
		final Window w=new Window();
		final GameMap gmap=new GameMap(0,0,5);//Игровая панель, где рисуется ход игры-движка
		
		gmap.setSize(w.getSize());
		gmap.setLocation(150, 150);
		
		Log.printSizeWindow(w.getWidth(), w.getHeight());
		System.out.println("["+gmap.getWidth()+":"+gmap.getHeight()+"]");
		keyControl = new KeyControl(gmap.getLoupe());
		gmap.addKeyListener(keyControl);
		
		
		LoupeTransformation loupe = new LoupeTransformation(gmap.getLoupe());
		AdditionCreatures addCreatures = new AdditionCreatures(loupe);
		MouseControl mouseControl = new MouseControl(loupe);
		
		gmap.addMouseMotionListener(new MouseMotionControl(addCreatures,loupe));
		gmap.addMouseListener(mouseControl);
		gmap.setFocusable(true);
		
		w.add(gmap);
		w.setVisible(true);
		return gmap;
	}
}
