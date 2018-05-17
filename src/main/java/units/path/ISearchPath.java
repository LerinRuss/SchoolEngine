package units.path;

import coordinates.IntegerCoordinates;
import textures1.Cell;
import units.IPassability;

public interface ISearchPath {
	public Node searchPath(Cell cell[][], IntegerCoordinates start, IntegerCoordinates goal, IPassability passability);
		
}
