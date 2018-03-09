package units;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import ai.AI;
import ai.Calm;
import coordinates.IntegerCoordinates;
import main.Main;
import map.SizeField;
import other.CreatureAction;

public class TestEnemy extends Creature{
	AI ai = new Calm();
	@Override
	public void action(Iterator<Creature> it) {
		if(health <= 0){
			it.remove();
			return;
		}
		hidden = false;
		if(Main.mp.checkTree(Main.mp.transformateCoordinates(getX(), getY()))){
			hidden = true;
		}
		ai = ai.doing(this,Main.creatures.getAllies());
		if(target != null){
			move();
		}
	}

	@Override
	public void moveTo(IntegerCoordinates t) {
		target = searhPath.searchPath(Main.mp.getCell(),this, t, passability);
	}

	@Override
	public void setDefaultTexture() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paint(Graphics g) {
		Color old = g.getColor();
		if(!hidden){
			g.setColor(Color.RED);
			g.fillOval(getX()-SizeField.SIZE_PART, getY()-SizeField.SIZE_PART, SizeField.SIZE_FIELD, SizeField.SIZE_FIELD);
			g.setColor(Color.BLACK);
			g.drawOval(getX()- getReview(), getY() - getReview(), getReview()*2, getReview()*2);
			g.setColor(Color.GREEN);
			g.drawOval(getX() - getAttackRange(), getY() - getAttackRange(), getAttackRange()*2, getAttackRange()*2);
			
		}else{
			int xpoints[] = {getX() - SizeField.SIZE_PART,getX(),getX()+SizeField.SIZE_PART};
			int ypoints[] = {getY() - SizeField.SIZE_PART,getY() + SizeField.SIZE_PART,getY() - SizeField.SIZE_PART};
			g.setColor(Color.RED);
			g.drawPolygon(xpoints, ypoints, 3);
		}
		g.setColor(old);
	}

	/**
	 * Клонируем
	 * @throws CloneNotSupportedException 
	 */
	public TestEnemy clone() throws CloneNotSupportedException{
		return (TestEnemy)super.clone();
	}
}
