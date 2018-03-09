package control;
/**
 * Класс описывающий объекты, которые управляемы через клавиатуру
 * @author Viteker
 * @version 0.0
 */
public interface KeyControlled{
	/**
	 * Двигаем объект по игровому полю
	 * где передаваемые булевые значения указывают в какую сторону двигать
	 * Присутствует проверка: выход объекта за игровое поле - недопустимо
	 * @param up - движение вверх
	 * @param down - движение вниз
	 * @param left - движение влево
	 * @param right - движение вправо 
	 */
	public void move(boolean up,boolean down,boolean left,boolean right);
}
