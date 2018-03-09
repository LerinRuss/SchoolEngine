package map;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ListIterator;

import coordinates.Coordinates;
import coordinates.IntegerCoordinates;
import log.Log;
import main.Main;
import textures.Cell;
import textures.covering.Grass;
import textures.covering.Tree;
import textures.covering.Water;
import textures.walls.Wall;

/**
 * Игровая карта на которой размещены все статические объекты и именно отсюда
 * вызывается отрисовка всех объектов, находящихся на карте.
 * 
 * @author Lerin
 * @version 1.3
 */
public class Map implements Serializable, SizeField {
	/**
	 * Массив текстур на карте, на которые можно установить объекты. Это может
	 * быть трава, стена и прочее
	 */
	private Cell[][] map;

	/**
	 * Главный конструктор, который устанавливает размер игрового поля по
	 * правилу: ширину и высоту делим на размер ячейки поля. Затем полученную
	 * ширину и высоту устанавливаем на карте. И тут же инициализируем стартовую
	 * карту.
	 * 
	 * @see Map#installMap(int, int)
	 */
	public Map(int width, int height) {
		width /= SIZE_FIELD;
		height /= SIZE_FIELD;
		Log.printSizes("карты", width, height);
		installMap(width, height);
	}

	/**
	 * Конструктор который устанавливает размер игровой карты, вызывая главный
	 * конструктор
	 * 
	 * @see Map#Map(int,int)
	 * @param size
	 *            - размер установки игрового поля
	 */
	public Map(Dimension size) {
		this((int) size.getWidth(), (int) size.getHeight());
	}

	/**
	 * Инициализирует массив {@link Map#map} Заполняя карту травой
	 * {@link textures.covering.Grass}, а по краям устанавливает стену
	 * {@link textures.walls.Wall} Размеры травы и стены - размер ячейки.
	 * 
	 * @param width
	 *            - количество столбцов в массиве
	 * @param height
	 *            - кол-во строчек в массиве
	 */
	private void installMap(int width, int height) {
		map = new Cell[height][width];
		int x, y = 0;
		for (y = 1; y < height - 1; y++)
			for (x = 1; x < width - 1; x++)
				map[y][x] = new Grass(x * SIZE_FIELD + SizeField.SIZE_PART, y * SIZE_FIELD + SizeField.SIZE_PART, Main.textures.getNatureAndContruction().getGrass());

		y = 0;
		for (x = 0; x < width; x++)
			map[y][x] = new Water(x * SIZE_FIELD + SizeField.SIZE_PART, y * SIZE_FIELD + SizeField.SIZE_PART, Main.textures.getNatureAndContruction().getWater());
		for (--x; y < height; y++)
			map[y][x] = new Water(x * SIZE_FIELD + SizeField.SIZE_PART, y * SIZE_FIELD + SizeField.SIZE_PART, Main.textures.getNatureAndContruction().getWater());
		for (--y; x >= 0; x--)
			map[y][x] = new Water(x * SIZE_FIELD + SizeField.SIZE_PART, y * SIZE_FIELD + SizeField.SIZE_PART, Main.textures.getNatureAndContruction().getWater());
		for (++x; y > 0; y--)
			map[y][x] = new Water(x * SIZE_FIELD + SizeField.SIZE_PART, y * SIZE_FIELD + SizeField.SIZE_PART, Main.textures.getNatureAndContruction().getWater());

	}

	/**
	 * Устанавливаем на карте в текущих координатах объект типа {@link Cell}
	 * 
	 * @param x
	 * @param y
	 * @param on
	 *            - объект типа {@link Cell}
	 */
	private void setCellOnMap(int x, int y, Cell on) {
		if (x < 0 || x >= map[0].length || y < 0 || y >= map.length)
			return;
		// map[y][x].setOn(on);
		map[y][x] = on;
	}

	/**
	 * Расставляет на карте, необходимые текстуры сначала преобразуем переданные
	 * координаты в координаты массива карты а потом задаем значение клетки, то
	 * есть ее координаты в массштабной карте. и наконец ставим клетку
	 * 
	 * @param x
	 * @param y
	 * @param on
	 *            - объект, который хотим поставить на поле типа {@link Cell}
	 */
	public void add(IntegerCoordinates coords, Cell on) {
		int x =coords.getX() / SIZE_FIELD;
		int y =coords.getY() / SIZE_FIELD;
		on.setValues(x * SIZE_FIELD + SIZE_PART, y * SIZE_FIELD + SIZE_PART);
		setCellOnMap(x, y, on);
	}
	/**
	 * Добавляет объекты на клетки карты
	 * @param transformateCoordinates
	 * @param clone
	 */
	public void addObjects(IntegerCoordinates coords, Tree tree) {
		if(!checkAddition(coords, tree))
			return;
		System.out.println("Работает");
		int x = coords.getX() / SIZE_FIELD;
		int y = coords.getY() / SIZE_FIELD;
		
		tree.setValues(x * SIZE_FIELD + SIZE_PART, y * SIZE_FIELD + SIZE_PART);
		Main.trees.addTree(tree);
		((Grass)map[y][x]).setOn(tree);
	}
	public boolean checkAddition(IntegerCoordinates coords,Tree tree){
		int x = coords.getX() / SIZE_FIELD;
		int y = coords.getY() / SIZE_FIELD;
		if(map[y][x] instanceof Grass && ((Grass) map[y][x]).checkOn())
			return true;
		return false;
	}
	/**
	 * Добавляет объекты на клетки карты, во время игры
	 * @param transformateCoordinates
	 * @param clone
	 */
	public void disseminateObjects(ListIterator<Tree> it,IntegerCoordinates coords, Tree tree) {
		if(!checkAddition(coords, tree))
			return;
		System.out.println("Распространение");
		int x = coords.getX() / SIZE_FIELD;
		int y = coords.getY() / SIZE_FIELD;
		
		tree.setValues(x * SIZE_FIELD + SIZE_PART, y * SIZE_FIELD + SIZE_PART);
		it.add(tree);
		((Grass)map[y][x]).setOn(tree);
	}
	/**
	 * Преобразуем координаты x и y в координаты
	 * массива карты
	 * @param x
	 * @param y
	 * @return Target - обертка для координат
	 */
	public IntegerCoordinates transformateCoordinates(int x, int y) {
		return new Coordinates(x/SIZE_FIELD, y/SIZE_FIELD);
	}
	/**
	 * Проверяем указывают ли переданные координаты на стену,
	 * если да пишем текст, что это стена
	 * @param g
	 * @param x
	 * @param y
	 */
	public void paintStringCell(Graphics g, int x, int y) {
		if (map[y][x] instanceof Wall) {
			g.drawString("Wall", x*SIZE_FIELD, y*SIZE_FIELD);
		}
	}
	/**
	 * 
	 */
	public boolean checkTree(IntegerCoordinates coords){
		if(map[coords.getY()][coords.getX()] instanceof Grass){
			return !((Grass)map[coords.getY()][coords.getX()]).checkOn();
		}
		return false;
	}
	/**
	 * Отрисовывает все клетки карты и рисует Текст если курсор наведен на Стену
	 * 
	 * @see interfaces.DrawStaticObject#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		for (Cell[] nodes : map) {
			for (Cell cell : nodes) {
				cell.paint(g);
			}
		}

	}

	public Cell[][] getCell() {
		return map;
	}
}
