package GameStates;

import static Utilz.Constants.UI.UrmButton.URM_SIZE;

import Ui.PauseButton;
import Ui.UrmButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import Main.Game;
import Ui.UrmButton;
import Utilz.LoadSave;

public class HighScores extends State implements StateMethods {
    private int[] m_HighScore = new int[4];
    private static final String SCORE_FILE = "high_scores.txt";
    private BufferedImage backgroundImgPink,backgroundImghightscore;
    private int X, Y, W, H;
	private UrmButton menuB;
    public HighScores(Game game) {
        super(game);
        loadImgs();
        loadButtons();
        loadHighScores();
    }
    private void loadButtons() {
		int menuX = (int) (445 * Game.SCALE);
		int menuY = (int) (390 * Game.SCALE);
		menuB = new UrmButton(menuX, menuY, URM_SIZE, URM_SIZE, 2);
	}
    private void loadImgs() {
        backgroundImgPink = LoadSave.GetSpriteAtlas(LoadSave.Menu_Background_img);
        backgroundImghightscore = LoadSave.GetSpriteAtlas(LoadSave.Menu_Background_img123);
        W = (int) (backgroundImghightscore.getWidth() * Game.SCALE + 100);
        H = (int) (backgroundImghightscore.getHeight() * Game.SCALE + 200);
        X = Game.GAME_WIDTH / 2 - W / 2;
        Y = Game.GAME_HEIGHT / 2 - H / 2 + 10;
    }

    private void loadHighScores() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SCORE_FILE))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null && index < m_HighScore.length) {
                m_HighScore[index] = Integer.parseInt(line.trim());
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveHighScores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCORE_FILE))) {
            for (int score : m_HighScore) {
                writer.write(score + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateHighScores(int newScore) {
    		for (int i = 0; i < m_HighScore.length; i++) {
                if (Playing.score > m_HighScore[i]) {
                    int temp = m_HighScore[i];
                    m_HighScore[i] = Playing.score;
                    Playing.score = temp;
                }
    		}
    		Arrays.sort(m_HighScore);
    		reverseArray1(m_HighScore);
            saveHighScores();
    	}
    	
    	private void reverseArray1(int[] arr) {
            int start = 0;
            int end = arr.length - 1;
            while (start < end) {
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
                start++;
                end--;
            }
        }
    private void reverseArray(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    @Override
    public void update() {
        int currentScore = Playing.score;
        updateHighScores(currentScore);
		menuB.update();

    }

    @Override
    public void draw(Graphics g) {
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.setColor(Color.BLACK);
        g.drawImage(backgroundImgPink, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        g.drawImage(backgroundImghightscore, X, Y, W, H, null);
        g.drawString("High Scores", 500, 250);
        Font largeFont = g.getFont().deriveFont(Font.BOLD, 30f);
        g.setFont(largeFont);
        int y = 300;
        for (int i = 0; i < m_HighScore.length; i++) {
            g.drawString((i + 1) + ": " + m_HighScore[i], 545, y);
            y += 50;
        }
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
