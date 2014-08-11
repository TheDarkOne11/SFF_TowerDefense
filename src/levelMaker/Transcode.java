package levelMaker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import lib.PathVar;

public class Transcode {
	public static File levelFile;
	public static File levelFile_Var;
	public static PrintWriter writer;
	
	private static File newFileName;
	private static int num;
	private static String fileName;
	private static BufferedReader reader;
	
	private static File futureLevelFile;
	private static File futureLevelFile_Var;
	private static String futureNewFileName;
	
	private static void getFileName() {
		try {
			newFileName = new File(PathVar.levelPath + "NewFileName" + PathVar.levelVarExtension);
			reader = new BufferedReader(new FileReader(newFileName));
			fileName = reader.readLine();
			String tmp = reader.readLine();
			num = Integer.valueOf(tmp).intValue();
			fileName += tmp;
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void setNewFileName() {
		try {
			writer = new PrintWriter(new FileWriter(newFileName));
			writer.println("Level");
			writer.print(Integer.toString(++num));
			writer.close();
			futureNewFileName = "Level" + num;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void setFutureFiles() {
		futureLevelFile = new File(PathVar.gamePath + PathVar.levelPath + futureNewFileName + "\\" + futureNewFileName + PathVar.levelExtension);
		futureLevelFile_Var = new File(PathVar.gamePath + PathVar.levelPath + futureNewFileName + "\\" + futureNewFileName + PathVar.levelVarExtension);
	
		try {
			futureLevelFile.mkdirs();
			futureLevelFile.delete();
			futureLevelFile.createNewFile();
			futureLevelFile_Var.mkdirs();
			futureLevelFile_Var.delete();
			futureLevelFile_Var.createNewFile();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void transcodeLevel() {
		getFileName();
		try {
			levelFile = new File(PathVar.gamePath + PathVar.levelPath + fileName + "\\" + fileName + PathVar.levelExtension);
			levelFile_Var = new File(PathVar.gamePath + PathVar.levelPath + fileName + "\\" + fileName + PathVar.levelVarExtension);
			
			levelFile.delete();
			levelFile.createNewFile();
			
			levelFile_Var.delete();
			levelFile_Var.createNewFile();
			
			writer = new PrintWriter(new FileWriter(levelFile_Var));
			
			writer.println(Integer.toString(LevelMaker.gridCountX));
			writer.println(Integer.toString(LevelMaker.gridCountY));
			writer.flush();
			writer.close();
			
			writer = new PrintWriter(new FileWriter(levelFile));
			for(int y = 0; y < LevelMaker.gridCountY; y++) {
				for(int x = 0; x < LevelMaker.gridCountX; x++) {
					writer.print(LevelMaker.gameGrid.get(x + y*LevelMaker.gridCountX).id + " ");
				}
				writer.println("");
			}
			writer.flush();
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setNewFileName();
		setFutureFiles();
	}
}
