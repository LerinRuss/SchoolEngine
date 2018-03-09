package addition_in_engine;

import coordinates.IntegerCoordinates;
import main.Main;
import map.SizeField;
import units.Creature;

public class AdditionCreatures{
	/*
	 * Координаты лупы в игре, чтобы расставлять существ в соответствии с позицией лупы
	 */
	private LoupeTransformation loupe;
	
	public AdditionCreatures(LoupeTransformation loupe) {
		this.loupe = loupe;
	}
	
	public void addAlly(Creature creature,IntegerCoordinates coords) {
		creature.setCoordinates(loupe.transformateCoordinates(coords.getX()/SizeField.SIZE_FIELD*SizeField.SIZE_FIELD, coords.getY()/SizeField.SIZE_FIELD*SizeField.SIZE_FIELD));
		Main.creatures.addAlly(creature);
	}
	
	public void addEnemy(Creature creature,IntegerCoordinates coords) {
		creature.setCoordinates(loupe.transformateCoordinates(coords.getX()/SizeField.SIZE_FIELD*SizeField.SIZE_FIELD, coords.getY()/SizeField.SIZE_FIELD*SizeField.SIZE_FIELD));
		Main.creatures.addEnemy(creature);
	}
	public void addHero(Creature creature,IntegerCoordinates coords) {
		creature.setCoordinates(loupe.transformateCoordinates(coords.getX()/SizeField.SIZE_FIELD*SizeField.SIZE_FIELD, coords.getY()/SizeField.SIZE_FIELD*SizeField.SIZE_FIELD));
		Main.creatures.setHero(creature);
	}
}
