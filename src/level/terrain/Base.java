package level.terrain;

/**
 * Základna, kam se snaží dostat nepøátelé.
 * @author Petr
 *
 */
public class Base extends TerrainSuperClass {
	public int id = 3;
	public boolean isWalkable = true;
	private int xPos;
	private int yPos;
	
	public Base(int xPos, int yPos) {
		super(xPos, yPos);
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public Base(int id, boolean isWalkable, int xPos, int yPos) {
		super(id, isWalkable, xPos, yPos);
		this.id = id;
		this.isWalkable = isWalkable;
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
