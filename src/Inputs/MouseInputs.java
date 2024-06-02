package Inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import GameStates.GameState;
import Main.GamePanel;

public class MouseInputs implements MouseListener, MouseMotionListener {

	private GamePanel gamePanel;

	public MouseInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	//keo chuot
	public void mouseDragged(MouseEvent e) {
		switch (GameState.state) {
		case PLAYING:
			gamePanel.getGame().getPlaying().mouseDragged(e);
			break;
		case OPTIONS:
			gamePanel.getGame().getGameOptions().mouseDragged(e);
			break;
		default:
			break;

		}
	}


	@Override
	//di chuyen chuot
	public void mouseMoved(MouseEvent e) {
		switch (GameState.state) {
		case MENU:
			gamePanel.getGame().getMenu().mouseMoved(e);
			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().mouseMoved(e);
			break;
		case HIGHSCORE:
			gamePanel.getGame().getHighScores().mouseMoved(e);
			break;
		case OPTIONS:
			gamePanel.getGame().getGameOptions().mouseMoved(e);
			break;
		case LEVEL:
			gamePanel.getGame().getLevelGame().mouseMoved(e);
			break;
		case INSTRUCTION:
			gamePanel.getGame().getInstruction().mouseMoved(e);
			break;
		default:
			break;

		}
	}

	@Override
	//click chuột
	public void mouseClicked(MouseEvent e) {
		switch (GameState.state) {
		case PLAYING:
			gamePanel.getGame().getPlaying().mouseClicked(e);
			break;
		case HIGHSCORE:
			gamePanel.getGame().getHighScores().mouseClicked(e);
			break;

		case OPTIONS:
			gamePanel.getGame().getGameOptions().mouseClicked(e);
			break;
		case LEVEL:
			gamePanel.getGame().getLevelGame().mouseClicked(e);
			break;
		
		default:
			break;

		}
	}

	@Override
	// sự kiện nhấn chuột
	public void mousePressed(MouseEvent e) {
		switch (GameState.state) {
		case MENU:
			gamePanel.getGame().getMenu().mousePressed(e);
			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().mousePressed(e);
			break;
		case HIGHSCORE:
			gamePanel.getGame().getHighScores().mousePressed(e);
			break;
		case OPTIONS:
			gamePanel.getGame().getGameOptions().mousePressed(e);
			break;
		case LEVEL:
			gamePanel.getGame().getLevelGame().mousePressed(e);
			break;
		case INSTRUCTION:
			gamePanel.getGame().getLevelGame().mousePressed(e);
			break;
		default:
			break;

		}
	}

	@Override
	//sự kiện phát hành chuột: Phương thức này được gọi khi thả nút chuột
	public void mouseReleased(MouseEvent e) {
		switch (GameState.state) {
		case MENU:
			gamePanel.getGame().getMenu().mouseReleased(e);
			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().mouseReleased(e);
			break;
		case HIGHSCORE:
			gamePanel.getGame().getHighScores().mouseReleased(e);
			break;
		case OPTIONS:
			gamePanel.getGame().getGameOptions().mouseReleased(e);
			break;
		case LEVEL:
			gamePanel.getGame().getLevelGame().mouseReleased(e);
			break;
		case INSTRUCTION:
			gamePanel.getGame().getLevelGame().mouseReleased(e);
			break;
		default:
			break;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}
