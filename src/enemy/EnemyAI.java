package enemy;

import level.Level;

/** 
 * Um�l� inteligence v�ech nep��tel, inicializuje pohyb, �tok atd. 
 */
public class EnemyAI {
	
	public static EnemyRoute enemyRoute;
	public static EnemyAIMove enemyAIMove;
	
	public int basePosX;
	public int basePosY;
	public int id;
	
	public EnemyAI(Level level) {
		enemyRoute = new EnemyRoute(level);
		
		this.basePosX = enemyRoute.base.getX();
		this.basePosY = enemyRoute.base.getY();
		
		enemyAIMove = new EnemyAIMove(id);
	}
	
	public EnemyAI(int id) {
		this.id = id;

		this.basePosX = enemyRoute.base.getX();
		this.basePosY = enemyRoute.base.getY();
	}
	
	public EnemyRoute getRoute() {
		return enemyRoute;
	}
}
