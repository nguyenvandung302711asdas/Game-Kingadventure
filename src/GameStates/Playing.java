package GameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Main.Game;
import Ui.PauseOverlay;											//PauseMenu
import Utilz.LoadSave;
import Ui.PauseOverlay;


public class Playing extends State implements StateMethods{
	
	private PauseOverlay pauseOverlay;
	private boolean paused = true;
	
	public Playing(Game game) {
		super(game);
		pauseOverlay = new PauseOverlay(this);						//PauseMenu

	}
	
	@Override
	public void update() {
		if(paused)
			pauseOverlay.update();
		else ;
		
	}

	@Override
	public void draw(Graphics g) {
		if(paused)
			pauseOverlay.draw(g);	
		else ;
			
	}

	@Override
	public void mouseClicked(MouseEvent e) {
//		System.out.println("Clicked");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (paused)
			pauseOverlay.mousePressed(e);
		else ;
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseReleased(e);
		else ;
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseMoved(e);
		else ;
		
	}

	public void unpauseGame() {
		paused = false;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			GameState.state = GameState.MENU;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
