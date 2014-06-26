package enemies;

import level.Base;
import level.Level;
import core.Screen;

public class EnemyRoute {
	Level level;
	int[][] route = new int[Screen.gridCountX][Screen.gridCountY];
	int baseID = 3;
	Base base;
	
	int xPos;
	int yPos;
	int lastPos = -1;
	public int UP = 1;
	public int DOWN = 2;
	public int LEFT = 3;
	public int RIGHT = 4;
	
	public EnemyRoute(Level level) {
		this.level = level;
		this.xPos = this.level.spawnPoint.getX();
		this.yPos = this.level.spawnPoint.getY();
		
		calculateRoute();
	}

	private void calculateRoute() {
		while(base == null) {
			calculateNextPos();
		}
	}

	private void calculateNextPos() {
		for(int i = 1; i <= 4; i++) {
			if(i != this.lastPos) {
				if(i == UP && yPos > 1) {	// yPos nesmí jít mimo grid
					if(level.map[this.xPos][this.yPos - 1] == 1) {
						this.lastPos = DOWN;	// Když jsem šel nahoru, posl. pozice musí být dole
						this.route[this.xPos][this.yPos] = UP;
						this.yPos--;
						
						break;
					} else if(level.map[this.xPos][this.yPos - 1] == this.baseID) {
						base = new Base(this.xPos, this.yPos - 1);
						break;
					}
				}
				
				if(i == DOWN && yPos < Screen.gridCountY-1) {	
					if(level.map[this.xPos][this.yPos + 1] == 1) {
						this.lastPos = UP;
						this.route[this.xPos][this.yPos] = DOWN;
						this.yPos++;
						
						break;
					} else if(level.map[this.xPos][this.yPos + 1] == this.baseID) {
						base = new Base(this.xPos, this.yPos + 1);
						break;
					}
				}
				
				if(i == LEFT && xPos > 1) {	
					if(level.map[this.xPos - 1][this.yPos] == 1) {
						this.lastPos = RIGHT;
						this.route[this.xPos][this.yPos] = LEFT;
						this.xPos--;
						
						break;
					} else if(level.map[this.xPos - 1][this.yPos] == this.baseID) {
						base = new Base(this.xPos - 1, this.yPos);
						break;
					}
				}
				
				if(i == RIGHT && xPos < Screen.gridCountX-1) {	
					if(level.map[this.xPos + 1][this.yPos] == 1) {
						this.lastPos = LEFT;
						this.route[this.xPos][this.yPos] = RIGHT;
						this.xPos++;
						
						break;
					} else if(level.map[this.xPos + 1][this.yPos] == this.baseID) {
						base = new Base(this.xPos + 1, this.yPos);
						break;
					}
				}
			}
		}
	}
}
