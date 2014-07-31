package level.terrain;

/**
 * Poèáteèní místo nepøátel.
 * @author Petr
 *
 */
public class SpawnPoint extends TerrainSuperClass {
	public int id = 2;
	public boolean isWalkable = true;
	private int xPos;
	private int yPos;
	
	public SpawnPoint(int xPos, int yPos) {
		super(xPos, yPos);
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public SpawnPoint(int id, boolean isWalkable, int xPos, int yPos) {
		super(id, isWalkable, xPos, yPos);
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public int getX() {
		return xPos;
	}

	public int getY() {
		return yPos;
	}

	public void getTextureFile() {
		
	}

}
