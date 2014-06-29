package core;

import handlers.KeyHandler;
import handlers.MouseHandler;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import level.Level;
import level.LevelFile;
import towers.Tower;
import additional_programs.LevelMaker;
import enemies.Enemy;
import enemies.EnemyAI;
import enemies.EnemyMove;
import enemies.Wave;

//TODO Epizoda 26

/* Hlavní tøída, která celou hru povede. */
public class Screen extends JPanel implements Runnable {
	
	Thread thread = new Thread(this);
	FrameClass frame;
	private int fps = 0;
	boolean running;
	
	// Main Grid
	public static int gridCountX = 25;	// 25
	public static int gridCountY = 15;	// 15
	/* Velikost x a y je stejná*/
	public static double gridSize = 1;
	
	// Shop Grid
	public static double shopGridStartX, shopGridStartY;
	public static int shopGridCountX = 20;
	public static int shopGridCountY = 2;

	// Mouse
	/**
	 * 1 = vìž v ruce
	 */
	public int hand = 0;
	public int handYPos = 0;
	public int handXPos = 0;
	
	// Map and Levels
		Level level;
		LevelFile levelFile;
		
		// Uloženy všechny hodnoty pozic gridu z LevelFile
		public int[][] map = new int[gridCountX][gridCountY];
		
		// Uloženy všechny hodnoty pozic vìží
		public Tower[][] towerMap = new Tower[gridCountX][gridCountY];
		public Image[] terrain = new Image[100];
		String packageName = "core";
		
	// Enemy
		/** Kolik nepøátel na mapì je na mapì v jednu chvíli. */
		public EnemyMove[] enemyMap = new EnemyMove[200];
		private int enemies = 0;
		public Wave wave;
		EnemyAI enemyAI;
	
	/**
	 * 0. Start 
	 * 1. In Game */
	public int scene;
	
	/** Account */
	User user;
	
