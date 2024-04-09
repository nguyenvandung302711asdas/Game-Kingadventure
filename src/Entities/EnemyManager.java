package Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import GameStates.Playing;
import Main.Game;
import Utilz.LoadSave;

import static Utilz.Constants.EnemyConstants.*;

public class EnemyManager {

	private Playing playing;
	
	BufferedImage[][] pigArr;
	ArrayList<Piggy> pigs = new ArrayList<>();
	
	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();
		addEnemies();
	}
	
	private void addEnemies() {
		pigs = Piggy.GetPigs();
	}

	public void update(int[][] level, Player player) {
		for(Piggy p : pigs) {
			p.update(level, player);
		}
	}
	
	public void draw(Graphics g) {
		drawPigs(g);
	}
	
	private void drawPigs(Graphics g) { 
		for(Piggy p : pigs) {
			if (p.isActive()) {
				g.drawImage(pigArr[p.getEnemyState()][p.getAniIndex()],(int) p.getHitbox().x - PIGGY_DRAWOFFSET_X + p.flipX(), (int) p.getHitbox().y - PIGGY_DRAWOFFSET_Y, (int)(PIGGY_WIDTH * 1.2 * p.flipW()),(int)(PIGGY_HEIGHT * 1.2), null);
//				p.drawHitbox(g);
//				p.drawAttackBox(g);
			}
		}
	}

	private void loadEnemyImgs() {
		pigArr = new BufferedImage[8][11];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.PIG);
		for(int j=0;j<pigArr.length;j++) 
			for(int i=0;i<pigArr[j].length;i++) 
				pigArr[j][i] = temp.getSubimage(i*34,j*28, 34, 28);
	}
	
	public void checkEnemyHit(Rectangle2D.Float attackBox) {
		for (Piggy c : pigs)
			if (c.isActive())
				if (attackBox.intersects(c.getHitbox())) {
					c.hurt(1);
					return;
				}
	}
	
	public void resetAllEnemies() {
		for (Piggy c : pigs)
			c.resetEnemy();
	}
}