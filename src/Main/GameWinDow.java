package Main;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.Image;

public class GameWinDow{
    private JFrame jframe;

    public GameWinDow(GamePanel gamePanel) {
        jframe = new JFrame();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLocation(200, 80);
        jframe.add(gamePanel);
        jframe.pack();
        jframe.setResizable(false);
        jframe.setVisible(true);

        // Set the icon image
        String iconPath = "C:/Users/ADMIN/Downloads/icon (1).png";
        ImageIcon icon = new ImageIcon(iconPath);
        jframe.setIconImage(icon.getImage());
    }
}
