package enemies;

import core.Screen;
import core.SpawnPoint;

public class EnemyMove {
	Enemy enemy;
	SpawnPoint spawnPoint;
	
	int xPos;
	int yPos;
	int routePosX;
	int routePosY;
	
	boolean attack;
	int health;
	
	public EnemyMove(Enemy enemy, SpawnPoint spawnPoint) {
		this.enemy = enemy;
		this.routePosX = spawnPoint.getX();
		this.routePosY = spawnPoint.getY();
		this.xPos = (int) (spawnPoint.getX() * Screen.gridWidth);
		this.yPos = (int) (spawnPoint.getY() * Screen.gridHeight);
		this.attack = false;
		this.health = enemy.health;
	}
	
	public EnemyMove update() {
		EnemyMove currentEnemy = this;
		
		if(currentEnemy.health <= 0) {
			return null;
		}
		
		return currentEnemy;
	}
}
