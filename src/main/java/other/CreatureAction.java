package other;

import java.util.Iterator;

import coordinates.IntegerCoordinates;
import units.Creature;
/**
 * 
 * @author 123
 *
 */
public interface CreatureAction {
	/**
	 * Выполнение AI
	 * @param it - итератор объекта в коллекции
	 */
	public void action(Iterator<Creature> it);
	/**
	 * 
	 * @param coords1
	 * @param coords2
	 * @return квадрат расстояния между точками
	 */
	default int hypot(IntegerCoordinates coords1, IntegerCoordinates coords2){
		int x1 = coords1.getX();
		int y1 = coords1.getY();
		int x2 = coords2.getX();
		int y2 = coords2.getY();
		
		return (x1 - x2)*(x1 - x2)+(y1 - y2)*(y1 - y2);
	}
	
	/* !!!Протестированно!!!
	 *  Вещественный расчет расстояния между двумя точками
	 * @param x1 
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return квадрат расстояния между точками
	 *
	default float hypot(float x1,float y1,float x2, float y2){
		return (x1 - x2)*(x1 - x2)+(y1 - y2)*(y1 - y2);
	}
	/** !!!Протестированно!!!
	 *  Целый расчет расстояния между двумя точками
	 * @param x1 
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return квадрат расстояния между точками
	 *
	default int hypot(int x1,int y1,int x2, int y2){
		return (x1 - x2)*(x1 - x2)+(y1 - y2)*(y1 - y2);
	}
	*/
}
