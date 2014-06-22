package enemies;

import level.Level;

public class EnemyAI {
	
	public static EnemyRoute enemyRoute;
	public static EnemyAIMove moveAI;
	
	public int basePosX;
	public int basePosY;
	public int id;
	
	public EnemyAI(Level level) {
		enemyRoute = new EnemyRoute(level);
		
		this.basePosX = enemyRoute.base.getX();
		this.basePosY = enemyRoute.base.getY();
		
		moveAI = new EnemyAIMove(0);
	}
	
	public EnemyAI(int id) {
		this.id = id;
	}
	
	public EnemyRoute getRoute() {
		return enemyRoute;
	}
}
