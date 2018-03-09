package other;

import java.util.ListIterator;

import textures.covering.Tree;

public interface TreeAction {
	/**
	 * Выполнение действий
	 * @param it - итератор объекта в коллекции
	 */
	public void action(ListIterator<Tree> it);
}
