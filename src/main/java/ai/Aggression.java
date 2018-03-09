package ai;

import java.util.Collection;

import coordinates.Coordinates;
import coordinates.IntegerCoordinates;
import main.Main;
import other.CreatureAction;
import units.Creature;

public class Aggression implements AI{
	private Creature target;
	/*
	 * Самые начальные координаты цели, если у цели не совпадают координаты с этими.
	 * Значит цель сместилась и надо заново искать маршрут
	 */
	private IntegerCoordinates oldTargetCoords;
	public Aggression(CreatureAction current) {
		target = (Creature)current;
		oldTargetCoords = new Coordinates(target.getX(),target.getY());
	}
	 
	public AI doing(Creature creature,Collection<Creature> enemies){
		if(!creature.isTarget()){
			creature.moveTo(target);
		}else if(checkTargetOffset(target)){
			oldTargetCoords = new Coordinates(target.getX(),target.getY());
			creature.moveTo(target);
		}
		
		int distance = creature.hypot(creature.getCoordinates(),target.getCoordinates());
		boolean killed = false;
		if(distance < creature.getAttackRange()*creature.getAttackRange())
			killed = target.attacked(creature.getDamage());
		if(killed || distance>creature.getReview()*creature.getReview())
			return new Calm();
		return this;
	}

	private boolean checkTargetOffset(IntegerCoordinates t2) {
		IntegerCoordinates t1 = Main.mp.transformateCoordinates(oldTargetCoords.getX(), oldTargetCoords.getY());
		t2 = Main.mp.transformateCoordinates(t2.getX(), t2.getY());
		
		return !(t1.getX() == t2.getX() && t1.getY() == t2.getY());
	}
	
}
