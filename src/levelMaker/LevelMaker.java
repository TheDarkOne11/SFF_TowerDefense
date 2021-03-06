package levelMaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import levelMaker.buttons.ButtonDown_GCX;
import levelMaker.buttons.ButtonDown_GCY;
import levelMaker.buttons.ButtonUp_GCX;
import levelMaker.buttons.ButtonUp_GCY;
import levelMaker.buttons.TranscodeButton;
import levelMaker.terrain.GameGrid;
import levelMaker.terrain.TerrainMenu;
import core.Screen;

public class LevelMaker {
	Screen screen;
	int terrainMenuColumnNum = 4;
	public static int gridCountX = 25;
	public static int gridCountY = 15;
	public static int lastGridCountX = gridCountX;
	public static int lastGridCountY = gridCountY;

	public String gridCountXStr, gridCountYStr;
	public MyButton buttonUp_GCX, buttonDown_GCX, buttonUp_GCY, buttonDown_GCY;
	
	public MyButton transcodeButton;
	
	public static LinkedList<TerrainMenu> terrainTypes = new LinkedList<TerrainMenu>();
	public static LinkedList<GameGrid> gameGrid = new LinkedList<GameGrid>();
	public static LinkedList<GameGrid> markedGameGrid = new LinkedList<GameGrid>();

	public LevelMaker(Screen screen) {
		this.screen = screen;
		this.gridCountXStr = "LevelMaker.gridCountX: ";
		this.gridCountYStr = "LevelMaker.gridCountY: ";

		this.init();
	}

	public void init() {
		// GridCountButtons
		buttonUp_GCX = new ButtonUp_GCX((int) ((LevelMaker.gridCountX + 2) * Screen.gridSize), (int) Screen.gridSize, 25, 25);
		buttonDown_GCX = new ButtonDown_GCX((int) ((LevelMaker.gridCountX + 2) * Screen.gridSize), (int) (Screen.gridSize * 2), 25, 25);
		buttonUp_GCY = new ButtonUp_GCY((int) ((LevelMaker.gridCountX + 2) * Screen.gridSize), (int) (Screen.gridSize * 4), 25, 25);
		buttonDown_GCY = new ButtonDown_GCY((int) ((LevelMaker.gridCountX + 2) * Screen.gridSize), (int) (Screen.gridSize * 5), 25, 25);

		
		// Transcode button
		transcodeButton = new TranscodeButton((int) ((LevelMaker.gridCountX + 2) * Screen.gridSize), (int) Screen.gridSize*6, 60, 50);
		
		// Terrain menu
		for (int i = 0; i < ((int) Math.ceil(Screen.terrain.size() / (double) terrainMenuColumnNum)); i++) {
			for (int f = 0; f < terrainMenuColumnNum && terrainMenuColumnNum * i + f < Screen.terrain.size(); f++) {
				terrainTypes.addLast(new TerrainMenu((int) ((LevelMaker.gridCountX + 2 + f) * Screen.gridSize), (int) (Screen.gridSize * (8 + i)), (int) Screen.gridSize, (int) Screen.gridSize, terrainMenuColumnNum * i + f));
			}
		}
		
		// Game grid
		gameGrid.clear();
		for (int x = 0; x < LevelMaker.gridCountX; x++) {
			for (int y = 0; y < LevelMaker.gridCountY; y++) {
				gameGrid.addLast(new GameGrid((int) (Screen.gridSize + (x * Screen.gridSize)), (int) (Screen.gridSize + (y * Screen.gridSize)), (int) Screen.gridSize, (int) Screen.gridSize));
			}
		}
	}
	
	/**
	 * Po kliknut� projde v�echny tla��tka v LevelMakeru, aby zjistilo, kter� bylo kliknuto.
	 */
	public void isButtonClicked(MouseEvent e) {
		// Terrain menu
		for (int i = 0; i < ((int) Math.ceil(Screen.terrain.size() / (double) terrainMenuColumnNum)); i++) {
			for (int f = 0; f < terrainMenuColumnNum && terrainMenuColumnNum * i + f < Screen.terrain.size(); f++) {
				terrainTypes.get(terrainMenuColumnNum * i + f).clickButton(e);
			}
		}
		
		// Game grid
		for (int x = 0; x < LevelMaker.gridCountX; x++) {
			for (int y = 0; y < LevelMaker.gridCountY; y++) {
				gameGrid.get(x + y * gridCountX).clickButton(e);
			}
		}
		
		// GridCountButtons
		buttonUp_GCX.clickButton(e);
		buttonDown_GCX.clickButton(e);
		buttonUp_GCY.clickButton(e);
		buttonDown_GCY.clickButton(e);
		
		// Transcode button
		transcodeButton.clickButton(e);
	}
	
