package Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import GameStates.GameState;
import Main.GamePanel;


public class KeyboardInputs implements KeyListener {

	private GamePanel gamePanel;

	public KeyboardInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (GameState.state) {
		case MENU:
			gamePanel.getGame().getMenu().keyReleased(e);
			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().keyReleased(e);
			break;
		case LEVEL:
			gamePanel.getGame().getLevelGame().keyReleased(e);
			break;
		case HIGHSCORE:
			gamePanel.getGame().getHighScores().keyReleased(e);
			break;
		case INSTRUCTION:
			gamePanel.getGame().getHighScores().keyReleased(e);
			break;
		default:
			break;

		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (GameState.state) {
		case MENU:
			gamePanel.getGame().getMenu().keyPressed(e);		
			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().keyPressed(e);
			break;
		case LEVEL:
			gamePanel.getGame().getLevelGame().keyPressed(e);
			break;
		case HIGHSCORE:
			gamePanel.getGame().getHighScores().keyPressed(e);
			break;
		case INSTRUCTION:
			gamePanel.getGame().getHighScores().keyPressed(e);
			break;
		default:
			break;
		}
	}
}