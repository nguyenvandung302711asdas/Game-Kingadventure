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
import Entities.Enemies;
import Entities.Player;


public class Playing extends State implements StateMethods{
	
	private Player player;
	private Enemies enemy1, enemy2, enemy3, enemy4;
	private PauseOverlay pauseOverlay;
	private boolean paused = false;
	
	public Playing(Game game) {
		super(game);
		initClasses();

	}
	
	public void initClasses() {
		
		enemy1 = new Enemies(800, 300, 32 , 32);
		enemy2 = new Enemies(600, 200, 32 , 32);
		enemy3 = new Enemies(200, 300, 32 , 32);
		enemy4 = new Enemies(400, 300, 32 , 32);
		player = new Player(300, 500, 78, 57);
		pauseOverlay = new PauseOverlay(this);						//PauseMenu
	}
	
	@Override
	public void update() {
		if(!paused) {
			player.update();
			enemy1.update();
			enemy2.update();
			enemy3.update();
			enemy4.update();
		}
		else 
			pauseOverlay.update();
		
	}

	@Override
	public void draw(Graphics g) {
		if(!paused) {
			player.render(g);
			enemy1.render(g);
			enemy2.render(g);
			enemy3.render(g);
			enemy4.render(g);
		}
		else 
			pauseOverlay.draw(g);	
			
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
			paused = !paused;
			break;
		case KeyEvent.VK_A:
			player.setLeft(true);
			break;
		case KeyEvent.VK_D:
			player.setRight(true);
			break;
		case KeyEvent.VK_W:
			player.setUp(true);
			break;
		case KeyEvent.VK_S:
			player.setDown(true);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			player.setLeft(false);
			break;
		case KeyEvent.VK_D:
			player.setRight(false);
			break;
		case KeyEvent.VK_W:
			player.setUp(false);
			break;
		case KeyEvent.VK_S:
			player.setDown(false);
			break;
		case KeyEvent.VK_SPACE:
			break;
		}
		
	}
	
	public Player getPlayer() {
		return player;
	}
	
}
