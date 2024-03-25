package Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Inputs.KeyboardIputs;
import Inputs.MosueIputs;

import static Utilz.Constants.PlayerConstants.*;
import static Utilz.Constants.Directions.*;

public class GamePanel extends JPanel{

	private MosueIputs mouseInputs;
	private float xDelta = 100, yDelta = 100;
	private BufferedImage img, subImg;

	public GamePanel() {

		mouseInputs = new MosueIputs(this);

		importImg();

		setPanelSize();
		addKeyListener(new KeyboardIputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);

	}

	private void importImg() {
		InputStream is = getClass().getResourceAsStream("/Run (78x58).png");

		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void setPanelSize() {
		Dimension size = new Dimension(1280, 800);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);

	}

	public void changeXDelta(int value) {
		this.xDelta += value;

	}

	public void changeYDelta(int value) {
		this.yDelta += value;

	}

	public void setRectPos(int x, int y) {
		this.xDelta = x;
		this.yDelta = y;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		subImg = img.getSubimage(0 * 78, 0 * 58, 78, 58);
		g.drawImage(subImg, (int) xDelta, (int) yDelta, 128, 80, null);

	}

	
}
