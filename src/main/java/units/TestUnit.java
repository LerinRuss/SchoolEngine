package units;

import java.awt.Graphics;
import java.util.Iterator;

import control.KeyControlled;
import coordinates.IntegerCoordinates;
import main.Main;
import map.SizeField;

public class TestUnit extends Creature implements KeyControlled{
	public TestUnit(){
		setDefaultTexture();
	}
	@Override
	public void action(Iterator<Creature> it) {
		if(health <= 0 ){
			die(it);
			return;
		}
		hidden = false;
		if(Main.mp.checkTree(Main.mp.transformateCoordinates(getX(), getY()))){
			hidden = true;
		}
		if(target != null){
			move();
		}
	}

	@Override
	public void moveTo(IntegerCoordinates t) {
		System.out.println("start = "+getX()+";"+getY()+"  goal = "+t);
		long time = System.currentTimeMillis();
		target = searhPath.searchPath(Main.mp.getCell(),this, t, passability);
		System.out.println(" delta = "+(System.currentTimeMillis() - time));
		
	}
	public void move(boolean up,boolean down, boolean left, boolean right){
		if(up)y-=speed;
		if(down)y+=speed;
		if(left)x-=speed;
		if(right)x+=speed;
	}
	@Override
	public void setDefaultTexture() {
		texture = Main.textures.getUnit().getTestUnit();
	}

	@Override
	public void paint(Graphics g) {
		if(!hidden){
			g.drawImage(texture, getX() - SizeField.SIZE_PART, getY() - SizeField.SIZE_PART, null);
		}else{
			int xpoints[] = {getX() - SizeField.SIZE_PART,getX(),getX()+SizeField.SIZE_PART};
			int ypoints[] = {getY() - SizeField.SIZE_PART,getY() + SizeField.SIZE_PART,getY() - SizeField.SIZE_PART};
			g.drawPolygon(xpoints, ypoints, 3);
		}
	}
	public boolean attacked(int damage2) {
		health -=damage2;
		if(health <= 0)
			return true;
		return false;
	}
	private boolean die(Iterator<Creature> it){
		System.out.println("Герой умер");
		it.remove();
		Main.creatures.setHero(null);
		return true;
	}
}
