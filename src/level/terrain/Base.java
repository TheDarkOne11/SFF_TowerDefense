package level.terrain;

/**
 * Základna, kam se snaží dostat nepøátelé.
 * @author Petr
 *
 */
public class Base implements TerrainInterface {
	public int id = 3;
	public boolean isWalkable = true;
	private int xPos;
	private int yPos;
	
	public Base(int xPos, int yPos) {
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
