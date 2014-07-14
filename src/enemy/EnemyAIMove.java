package enemy;

import core.Screen;

/**
 * Umìlá inteligence pohybu všech nepøátel.
 * @author Petr
 *
 */
public class EnemyAIMove extends EnemyAI {
	private double distanceToCenter;
	private int routePointNumber = 0;
	long time = System.currentTimeMillis();
	
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
				
				if(routePointNumber <= enemyRoute.routePointList.size()) {
					
					if(enemyRoute.route[enemyMove.routePosX][enemyMove.routePosY] == enemyRoute.UP) {
						distanceToCenter = Screen.gridSize*(enemyMove.routePosY-enemyRoute.routePointList.get(routePointNumber).getY());
					} else if(enemyRoute.route[enemyMove.routePosX][enemyMove.routePosY] == enemyRoute.DOWN) {
						distanceToCenter = Screen.gridSize*(enemyRoute.routePointList.get(routePointNumber).getY()-enemyMove.routePosY);
					} else if(enemyRoute.route[enemyMove.routePosX][enemyMove.routePosY] == enemyRoute.LEFT) {
						distanceToCenter = Screen.gridSize*(enemyMove.routePosX-enemyRoute.routePointList.get(routePointNumber).getX());
					} else if(enemyRoute.route[enemyMove.routePosX][enemyMove.routePosY] == enemyRoute.RIGHT) {
						distanceToCenter = Screen.gridSize*(enemyRoute.routePointList.get(routePointNumber).getX()-enemyMove.routePosX);
					} else {
						this.cantCountDistance();
					}
					
					enemyMove.routePosX = enemyRoute.routePointList.get(routePointNumber).getX();
					enemyMove.routePosY = enemyRoute.routePointList.get(routePointNumber).getY();
					routePointNumber++;
				}
			}
		} else {
			double xPos = (int) enemyMove.xPos / Screen.gridSize;
			double yPos = (int) enemyMove.yPos / Screen.gridSize;
			
			//System.out.println(enemyMove.enemy.speed);
			//System.out.println("xPos/yPos: " + enemyMove.xPos + "/ " + enemyMove.yPos);
			//System.out.println("distancetoCenter: " + this.distanceToCenter + '\r');
			
			if(xPos > enemyMove.routePosX && this.distanceToCenter > enemyMove.enemy.speed) {
				enemyMove.xPos -= enemyMove.enemy.speed;
				this.distanceToCenter -= enemyMove.enemy.speed;
			} else if(xPos > enemyMove.routePosX && this.distanceToCenter <= enemyMove.enemy.speed) {
				enemyMove.xPos -= enemyMove.xPos - Screen.gridSize*enemyMove.routePosX;
			}
			
			if(xPos < enemyMove.routePosX && this.distanceToCenter > enemyMove.enemy.speed) {
				enemyMove.xPos += enemyMove.enemy.speed;
				this.distanceToCenter -= enemyMove.enemy.speed;
			} else if(xPos < enemyMove.routePosX && this.distanceToCenter <= enemyMove.enemy.speed) {
				//System.out.println("enemyMove.xPos1: " + enemyMove.xPos);
				enemyMove.xPos += Screen.gridSize*enemyMove.routePosX-enemyMove.xPos;
				//System.out.println("enemyMove.xPos2: " + enemyMove.xPos + '\r');
			}
			
			if(yPos > enemyMove.routePosY && this.distanceToCenter > enemyMove.enemy.speed) {
				enemyMove.yPos -= enemyMove.enemy.speed;
				this.distanceToCenter -= enemyMove.enemy.speed;
			} else if(yPos > enemyMove.routePosY && this.distanceToCenter <= enemyMove.enemy.speed) {
				enemyMove.yPos -= enemyMove.yPos - Screen.gridSize*enemyMove.routePosY;
			}
			
			if(yPos < enemyMove.routePosY && this.distanceToCenter > enemyMove.enemy.speed) {
				enemyMove.yPos += enemyMove.enemy.speed;
				this.distanceToCenter -= enemyMove.enemy.speed;
			} else if(yPos < enemyMove.routePosY && this.distanceToCenter <= enemyMove.enemy.speed) {
				enemyMove.yPos += Screen.gridSize*enemyMove.routePosY-enemyMove.yPos;
			}
		}
	}
	
	public void cantCountDistance() {
		System.out.println("[EnemyAIMove] Can't count distance: " + this.distanceToCenter);
	}
}
