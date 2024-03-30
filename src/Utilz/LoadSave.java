package Utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Main.Game;

public class LoadSave {

	public static final String Menu_Button = "button_atlas.png";	

	public static final String Menu_Background = "menu_background.png";
	
	public static final String Pause_Background = "pause_menu.png";
	
	public static final String Sound_Button = "sound_button.png";
	
	public static final String URM_Button = "urm_buttons.png";

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