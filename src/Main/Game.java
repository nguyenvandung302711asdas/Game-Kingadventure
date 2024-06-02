package Main;

import java.awt.Graphics;
import Audio.AudioPlayer;
import GameStates.GameOptions;
import GameStates.GameState;
import GameStates.HighScores;
import GameStates.Instruction;
import GameStates.LevelGame;
import GameStates.Menu;
import GameStates.Playing;
import Levels.LevelManager;
import Ui.AudioOptions;

public class Game implements Runnable {
	private GameWinDow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120;
	private final int UPS_SET = 200;

	private Playing playing;
	private Menu menu;
	private GameOptions gameOptions;
	private LevelGame levelGame;
	private HighScores gameHighScore;
	private LevelManager levelManager;
	private AudioOptions audioOptions;
	private AudioPlayer audioPlayer;
	private Instruction instruction;

	public final static int TILES_DEFAULT_SIZE = 32;
	public final static float SCALE = 1.2f;
	public final static int TILES_IN_WIDTH = 30;
	public final static int TILES_IN_HEIGHT = 18;
	public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

	public Game() {
		initClasses();
		gamePanel = new GamePanel(this);
		gameWindow = new GameWinDow(gamePanel);
		gamePanel.setFocusable(true);
		gamePanel.requestFocus();
		startGameLoop();
	}

	private void initClasses() {
		audioOptions = new AudioOptions(this);	
		audioPlayer = new AudioPlayer();
		gameOptions = new GameOptions(this);
		menu = new Menu(this);
		playing = new Playing(this);
		levelManager = new LevelManager(this);
		levelGame = new LevelGame(this, playing, levelManager); 
		gameHighScore = new HighScores(this);
		instruction = new Instruction(this);
	}
	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void update() {
		switch (GameState.state) {
		case MENU:
			menu.update();
			break;
		case PLAYING:
			playing.update();
			break;
		case HIGHSCORE:
			gameHighScore.update();
			break;
		case LEVEL:
			levelGame.update();
			break;
		case OPTIONS:
			gameOptions.update();
			break;
		case INSTRUCTION:
			instruction.update();
			break;
		case QUIT:
			System.exit(0);
		}
	}

	public void render(Graphics g) {
		levelManager.draw(g);
		switch (GameState.state) {
		case MENU:
			menu.draw(g);
			break;
		case PLAYING:
			playing.draw(g);
			break;
		case HIGHSCORE:
			gameHighScore.draw(g );
			break;
		case OPTIONS:
			gameOptions.draw(g);
			break;
		case LEVEL:
			levelGame.draw(g);
			break;
		case INSTRUCTION:
			instruction.draw(g);
			break;
		default:
			break;
		}
	}

	@Override
	public void run() {

		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;

		long previousTime = System.nanoTime();

		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();

		double deltaU = 0;
		double deltaF = 0;

		while (true) {
			long currentTime = System.nanoTime();

			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;

			if (deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}

			if (deltaF >= 1) {
				gamePanel.repaint();
				frames++;
				deltaF--;
			}

			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				frames = 0;
				updates = 0;
			}
		}

	}

	public void windowFocusLost() {
		if (GameState.state == GameState.PLAYING);
			playing.getPlayer().resetDirBooleans();
			
	}

	public Menu getMenu() {
		return menu;
	}

	public Playing getPlaying() {
		return playing;
	}
	
	public AudioOptions getAudioOptions() {
		return audioOptions;
	}
	public AudioPlayer getAudioPlayer() {
		return audioPlayer;
	}

	public GameOptions getGameOptions() {
		return gameOptions;
	}
	public LevelGame getLevelGame() {
		return levelGame;
	}
	
	public HighScores getHighScores() {
		return gameHighScore;
	}
	public Instruction getInstruction() {
		return instruction;
	}
}