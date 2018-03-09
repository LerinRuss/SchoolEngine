package control;

import coordinates.IntegerCoordinates;

/**
 * Этот интерфейс означает, что этим объектом игрок может управлять в игре  
 * @author Viteker
 * @version 0.0
 */
public interface MouseControlled {
	/**
	 * Указывает куда идти управляемому объекту
	 * @param t - координаты к цели
	 */
	void moveTo(IntegerCoordinates t);
}
