package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import addition.CreatureAddition;
import addition.LoupeTransformation;
import coordinates.Coordinates;
import log.Log;
import main.Main;
import units.TestAlly;
import units.TestUnit;
/**
 * 
 * @author Viteker
 * @version 0.2
 */
public class MouseMotionControl extends Control implements MouseMotionListener{
	/*
	 * Добавление в игру разных существ, когда нажимает клавишу мыши
	 */
	private CreatureAddition addCreature;
	/*
	 * Преобразование координат, относительно лупы
	 */
	private LoupeTransformation loupe;
	/**
	 * Базовый конструктор инициализации полей
	 * @param loupe 
	 */
	public MouseMotionControl(CreatureAddition addCreature, LoupeTransformation loupe){
		super();
		this.addCreature = addCreature;
		this.loupe = loupe;
	}
	@Override
	public void mouseDragged(MouseEvent e){
		/*
		if(e.getButton() != MouseEvent.BUTTON1){
			return;
		}
		*/
		if(cell!=null){
			try {
				Main.mp.add(loupe.transformCoordinates(e.getX(), e.getY()), cell.clone());
			} catch (CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(tree != null){
			try {
				Main.mp.addObjects(loupe.transformCoordinates(e.getX(), e.getY()), tree.clone());
			} catch (CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(creature != null){
			if(creature instanceof TestUnit){
				if(Main.creatures.getHero() == null){
					addCreature.addHero(creature, new Coordinates(e.getX(), e.getY()));
					Main.keyControl.setKeyControlHero(creature);
				}else{
					Log.println("Образец героя и так уже есть в игре");
				}
			}else if(creature instanceof TestAlly){
				try {
					addCreature.addAlly(creature.clone(), new Coordinates(e.getX(), e.getY()));
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else{
				try {
					addCreature.addEnemy(creature.clone(), new Coordinates(e.getX(), e.getY()));
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x=e.getX();y=e.getY();
	}

}
