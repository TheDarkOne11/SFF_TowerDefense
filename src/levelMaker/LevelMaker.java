package levelMaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import core.Screen;

public class LevelMaker {
	Screen screen;
	public int gridCountX = 25;
	public int gridCountY = 15;
	/**
	 * Tlaèítka:
	 * 1/ Up = Pøidávájí 1 k hodnotì
	 * 2/ Down = Ubírají 1 z hodnoty
	 * 
	 * GCX = GridCountX
	 * GCY = GridCountY
	 */
	public MyButton buttonUp_GCX, buttonDown_GCX, buttonUp_GCY, buttonDown_GCY;
	public String gridCountXStr, gridCountYStr;
	
	public LevelMaker(Screen screen) {
		this.screen = screen;
		gridCountXStr = "GridCountX: ";
		gridCountYStr = "GridCountY: ";
		
		buttonUp_GCX = new MyButton((int) ((Screen.gridCountX+2)*Screen.gridSize), (int) Screen.gridSize, 30, 30, 1, Screen.gridCountX).getTextureFile("ButtonUp");
		buttonDown_GCX = new MyButton((int) ((Screen.gridCountX+2)*Screen.gridSize), (int) (Screen.gridSize*2), 30, 30, -1, Screen.gridCountY).getTextureFile("ButtonDown");
		
		buttonUp_GCY = new MyButton((int) ((Screen.gridCountX+2)*Screen.gridSize), (int) (Screen.gridSize*4), 30, 30, 1, Screen.gridCountX).getTextureFile("ButtonUp");
		buttonDown_GCY = new MyButton((int) ((Screen.gridCountX+2)*Screen.gridSize), (int) (Screen.gridSize*5), 30, 30, -1, Screen.gridCountY).getTextureFile("ButtonDown");
		
	}
	//TODO Vytvoøit více sloupcù, až vytvoøím pro každou krajinu objekt
	public void drawTerrainMenu(Graphics g) {
		for(int i = 0; i < screen.terrain.length; i++) {
			if(screen.terrain[i] != null) {
				g.drawImage(screen.terrain[i], (int) ((Screen.gridCountX+2)*Screen.gridSize), (int) (Screen.gridSize*(6+i)), (int) Screen.gridSize, (int) Screen.gridSize, null);
				g.drawRect((int) ((Screen.gridCountX+2)*Screen.gridSize), (int) (Screen.gridSize*(6+i)), (int) Screen.gridSize, (int) Screen.gridSize);
			}
		}
	}
	
	public void drawGridCountButtons(Graphics g) {
		// MyButton buttons
		g.drawImage(buttonUp_GCX.texture, buttonUp_GCX.x, buttonUp_GCX.y, null);
		g.drawImage(buttonDown_GCX.texture, buttonDown_GCX.x, buttonDown_GCX.y, null);
		
		g.drawImage(buttonUp_GCY.texture, buttonUp_GCY.x, buttonUp_GCY.y, null);
		g.drawImage(buttonDown_GCY.texture, buttonDown_GCY.x, buttonDown_GCY.y, null);
		
		// GridCountX a GridCountY strings
		g.setColor(Color.BLACK);
		g.drawString(gridCountXStr + Screen.gridCountX, buttonUp_GCX.x+50, buttonUp_GCX.y + buttonUp_GCX.height/2);
		g.drawString(gridCountYStr + Screen.gridCountY, buttonUp_GCY.x+50, buttonUp_GCY.y + buttonUp_GCY.height/2);
	}
	
	//TODO Buï vložit terrain image do ruky nebo vytvoøit oznaèování ve gridu a kliknutím dát daný terrain image na pøíslušné místo
	public void clickTerrainImage(MouseEvent e) { 
		
	}
	
	public void clickGridCountButton(MouseEvent e) {
		if(e.getXOnScreen() >= buttonDown_GCY.x && e.getXOnScreen() <= buttonUp_GCX.x+buttonUp_GCX.width && e.getYOnScreen() >= buttonUp_GCX.y && e.getYOnScreen() <= buttonUp_GCX.y+buttonUp_GCX.height && Screen.gridCountX < screen.map.length) {
			Screen.gridCountX++;
		}
		
		if(e.getXOnScreen() >= buttonDown_GCX.x && e.getXOnScreen() <= buttonDown_GCX.x+buttonDown_GCX.width && e.getYOnScreen() >= buttonDown_GCX.y && e.getYOnScreen() <= buttonDown_GCX.y+buttonDown_GCX.height && Screen.gridCountX > 0) {
			Screen.gridCountX--;
		}
		
		if(e.getXOnScreen() >= buttonUp_GCY.x && e.getXOnScreen() <= buttonUp_GCY.x+buttonUp_GCY.width && e.getYOnScreen() >= buttonUp_GCY.y && e.getYOnScreen() <= buttonUp_GCY.y+buttonUp_GCY.height && Screen.gridCountY < screen.map[0].length) {
			Screen.gridCountY++;
		}
		
		if(e.getXOnScreen() >= buttonUp_GCY.x && e.getXOnScreen() <= buttonDown_GCY.x+buttonDown_GCY.width && e.getYOnScreen() >= buttonDown_GCY.y && e.getYOnScreen() <= buttonDown_GCY.y+buttonDown_GCY.height && Screen.gridCountY > 0) {
			Screen.gridCountY--;
		}
	}

}
