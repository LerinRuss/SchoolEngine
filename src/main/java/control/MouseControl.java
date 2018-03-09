package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import addition_in_engine.AdditionCreatures;
import addition_in_engine.LoupeTransformation;
import coordinates.Coordinates;
import log.Log;
import main.Main;
import units.Creature;
import units.TestAlly;
import units.TestUnit;
/**
 * !!!СОБИРАЮТ И ПОДАЮТ ИНФОРМАЦИЮ!!!
 * 
 * Класс для управления нажатия клавиш мышки в игре
 * @author Viteker
 * @version 1.5
 */
public class MouseControl extends Control implements MouseListener{
	/*
	 * Преобразование координат, относительно лупы
	 */
	private LoupeTransformation loupe;
	/**
	 * Базовый конструктор инициализации полей
	 * @param loupe 
	 */
	public MouseControl(LoupeTransformation loupe){
		super();
		this.loupe = loupe;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
	/**
	 * Берем координаты куда хотим поставить что-то, преобразуем их 
	 * с учетом куда смотрим игровая лупа и говорим карте ставить эту текстурку
	 * у себя. Мы как бы кидаем ей текстурку, и говорим куда ставить, а она ставит.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		Creature hero = Main.creatures.getHero();
		if(e.getButton() == MouseEvent.BUTTON3 && hero != null){
			hero.moveTo(loupe.transformateCoordinates(e.getX(), e.getY()));//Не забывай преобразовывать координаты относительно лупы
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * Клонируем объекты
	 * Сначала создаем байтовый массивный выходной поток, куда будем записывать наш клонируемый объект
	 * Затем создаем Объектовый поток, веди мы записываем объект
	 * 
	 * Аналогично переписывает в входной байтово-массивный поток, массив из байтово-выходного
	 * И записываем эти байты в объект и возвращаем этот объект
	 *
	private Object clone(Object obj) throws IOException, ClassNotFoundException{
		ByteArrayOutputStream array=new ByteArrayOutputStream();
		ObjectOutputStream out=new ObjectOutputStream(array);
		out.writeObject(obj);
		out.close();
		
		ByteArrayInputStream inArr=new ByteArrayInputStream(array.toByteArray());
		ObjectInputStream in=new ObjectInputStream(inArr);
		return in.readObject();
	}*/
}
