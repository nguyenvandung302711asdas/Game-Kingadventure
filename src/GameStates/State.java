package GameStates;

import java.awt.event.MouseEvent;

import Audio.AudioPlayer;
import Main.Game;
import Ui.LevelButton;
import Ui.MenuButton;

public class State {

	protected Game game;

	public State(Game game) {
		this.game = game;
	}
	
	public boolean isIn(MouseEvent e, MenuButton mb) {
		return mb.getBounds().contains(e.getX(), e.getY());
	}
	
	public boolean isInLB(MouseEvent e, LevelButton lb) {
		return lb.getBounds().contains(e.getX(), e.getY());
	}
	
	public void setGamestate(GameState state) {
		switch (state) {
			case MENU -> game.getAudioPlayer().playSong(AudioPlayer.MENU_1);
			case PLAYING -> game.getAudioPlayer().setLevelSong(game.getPlaying().getLevelManager().getLevelIndex());
		default -> throw new IllegalArgumentException("Unexpected value: " + state);
		}

		GameState.state = state;
	}
	
	public Game getGame() {
		return game;
	}
}