	public Screen(FrameClass frame) {
		thread.start();	// Zaène run()
		this.frame = frame;
		this.frame.addKeyListener(new KeyHandler(this));
		this.frame.addMouseListener(new MouseHandler(this));
		this.frame.addMouseMotionListener(new MouseHandler(this));
	}
	
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, this.frame.getWidth(), this.frame.getHeight());
		
		if(scene == 0) {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, this.frame.getWidth(), this.frame.getHeight());
		} else if(scene == 1){			
			Screen.gridSize = this.getHeight() / (Screen.gridCountY + Screen.shopGridCountY + 2);
						
			// Background
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, this.frame.getWidth(), this.frame.getHeight());
			
			// Grid
			g.setColor(Color.black);
			for(int x = 0; x < gridCountX; x++) {
				for(int y = 0; y < gridCountY; y++) {
					g.drawImage(terrain[map[x][y]], (int) (Screen.gridSize + (x*Screen.gridSize)), (int) (Screen.gridSize + (y*Screen.gridSize)),(int) Screen.gridSize,(int) Screen.gridSize, null);
					g.drawRect((int) (Screen.gridSize + (x*Screen.gridSize)),(int) (Screen.gridSize + (y*Screen.gridSize)),(int) Screen.gridSize,(int) Screen.gridSize);
				}
			}
			
			// Enemy
			for(int i = 0; i < this.enemyMap.length; i++) {
				if(this.enemyMap[i] != null) {
					g.drawImage(this.enemyMap[i].enemy.texture,(int) (enemyMap[i].xPos + Screen.gridSize),(int) (enemyMap[i].yPos + Screen.gridSize),(int) Screen.gridSize,(int) Screen.gridSize, null);
				}
			}
			
			// Tower list
			Screen.shopGridStartX = Screen.gridSize*6.5;
			Screen.shopGridStartY = Screen.gridSize*(Screen.gridCountY + 1.25);
			
			for(int x = 0; x < Screen.shopGridCountX; x++) {
				for(int y = 0; y < Screen.shopGridCountY; y++) {
					if(Tower.towerList[x*Screen.shopGridCountY + y] != null) {
						// Tower Image
						g.drawImage(Tower.towerList[x*Screen.shopGridCountY + y].texture,(int) (Screen.shopGridStartX + (x*Screen.gridSize)),(int) (Screen.shopGridStartY + (y*Screen.gridSize)), (int) gridSize, (int) gridSize, null);
					
						// Zašedne vìž, pokud na ní není dostatek penìz
						if(Tower.towerList[x*Screen.shopGridCountY + y].cost > this.user.player.money) {
							g.setColor(new Color(68, 0, 68, 100));
							g.fillRect((int) (Screen.shopGridStartX + (x*Screen.gridSize)),(int) (Screen.shopGridStartY + (y*Screen.gridSize)), (int) gridSize, (int) gridSize);
						}
					
					}
					
					// Shop grid
					g.setColor(Color.black);
					g.drawRect((int) (Screen.shopGridStartX + (x*Screen.gridSize)),(int) (Screen.shopGridStartY + (y*Screen.gridSize)),(int) Screen.gridSize,(int) Screen.gridSize);
				}
			}
			
			// Health and money
				String health = "Health: " + user.player.health;
				String money = "Money: " + user.player.money;
					
				g.drawRect((int) Screen.gridSize, (int) (Screen.gridSize*(Screen.gridCountY+1.25)), (int) Screen.gridSize*4, (int) Screen.gridSize);
				g.drawString(health, (int) (Screen.gridSize*3 - g.getFontMetrics().stringWidth(health)/2), (int) (Screen.gridSize*(Screen.gridCountY+1.75) + g.getFontMetrics().getHeight()/4));
						
				g.drawRect((int) Screen.gridSize, (int) (Screen.gridSize*(Screen.gridCountY+2.25)), (int) Screen.gridSize*4, (int) Screen.gridSize);
				g.drawString(money, (int) (Screen.gridSize*3 - g.getFontMetrics().stringWidth(money)/2), (int) (Screen.gridSize*(Screen.gridCountY+2.75) + g.getFontMetrics().getHeight()/4));
				
				// Tower scrool list num. 1
				g.drawRect((int) (Screen.gridSize*5.25), (int) (Screen.gridSize*(Screen.gridCountY+1.25)), (int) Screen.gridSize, (int) Screen.gridSize*Screen.shopGridCountY);
		}
		
		// Umístìní vìže		
		for(int x = 0; x < Screen.gridCountX; x++){
			for(int y = 0; y < Screen.gridCountY; y++) {
				if(towerMap[x][y] != null) {
					int centerX = (int) gridSize + x*(int) gridSize - towerMap[x][y].range*(int) gridSize;
					int centerY = (int) gridSize + y*(int) gridSize - towerMap[x][y].range*(int) gridSize;
					
					g.setColor(Color.gray);
					g.drawOval(centerX, centerY, towerMap[x][y].range*2*(int) gridSize + (int) gridSize, towerMap[x][y].range*2*(int) gridSize + (int) gridSize);
					g.setColor(new Color(64, 64, 64, 64));
					g.fillOval(centerX, centerY, towerMap[x][y].range*2*(int) gridSize + (int) gridSize, towerMap[x][y].range*2*(int) gridSize + (int) gridSize);
					g.drawImage(Tower.towerList[towerMap[x][y].id].texture, (int) gridSize + x*(int)gridSize, (int) gridSize + y*(int) gridSize, (int) gridSize, (int) gridSize, null);
				}
			}
		}
		
		// HAND
		if(hand != 0 && Tower.towerList[hand-1] != null) {
			g.drawImage(Tower.towerList[hand-1].texture, this.handXPos - (int) Screen.gridSize/2, this.handYPos - (int) Screen.gridSize/2, (int) Screen.gridSize, (int) Screen.gridSize, null);
		}
		
		// FPS
		g.setColor(Color.black);
		g.drawString(fps + "", 10, 10);
	}
	
	public void loadGame() {
		user = new User(this);
		levelFile = new LevelFile();
		ClassLoader classLoader = this.getClass().getClassLoader();
		wave = new Wave(this);
		
		for(int y = 0; y < 10; y++) {
			for(int x = 0; x < 10; x++) {	// terrain.png je 250*250 pixelù, jeden typ krajiny je 25*25 pixelù
				terrain[x + y*10] = new ImageIcon(classLoader.getResource(packageName + "/terrain.png")).getImage();
				terrain[x + y*10] = createImage(new FilteredImageSource(terrain[x + y*10].getSource(), new CropImageFilter(x*25, y*25, 25, 25)));	// 25 je 1 pixel v Paint.Net
			}
		}
		
		running = true;
	}
	
	public void startGame(User user, String levelName) {
		user.createPlayer();
		this.level = levelFile.getLevel(levelName);
		this.level.FindSpawnPoint();
		this.map = this.level.map;
		this.enemyAI = new EnemyAI(this.level);
		
		this.scene = 1;	// Level 1
		this.wave.waveNumber = 0;
	}
	
	public void run() {
		long lastFrame = System.currentTimeMillis();
		int frames = 0;
		scene = 0;
		
		loadGame();
		
		while(running) {
			repaint();
			frames++;
			
			if(System.currentTimeMillis() - 1000 >= lastFrame) {
				fps = frames;
				lastFrame = System.currentTimeMillis();
				frames = 0;
			}
			
			updateEnemy();
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.exit(0);
	}
	
	public void updateEnemy() {
		// Moving
		for(int i = 0; i < this.enemyMap.length; i++) {
			if(this.enemyMap[i] != null) {
				if(!this.enemyMap[i].attack) {
					EnemyAI.moveAI.move(enemyMap[i]);
				}
				
				enemyMap[i].update();
			}
		}
		
		// Spawning
		if(wave.isEnemySpawning) {
			wave.spawnEnemies();
		}
	}
	
	public void spawnEnemy() {
		for(int i = 0; i < this.enemyMap.length; i++) {
			if(this.enemyMap[i] == null) {
				this.enemyMap[i] = new EnemyMove(Enemy.enemyList[0], level.spawnPoint);
				break;
			}
		}
	}
	
	public void placeTower(int x, int y) {
		int xPos = (int) (x /gridSize);
		int yPos = (int) (y/ gridSize);
		
		if(xPos > 0 && xPos <= Screen.gridCountX && yPos > 0 && yPos <= Screen.gridCountY) {
			xPos -= 1;
			yPos -= 1;
			
			if(towerMap[xPos][yPos] == null && map[xPos][yPos] == 0) {	// Pokud je místo prázdné
				user.player.money -= Tower.towerList[hand-1].cost;
				towerMap[xPos][yPos] = Tower.towerList[hand-1];
			}
		}
	}
	
	public class MouseHeld {
		boolean mouseDown;

		public void mouseMoved(MouseEvent e) {
			handXPos = e.getXOnScreen();
			handYPos = e.getYOnScreen();
		}
		
		public void updateMouse(MouseEvent e) {
			if(scene == 1) {
				if(mouseDown && hand == 0) {
					// Jestli je myš umístìna nìkdy v shopu_Pozice X
					if(e.getXOnScreen() >= shopGridStartX && e.getXOnScreen() <= shopGridStartX*(1 + gridSize)) {
						// Jestli je myš umístìna nìkdy v shopu_Pozice X
						if(e.getYOnScreen() >= shopGridStartY && e.getYOnScreen() <= shopGridStartY*(1 + gridSize)) {
							// Tower 1
							if(e.getXOnScreen() >= shopGridStartX && e.getXOnScreen() <= shopGridStartX+gridSize && e.getYOnScreen() >= shopGridStartY && e.getYOnScreen() <= shopGridStartY+gridSize) {
								if(user.player.money >= Tower.towerList[0].cost) {
									hand = 1;
								}
							}
						}
					}
				}
			}
		}
		
		public void mouseDown(MouseEvent e) {
			mouseDown = true;
			
			if(hand != 0) {
				placeTower(e.getXOnScreen(), e.getYOnScreen());
				hand = 0;
			}
			
			updateMouse(e);
		}
		
	}
	
	public class KeyTyped {
		
		public void keyENTER() {
			wave.nextWave();
		}
		
		public void keyESC() {
			running = false;
		}
		
		public void keySPACE() {
			startGame(user, "Level1");
		}
		
		public void keyBACKSPACE() {
			LevelMaker.testLevel();
			startGame(user, "TestLevel");
		}
		
	}

}