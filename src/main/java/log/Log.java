package log;
/**
 * Класс для вывода на консоль событий игры.
 * Например, созданный размер окна и игровой карты
 * Что-нибудь еще
 * @author Lerin
 * @version 0.2
 */
public class Log {
	/**
	 * Метод печатающий на консоль размеры разных объектов,
	 * которые инизиализировались в игре, допустим, даже размер
	 * окна или игрового поля
	 * @param s - размер чего указываем
	 * @param width - его ширина
	 * @param height - его высота
	 */
	public static void printSizes(String s,int width,int height){
		System.out.println("Размер "+s+" = "+width+":"+height);
	}
	/**
	 * Печатаем текущий объект, который выбрали в редакторе,
	 * чтобы поставить его в игре
	 * @param obj - объект который хотим поставить
	 */
	public static void printCreated(String obj){
		System.out.println(obj + " создана");
	}
	/**
	 * печатаем созданные размеры окна
	 * @param width - ширина окна
	 * @param height - высота окна
	 */
	public static void printSizeWindow(int width,int height){
		System.out.println("размер окна "+width+":"+height);
	}
	/**
	 * Просто печатаем что-то
	 * @param obj - объект который хотим поставить
	 */
	public static void println(String obj){
		System.out.println(obj);
	}
}
