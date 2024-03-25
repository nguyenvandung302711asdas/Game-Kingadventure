package Main;

import javax.swing.JFrame;


public class GameWinDow{
	private JFrame jframe;
	public GameWinDow(GamePanel gamePanel) {
		jframe = new JFrame();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLocationRelativeTo(null);
        jframe.add(gamePanel);
        jframe.pack();
        jframe.setResizable(false);
        jframe.setVisible(true);
	}

}
