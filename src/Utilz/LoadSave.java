package Utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Main.Game;

public class LoadSave {

	public static final String Player = "king.png";	
	
	public static final String Menu_Button = "button_atlas.png";	

	public static final String Menu_Background = "menu_background.png";
	
	public static final String Pause_Background = "pause_menu.png";
	
	public static final String Sound_Button = "sound_button.png";
	
	public static final String URM_Button = "urm_buttons.png";

	public static final String LEVEL_ATLAS = "14-TileSets/Terrain (32x32).png";
	public static final String LEVEL_DECOR = "14-TileSets/Decorations (32x32).png";
//	public static final String CLOSE = "13-Dialogue Boxes/Closiong(46x56).png";
	public static final String THUNG = "08-Box/Idle.png";
	public static final String MONSTER_THUNG = "04-Pig Throwing a Box/Idle (26x30).png";
	public static final String BOON_OFF = "09-Bomb/Bomb Off.png";
	public static final String IDLE_CANNON = "10-Cannon/Idle.png";
	public static final String KING_PING = "02-King Pig/Ground (38x28).png";
	public static final String PING_CANNON = "07-Pig With a Match/Match On (26x18).png";
	public static final String THUNG_PING = "03-Pig/Idle (34x28).png";
	public static final String PING_Static = "03-Pig/Idle (34x28).png";
	public static final String PING_BOOM = "05-Pig Thowing a Bomb/Idle (26x26).png";
	public static final String LIVE_BAR = "12-Live and Coins/Live Bar.png";
	
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

//	public static int[][] GetLevelData() {
//		int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
//		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
//
//		for (int j = 0; j < img.getHeight(); j++)
//			for (int i = 0; i < img.getWidth(); i++) {
//				Color color = new Color(img.getRGB(i, j));
//				int value = color.getRed();
//				if (value >= 48)
//					value = 0;
//				lvlData[j][i] = value;
//			}
//		return lvlData;
//
//	}
}