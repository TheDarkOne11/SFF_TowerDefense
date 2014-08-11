package levelMaker.terrain;

import java.awt.event.MouseEvent;

import levelMaker.LevelMaker;
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
	
	//TODO Bude si ukl�dat, kam vlo�il spawnPoint a base.
	public void action() {
		// Vlo�en� krajiny do �tverce
		//System.out.println("x/y: " + x + "/ " + y);
		if(Screen.handTerrain != -1 && super.mouseEvent.getButton() == MouseEvent.BUTTON1) {
			this.id = Screen.handTerrain;
			Screen.handTerrain = -1;	//TODO Mo�n� zanechat v ruce a odstranit p�i kliknut� mimo grid.
			this.getTextureFile();
		} 
		// Ozna�en� �tverce
		//TODO Ozna�it v�ce �tverc� pomoc� SHIFT + RIGHT CLICK
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
