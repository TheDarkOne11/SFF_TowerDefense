package level.terrain;

/**
 * Nadtøída, ze které budou další potomci krajin dìdit.
 * @author Petr
 *
 */
public abstract class TerrainSuperClass {
	public int id = -1;
	public boolean isWalkable = false;
	private int xPos, yPos;
	
	public TerrainSuperClass(int id) {
		this.id = id;
		this.isWalkable = false;
	}
	
	public TerrainSuperClass(int id, boolean isWalkable) {
		this.id = id;
		this.isWalkable = isWalkable;
	}
	
	public TerrainSuperClass(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public TerrainSuperClass(int id, boolean isWalkable, int xPos, int yPos) {
		this.id = id;
		this.isWalkable = isWalkable;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	/** Buï bude k texturám pøistupovat pomocí terrain array nebo k nim bude pøistupovat pøímo ze souboru tzn. bude 
	 * 	si sám vytváøet texturu (Image). */
	public abstract void getTextureFile();
}
