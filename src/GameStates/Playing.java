package GameStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import Main.Game;
import Objects.ObjectManager;
import Ui.GameOverOverlay;
import Ui.LevelCompletedOverlay;
import Ui.PauseOverlay;			
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
	private ObjectManager objectManager;
	
	private int xLvlOffset;
	private int maxLvlOffsetX;
	private int leftBorder = (int) (0.2 * Game.GAME_WIDTH);
	private int rightBorder = (int) (0.8 * Game.GAME_WIDTH);
	
	private boolean paused = false;
	private boolean gameOver = false;
	private boolean lvlCompleted = false;
	private boolean playerDying;
	
	private int CurrentScore;
	public static int score;
	
	public Playing(Game game) {
		super(game);
		initClasses();
		loadStartLevel();
	}

	public void initClasses() {
		objectManager = new ObjectManager(this);
		levelManager = new LevelManager(game);
		player = new Player(50, 270, 78, 57, this);
		player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
		player.setSpawn(levelManager.getCurrentLevel().getPlayerSpawn());
		enemyManager = new EnemyManager(this);
		pauseOverlay = new PauseOverlay(this);
		gameOverOverlay = new GameOverOverlay(this);
		levelCompletedOverlay = new LevelCompletedOverlay(this);
	}
	
	private void loadStartLevel() {
		enemyManager.loadEnemies(levelManager.getCurrentLevel());
		objectManager.loadObjects(levelManager.getCurrentLevel());
		CurrentScore = score;
	}
	
	public void loadNextLevel() {
		resetAll();
		levelManager.loadNextLevel();
		player.setSpawn(levelManager.getCurrentLevel().getPlayerSpawn());
		CurrentScore = score;
	}
	
	public void loadPreviousLevel() {
		resetAll();
		levelManager.loadPreviousLevel();
		player.setSpawn(levelManager.getCurrentLevel().getPlayerSpawn());
	}
	
	@Override
	public void update() {
		System.out.println( score);
		if (paused) {
			pauseOverlay.update();
		} else if (lvlCompleted) {
			levelCompletedOverlay.update();
		} else if (gameOver) {
			gameOverOverlay.update();
		} else if (playerDying) {
			player.update();
		} else {
			levelManager.update();
			objectManager.update(levelManager.getCurrentLevel().getLevelData(), player);
			player.update();
			enemyManager.update(levelManager.getCurrentLevel().getLevelData(), player);
			checkCloseToBorder();
		}
	}
	
	@Override
	public void draw(Graphics g) {
		player.render(g);
		player.drawScore(g);
		enemyManager.draw(g);
		objectManager.draw(g, xLvlOffset);
		
		if (paused) {
			g.setColor(new Color(0, 0, 0, 150));
			g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
			pauseOverlay.draw(g);
		} else if (gameOver)
			gameOverOverlay.draw(g);
		else if (lvlCompleted)
			levelCompletedOverlay.draw(g);
	}
	
	private void checkCloseToBorder() {
		int playerX = (int) player.getHitbox().x;
		int diff = playerX - xLvlOffset;

		if (diff > rightBorder)
			xLvlOffset += diff - rightBorder;
		else if (diff < leftBorder)
			xLvlOffset += diff - leftBorder;

		if (xLvlOffset > maxLvlOffsetX)
			xLvlOffset = maxLvlOffsetX;
		else if (xLvlOffset < 0)
			xLvlOffset = 0;
	} 	

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
	public void mouseDragged(MouseEvent e) {
		if (!gameOver)
			if (paused)
				pauseOverlay.mouseDragged(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (!gameOver) {
			if (paused)
				pauseOverlay.mousePressed(e);
			else if (lvlCompleted)
				levelCompletedOverlay.mousePressed(e);
		} else
			gameOverOverlay.mousePressed(e);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (!gameOver) {
			if (paused)
				pauseOverlay.mouseReleased(e);
			else if (lvlCompleted)
				levelCompletedOverlay.mouseReleased(e);
		} else
			gameOverOverlay.mouseReleased(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (!gameOver) {
			if (paused)
				pauseOverlay.mouseMoved(e);
			else if (lvlCompleted)
				levelCompletedOverlay.mouseMoved(e);
		} else
			gameOverOverlay.mouseMoved(e);
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
		case KeyEvent.VK_SPACE:
			player.setJump(false);
			break;
		}
		
	}

	public void resetAll() {
		gameOver = false;
		paused = false;
		lvlCompleted = false;
		playerDying = false;
		player.resetAll();
		enemyManager.resetAllEnemies();
		objectManager.resetAllObjects();
		player.setSpawn(levelManager.getCurrentLevel().getPlayerSpawn());

	}
	
	public void checkEnemyHit(Rectangle2D.Float attackBox) {
		enemyManager.checkEnemyHit(attackBox);
	}
	public void checkObjectHit(Rectangle2D.Float attackBox) {
		objectManager.checkObjectHit(attackBox);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void checkSpikeTouched(Player p) {
		objectManager.checkSpikesTouched(p);
		
	}
	
	public EnemyManager getEnemyManager() {
		return enemyManager;
	}
	public void checkDiamonTouched(Rectangle2D.Float hitbox) {
		objectManager.checkObjectTouched(hitbox);
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public void unpauseGame() {
		paused = false;
	}
	
	public void setLevelCompleted(boolean b) {
		lvlCompleted = b;
		
	}
	
	public ObjectManager getObjectManager() {
		return objectManager;
	}
	
	public void setPlayerDying(boolean playerDying) {
		this.playerDying = playerDying;

	}
	
	public LevelManager getLevelManager() {
		return levelManager;
	}

	public int getCurrentScore() {
		return CurrentScore;
	}
	

	
}
