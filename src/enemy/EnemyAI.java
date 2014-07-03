package enemy;

import level.Level;

/** 
 * Umìlá inteligence všech nepøátel, inicializuje pohyb, útok atd. 
 */
public class EnemyAI {
	
	public static EnemyRoute enemyRoute;
	public static EnemyAIMove enemyMoveAI;
	
	public int basePosX;
	public int basePosY;
	public int id;
	
	public EnemyAI(Level level) {
		enemyRoute = new EnemyRoute(level);

		this.basePosX = enemyRoute.base.getX();
		this.basePosY = enemyRoute.base.getY();
		
		enemyMoveAI = new EnemyAIMove(this.id);
	}
	
	public EnemyAI(int id) {
		this.id = id;
	}
	
	public EnemyRoute getRoute() {
		return enemyRoute;
	}
}
