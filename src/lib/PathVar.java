package lib;

public class PathVar {
	public static String gamePath = System.getProperty("java.class.path").substring(0, System.getProperty("java.class.path").lastIndexOf("\\")) + "\\";
	
	public static String enemyPath = "res/enemy/";
	public static String gridCountButtonPath = "res/levelMaker/";
	public static String transcodeButtonPath = "res/levelMaker/";
	public static String towerPath = "res/tower/";
	public static String terrainSprite = "res/sprites/terrain.png";
	
	public static String levelPath = "level\\";
	public static String levelExtension = ".level";
	public static String levelVarExtension = "_Var" + PathVar.levelExtension;
	public static String levelWaveExtension = "_Wave" + PathVar.levelExtension;
}
