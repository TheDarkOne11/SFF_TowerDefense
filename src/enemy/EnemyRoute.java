package enemy;

import java.util.LinkedList;

import level.Base;
import level.Level;
import level.RoutePoint;
import core.Screen;

/**
 * Získává souøadnice další pozice cesty.
 */
public class EnemyRoute {
	Level level;
	int[][] route = new int[Screen.gridCountX][Screen.gridCountY];
	LinkedList<RoutePoint> routePointList = new LinkedList<RoutePoint>();
	int baseID = 3;
	Base base;

	int xPos;
	int yPos;
	int lastPos = -1;
	public int UP = 1;
	public int DOWN = 2;
	public int RIGHT = 3;
	public int LEFT = 4;

	public EnemyRoute(Level level) {
		this.level = level;
		this.xPos = this.level.spawnPoint.getX();
		this.yPos = this.level.spawnPoint.getY();
		calculateRoute();
	}

	private void calculateRoute() {
		while (base == null) {
			calculateNextPos();
		}

		routePointList.addLast(new RoutePoint(base.getX(), base.getY()));
	}

	/**
	 * Zjistí, zda další pozice cesty je nahoru, dolu, doleva, doprava od
	 * nynìjší pozice
	 */
	private void calculateNextPos() {
		for (int i = 1; i <= 4; i++) {
			if (i != this.lastPos) { // Podmínka: jinak by se pohyboval mezi
										// nynìjší a pøedchozí pozicí
				if (i == UP && yPos > 0) { // yPos nesmí jít mimo grid
					if (level.map[this.xPos][(this.yPos - 1)] == 1) {
						this.route[this.xPos][this.yPos] = UP;
						if (lastPos != this.route[this.xPos][this.yPos] + 1 && lastPos != -1) {
							routePointList.add(new RoutePoint(this.xPos, this.yPos));
						}

						this.lastPos = DOWN; // Když jsem šel nahoru, posl.
												// pozice musí být dole
						this.yPos--;
						break;
					} else if (level.map[this.xPos][this.yPos - 1] == this.baseID) {
						base = new Base(this.xPos, this.yPos);
						break;
					}
				}

				if (i == DOWN && yPos < Screen.gridCountY - 1) {
					if (level.map[this.xPos][(this.yPos + 1)] == 1) {
						this.route[this.xPos][this.yPos] = DOWN;
						if (lastPos != this.route[this.xPos][this.yPos] - 1 && lastPos != -1) {
							routePointList.add(new RoutePoint(this.xPos, this.yPos));
						}

						this.lastPos = UP;
						this.yPos++;
						break;
					} else if (level.map[this.xPos][this.yPos + 1] == this.baseID) {
						base = new Base(this.xPos, this.yPos);
						break;
					}
				}

				if (i == LEFT && xPos > 0) {
					if (level.map[this.xPos - 1][this.yPos] == 1) {
						this.route[this.xPos][this.yPos] = LEFT;
						if (lastPos != this.route[this.xPos][this.yPos] - 1 && lastPos != -1) {
							routePointList.add(new RoutePoint(this.xPos, this.yPos));
						}

						this.lastPos = RIGHT;
						this.xPos--;
						break;
					} else if (level.map[this.xPos - 1][this.yPos] == this.baseID) {
						base = new Base(this.xPos, this.yPos);
						break;
					}
				}

				if (i == RIGHT && xPos < Screen.gridCountX - 1) {
					if (level.map[this.xPos + 1][this.yPos] == 1) {
						this.route[this.xPos][this.yPos] = RIGHT;
						if (lastPos != this.route[this.xPos][this.yPos] + 1 && lastPos != -1) {
							routePointList.add(new RoutePoint(this.xPos, this.yPos));
						}

						this.lastPos = LEFT;
						this.xPos++;
						break;
					} else if (level.map[this.xPos + 1][this.yPos] == this.baseID) {
						base = new Base(this.xPos, this.yPos);
						break;
					}
				}
			}
		}
	}
}
