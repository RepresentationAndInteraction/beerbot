import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import jason.environment.grid.GridWorldView;
import jason.environment.grid.Location;

/** class that implements the View of Domestic Robot application */
public class HouseView extends GridWorldView {

	HouseModel hmodel;
	BufferedImage beerbotImg,ownerImg,fridgeImg;

	public HouseView(HouseModel model) {
		super(model, "Domestic Robot", 700);

		try {
			beerbotImg=ImageIO.read(new File("gfx/beerbot.png"));
			ownerImg=ImageIO.read(new File("gfx/owner.png"));
			fridgeImg=ImageIO.read(new File("gfx/fridge.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		hmodel = model;
		defaultFont = new Font("Arial", Font.BOLD, 16); // change default font
		setVisible(true);
		repaint();
	}

	@Override
	public void drawEmpty(Graphics g, int x, int y) {
		g.setColor(new Color(240,240,240));
		g.fillRect(x*cellSizeW,y*cellSizeH,cellSizeW,cellSizeH);
		g.setColor(Color.lightGray);
		g.drawRect(x*cellSizeW,y*cellSizeH,cellSizeW,cellSizeH);
		repaint();
	}

	/** draw application objects */
	@Override
	public void draw(Graphics g, int x, int y, int object) {
		Location lRobot = hmodel.getAgPos(0);
		// super.drawAgent(g, x, y, Color.lightGray, -1);

		switch (object) {
			case HouseModel.FRIDGE:
				int aspectF=fridgeImg.getHeight()/cellSizeH;
				g.drawImage(fridgeImg, x*cellSizeW+cellSizeW/2-fridgeImg.getWidth()/aspectF/2, y*cellSizeH, fridgeImg.getWidth()/aspectF,cellSizeH, null);
				if (lRobot.equals(hmodel.lFridge)) {
					// super.drawAgent(g, x, y, Color.yellow, -1);
					int aspectBB=beerbotImg.getHeight()/cellSizeH;
					g.drawImage(beerbotImg, x*cellSizeW+cellSizeW/2-beerbotImg.getWidth()/aspectBB/2, y*cellSizeH, beerbotImg.getWidth()/aspectBB,cellSizeH, null);
				}
				g.setColor(Color.black);
				drawString(g, x, y, defaultFont, "("+hmodel.availableBeers+")");
				break;
			case HouseModel.OWNER:
				int aspectO=ownerImg.getHeight()/cellSizeH;
				g.drawImage(ownerImg, x*cellSizeW+cellSizeW/2-ownerImg.getWidth()/aspectO/2, y*cellSizeH, ownerImg.getWidth()/aspectO,cellSizeH, null);
				if (lRobot.equals(hmodel.lOwner)) {
					// super.drawAgent(g, x, y, Color.yellow, -1);
					int aspectBB=beerbotImg.getHeight()/cellSizeH;
					g.drawImage(beerbotImg, x*cellSizeW+cellSizeW/2-beerbotImg.getWidth()/aspectBB/2, y*cellSizeH, beerbotImg.getWidth()/aspectBB,cellSizeH, null);
				}
				// String o = "Owner";
				if (hmodel.sipCount > 0) {
					// o +=  "("+hmodel.sipCount+")";
				}
				g.setColor(Color.black);
				drawString(g, x, y, defaultFont, "("+hmodel.sipCount+")");
				break;
		}
		// repaint();
	}

	@Override
	public void drawAgent(Graphics g, int x, int y, Color c, int id) {
		Location lRobot = hmodel.getAgPos(0);
		if (!lRobot.equals(hmodel.lOwner) && !lRobot.equals(hmodel.lFridge)) {
			c = Color.yellow;
			if (hmodel.carryingBeer) c = Color.orange;
			// super.drawAgent(g, x, y, c, -1);
			int aspectBB=beerbotImg.getHeight()/cellSizeH;
			g.drawImage(beerbotImg, x*cellSizeW+cellSizeW/2-beerbotImg.getWidth()/aspectBB/2, y*cellSizeH, beerbotImg.getWidth()/aspectBB,cellSizeH, null);
			g.setColor(Color.black);
			// super.drawString(g, x, y, defaultFont, "Robot");
		}
	}
}
