package units;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import control.MouseControlled;
import coordinates.Coordinates;
import coordinates.IntegerCoordinates;
import map.SizeField;
import other.CreatureAction;
import textures.Cell;
import textures.covering.Grass;
import textures.covering.Water;
import textures.walls.Wall;
import units.path.ISearchPath;
import units.path.Node;
import units.path.SearchPathAstar;

public abstract class Creature implements CreatureAction,MouseControlled,IntegerCoordinates,Cloneable{

	protected float x,y;
	/**
	 * Текстура
	 */
	protected transient BufferedImage texture;
	/*
	 * Поиск пути
	 */
	protected ISearchPath searhPath = new SearchPathAstar();
	/*
	 * Узловая цель, до которой перемещаемся
	 */
	protected Node target;
	/*
	 * Стандартная проверка для проходимости
	 */
	protected IPassability passability = new Passability();
	/*
	 * Радиус обзора 
	 */
	private int review = 250;
	/*
	 * Радиус аттаки
	 */
	private int attackRange = 10;
	/*
	 * Урон, который наносит существо
	 */
	private int damage = 25;
	/*
	 * Здоровье существа
	 */
	protected int health = 100;//protected заметь, может и не нужно!!!
	/*
	 * Спрятан или нет
	 */
	protected boolean hidden;
	/*
	 * скорость существа
	 */
	protected int speed = 5;
	/**
	 * Устанавливает полю {@link Cell#texture} стандартную
	 * картинку
	 */
	public abstract void setDefaultTexture();
	/*
	 * Рисуем существо
	 */
	public abstract void paint(Graphics g);
	/**
	 * Устанавливаем коодинаты существу по правилу, так чтобы его координаты распологались в центре.
	 * Т.е. мы прибавляем SIZE_PART, т.к. при добавлении существа в редакторе его центр не совпадает с курсором
	 */
	public void setCoordinates(IntegerCoordinates coords){
		this.x = coords.getX() + SizeField.SIZE_PART;
		this.y = coords.getY() + SizeField.SIZE_PART;
		System.out.println("Координаты создания игрока: = ["+x+";"+y+"]");
	}
	/*
	 * Если есть узловая цель, то за каждую итерацию метода action()
	 * меняем координату этого существа
	 */
	protected void move(){
		int targetX = target.getX() * SizeField.SIZE_FIELD + SizeField.SIZE_PART;//Смещения координат в центр клетки
		int targetY = target.getY() * SizeField.SIZE_FIELD + SizeField.SIZE_PART;
		
		if(getX() == targetX && getY() == targetY){
			target = target.getParent();
			return;
		}
		if(getX() > targetX) x--;
		if(getX() < targetX) x++;
		if(getY() > targetY) y--;
		if(getY() < targetY) y++;
		
		
	}
	
	/**
	 * Клонируем
	 * @throws CloneNotSupportedException 
	 */
	public Creature clone() throws CloneNotSupportedException{
		return (Creature)super.clone();
	}
	/*
	 * Проверка есть ли цель, true - есть
	 * false - нет
	 */
	public boolean isTarget(){
		return target == null ? false : true;
	}
	public boolean isHidden(){
		return hidden;
	}
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return (int) x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return (int) y;
	}
	@Override
	public IntegerCoordinates getCoordinates() {
		// TODO Auto-generated method stub
		return new Coordinates(getX(), getY());
	}
	
	/**
	 * Метод, который аттакует ТЕКУЩЕЕ существо, тоесть себя
	 * @param damage2 = наносимый урон
	 * @return true - если существо погибло
	 * false - иначе
	 */
	public boolean attacked(int damage2) {
		health -=damage2;
		if(health <= 0)
			return die();
		return false;
	}
	private boolean die(){
		System.out.println("бот умер");
		return true;
	}
	public int getReview() {
		return review;
	}
	public int getAttackRange() {
		// TODO Auto-generated method stub
		return attackRange;
	}
	public int getDamage() {
		// TODO Auto-generated method stub
		return damage;
	}
	/*
	 * Внутрений класс, проверки проходимости	
	 */
	public class Passability implements IPassability{

		@Override
		public boolean checkPassability(Cell cell) {
			if(cell instanceof Wall || cell instanceof Water)
				return false;
			//if(cell instanceof Grass && !((Grass)cell).checkOn())
			//	return false;
			return true;
		}
		
	}
}
