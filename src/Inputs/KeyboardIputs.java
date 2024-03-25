package Inputs;



import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Main.GamePanel;
import Utilz.Constants;

public class KeyboardIputs implements KeyListener{

//	private static final int UP = 0;
//	private static final int LEFT = 1;
//	private static final int DOWN = 2;
//	private static final int RIGHT = 3;
	private GamePanel gamePanel;
    public KeyboardIputs(GamePanel gamePanel) {
	 this.gamePanel = gamePanel;
	
 }
	@Override  
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
//		switch (e.getKeyCode()) {
//		case KeyEvent.VK_W:
//		case KeyEvent.VK_A:
//		case KeyEvent.VK_S:
//		case KeyEvent.VK_D:
//			gamePanel.setMoving(false);
//			break;
//		}
//		
	}

	@Override
	public void keyReleased(KeyEvent e) {
//		switch (e.getKeyCode()) {
//		case KeyEvent.VK_W:
//			gamePanel.setDirection(UP);
//			break;
//		case KeyEvent.VK_A:
//			gamePanel.setDirection(LEFT);
//			break;
//		case KeyEvent.VK_S:
//			gamePanel.setDirection(DOWN);
//			break;
//		case KeyEvent.VK_D:
//			gamePanel.setDirection(RIGHT);
//			break;
//		}
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A: {
			System.out.println("A");break;
		}
		case KeyEvent.VK_D:{
			System.out.println("D");break;
		}
		
		}
	}
}


