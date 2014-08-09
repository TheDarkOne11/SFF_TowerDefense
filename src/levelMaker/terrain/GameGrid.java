package levelMaker.terrain;

import levelMaker.MyButton;
import core.Screen;

/**
 * Každý ètverec v gridu bude kliknutelný. Po kliknutí se buï oznaèí ètverec nebo vloží krajinu.
 */
public class GameGrid extends MyButton {
	public int id = 0;

	public GameGrid(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.getTextureFile();
	}

	public void action() {
		// Vložení krajiny do ètverce
		System.out.println("x/y: " + this.x + "/ " + this.y);
		if(Screen.handTerrain != -1) {
			this.id = Screen.handTerrain;
			this.getTextureFile();
		} 
		// Oznaèení ètverce
		else {
			
		}
	}

	public void getTextureFile() {
		this.texture = Screen.terrain.get(id);
	}

}
