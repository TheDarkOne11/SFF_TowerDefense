package additional_programs;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import core.Screen;

/* Vytvoøí level dle velikosti gridu. */
public class LevelMaker {
	static File file;
	static String fileName;
	static BufferedWriter buff;
	final static int numberOfTerrains = 3;
	
	public static void randomLevel() {
		fileName = "RandomLevel";
		Random rn = new Random();
		
		try {
			file = new File("level/" + fileName + ".level");
			buff = new BufferedWriter(new FileWriter(file));
			
			for(int y = 0; y < Screen.gridCountY; y++) {
				for(int x = 0; x < Screen.gridCountX; x++) {
					buff.write(rn.nextInt(numberOfTerrains) + " ");
				}
				buff.newLine();
			}
			buff.flush();
			buff.close();
		
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void blankLevel() {
		fileName = "BlankLevel";
		try {
			file = new File("level/" + fileName + ".level");
			buff = new BufferedWriter(new FileWriter(file));
			
			for(int y = 0; y < Screen.gridCountY; y++) {
				for(int x = 0; x < Screen.gridCountX; x++) {
					buff.write(0 + " ");
				}
				buff.newLine();
			}
			buff.flush();
			buff.close();
		
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		blankLevel();
	}

}