	public void drawTerrainMenu(Graphics g) {
		for (int i = 0; i < ((int) Math.ceil(Screen.terrain.size() / (double) terrainMenuColumnNum)); i++) {
			for (int f = 0; f < terrainMenuColumnNum && terrainMenuColumnNum * i + f < Screen.terrain.size(); f++) {
				terrainTypes.get(terrainMenuColumnNum * i + f).x = (int) ((LevelMaker.gridCountX + 2 + f) * Screen.gridSize);
				terrainTypes.get(terrainMenuColumnNum * i + f).y = (int) (Screen.gridSize * (8 + i));
				terrainTypes.get(terrainMenuColumnNum * i + f).width = (int) Screen.gridSize;
				terrainTypes.get(terrainMenuColumnNum * i + f).height = (int) Screen.gridSize;
				terrainTypes.get(terrainMenuColumnNum * i + f).drawButton(g);
			}
		}
	}

	public void drawButtons(Graphics g) {
		// MyButton buttons
		buttonUp_GCX.drawButton(g);
		buttonDown_GCX.drawButton(g);
		buttonUp_GCY.drawButton(g);
		buttonDown_GCY.drawButton(g);

		// LevelMaker.gridCountX a LevelMaker.gridCountY strings
		g.setColor(Color.BLACK);
		g.drawString(gridCountXStr + LevelMaker.gridCountX, buttonUp_GCX.x + 50, buttonUp_GCX.y + buttonUp_GCX.height / 2);
		g.drawString(gridCountYStr + LevelMaker.gridCountY, buttonUp_GCY.x + 50, buttonUp_GCY.y + buttonUp_GCY.height / 2);

		// Transcode button
		transcodeButton.drawButton(g);
	}
	
	public void drawGameGrid(Graphics g) {
		g.setColor(Color.black);
		for (int y = 0; y < LevelMaker.gridCountY; y++) {
			for (int x = 0; x < LevelMaker.gridCountX; x++) {
				
				if(LevelMaker.gridCountX != LevelMaker.lastGridCountX) {
					if((x+1) % LevelMaker.gridCountX == 0 && x != 0) {
						if(LevelMaker.gridCountX > LevelMaker.lastGridCountX) {
							gameGrid.add(x + y * gridCountX, new GameGrid((int) (Screen.gridSize + (x * Screen.gridSize)), (int) (Screen.gridSize + (y * Screen.gridSize)), (int) Screen.gridSize, (int) Screen.gridSize));
						} else {
							gameGrid.remove((x+1) + y * gridCountX);
						}
					}
				} else if(LevelMaker.gridCountY != LevelMaker.lastGridCountY) {
					if((y+1) == LevelMaker.gridCountY) {
						if(LevelMaker.gridCountY > LevelMaker.lastGridCountY) {
							gameGrid.addLast(new GameGrid((int) (Screen.gridSize + (x * Screen.gridSize)), (int) (Screen.gridSize + (y * Screen.gridSize)), (int) Screen.gridSize, (int) Screen.gridSize));
						} else {
							gameGrid.removeLast();
						}
					}
				}
				
				gameGrid.get(x + y * gridCountX).x = (int) (Screen.gridSize + (x * Screen.gridSize));
				gameGrid.get(x + y * gridCountX).y = (int) (Screen.gridSize + (y * Screen.gridSize));
				gameGrid.get(x + y * gridCountX).width = (int) Screen.gridSize;
				gameGrid.get(x + y * gridCountX).height = (int) Screen.gridSize;
				gameGrid.get(x + y * gridCountX).drawButton(g);
				
				g.drawRect((int) (Screen.gridSize + (x * Screen.gridSize)), (int) (Screen.gridSize + (y * Screen.gridSize)), (int) Screen.gridSize, (int) Screen.gridSize);
			}
		}
		LevelMaker.lastGridCountX = LevelMaker.gridCountX;
		LevelMaker.lastGridCountY = LevelMaker.gridCountY;
		//TODO Prozat�mn�, Screen bude gridCount hodnoty z�sk�vat ze souboru.
		Screen.gridCountX = LevelMaker.gridCountX;
		Screen.gridCountY = LevelMaker.gridCountY;
	}
	
	public void drawMarkedGameGrid(Graphics g) {
		Color lastColor = g.getColor();
		g.setColor(new Color(64, 64, 64, 145));
		for(int i = 0; i < LevelMaker.markedGameGrid.size(); i++) {
			g.fillRect(LevelMaker.markedGameGrid.get(i).x, LevelMaker.markedGameGrid.get(i).y, LevelMaker.markedGameGrid.get(i).width, LevelMaker.markedGameGrid.get(i).height);
		}
		g.setColor(lastColor);
	}
}
