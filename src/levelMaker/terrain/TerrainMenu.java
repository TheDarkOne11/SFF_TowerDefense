package levelMaker.terrain;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import levelMaker.MyButton;
import core.Screen;

/** Abstraktní metoda pro krajiny. */
public class TerrainMenu extends MyButton {
	public int id;

	public TerrainMenu(int x, int y, int width, int height, int id) {
		super(x, y, width, height);
		this.id = id;
		this.getTextureFile();
	}

	public void getTextureFile() {
		this.texture = Screen.terrain.get(id);
	}
	
	public void drawButton(Graphics g) {
		g.drawImage(this.texture, this.x, this.y, this.width, this.height, null);
		g.drawRect(this.x, this.y, this.width, this.height);
	}

	/** Vloží krajinu do ruky levým tlaèítkem.
	 *  Vloží krajinu na oznaèená místa v game gridu pravým tlaèítkem.*/
	public void action() {
		// Levé tlaèítko
		if(super.mouseEvent.getButton() == MouseEvent.BUTTON1) {
			Screen.handTerrain = id;
		} 
		// Pravé tlaèítko
		else if(super.mouseEvent.getButton() == MouseEvent.BUTTON3) {
			Screen.handTerrain = -1;
		}
	}

}