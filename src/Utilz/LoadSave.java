package Utilz;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

public class LoadSave {
	
	public static final String PIG = "Pig.png";
	public static final String Player = "king.png";	
	public static final String Menu_Button = "anh.png";
	public static final String Menu_Background = "menu_background.png";
	public static final String Pause_Background = "pause_menu.png";
	public static final String Sound_Button = "sound_button.png";
	public static final String URM_Button = "urm_buttons.png";
	public static final String Menu_Background_img = "background.png";
	public static final String LEVEL_ONE_DATA = "Level_1.png";
	public static final String LEVEL_ATLAS = "Sprite1.png";
	public static final String STATUS_BAR = "Live Bar.png";
	public static final String HEART = "Small Heart Idle (18x14).png";
	public static final String COMPLETED = "level-completed.png";
	public static final String DIAMON = "Diamon.png";
	public static final String BOX_ATLAS = "Box.png";
	public static final String TRAP_ATLAS = "gai13.png";
	public static final String CANNON = "cannon_atlas.png";
	public static final String VOLUME_BUTTONS = "volume_buttons.png";
	public static final String OPTIONS_MENU = "options_background.png";
	public static final String CANNON_BALL = "ball.png";
	public static final String DEATH_SCREEN = "death_screen.png";
	public static final String DECOR = "decor.png";
	public static final String Level_Button = "Panel0.png";
	public static final String A = "A-Key.png";
	public static final String D = "D-Key.png";
	public static final String J = "J-Key.png";
	public static final String SPACE = "Space-Key.png";
	public static final String Menu_Background_img123 = "options_background123.png";
	public static BufferedImage GetSpriteAtlas(String fileName) {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
		try {
			img = ImageIO.read(is);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
	                is.close();
	            }
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	} 
	
	public static BufferedImage[] getAllLevels() {
		URL url = LoadSave.class.getResource("/lvls");
		File file = null;
	
		try {
			file = new File(url.toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File[] files = file.listFiles();
		File[] filesSorted = new File[files.length];
		
		for (int i = 0; i < filesSorted.length; i++)
			for (int j = 0; j < files.length; j++)
				if (files[j].getName().equals((i + 1) + ".png"))
					filesSorted[i] = files[j];
		BufferedImage[] imgs = new BufferedImage[filesSorted.length];
		for (int i = 0; i < imgs.length; i++)
			try {
				imgs[i] = ImageIO.read(filesSorted[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}

		return imgs;
	}
	
	
	
}