package GameStates;

import static Utilz.Constants.UI.UrmButton.URM_SIZE;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import Main.Game;
import Ui.PauseButton;
import Ui.UrmButton;
import Utilz.LoadSave;

public class Instruction extends State implements StateMethods{
	private BufferedImage backgroundImgPink,backgroundImgPink1,backgroundImgPink2,backgroundImgPink3,backgroundImgPink4;
	private UrmButton menuB;
	public Instruction(Game game) {
		super(game);
		loadImgs();
	    loadButtons();
	}
	
	  private void loadButtons() {
			int menuX = (int) (445 * Game.SCALE);
			int menuY = (int) (410 * Game.SCALE);
			menuB = new UrmButton(menuX, menuY, URM_SIZE, URM_SIZE, 2);
		}
	
	  private void loadImgs() {
	        backgroundImgPink = LoadSave.GetSpriteAtlas(LoadSave.Menu_Background_img);
	        backgroundImgPink1 = LoadSave.GetSpriteAtlas(LoadSave.A);
	        backgroundImgPink2 = LoadSave.GetSpriteAtlas(LoadSave.D);
	        backgroundImgPink3 = LoadSave.GetSpriteAtlas(LoadSave.J);
	        backgroundImgPink4 = LoadSave.GetSpriteAtlas(LoadSave.SPACE);

	    }

	@Override
	public void update() {
		menuB.update();
		
	}

	@Override
	public void draw(Graphics g) {
		 g.drawImage(backgroundImgPink, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
		Font largeFont = g.getFont().deriveFont(Font.BOLD, 30f);
        g.setFont(largeFont);
        g.setColor(Color.black);
		g.drawString("A di chuyển sang trái",500, 120);
		g.drawString("D di chuyển sang phải", 500,240);
		g.drawString("Space nhảy lên cao",500,360);
		g.drawString("J tấn công ", 500, 480);
		g.drawImage(backgroundImgPink1, 240, 74, 120, 60, null);
		g.drawImage(backgroundImgPink2, 240, 194,120, 60, null);
		g.drawImage(backgroundImgPink4, 240, 314, 120, 60, null);
		g.drawImage(backgroundImgPink3, 240, 434, 120, 60, null);
		 menuB.draw(g);
		
	}
	
	 @Override
	    public void keyPressed(KeyEvent e) {
	        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
	            GameState.state = GameState.MENU;
	    }

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
			if (isIn(e, menuB)) {
				menuB.setMousePressed(true);
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (isIn(e, menuB)) {
				if (menuB.isMousePressed())
					GameState.state = GameState.MENU;}
				menuB.resetBools();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			menuB.setMouseOver(false);

			if (isIn(e, menuB))
				menuB.setMouseOver(true);
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		private boolean isIn(MouseEvent e, PauseButton b) {
			return b.getBounds().contains(e.getX(), e.getY());
		}
	    
	}
