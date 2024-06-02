package GameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import Main.Game;
import Ui.MenuButton;
import Utilz.LoadSave;

public class Menu extends State implements StateMethods {

	private MenuButton[] buttons = new MenuButton[6];
	private BufferedImage backgroundImg,backgroundImgPink;
	private int menuX, menuY, menuWidth, menuHeight;
	

	public Menu(Game game) {
		super(game);
		loadButtons();
		loadBackground();
		backgroundImgPink = LoadSave.GetSpriteAtlas(LoadSave.Menu_Background_img);
	}

	private void loadButtons() {
		buttons[0] = new MenuButton(Game.GAME_WIDTH / 2, (int) (150 * Game.SCALE), 0, GameState.PLAYING);
		buttons[1] = new MenuButton(Game.GAME_WIDTH / 2, (int) (215 * Game.SCALE), 1, GameState.HIGHSCORE);
		buttons[2] = new MenuButton(Game.GAME_WIDTH / 2, (int) (280 * Game.SCALE), 2, GameState.OPTIONS);
		buttons[3] = new MenuButton(Game.GAME_WIDTH / 2, (int) (345 * Game.SCALE), 4, GameState.LEVEL);
		buttons[4] = new MenuButton(Game.GAME_WIDTH / 2, (int) (410 * Game.SCALE), 5, GameState.INSTRUCTION);
		buttons[5] = new MenuButton(Game.GAME_WIDTH / 2, (int) (470 * Game.SCALE), 3, GameState.QUIT);
		

	}
	
	private void loadBackground() {
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.Menu_Background);
		menuWidth = 500;
		menuHeight =672;
		menuX = Game.GAME_WIDTH / 2 - menuWidth / 2;
		menuY = (int) (3 * Game.SCALE);
	}
	@Override
	public void update() {
		for (MenuButton mb : buttons)
			mb.update();
	}

	@Override
	public void draw(Graphics g) {
		
		g.drawImage(backgroundImgPink, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);  

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