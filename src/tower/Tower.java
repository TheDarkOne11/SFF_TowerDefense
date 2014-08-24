package tower;

import java.awt.Image;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.ImageIcon;

import lib.PathVar;
import lib.TowerVar;
import tower.types.TowerLightning;
import core.Screen;
import enemy.EnemyMove;

public class Tower implements Cloneable {
	public String textureFile = "";
	public Image texture;

	public static final Tower[] towerList = new Tower[200];
	public int id;
	public int cost;
	public int range;
	public int damage;
	
	public EnemyMove target;
	public int attackTime = 0;	//(timer) How long does attack last. (laser lasts more than missile)
	public int attackDelay = 0;	//(timer) Time between attacks.
	public int attackTimeMax;
	public int attackDelayMax;
	
	/** Whether attack an enemy closest to Base, the strongest etc. */
	public int attackStrategy;
	public int RANDOM = 0;
	public int CLOSEST = 1;

	public static final Tower lightningTower = new TowerLightning(TowerVar.TowerLightningId, TowerVar.basicCost, TowerVar.basicRange, TowerVar.basicDamage, TowerVar.basicAttackTimeMax, TowerVar.basicDelayTimeMax).getTextureFile("LightningTower");

	public Tower(int id, int cost, int range, int damage, int attackTimeMax, int delayTimeMax) {
		if (towerList[id] != null) {
			System.out.println("[TowerInitialization] Two towers with same id: " + id);
		} else {
			towerList[id] = this;
			this.id = id;
			this.cost = cost;
			this.range = range;
			this.damage = damage;
			this.attackTimeMax = attackTimeMax;
			this.attackDelayMax = delayTimeMax;
		}
	}
	/*
	*//** Finds all enemies in range. *//*
	public EnemyMove[] calculateEnemy(EnemyMove[] enemyMap, int x, int y) {
		// Enemies in range
		EnemyMove[] enemiesInRange = new EnemyMove[enemyMap.length];
		
		int towerRadius = this.range;
		int enemyRadius = 1;
		int towerX = x;
		int towerY = y;
		int enemyX;
		int enemyY;
		
		for(int i = 0; i < enemyMap.length; i++) {
			if(enemyMap[i] != null) {
				enemyX = (int) (enemyMap[i].xPos/Screen.gridSize);
				enemyY = (int) (enemyMap[i].yPos/Screen.gridSize);
				
				int dx = enemyX - towerX;
				int dy = enemyY - towerY;
				int dRadius = enemyRadius + towerRadius;
				
				if((dx*dx) + (dy*dy) < (dRadius*dRadius)) {
					enemiesInRange[i] = enemyMap[i];
				}
			}
		}
		
		return enemiesInRange;
	}

	*//** Targets enemy. *//*
	public EnemyMove targetEnemy(EnemyMove[] enemiesInRange) {
		
		if(this.attackStrategy == this.RANDOM) {
			int totalEnemies = 0;
			
			for(int i = 0; i < enemiesInRange.length; i++) {
				if(enemiesInRange[i] != null) {
					totalEnemies++;
				}
			}

			if(totalEnemies > 0) {
				int enemyIndex = new Random().nextInt(totalEnemies);
				int enemiesTaken = 0;
				int i = 0;
				
				
				while(true) {
					if(enemiesTaken == enemyIndex && enemiesInRange[i] != null){
						return enemiesInRange[i]; 
					}
					
					if(enemiesInRange[i] != null) {
						enemiesTaken++;
					}
					
					i++;
				}
			}
		}
		return null;
		
	}
	*/
	/** Finds all enemies in range. */
	public LinkedList<EnemyMove> calculateEnemyNew(EnemyMove[] enemyMap, int x, int y) {
		// Enemies in range
		LinkedList<EnemyMove> enemiesInRange = new LinkedList<EnemyMove>();
		
		int towerRadius = this.range;
		int enemyRadius = 1;
		int towerX = x;
		int towerY = y;
		int enemyX;
		int enemyY;
		
		for(int i = 0; i < enemyMap.length; i++) {
			if(enemyMap[i] != null) {
				enemyX = (int) (enemyMap[i].xPos/Screen.gridSize);
				enemyY = (int) (enemyMap[i].yPos/Screen.gridSize);
				
				int dx = enemyX - towerX;
				int dy = enemyY - towerY;
				int dRadius = enemyRadius + towerRadius;
				
				if((dx*dx) + (dy*dy) < (dRadius*dRadius)) {
					enemiesInRange.add(enemyMap[i]);
				}
			}
		}
		
		return enemiesInRange;
	}
	
	/** Targets enemy. */
	public EnemyMove targetEnemyNew(LinkedList<EnemyMove> enemiesInRange) {
		
		if(this.attackStrategy == this.RANDOM) {

			if(enemiesInRange.size() > 0) {
				int enemyIndex = new Random().nextInt(enemiesInRange.size());
				
				for(int i = 0; i < enemiesInRange.size(); i++) {
					if(i == enemyIndex && enemiesInRange.get(i) != null){
						return enemiesInRange.get(i); 
					}
				}
			}
		}
		return null;
		
	}
	
	public Tower getTextureFile(String textureFile) {
		this.textureFile = textureFile;
		this.texture = new ImageIcon(PathVar.towerPath + textureFile + ".png").getImage();

		return this;
	}

	/**
	 * Bez klonování by se stalo, že po zmìnì stavu(napø. range, útok) jedné
	 * vìže na mapì by zmìna nastala u všech vìží.
	 */
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}
