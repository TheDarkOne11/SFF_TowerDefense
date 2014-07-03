package enemy;

import core.Screen;

/**
 * Um�l� inteligence pohybu v�ech nep��tel
 * @author Petr
 *
 */
public class EnemyAIMove extends EnemyAI {

	public EnemyAIMove(int id) {
		super(id);
	}
	
	public void move(EnemyMove enemyMove) {
		/*
		 * Podm�nka 1: Pokud je p��mo uprost�ed �tverce (enemyMove.xPos % Screen.gridSize == 0).
		 * Podm�nka 2: Pokud ji� byla routePos updatov�na (enemyMove.routePosX == (int) (enemyMove.xPos/Screen.gridSize))
		 */
		//TODO Upravit podm�nku, tato nepracuje pro v�echny rychlosti(nep��tel mine prost�edek �tverce)
		if((int) enemyMove.xPos % Screen.gridSize == 0 && (int) enemyMove.yPos % Screen.gridSize == 0 && enemyMove.routePosX == (int) (enemyMove.xPos/Screen.gridSize) && enemyMove.routePosY == (int) (enemyMove.yPos/Screen.gridSize)) {
			if(enemyMove.routePosX == super.basePosX && enemyMove.routePosY == super.basePosY) {
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
			double xPos = (int) enemyMove.xPos / Screen.gridSize;
			double yPos = (int) enemyMove.yPos / Screen.gridSize;
			if(xPos > enemyMove.routePosX) enemyMove.xPos -= enemyMove.enemy.speed/210;
			if(xPos < enemyMove.routePosX) enemyMove.xPos += enemyMove.enemy.speed/210;
			if(yPos > enemyMove.routePosY) enemyMove.yPos -= enemyMove.enemy.speed/210;
			if(yPos < enemyMove.routePosY) enemyMove.yPos += enemyMove.enemy.speed/210;
		}
	}
	
	public void cantFindRoute() {
		System.out.println("[EnemyAIMove] Can't find route.");
	}
}
