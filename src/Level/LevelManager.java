package Level;

import java.awt.Graphics;

import java.awt.image.BufferedImage;

import Main.Game;
import Utilz.LoadSave;

public class LevelManager {
	private Game game;
	private BufferedImage[] levelsprite;
	private BufferedImage[] leveldecor;
	private BufferedImage[] levelclosing;
	private BufferedImage[] thung;
	private BufferedImage[] monster_thung;
	private BufferedImage[] bom_off;
	private BufferedImage[] king_ping;
	private BufferedImage[] idle_cannon;
	private BufferedImage[] ping_cannon;
	private BufferedImage[] box_king;
	private BufferedImage[] ping_tatic;
	private BufferedImage[] live_bar;


	public LevelManager(Game game) {
		this.game = game;
		importOutsideSprites();

	}

	private void importOutsideSprites() {

		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
		BufferedImage img1 = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_DECOR);
//		BufferedImage img2 = LoadSave.GetSpriteAtlas(LoadSave.CLOSE);
		BufferedImage img3 = LoadSave.GetSpriteAtlas(LoadSave.THUNG);
		BufferedImage img4 = LoadSave.GetSpriteAtlas(LoadSave.MONSTER_THUNG);
		BufferedImage img5 = LoadSave.GetSpriteAtlas(LoadSave.BOON_OFF);
		BufferedImage img6 = LoadSave.GetSpriteAtlas(LoadSave.KING_PING);
		BufferedImage img7 = LoadSave.GetSpriteAtlas(LoadSave.IDLE_CANNON);
		BufferedImage img8 = LoadSave.GetSpriteAtlas(LoadSave.PING_CANNON);
		BufferedImage img9 = LoadSave.GetSpriteAtlas(LoadSave.THUNG_PING);
		BufferedImage img10 = LoadSave.GetSpriteAtlas(LoadSave.PING_Static);
		BufferedImage img11 = LoadSave.GetSpriteAtlas(LoadSave.PING_BOOM);
		BufferedImage img12 = LoadSave.GetSpriteAtlas(LoadSave.LIVE_BAR);


		levelsprite = new BufferedImage[150];
		leveldecor = new BufferedImage[20];
		levelclosing = new BufferedImage[3];
		monster_thung = new BufferedImage[9];
		thung = new BufferedImage[1];
		bom_off = new BufferedImage[1];
		king_ping = new BufferedImage[1];
		idle_cannon = new BufferedImage[1];
		ping_cannon = new BufferedImage[3];
		box_king = new BufferedImage[1];
		ping_tatic = new BufferedImage[11];
		live_bar= new BufferedImage[3];

		levelsprite[1] = img.getSubimage(2 * 32, 2 * 32, 32, 32);// background đen
		levelsprite[2] = img.getSubimage(2 * 32, 3 * 32, 32, 32);// tường trên
		levelsprite[3] = img.getSubimage(3 * 32, 2 * 32, 32, 32);// tường dọc trái
		levelsprite[4] = img.getSubimage(1 * 32, 1 * 32, 32, 32);// ô nối1
		levelsprite[6] = img.getSubimage(1 * 32, 3 * 32, 32, 32);// ô nối2
		levelsprite[5] = img.getSubimage(2 * 32, 1 * 32, 32, 32);// tường ngang dưới
		levelsprite[7] = img.getSubimage(5 * 32, 5 * 32, 32, 32);// tường dọc dưới
		levelsprite[8] = img.getSubimage(1 * 32, 2 * 32, 32, 32);// tường dọc phải
		levelsprite[9] = img.getSubimage(3 * 32, 1 * 32, 32, 32);// ô nối3
		levelsprite[10] = img.getSubimage(3 * 32, 3 * 32, 32, 32);// ô nối4
		
		// gạch
		levelsprite[16] = img.getSubimage(2 * 32, 8 * 32, 32, 32);// live B

		
		/*--------*/
		leveldecor[0] = img1.getSubimage(2 * 32, 3 * 32, 32, 32);
		leveldecor[1] = img1.getSubimage(3 * 32, 3 * 32, 32, 32);
		leveldecor[2] = img1.getSubimage(2 * 32, 4 * 32, 32, 32);
		leveldecor[3] = img1.getSubimage(3 * 32, 4 * 32, 32, 32);
		/*---thanh go---*/
		leveldecor[4] = img1.getSubimage(2 * 32, 2 * 32, 32, 32);
		leveldecor[5] = img1.getSubimage(3 * 32, 2 * 32, 32, 32);
		leveldecor[6] = img1.getSubimage(4 * 32, 2 * 32, 32, 32);
		leveldecor[14] = img1.getSubimage(5 * 32, 2 * 32, 32, 32);
		/*-------*/
		leveldecor[7] = img1.getSubimage(2 * 32, 1 * 32, 32, 32);
		leveldecor[8] = img1.getSubimage(3 * 32, 1 * 32, 32, 32);
		leveldecor[9] = img1.getSubimage(4 * 32, 1 * 32, 32, 32);
		leveldecor[10] = img1.getSubimage(5 * 32, 1 * 32, 32, 32);
		/*------*/
		/*--la co---*/
		leveldecor[11] = img1.getSubimage(1 * 32, 1 * 32, 32, 32);
		leveldecor[12] = img1.getSubimage(1 * 32, 2 * 32, 32, 32);
		leveldecor[13] = img1.getSubimage(1 * 32, 3 * 32, 32, 32);

		
		/*-------*/
//		levelclosing[0] = img2.getSubimage(2 * 46, 0, 46, 56);
		/*----MOnster----*/
		thung[0] = img3.getSubimage(0, 0, 22, 16);
		/*-----MONSTERidle----*/

