package GameStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Main.Game;
import Ui.GameOverOverlay;
import Ui.LevelCompletedOverlay;
import Ui.PauseOverlay;											//PauseMenu
import Utilz.LoadSave;
import Ui.PauseOverlay;
import Entities.Enemy;
import Entities.EnemyManager;
import Entities.Player;
import Levels.LevelManager;


public class Playing extends State implements StateMethods{
	
	private Player player;
	private EnemyManager enemyManager;
	private LevelManager levelManager;
	private PauseOverlay pauseOverlay;
	private GameOverOverlay gameOverOverlay;
	private LevelCompletedOverlay levelCompletedOverlay;
	
	private boolean paused = false;
	private boolean gameOver = false;
	private boolean lvlCompleted = false;
	
	public Playing(Game game) {
		super(game);
		initClasses();
		loadStartLevel();
	}

	public void initClasses() {
		levelManager = new LevelManager(game);
		player = new Player(50, 270, 78, 57, this);
		player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
		player.setSpawn(levelManager.getCurrentLevel().getPlayerSpawn());
		enemyManager = new EnemyManager(this);
		pauseOverlay = new PauseOverlay(this);						//PauseMenu
		gameOverOverlay = new GameOverOverlay(this);
		levelCompletedOverlay = new LevelCompletedOverlay(this);
	}
	
	public void loadNextLevel() {
		resetAll();
		levelManager.loadNextLevel();
		player.setSpawn(levelManager.getCurrentLevel().getPlayerSpawn());
	}
	
	private void loadStartLevel() {
		enemyManager.loadEnemies(levelManager.getCurrentLevel());
	}
	
	@Override
	public void update() {
		if (paused) {
			pauseOverlay.update();
		} else if (lvlCompleted) {
			levelCompletedOverlay.update();
		} else if (!gameOver) {
			levelManager.update();
			enemyManager.update(levelManager.getCurrentLevel().getLevelData(), player);
			player.update();
		}
	}

	@Override
	public void draw(Graphics g) {
		player.render(g);
		enemyManager.draw(g);
			
		if (paused) {
			g.setColor(new Color(0, 0, 0, 150));
			g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
			pauseOverlay.draw(g);
		} else if (gameOver)
			gameOverOverlay.draw(g);
		else if (lvlCompleted)
			levelCompletedOverlay.draw(g);
	}
	
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
//		System.out.println("Clicked");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (!gameOver)
			if (paused)
				pauseOverlay.mousePressed(e);
		if(lvlCompleted)
			levelCompletedOverlay.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (!gameOver)
			if (paused)
				pauseOverlay.mouseReleased(e);
		if(lvlCompleted)
			levelCompletedOverlay.mouseReleased(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (!gameOver)
			if (paused)
				pauseOverlay.mouseMoved(e);
		if(lvlCompleted)
			levelCompletedOverlay.mouseMoved(e);
	}

	public void unpauseGame() {
		paused = false;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (gameOver)
			gameOverOverlay.keyPressed(e);
		else
		switch(e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			paused = !paused;
			break;
		case KeyEvent.VK_A:
			player.setLeft(true);;
			break;
		case KeyEvent.VK_D:
			player.setRight(true);
			break;
//		case KeyEvent.VK_W:
//			player.setUp(true);
//			break;
//		case KeyEvent.VK_S:
//			player.setDown(true);
//			break;
		case KeyEvent.VK_J:
			player.setAttacking(true);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(true);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (!gameOver)
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			player.setLeft(false);
			break;
		case KeyEvent.VK_D:
			player.setRight(false);
			break;
//		case KeyEvent.VK_W:
//			player.setUp(false);
//			break;
//		case KeyEvent.VK_S:
//			player.setDown(false);
//			break;
//		case KeyEvent.VK_J:
//			player.setAttacking(false);
//			break;
		case KeyEvent.VK_SPACE:
			player.setJump(false);
			break;
		}
		
	}

	public void resetAll() {
		gameOver = false;
		paused = false;
		lvlCompleted = false;
		player.resetAll();
		enemyManager.resetAllEnemies();
	}
	
	public void checkEnemyHit(Rectangle2D.Float attackBox) {
		enemyManager.checkEnemyHit(attackBox);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public EnemyManager getEnemyManager() {
		return enemyManager;
	}

	public void setLevelCompleted(boolean b) {
		lvlCompleted = b;
		
	}
}
