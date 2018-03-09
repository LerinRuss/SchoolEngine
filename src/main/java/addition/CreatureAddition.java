package addition;

import coordinates.IntegerCoordinates;
import main.Main;
import map.SizeField;
import units.Creature;

public class CreatureAddition {
	/*
	 * Loupe for order to add game object relative to one.
	 */
	private final LoupeTransformation loupe;
	
	public CreatureAddition(LoupeTransformation loupe) {
		this.loupe = loupe;
	}
	
	public void addAlly(Creature creature,IntegerCoordinates coords) {
		creature.setCoordinates(loupe.transformCoordinates(coords.getX()/SizeField.SIZE_FIELD*SizeField.SIZE_FIELD, coords.getY()/SizeField.SIZE_FIELD*SizeField.SIZE_FIELD));
		Main.creatures.addAlly(creature);
	}
	
	public void addEnemy(Creature creature,IntegerCoordinates coords) {
		creature.setCoordinates(loupe.transformCoordinates(coords.getX()/SizeField.SIZE_FIELD*SizeField.SIZE_FIELD, coords.getY()/SizeField.SIZE_FIELD*SizeField.SIZE_FIELD));
		Main.creatures.addEnemy(creature);
	}
	public void addHero(Creature creature,IntegerCoordinates coords) {
		creature.setCoordinates(loupe.transformCoordinates(coords.getX()/SizeField.SIZE_FIELD*SizeField.SIZE_FIELD, coords.getY()/SizeField.SIZE_FIELD*SizeField.SIZE_FIELD));
		Main.creatures.setHero(creature);
	}
}
