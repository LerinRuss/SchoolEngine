package ai;

import java.util.Collection;

import units.Creature;

public class Calm implements AI{

	@Override
	public AI doing(Creature creature,Collection<Creature> enemies) {
		int distance;
		for (Creature current : enemies) {
			distance = creature.hypot(creature.getCoordinates(),current.getCoordinates());
			if(distance < creature.getReview()*creature.getReview() && !current.isHidden() && creature != current){
				return new Aggression(current);
			}
		}
		return this;
	}

}
