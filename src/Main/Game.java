package Main;

public class Game {
	private GameWinDow gameWindow;
	private GamePanel gamePanel;
	public Game() {
		gamePanel = new GamePanel();
		gameWindow = new GameWinDow(gamePanel);
		//yeu cau ban phim chuot bat su kien ngay lap tuc
		gamePanel.requestFocus();
	}
	

}
