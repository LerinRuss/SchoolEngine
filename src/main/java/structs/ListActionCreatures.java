package structs;

import java.util.LinkedList;

import other.CreatureAction;
import units.Creature;

/**
 * Класс описывает список существ в игре
 * @author Viteker
 * @version 0.3
 */
public class ListActionCreatures {
	/*
	 * Союзники в игре
	 */
	private LinkedList<Creature> allies;
	/*
	 * Враги в игре
	 */
	private LinkedList<Creature> enemies;
	/*
	 * Игрок
	 */
	private Creature hero;
	/**
	 * Конструктор создает массив существ, максимальное кол-во которых указывается
	 * в параметрах
	 * @param length - максимальное количество существ
	 */
	public ListActionCreatures(){
		allies = new LinkedList<>();
		enemies = new LinkedList<>();
	}
	
	public LinkedList<Creature> getAllies(){
		return allies;
	}
	public LinkedList<Creature> getEnemies(){
		return enemies;
	}
	public Creature getHero(){
		return hero;
	}
	public void addEnemy(Creature creature) {
		enemies.add(creature);
	}
	public void addAlly(Creature creature){
		allies.add(creature);
	}
	/**
	 * Добавляем игрока в список союзников, он всегда первый в списке
	 * @param hero
	 */
	public void setHero(Creature hero){
		if(this.hero == null){//Если игрока еще нет, или его убили
			this.hero = hero;
			allies.addFirst(hero);
		}else if(hero != null){//Если передаваемый игрок живой, то меняем его в списке
			this.hero = hero;
			allies.removeFirst();
			allies.addFirst(hero);
		}else{
			this.hero = hero;//Иначе, если игрок живой,а передали null, это значит его нужно убить
		}
	}
}
