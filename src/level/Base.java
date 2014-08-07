package level;

/**
 * Základna, kam se snaží dostat nepøátelé.
 * @author Petr
 *
 */
public class Base {
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
	
}
