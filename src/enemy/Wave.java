package enemy;

import java.util.Random;

import core.Screen;

/** Spawnování nepøátel, zaèátek vln. */
public class Wave {
	Screen screen;
	public int waveNumber = 0;
	public int pointsThisRound = 10;
	public boolean isEnemySpawning;
	public int currentDelay = 0;
	public int spawnRate = 1000;

	public int enemyCount;
	public int currPoints;

	public Wave(Screen screen) {
		this.screen = screen;
		this.spawnRate /= Screen.updatesPerSec;
	}

	/**
	 * Zaène další vlnu.
	 */
	public void nextWave() {
		if (screen.gameState == 1) {
			this.waveNumber++;
			this.pointsThisRound = this.waveNumber*10;
			this.currPoints = 0;
			isEnemySpawning = true;

			System.out.println("[Wave] " + waveNumber + ". wave spawning.");

			for (int i = 0; i < screen.enemyMap.length; i++) {
				this.screen.enemyMap[i] = null;
			}
		}
	}

	/**
	 * Zaène spawnovat jednotlivé nepøátele
	 */
	public void spawnEnemies() {
		if (currPoints < this.pointsThisRound) {
			if (this.currentDelay < this.spawnRate) {
				this.currentDelay++;
			} else {
				this.currentDelay = 0;

				System.out.println("[Wave] " + (++enemyCount) + ". enemy spawned.");

				int[] enemiesSpawnableId = new int[Enemy.enemyList.length];
				int enemiesSpawnableSoFar = 0;
				
				for(int i = 0; i < Enemy.enemyList.length; i++) {
					if(Enemy.enemyList[i] != null) {
						if(Enemy.enemyList[i].points + currPoints <= this.pointsThisRound && Enemy.enemyList[i].points <= this.pointsThisRound) {
							enemiesSpawnableId[enemiesSpawnableSoFar] = Enemy.enemyList[i].id;
							enemiesSpawnableSoFar++;
						}
					}
				}
				int enemyId = new Random().nextInt(enemiesSpawnableSoFar);
				this.currPoints += Enemy.enemyList[enemyId].points;
				this.screen.spawnEnemy(enemiesSpawnableId[enemyId]);
			}
		}
	}
}
