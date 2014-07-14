package enemy;

import core.Screen;

/** Spawnování nepøátel, zaèátek vln. */
public class Wave {
	Screen screen;
	public int waveNumber = 0;
	public int enemiesThisRound = 0;
	public int enemiesPerRound = 10;
	public boolean isEnemySpawning;
	public int currentDelay = 0;
	public int spawnRate = 1000;
	
	public int enemyCount;
	
	public Wave(Screen screen) {
		this.screen = screen;
		this.spawnRate /= screen.updatesPerSec;
	}
	
	/**
	 * Zaène další vlnu.
	 */
	public void nextWave() {
		this.waveNumber++;
		this.enemiesThisRound = 0;
		isEnemySpawning = true;
		
		System.out.println("[Wave] " + waveNumber + ". wave spawning.");
		
		for(int i = 0; i < screen.enemyMap.length; i++) {
			this.screen.enemyMap[i] = null;
		}
	}
	
	/**
	 * Zaène spawnovat jednotlivé nepøátele
	 */
	public void spawnEnemies() {
		if(this.enemiesThisRound < waveNumber * this.enemiesPerRound) {
			if(this.currentDelay < this.spawnRate) {
				this.currentDelay++;
			} else {
				this.currentDelay = 0;
				
				System.out.println("[Wave] " + (++enemyCount) + ". enemy spawned.");
				
				this.enemiesThisRound++;
				this.screen.spawnEnemy();
			}
		}
	}
}
