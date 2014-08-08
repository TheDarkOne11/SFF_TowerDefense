package levelMaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;

import levelMaker.gridCountButtons.ButtonDown_GCX;
import levelMaker.gridCountButtons.ButtonDown_GCY;
import levelMaker.gridCountButtons.ButtonUp_GCX;
import levelMaker.gridCountButtons.ButtonUp_GCY;
import core.Screen;

public class LevelMaker {
	Screen screen;
	public int gridCountX = 25;
	public int gridCountY = 15;
	
	FileInputStream levelFile;
	FileInputStream levelFile_Var;
	
	public String gridCountXStr, gridCountYStr;
	public MyButton buttonUp_GCX, buttonDown_GCX, buttonUp_GCY, buttonDown_GCY;
	
	public LevelMaker(Screen screen) {
		this.screen = screen;
		this.gridCountXStr = "GridCountX: ";
		this.gridCountYStr = "GridCountY: ";
		
		this.init();
	}
	
	public void init() {
		buttonUp_GCX = new ButtonUp_GCX((int) ((Screen.gridCountX+2)*Screen.gridSize), (int) Screen.gridSize, 30, 30);
		buttonDown_GCX = new ButtonDown_GCX((int) ((Screen.gridCountX+2)*Screen.gridSize), (int) (Screen.gridSize*2), 30, 30);
		buttonUp_GCY = new ButtonUp_GCY((int) ((Screen.gridCountX+2)*Screen.gridSize), (int) (Screen.gridSize*4), 30, 30);
		buttonDown_GCY = new ButtonDown_GCY((int) ((Screen.gridCountX+2)*Screen.gridSize), (int) (Screen.gridSize*5), 30, 30);

	}
	
	public void transcodeLevel() {
		
	}

	public void isButtonClicked(MouseEvent e) {
		buttonUp_GCX.clickButton(e);
		buttonDown_GCX.clickButton(e);
		buttonUp_GCY.clickButton(e);
		buttonDown_GCY.clickButton(e);
	}
	
	//TODO Z obrázkù krajin udìlat instance MyButton.
	public void drawTerrainMenu(Graphics g) {
		int columnNum = 4;
		for(int i = 0; i < ((int) Math.ceil(screen.terrain.size()/(double) columnNum)); i++) {
			for(int f = 0; f < columnNum && columnNum*i+f < screen.terrain.size(); f++) {
				g.drawImage(screen.terrain.get(columnNum*i + f), (int) ((Screen.gridCountX+2+f)*Screen.gridSize), (int) (Screen.gridSize*(6+i)), (int) Screen.gridSize, (int) Screen.gridSize, null);
				g.drawRect((int) ((Screen.gridCountX+2+f)*Screen.gridSize), (int) (Screen.gridSize*(6+i)), (int) Screen.gridSize, (int) Screen.gridSize);
			}
		}
	}
	
	public void drawGridCountButtons(Graphics g) {
		
		// MyButton buttons
		buttonUp_GCX.drawButton(g);
		buttonDown_GCX.drawButton(g);
		buttonUp_GCY.drawButton(g);
		buttonDown_GCY.drawButton(g);
		
		// GridCountX a GridCountY strings
		g.setColor(Color.BLACK);
		g.drawString(gridCountXStr + Screen.gridCountX, buttonUp_GCX.x+50, buttonUp_GCX.y + buttonUp_GCX.height/2);
		g.drawString(gridCountYStr + Screen.gridCountY, buttonUp_GCY.x+50, buttonUp_GCY.y + buttonUp_GCY.height/2);
		
	}
	
	//TODO Možná udìlat každý ètverec z game gridu instancí MyButton exkluzivnì pro LevelMaker. Jednodušší oznaèování.
	
	//TODO Buï vložit terrain image do ruky nebo vytvoøit oznaèování ve gridu a kliknutím dát daný terrain image na pøíslušné místo
	public void clickTerrainImage(MouseEvent e) { 
		
	}

}
