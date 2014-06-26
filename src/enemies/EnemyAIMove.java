package enemies;

import core.Screen;

public class EnemyAIMove extends EnemyAI {

	public EnemyAIMove(int id) {
		super(id);
	}
	
	public void move(EnemyMove enemyMove) {
		// Pokud je pøímo uprostøed ètverce
		if(enemyMove.xPos % Screen.gridCountX == 0 && enemyMove.yPos % Screen.gridCountY == 0 && enemyMove.routePosX == enemyMove.xPos/Screen.gridCountX && enemyMove.routePosY == enemyMove.yPos/Screen.gridCountY) {
			if(enemyMove.routePosX == this.basePosX && enemyMove.routePosY == this.basePosY) {
				enemyMove.attack = true;
			} else {
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
			double xPos = enemyMove.xPos / Screen.gridSize;
			double yPos = enemyMove.yPos / Screen.gridSize;
			
			if(xPos > enemyMove.routePosX) enemyMove.xPos -= enemyMove.enemy.speed/Screen.gridCountX;
			if(xPos < enemyMove.routePosX) enemyMove.xPos += enemyMove.enemy.speed/Screen.gridCountX;
			if(yPos > enemyMove.routePosY) enemyMove.yPos -= enemyMove.enemy.speed/Screen.gridCountY;
			if(yPos < enemyMove.routePosY) enemyMove.yPos += enemyMove.enemy.speed/Screen.gridCountY;
		}
	}
	
	public void cantFindRoute() {
		System.out.println("[EnemyAIMove] Can't find route.");
	}
}
