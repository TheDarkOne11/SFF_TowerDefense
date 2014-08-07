package level;

/**
 * Poèáteèní místo nepøátel.
 * @author Petr
 *
 */
public class SpawnPoint {
	private int xPos;
	private int yPos;
	
	public SpawnPoint(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public int getX() {
		return xPos;
	}

	public int getY() {
		return yPos;
	}

}
