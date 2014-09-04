package enemy;

import level.SpawnPoint;
import core.Screen;

/**
 * Pohyb aktu�ln�ho nep��tele.
 * 
 * @author Petr
 * 
 */
public class EnemyMove {
	public Enemy enemy;
	SpawnPoint spawnPoint;

	/** Sou�adnicov� re�ln� pozice nep��tele. */
	public double xPos, yPos;
	/** Sou�adnicov� pozice �tverce, na kter�m se nep��tel pr�v� nach�z�. */
	int routePosX, routePosY;
	/** Ke kter� pozici v routePointList se tento nep��tel bl��. */
	public int routePointNumber = 0;
	/** Vzd�lenost tohoto nep��tele od st�edu �tverce. */
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
