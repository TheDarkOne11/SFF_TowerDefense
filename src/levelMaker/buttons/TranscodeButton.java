package levelMaker.buttons;

import java.awt.Color;
import java.awt.Graphics;

import levelMaker.MyButton;
import levelMaker.Transcode;

/** Pøevede vytvoøený level do souboru. */
public class TranscodeButton extends MyButton {
	private String text = "Transcode";

	public TranscodeButton(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public void drawButton(Graphics g) {
		Color lastColor = g.getColor();
		
		g.setColor(new Color(168, 168, 168));
		g.fillRect(this.x, this.y, this.width, this.height);
		g.setColor(Color.black);
		g.drawRect(this.x, this.y, this.width, this.height);
		g.drawString(text, (int) (this.x + this.width/2 - (g.getFontMetrics().stringWidth(text)/2)), (int) (this.y + this.height/2 + g.getFontMetrics().getHeight() / 4));
		
		g.setColor(lastColor);
	}

	public void action() {
		Transcode.transcodeLevel();
	}

	public void getTextureFile() {}

}
