package window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import control.KeyControl;
import control.MouseControl;
import control.MouseMotionControl;
/**
 * 
 * @author Viteker
 * @version 0.1
 */
public class Window extends Frame{
	/*
	 * Стартовая инициализация окна
	 */
	{
		//Устанавливаем размер окна где ширина и высота: 3/4 от размера экрана
		Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
		int width=(int) (size.getWidth()-size.getWidth()/4), 
				height=(int) (size.getHeight()-size.getHeight()/4);
		height = 680;
		//Настраиваем окно
		setDefaultSettings(width, height);
		addListeners();
	}
	
	/*
	 * Устанавливаем размер и расположеиние окна,
	 *  а также его способность изменять размер
	 */
	private void setDefaultSettings(int width,int height){
		setSize(width,height);
		setLocation(width*2/3-width/2,0);
		setResizable(false);
		
		setBackground(Color.BLACK);
	}
	/*
	 * Устанавливаем слушаетелей окна
	 */
	private void addListeners(){
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}
}
