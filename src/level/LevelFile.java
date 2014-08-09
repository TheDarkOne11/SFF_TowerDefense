package level;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

import core.Screen;

/**
 * Ète údaje z level souboru.
 * 
 * @author Petr
 * 
 */
public class LevelFile {
	FileInputStream levelFile;
	FileInputStream levelFile_Var;
	InputStreamReader reader;
	Scanner scanner;
	Level level = new Level();

	public Level getLevel(String fileName) {
		try {
			levelFile = new FileInputStream("level/" + fileName + "/" + fileName + ".level");
			levelFile_Var = new FileInputStream("level/" + fileName + "/" + fileName + "_Var.level");
			reader = new InputStreamReader(levelFile);
			scanner = new Scanner(reader);

			level.map = new int[Screen.gridCountX][Screen.gridCountY];

			int x = 0;
			int y = 0;
			while (scanner.hasNext()) {
				level.map[x][y] = scanner.nextInt();
				if (x < Screen.gridCountX - 1) {
					x++;
				} else {
					y++;
					x = 0;
				}
			}
			return level;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}
}
