package level;

/**
 * Z�kladna, kam se sna�� dostat nep��tel�.
 * @author Petr
 *
 */
public class Base {
	private int xPos;
	private int yPos;
	int baseGround;
	/*int UP = EnemyRoute.UP;
	int DOWN = EnemyRoute.DOWN;;
	int LEFT = EnemyRoute.LEFT;
	int RIGHT = EnemyRoute.RIGHT;*/
	
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
