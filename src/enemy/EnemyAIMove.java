package enemy;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lib.EnemyVar;
import core.Screen;

/**
 * Umìlá inteligence pohybu všech nepøátel.
 * @author Petr
 *
 */
public class EnemyAIMove extends EnemyAI {
	private double distanceToCenter;
	long time = System.currentTimeMillis();
	
	public EnemyAIMove(int id) {
		super(id);
	}
	
	/* Mìl bych to upravit tak, aby nepøítel musel zastavit uprostøed ètverce, jen pokud bude zatáèet.
	 * Nemuseli by se tolik sekat. */
	public void move(EnemyMove enemyMove) {
		/*
		 * Podmínka 1: Pokud je pøímo uprostøed ètverce (enemyMove.xPos % Screen.gridSize == 0).
		 * Podmínka 2: Pokud již byla routePos updatována (enemyMove.routePosX == (int) (enemyMove.xPos/Screen.gridSize))
		 */
		if((int) enemyMove.xPos % Screen.gridSize == 0 && (int) enemyMove.yPos % Screen.gridSize == 0 && enemyMove.routePosX == (int) (enemyMove.xPos/Screen.gridSize) && enemyMove.routePosY == (int) (enemyMove.yPos/Screen.gridSize)) {
			//System.out.println(enemyMove.routePosX + "/ " + enemyMove.routePosY + ": " + (System.currentTimeMillis() - time));
			time = System.currentTimeMillis();
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
				//System.out.println("Screen.gridSize*enemyMove.routePosX-enemyMove.xPos: " + (Screen.gridSize*enemyMove.routePosX-enemyMove.xPos));
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
	
	public void cantFindRoute() {
		System.out.println("[EnemyAIMove] Can't find route.");
	}
}
