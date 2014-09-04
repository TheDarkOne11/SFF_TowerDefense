package enemy;

import level.SpawnPoint;
import core.Screen;

/**
 * Pohyb aktuálního nepøítele.
 * 
 * @author Petr
 * 
 */
public class EnemyMove {
	public Enemy enemy;
	SpawnPoint spawnPoint;

	/** Souøadnicová reálná pozice nepøítele. */
	public double xPos, yPos;
	/** Souøadnicová pozice ètverce, na kterém se nepøítel právì nachází. */
	int routePosX, routePosY;
	/** Ke které pozici v routePointList se tento nepøítel blíží. */
	public int routePointNumber = 0;
	/** Vzdálenost tohoto nepøítele od støedu ètverce. */
	public double distanceToCenter;

	public boolean attack;
	public int health;

	public EnemyMove(Enemy enemy, SpawnPoint spawnPoint) {
		this.enemy = enemy;
		this.routePosX = spawnPoint.getX();
		this.routePosY = spawnPoint.getY();
		this.xPos = (int) (spawnPoint.getX() * Screen.gridSize);
		this.yPos = (int) (spawnPoint.getY() * Screen.gridSize);
		this.attack = false;
		this.health = enemy.health;
	}

	public EnemyMove update() {
		EnemyMove currentEnemy = this;

		if (currentEnemy.health <= 0) {
			Screen.user.player.money += currentEnemy.enemy.reward;
			return null;
		}

		return currentEnemy;
	}
}
