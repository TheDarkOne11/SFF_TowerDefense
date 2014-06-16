package towers;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Tower {
	public String textureFile = "";
	public Image texture;
	
	public static final Tower[] towerList = new Tower[200];
	public int id;
	public int cost;
	public int range;
	
	public static final Tower lightningTower = new TowerLightning(0, 10, 2).getTextureFile("LightningTower");
	
	public Tower(int id, int cost, int range) {
		if(towerList[id] != null) {
			System.out.println("[TowerInitialization] Two towers with same id: " + id);
		} else {
			towerList[id] = this;
			this.id = id;
			this.cost = cost;
			this.range = range;
		}
	}
	
	public Tower getTextureFile(String textureFile) {
		this.textureFile = textureFile;
		this.texture = new ImageIcon("res/tower/" + textureFile + ".png").getImage();
		
		return null;
	}
}
