package enemies;

import level.SpawnPoint;
import core.Screen;

public class EnemyMove {
	public Enemy enemy;
	SpawnPoint spawnPoint;
	
	public double xPos;
	public double yPos;
	int routePosX;
	int routePosY;
	
	public boolean attack;
	int health;
	
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
		
		if(currentEnemy.health <= 0) {
			return null;
		}
		
		return currentEnemy;
	}
}
