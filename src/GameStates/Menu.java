package GameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import Main.Game;
import Ui.MenuButton;
import Ui.PauseOverlay;
import Utilz.LoadSave;

public class Menu extends State implements StateMethods {

	private MenuButton[] buttons = new MenuButton[4];
	private BufferedImage backgroundImg;
	private int menuX, menuY, menuWidth, menuHeight;
	

	public Menu(Game game) {
		super(game);
		loadButtons();
		loadBackground();
		
	}


	private void loadButtons() {
		buttons[0] = new MenuButton(Game.GAME_WIDTH / 2, (int) (100 * Game.SCALE), 0, GameState.PLAYING);
		buttons[1] = new MenuButton(Game.GAME_WIDTH / 2, (int) (145 * Game.SCALE), 1, GameState.HIGHSCORE);
		buttons[2] = new MenuButton(Game.GAME_WIDTH / 2, (int) (190 * Game.SCALE), 2, GameState.OPTIONS);
		buttons[3] = new MenuButton(Game.GAME_WIDTH / 2, (int) (235 * Game.SCALE), 3, GameState.QUIT);

	}
	
	private void loadBackground() {
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.Menu_Background);
		menuWidth = (int) (backgroundImg.getWidth() / 1.8);
		menuHeight = (int) (backgroundImg.getHeight() / 1.2);
		menuX = Game.GAME_WIDTH / 2 - menuWidth / 2;
		menuY = (int) (18 * Game.SCALE);
	}

	@Override
	public void update() {
		for (MenuButton mb : buttons)
			mb.update();
	}

	@Override
	public void draw(Graphics g) {

		g.drawImage(backgroundImg, menuX, menuY, menuWidth, menuHeight, null);

		for (MenuButton mb : buttons)
			mb.draw(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (MenuButton mb : buttons) {
			if (isIn(e, mb)) {
				mb.setMousePressed(true);
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for (MenuButton mb : buttons) {
			if (isIn(e, mb)) {
				if (mb.isMousePressed())
					mb.applyGamestate();
				break;
			}
		}

		resetButtons();

	}

	private void resetButtons() {
		for (MenuButton mb : buttons)
			mb.resetBools();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for (MenuButton mb : buttons)
			mb.setMouseOver(false);

		for (MenuButton mb : buttons)
			if (isIn(e, mb)) {
				mb.setMouseOver(true);
				break;
			}

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

}