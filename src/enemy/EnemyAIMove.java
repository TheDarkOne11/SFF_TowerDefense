package enemy;

import core.Screen;

/**
 * Umìlá inteligence pohybu všech nepøátel.
 * @author Petr
 *
 */
public class EnemyAIMove extends EnemyAI {
	private double distanceToCenter;
	
	public EnemyAIMove(int id) {
		super(id);
	}
	
	public void move(EnemyMove enemyMove) {
		/*
		 * Podmínka 1: Pokud je pøímo uprostøed ètverce (enemyMove.xPos % Screen.gridSize == 0).
		 * Podmínka 2: Pokud již byla routePos updatována (enemyMove.routePosX == (int) (enemyMove.xPos/Screen.gridSize))
		 */
		if((int) enemyMove.xPos % Screen.gridSize == 0 && (int) enemyMove.yPos % Screen.gridSize == 0 && enemyMove.routePosX == (int) (enemyMove.xPos/Screen.gridSize) && enemyMove.routePosY == (int) (enemyMove.yPos/Screen.gridSize)) {
			if(enemyMove.routePosX == super.basePosX && enemyMove.routePosY == super.basePosY) {
				enemyMove.attack = true;
			} else {
				distanceToCenter = Screen.gridSize;
				
				if(enemyRoute.route[enemyMove.routePosX][enemyMove.routePosY] == enemyRoute.UP) {
					enemyMove.routePosY--;
				} else if(enemyRoute.route[enemyMove.routePosX][enemyMove.routePosY] == enemyRoute.DOWN) {
					enemyMove.routePosY++;
				} else if(enemyRoute.route[enemyMove.routePosX][enemyMove.routePosY] == enemyRoute.LEFT) {
					enemyMove.routePosX--;
				} else if(enemyRoute.route[enemyMove.routePosX][enemyMove.routePosY] == enemyRoute.RIGHT) {
					enemyMove.routePosX++;
				} else {
					this.cantFindRoute();
				}
			}
		} else {
			double xPos = (int) enemyMove.xPos / Screen.gridSize;
			double yPos = (int) enemyMove.yPos / Screen.gridSize;
			
			if(xPos > enemyMove.routePosX && this.distanceToCenter > enemyMove.enemy.speed) {
				enemyMove.xPos -= enemyMove.enemy.speed;
				this.distanceToCenter -= enemyMove.enemy.speed;
			} else if(xPos > enemyMove.routePosX && this.distanceToCenter <= enemyMove.enemy.speed) {
				enemyMove.xPos -= this.distanceToCenter;
			}
			
			if(xPos < enemyMove.routePosX && this.distanceToCenter > enemyMove.enemy.speed) {
				enemyMove.xPos += enemyMove.enemy.speed;
				this.distanceToCenter -= enemyMove.enemy.speed;
			} else if(xPos < enemyMove.routePosX && this.distanceToCenter <= enemyMove.enemy.speed) {
				enemyMove.xPos += this.distanceToCenter;
			}
			
			if(yPos > enemyMove.routePosY && this.distanceToCenter > enemyMove.enemy.speed) {
				enemyMove.yPos -= enemyMove.enemy.speed;
				this.distanceToCenter -= enemyMove.enemy.speed;
			} else if(yPos > enemyMove.routePosY && this.distanceToCenter <= enemyMove.enemy.speed) {
				enemyMove.yPos -= this.distanceToCenter;
			}
			
			if(yPos < enemyMove.routePosY && this.distanceToCenter > enemyMove.enemy.speed) {
				enemyMove.yPos += enemyMove.enemy.speed;
				this.distanceToCenter -= enemyMove.enemy.speed;
			} else if(yPos < enemyMove.routePosY && this.distanceToCenter <= enemyMove.enemy.speed) {
				enemyMove.yPos += this.distanceToCenter;
			}
		}
	}
	
	public void cantFindRoute() {
		System.out.println("[EnemyAIMove] Can't find route.");
	}
}
