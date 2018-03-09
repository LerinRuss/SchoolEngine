package structs;

import java.util.LinkedList;

import other.CreatureAction;
import textures.covering.Tree;

public class ListActionObjects {
	/*
	 * Деревья в игре
	 */
	private LinkedList<Tree> trees;
	
	public ListActionObjects(){
		trees = new LinkedList<>();
	}
	public LinkedList<Tree> getTrees(){
		return trees;
	}
	public void addTree(Tree tree) {
		trees.add(tree);
	}
}
