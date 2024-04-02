package Entities;

import static Utilz.Constants.Directions.*;
import static Utilz.Constants.PlayerConstants.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Utilz.LoadSave;

public class Enemies extends Entity{

	private BufferedImage[] animation;
	private int aniTick,aniIndex,aniSpeed = 25;
	private float Speed = 0.3f;
	double count = 0;

	public Enemies(float x, float y, int width ,int height) {
		super(x, y, width, height);
		loadAnimation();
		initHitbox(x + 18, y + 12, (int)36, (int)28);
	}
	
	public void update() {
		updatePos();
		updateAnimationTick();
//		setAnimation();
		
	}
	
	private void updatePos() {
		if(count < 150) {
			count += Speed;
			this.x -= Speed;
		}
		else if( count > 150) {
			count += Speed;
			this.x += Speed;
			if( count > 300)
				count = 0;
		}
		
	}

	public void render(Graphics g) {
		g.drawImage(animation[aniIndex], (int) x, (int) y, 64, 40, null);
//		drawHitbox(g);
	}
	private void updateAnimationTick() {
		aniTick++;
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
		        if(aniIndex >= 6) { 
			       aniIndex = 0;
		        }
		}
	}
	private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;
		
	}
	
	private void loadAnimation() {
		
			BufferedImage img = LoadSave.GetSpriteAtlas("03-Pig/Run (34x28).png");
			animation = new BufferedImage[6];
				for(int i=0;i<animation.length;i++) 
						animation[i] = img.getSubimage(i*34, 0, 34, 28);
			   
	}
	
}