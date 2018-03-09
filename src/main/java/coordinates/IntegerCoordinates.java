package coordinates;

/**
 * Этот класс  описывает объекты, которые имеют
 * целочисленные координаты
 * @author Viteker
 * @version 0.1
 */
public interface IntegerCoordinates {
	/**
	 * Возвращает координату x
	 * @return x - координата
	 */
	int getX();
	/**
	 * Возвращает координату y
	 * @return y - координата
	 */
	int getY();
	/**
	 * Возвращает укомплектованные координаты 
	 */
	IntegerCoordinates getCoordinates();
}
