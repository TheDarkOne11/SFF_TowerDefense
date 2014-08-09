package levelMaker.terrain;

import levelMaker.MyButton;
import core.Screen;

/**
 * Ka�d� �tverec v gridu bude kliknuteln�. Po kliknut� se bu� ozna�� �tverec nebo vlo�� krajinu.
 */
public class GameGrid extends MyButton {
	public int id = 0;

	public GameGrid(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.getTextureFile();
	}

	public void action() {
		// Vlo�en� krajiny do �tverce
		System.out.println("x/y: " + this.x + "/ " + this.y);
		if(Screen.handTerrain != -1) {
			this.id = Screen.handTerrain;
			this.getTextureFile();
		} 
		// Ozna�en� �tverce
		else {
			
		}
	}

	public void getTextureFile() {
		this.texture = Screen.terrain.get(id);
	}

}
