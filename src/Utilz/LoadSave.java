package Utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

import Main.Game;

public class LoadSave {
	
	public static final String PIG = "Pig.png";
	public static final String Player = "king.png";	
	public static final String Menu_Button = "button_atlas.png";
	public static final String Menu_Background = "menu_background.png";
	public static final String Pause_Background = "pause_menu.png";
	public static final String Sound_Button = "sound_button.png";
	public static final String URM_Button = "urm_buttons.png";
	public static final String Menu_Background_img = "background_menu.png";
	public static final String LEVEL_ONE_DATA = "Level_1.png";
	public static final String LEVEL_ATLAS = "Sprite.png";
	public static final String STATUS_BAR = "health_power_bar.png";
	public static final String COMPLETED = "level-completed.png";

	
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