package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.ListIterator;

import javax.swing.Timer;

import main.Main;
import textures.covering.Tree;
import units.Creature;
import window.GameMap;
/**
 * 
 * @author Viteker
 * @version 1.2
 */
public class Controler implements ActionListener{
	private GameMap gm;//Игровое поле которое постоянно отрисовываем
	
	//Конструкторо при котором запускаем Timer и указываем игровое поле
	public Controler(GameMap gm){
		this.gm=gm;
		Timer t=new Timer(10,this);
		t.start();
	}
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * Отрисовываем необходимые объекты
	 * И вызывает действие у AI
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		gm.repaint();
		gm.getLoupe().moving(50);
		
		Iterator<Creature> allies = Main.creatures.getAllies().listIterator();
		Iterator<Creature> enemies = Main.creatures.getEnemies().listIterator();
		ListIterator<Tree> trees = Main.trees.getTrees().listIterator();
		
		//Пробегаемся по союзникам и врагам по очереди, игрок в списке первый
		//Игрок
		Creature creature;
		while(allies.hasNext() || enemies.hasNext()){
			if(allies.hasNext()){
				creature = (Creature) allies.next();
				creature.action(allies);
				creature.paint(gm.getBackgroundGraphics());//Рисуем существо, но еще не отрисовываем на экране
			}
			if(enemies.hasNext()){
				creature = (Creature) enemies.next();
				creature.action(enemies);
				creature.paint(gm.getBackgroundGraphics());//Рисуем существо, но еще не отрисовываем на экране
			}
		}
		Tree tree;
		while(trees.hasNext()){
			tree = (Tree) trees.next();
			tree.action(trees);
		}
		gm.paint(gm.getBackgroundGraphics());//Это чтобы, в конце всех махинаций, отрисовать все на кране используя лупу
	}
}
