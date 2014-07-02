package level;

/**
 * Data o levelu. Uložena mapa a spawnpoint.
 * @author Petr
 *
 */
public class Level {
	public int[][] map;
	public SpawnPoint spawnPoint;
	
	public void FindSpawnPoint() {
		for(int x = 0; x < map.length; x++) {
			for(int y = 0; y < map[0].length; y++) {
				if(map[x][y] == 2) {
					spawnPoint = new SpawnPoint(x, y);
				}
			}
		}
	}
}
