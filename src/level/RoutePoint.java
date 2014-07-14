package level;

/** Uchovává souøadnice všech, kde nepøítel musí zùstat uprostøed ètverce(zatáèka, základna). 
 *  Poslední bod je základna. */
public class RoutePoint {
	private int xPos;
	private int yPos;
	
	public RoutePoint(int xPos, int yPos) {
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
