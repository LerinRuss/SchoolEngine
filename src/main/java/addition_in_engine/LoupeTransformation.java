package addition_in_engine;

import coordinates.Coordinates;
import coordinates.IntegerCoordinates;

public class LoupeTransformation {
	/*
	 * Координаты лупы в игре, чтобы расставлять существ в соответствии с позицией лупы
	 */
	private IntegerCoordinates loupe;
	
	public LoupeTransformation(IntegerCoordinates loupe) {
		this.loupe = loupe;
	}
	/*
	 * Преобразуем игровые координаты в соответствии с местоположением лупы
	 */
	private int putX(int x){
		return x + loupe.getX();
	}
	/*
	 * Преобразуем игровые координаты в соответствии с местоположением лупы
	 */
	private int putY(int y){
		return y + loupe.getY();
	}
	/**
	 * Преобразование координат относительно лупы
	 */
	public IntegerCoordinates transformateCoordinates(int x,int y){
		return new Coordinates(putX(x), putY(y));
	}
}
