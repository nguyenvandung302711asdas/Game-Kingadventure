package Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import GameStates.Playing;
import Main.Game;
import Utilz.LoadSave;

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
			g.drawImage(pigArr[p.getEnemyState()][p.getAniIndex()],(int)p.getHitbox().x, (int)p.getHitbox().y, (int)(38 * Game.SCALE), (int)(28 * Game.SCALE), null );
		}
	}

	private void loadEnemyImgs() {
		pigArr = new BufferedImage[8][11];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.PIG);
		for(int j=0;j<pigArr.length;j++) 
			for(int i=0;i<pigArr[j].length;i++) 
				pigArr[j][i] = temp.getSubimage(i*34,j*28, 34, 28);
	}
	
}
