package structs;

import java.util.LinkedList;

import textures1.covering.Tree;

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
