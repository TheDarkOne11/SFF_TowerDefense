package lib;

import core.Screen;

public class EnemyVar {
	public static int basicPoint = 1;
	public static int basicReward = 5;
	public static int basicHealth = 10;
	public static int basicDamage = 10;
	/** One grid rectangle per second. */
	public static double basicSpeed = Screen.gridSize / Screen.updatesPerSec;
	/** Attack per second. */
	public static double basicAttackSpeed = Screen.updatesPerSec;

	public static int slimeId = 0;
	public static int strongId = 1;

}
