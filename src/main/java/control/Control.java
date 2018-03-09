package control;

import textures.Cell;
import textures.covering.Tree;
import units.Creature;
/**
 * Класс представляет собой управление игрока
 * и одновременно управление при работе в движке. 
 * @author Viteker
 * @version 1.2
 */
public abstract class Control {
	/**
	 * Текущая клетка, которую мы хотим поставить
	 */
	protected static Cell cell;// static обязательно, так как объектов управления много, а экземпляр у них должен быть один
	/**
	 * Текущий юнит, который мы хотим поставить
	 */
	protected static Creature creature; // static обязательно, так как объектов управления много, а экземпляр у них должен быть один
	/**
	 * Дерево
	 */
	protected static Tree tree;// static обязательно, так как объектов управления много, а экземпляр у них должен быть один
	/**
	 * Координаты указателя, для подсветки указанной клетки
	 */
	public static int x,y;//Координаты Указателя
	/**
	 * Метод который обнуляем все поля,
	 * т.е. если мы не хотим ничего ставить
	 */
	protected void obnull(){
		cell=null;
		creature=null;
		tree = null;
	}
}