		monster_thung[0] = img4.getSubimage(1 * 26, 0, 26, 30);
		monster_thung[1] = img4.getSubimage(8 * 26, 0, 26, 30);
		monster_thung[2] = img4.getSubimage(4 * 26, 0, 26, 30);
		ping_tatic[0] = img10.getSubimage(8 * 34, 0, 34, 28);
		ping_tatic[1] = img11.getSubimage(2 * 26, 0, 26, 26);

		/*------------*/

		/*----bomofff------*/
		bom_off[0] = img5.getSubimage(0, 0, 25, 23);
		/*--------*/
		/*------king_ping-------*/
		king_ping[0] = img6.getSubimage(0, 0, 31, 23);
		box_king[0] = img9.getSubimage(0, 0, 26, 20);
		/*-----------*/

		/*------idle_cannon--------*/
		idle_cannon[0] = img7.getSubimage(0, 0, 44, 28);
		ping_cannon[0] = img8.getSubimage(1 * 26, 0, 26, 18);

		/*------------------*/
		/*-----livebar-------*/
		live_bar[0]= img12.getSubimage(0, 0, 33, 33);
		live_bar[1]= img12.getSubimage(1*33, 0, 33, 33);
		

	}
	

	public void update() {
		
	}

	public void draw1(Graphics g) {
		/*-------khung map------*/
		for (int i = 0; i < 27; i++) {
			for (int j = 0; j < 15; j++) {
				g.drawImage(levelsprite[1], Game.TILES_SIZE * i, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE,null);
				// set màn hình đen
			}
		}
		
		for (int i = 6; i < 25; i++) {
			for (int j = 10; j < 13; j++) {
				g.drawImage(levelsprite[16], Game.TILES_SIZE * i, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE,null);
				// set màn hình đen
			}
		}
		for (int i = 1; i < 25; i++) {
			for (int j = 1; j < 10; j++) {
				g.drawImage(levelsprite[16], Game.TILES_SIZE * i, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE,null);
				// set màn hình đen
			}
		}
		// tường trên
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < 2; j++) {
				if (i > 0 && i < 25)g.drawImage(levelsprite[2], Game.TILES_SIZE * i, 0, Game.TILES_SIZE, Game.TILES_SIZE, null);
			}
			if (i == 0) {// ô nối 2
				g.drawImage(levelsprite[4], Game.TILES_SIZE * i, 0, Game.TILES_SIZE, Game.TILES_SIZE, null);
			}
		}
		// tường dọc trái
		for (int i = 0; i < 2; i++) {
			for (int j = 1; j < 10; j++) {
				g.drawImage(levelsprite[3], 0, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
			}
		}
		// tường dọc phải
			for (int j = 1; j < 13; j++) {
				g.drawImage(levelsprite[8], Game.TILES_SIZE * 25, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
		}

	for(int i = 1;i<6;i++){
		g.drawImage(levelsprite[5], Game.TILES_SIZE * i, Game.TILES_SIZE * 10, Game.TILES_SIZE, Game.TILES_SIZE, null);
	}
	g.drawImage(levelsprite[6],Game.TILES_SIZE*0,Game.TILES_SIZE*10,Game.TILES_SIZE,Game.TILES_SIZE,null);
	for(int j = 11;j<13;j++){
		g.drawImage(levelsprite[3], Game.TILES_SIZE * 5, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
	}
	
	// o noi tuong duoi
	g.drawImage(levelsprite[9],Game.TILES_SIZE*5,Game.TILES_SIZE*10,Game.TILES_SIZE,Game.TILES_SIZE,null);
	for(int i = 6;i<10;i++){
		g.drawImage(levelsprite[5], Game.TILES_SIZE * i, Game.TILES_SIZE * 13, Game.TILES_SIZE, Game.TILES_SIZE, null);
	}

	for(int i = 11;i<18;i++){
		g.drawImage(levelsprite[5], Game.TILES_SIZE * i, Game.TILES_SIZE * 12, Game.TILES_SIZE, Game.TILES_SIZE, null);
	}
	
	g.drawImage(levelsprite[4],Game.TILES_SIZE*10,Game.TILES_SIZE*12,Game.TILES_SIZE,Game.TILES_SIZE,null);
	g.drawImage(levelsprite[9],Game.TILES_SIZE*17,Game.TILES_SIZE*12,Game.TILES_SIZE,Game.TILES_SIZE,null);
	for(int i = 18;i<25;i++){
		g.drawImage(levelsprite[5], Game.TILES_SIZE * i, Game.TILES_SIZE * 13, Game.TILES_SIZE, Game.TILES_SIZE, null);
	}
	
	// tường nối 4
	g.drawImage(levelsprite[10],Game.TILES_SIZE*25,Game.TILES_SIZE*13,Game.TILES_SIZE,Game.TILES_SIZE,null);

	// tường nối 5
	g.drawImage(levelsprite[9],Game.TILES_SIZE*25,Game.TILES_SIZE*0,Game.TILES_SIZE,Game.TILES_SIZE,null);

	/*------bac them king--------*/
	g.drawImage(leveldecor[7],Game.TILES_SIZE*21,Game.TILES_SIZE*8,Game.TILES_SIZE,Game.TILES_SIZE,null);
	g.drawImage(leveldecor[8],Game.TILES_SIZE*22,Game.TILES_SIZE*8,Game.TILES_SIZE,Game.TILES_SIZE,null);
	g.drawImage(leveldecor[9],Game.TILES_SIZE*23,Game.TILES_SIZE*8,Game.TILES_SIZE,Game.TILES_SIZE,null);
	g.drawImage(leveldecor[10],Game.TILES_SIZE*24,Game.TILES_SIZE*8,Game.TILES_SIZE,Game.TILES_SIZE,null);
	g.drawImage(thung[0],Game.TILES_SIZE*24,Game.TILES_SIZE*7,Game.TILES_SIZE,Game.TILES_SIZE,null);

	// go giua
	g.drawImage(leveldecor[4],Game.TILES_SIZE*12,Game.TILES_SIZE*4,Game.TILES_SIZE,Game.TILES_SIZE,null);
	g.drawImage(leveldecor[5],Game.TILES_SIZE*13,Game.TILES_SIZE*4,Game.TILES_SIZE,Game.TILES_SIZE,null);
	g.drawImage(leveldecor[6],Game.TILES_SIZE*14,Game.TILES_SIZE*4,Game.TILES_SIZE,Game.TILES_SIZE,null);
	g.drawImage(leveldecor[14],Game.TILES_SIZE*15,Game.TILES_SIZE*4,Game.TILES_SIZE,Game.TILES_SIZE,null);


	/*----la co------*/
	g.drawImage(leveldecor[11],Game.TILES_SIZE*24,Game.TILES_SIZE*1,Game.TILES_SIZE,Game.TILES_SIZE,null);
	g.drawImage(leveldecor[12],Game.TILES_SIZE*24,Game.TILES_SIZE*2,Game.TILES_SIZE,Game.TILES_SIZE,null);
	g.drawImage(leveldecor[13],Game.TILES_SIZE*24,Game.TILES_SIZE*3,Game.TILES_SIZE,Game.TILES_SIZE,null);

	/*----------bac them-----------*/
	for(int i = 7;i<11;i++){
		g.drawImage(leveldecor[5], Game.TILES_SIZE * i, Game.TILES_SIZE * 7, Game.TILES_SIZE, Game.TILES_SIZE, null);
	}
	for(int i = 18;i<21;i++){
		g.drawImage(leveldecor[5], Game.TILES_SIZE * i, Game.TILES_SIZE * 5, Game.TILES_SIZE, Game.TILES_SIZE, null);
	}
	for(int i = 15;i<18;i++){
		g.drawImage(leveldecor[5], Game.TILES_SIZE * i, Game.TILES_SIZE * 9, Game.TILES_SIZE, Game.TILES_SIZE, null);
	}
	
	/*----cua so----------*/
	g.drawImage(leveldecor[0],Game.TILES_SIZE*3,Game.TILES_SIZE*2,Game.TILES_SIZE,Game.TILES_SIZE,null);
	g.drawImage(leveldecor[1],Game.TILES_SIZE*4,Game.TILES_SIZE*2,Game.TILES_SIZE,Game.TILES_SIZE,null);
	g.drawImage(leveldecor[2],Game.TILES_SIZE*3,Game.TILES_SIZE*3,Game.TILES_SIZE,Game.TILES_SIZE,null);
	g.drawImage(leveldecor[3],Game.TILES_SIZE*4,Game.TILES_SIZE*3,Game.TILES_SIZE,Game.TILES_SIZE,null);
	/*-----------cua man--------------------*/

	g.drawImage(levelclosing[0],Game.TILES_SIZE*1,Game.TILES_SIZE*8,128,128,null);

	}
}