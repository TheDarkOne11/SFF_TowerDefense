package levelMaker.terrain;

import java.awt.event.MouseEvent;

import levelMaker.LevelMaker;
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
	
	//TODO Bude si ukládat, kam vložil spawnPoint a base.
	public void action() {
		// Vložení krajiny do ètverce
		//System.out.println("x/y: " + x + "/ " + y);
		if(Screen.handTerrain != -1 && super.mouseEvent.getButton() == MouseEvent.BUTTON1) {
			this.id = Screen.handTerrain;
			Screen.handTerrain = -1;	//TODO Možná zanechat v ruce a odstranit pøi kliknutí mimo grid.
			this.getTextureFile();
		} 
		// Oznaèení ètverce
		//TODO Oznaèit více ètvercù pomocí SHIFT + RIGHT CLICK
		else if(super.mouseEvent.getButton() == MouseEvent.BUTTON3) {
			if(LevelMaker.markedGameGrid.contains(this)) {
				LevelMaker.markedGameGrid.remove(this);
			} else {
				LevelMaker.markedGameGrid.add(this);
			}
		}
	}

	public void getTextureFile() {
		this.texture = Screen.terrain.get(this.id);
	}

}
