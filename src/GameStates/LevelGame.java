package GameStates;

import static Utilz.Constants.UI.UrmButton.URM_SIZE;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import Levels.LevelManager;
import Main.Game;
import Ui.AudioOptions;
import Ui.LevelButton;
import Ui.PauseButton;
import Ui.UrmButton;
import Utilz.LoadSave;

public class LevelGame extends State implements StateMethods {

	private LevelButton[] buttons = new LevelButton[6];
	private BufferedImage backgroundImgPink;
	private LevelManager levelManager;
	private Playing playing;
	private UrmButton menuB;
	public LevelGame(Game game, Playing playing, LevelManager levelManager) {
		super(game);
		this.playing = playing;
		this.levelManager = levelManager;
		loadButtons();
		backgroundImgPink = LoadSave.GetSpriteAtlas(LoadSave.Menu_Background_img);
	}

	private void loadButtons() {
		buttons[0] = new LevelButton(Game.GAME_WIDTH / 6 + Game.GAME_WIDTH / 13, (int) (135 * Game.SCALE), 1, 0);	
		buttons[1] = new LevelButton(Game.GAME_WIDTH / 6 + Game.GAME_WIDTH / 13 * 2, (int) (135 * Game.SCALE), 1, 1);		
		buttons[2] = new LevelButton(Game.GAME_WIDTH / 6 + Game.GAME_WIDTH / 13 * 3, (int) (135 * Game.SCALE), 1, 2);		
		buttons[3] = new LevelButton(Game.GAME_WIDTH / 6 + Game.GAME_WIDTH / 13 * 4, (int) (135 * Game.SCALE), 1, 3);
		buttons[4] = new LevelButton(Game.GAME_WIDTH / 6 + Game.GAME_WIDTH / 13 * 5, (int) (135 * Game.SCALE), 1, 4);
		buttons[5] = new LevelButton(Game.GAME_WIDTH / 6 + Game.GAME_WIDTH / 13 * 6, (int) (135 * Game.SCALE), 1, 5);
		
		int menuX = (int) (445 * Game.SCALE);
		int menuY = (int) (390 * Game.SCALE);
		menuB = new UrmButton(menuX, menuY, URM_SIZE, URM_SIZE, 2);
	}

	@Override
	public void update() {
		menuB.update();
		for (LevelButton lb : buttons)
			lb.update();		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(backgroundImgPink, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);  
		Font largeFont = g.getFont().deriveFont(Font.BOLD, 30f);
	        g.setFont(largeFont);
		for (LevelButton lb : buttons) {
			lb.draw(g);
	        g.drawString(Integer.toString(lb.getLevel() + 1), Game.GAME_WIDTH / 6 + (lb.getLevel() + 1) * Game.GAME_WIDTH / 13 + 18, (int) (135 * Game.SCALE) + 36);
		}
		menuB.draw(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (LevelButton lb : buttons) {
			if (isInLB(e, lb)) {
				
				lb.setMousePressed(true);
			}
		}
		if (isIn(e, menuB)) {
			menuB.setMousePressed(true);
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for (LevelButton lb : buttons) {
			if (isInLB(e, lb)) {
				if (lb.isMousePressed()) {
					lb.applyGamestate();
					if(levelManager.getLevelIndex() < lb.getLevel()) {
						while(levelManager.getLevelIndex() != lb.getLevel()) 
							playing.loadNextLevel();
					}
					else
						while(levelManager.getLevelIndex() != lb.getLevel()) 
							playing.loadPreviousLevel();
				}
				break;
			}
		}
		if (isIn(e, menuB)) {
			if (menuB.isMousePressed())
				GameState.state = GameState.MENU;
		}
		menuB.resetBools();
		resetButtons();
	}

	private void resetButtons() {
		for (LevelButton mb : buttons)
			mb.resetBools();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for (LevelButton mb : buttons)
			mb.setMouseOver(false);

		for (LevelButton lb : buttons)
			if (isInLB(e, lb)) {
				lb.setMouseOver(true);
				break;
			}
		menuB.setMouseOver(false);

		if (isIn(e, menuB))
			menuB.setMouseOver(true);
		
	

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			GameState.state = GameState.PLAYING;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}

}