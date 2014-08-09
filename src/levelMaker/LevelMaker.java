package levelMaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.util.LinkedList;

import levelMaker.gridCountButtons.ButtonDown_GCX;
import levelMaker.gridCountButtons.ButtonDown_GCY;
import levelMaker.gridCountButtons.ButtonUp_GCX;
import levelMaker.gridCountButtons.ButtonUp_GCY;
import levelMaker.terrain.TerrainMenu;
import core.Screen;

public class LevelMaker {
	Screen screen;
	int terrainMenuColumnNum = 4;
	public int gridCountX = 25;
	public int gridCountY = 15;

	FileInputStream levelFile;
	FileInputStream levelFile_Var;

	public String gridCountXStr, gridCountYStr;
	public MyButton buttonUp_GCX, buttonDown_GCX, buttonUp_GCY, buttonDown_GCY;
	
	LinkedList<TerrainMenu> terrainTypes = new LinkedList<TerrainMenu>();

	public LevelMaker(Screen screen) {
		this.screen = screen;
		this.gridCountXStr = "GridCountX: ";
		this.gridCountYStr = "GridCountY: ";

		this.init();
	}

	public void init() {
		// GridCountButtons
		buttonUp_GCX = new ButtonUp_GCX((int) ((Screen.gridCountX + 2) * Screen.gridSize), (int) Screen.gridSize, 25, 25);
		buttonDown_GCX = new ButtonDown_GCX((int) ((Screen.gridCountX + 2) * Screen.gridSize), (int) (Screen.gridSize * 2), 25, 25);
		buttonUp_GCY = new ButtonUp_GCY((int) ((Screen.gridCountX + 2) * Screen.gridSize), (int) (Screen.gridSize * 4), 25, 25);
		buttonDown_GCY = new ButtonDown_GCY((int) ((Screen.gridCountX + 2) * Screen.gridSize), (int) (Screen.gridSize * 5), 25, 25);

		// Terrain menu
		for (int i = 0; i < ((int) Math.ceil(Screen.terrain.size() / (double) terrainMenuColumnNum)); i++) {
			for (int f = 0; f < terrainMenuColumnNum && terrainMenuColumnNum * i + f < Screen.terrain.size(); f++) {
				terrainTypes.addLast(new TerrainMenu((int) ((Screen.gridCountX + 2 + f) * Screen.gridSize), (int) (Screen.gridSize * (6 + i)), (int) Screen.gridSize, (int) Screen.gridSize, terrainMenuColumnNum * i + f));
			}
		}
	}

	public void transcodeLevel() {

	}

	public void isButtonClicked(MouseEvent e) {
		// GridCountButtons
		buttonUp_GCX.clickButton(e);
		buttonDown_GCX.clickButton(e);
		buttonUp_GCY.clickButton(e);
		buttonDown_GCY.clickButton(e);
		
		// Terrain menu
		for (int i = 0; i < ((int) Math.ceil(Screen.terrain.size() / (double) terrainMenuColumnNum)); i++) {
			for (int f = 0; f < terrainMenuColumnNum && terrainMenuColumnNum * i + f < Screen.terrain.size(); f++) {
				terrainTypes.get(terrainMenuColumnNum * i + f).clickButton(e);
			}
		}
	}
/*
	// TODO Z obrázkù krajin udìlat instance MyButton.
	public void drawTerrainMenu(Graphics g) {
		int columnNum = 4;
		for (int i = 0; i < ((int) Math.ceil(Screen.terrain.size() / (double) columnNum)); i++) {
			for (int f = 0; f < columnNum && columnNum * i + f < Screen.terrain.size(); f++) {
				g.drawImage(Screen.terrain.get(columnNum * i + f), (int) ((Screen.gridCountX + 2 + f) * Screen.gridSize), (int) (Screen.gridSize * (6 + i)), (int) Screen.gridSize, (int) Screen.gridSize, null);
				g.drawRect((int) ((Screen.gridCountX + 2 + f) * Screen.gridSize), (int) (Screen.gridSize * (6 + i)), (int) Screen.gridSize, (int) Screen.gridSize);
			}
		}
	}
	*/
	public void drawTerrainMenu(Graphics g) {
		for (int i = 0; i < ((int) Math.ceil(Screen.terrain.size() / (double) terrainMenuColumnNum)); i++) {
			for (int f = 0; f < terrainMenuColumnNum && terrainMenuColumnNum * i + f < Screen.terrain.size(); f++) {
				terrainTypes.get(terrainMenuColumnNum * i + f).x = (int) ((Screen.gridCountX + 2 + f) * Screen.gridSize);
				terrainTypes.get(terrainMenuColumnNum * i + f).y = (int) (Screen.gridSize * (6 + i));
				terrainTypes.get(terrainMenuColumnNum * i + f).width = (int) Screen.gridSize;
				terrainTypes.get(terrainMenuColumnNum * i + f).height = (int) Screen.gridSize;
				terrainTypes.get(terrainMenuColumnNum * i + f).drawButton(g);
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
		g.drawString(gridCountXStr + Screen.gridCountX, buttonUp_GCX.x + 50, buttonUp_GCX.y + buttonUp_GCX.height / 2);
		g.drawString(gridCountYStr + Screen.gridCountY, buttonUp_GCY.x + 50, buttonUp_GCY.y + buttonUp_GCY.height / 2);

	}

	// TODO Buï vložit terrain image do ruky nebo vytvoøit oznaèování ve gridu a
	// kliknutím dát daný terrain image na pøíslušné místo
	public void clickTerrainImage(MouseEvent e) {

	}

}
