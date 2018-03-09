package ai;

import java.util.Collection;

import other.CreatureAction;
import units.Creature;

public interface AI{

	AI doing(Creature creature,Collection<Creature> enemies);
	
}
