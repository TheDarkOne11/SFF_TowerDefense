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

//TODO Epizoda 24

/* Hlavní tøída, která celou hru povede. */
public class Screen extends JPanel implements Runnable {
	
	Thread thread = new Thread(this);
	FrameClass frame;
	private int fps = 0;
	boolean running;
	
	// Main Grid
	public static int gridCountX = 25;	// 25
	public static int gridCountY = 10;	// 15
	public static int gridWidth = 1;
	public static int gridHeight = 1;
	
	// Shop Grid
	public int shopGridStartX_Mouse, shopGridStartY_Mouse, towerCountX_Mouse, towerCountY_Mouse;

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
			int gapX = this.frame.getWidth()/16 + 2*Screen.gridCountX;
			int gapY = this.frame.getHeight()/9 + 5*Screen.gridCountY;
			
			/** Velikost políèka X*/
			Screen.gridWidth = (this.frame.getWidth() - gapX) / (1 + gridCountX);
			/** Velikost políèka Y*/
			Screen.gridHeight = (this.frame.getHeight() - gapY) / (1 + gridCountY);
						
			// Background
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, this.frame.getWidth(), this.frame.getHeight());
			
			// Grid
			g.setColor(Color.black);
			for(int x = 0; x < gridCountX; x++) {
				for(int y = 0; y < gridCountY; y++) {
					g.drawImage(terrain[map[x][y]], Screen.gridWidth + (x*Screen.gridWidth), Screen.gridHeight + (y*Screen.gridHeight), Screen.gridWidth, Screen.gridHeight, null);
					g.drawRect(Screen.gridWidth + (x*Screen.gridWidth), Screen.gridHeight + (y*Screen.gridHeight), Screen.gridWidth, Screen.gridHeight);
				}
			}
			
			// Enemy
			for(int i = 0; i < this.enemyMap.length; i++) {
				if(this.enemyMap[i] != null) {
					g.drawImage(this.enemyMap[i].enemy.texture, enemyMap[i].xPos + Screen.gridWidth, enemyMap[i].yPos + Screen.gridHeight, Screen.gridWidth, Screen.gridHeight, null);
				}
			}
			
			// Tower list
			int TLCountX = 20;
			int TLCountY = 3;
			int startX = Screen.gridWidth/2 + Screen.gridWidth*4 + Screen.gridWidth + 10;
			int startY = this.frame.getHeight() - gapY + 5;
			this.shopGridStartX_Mouse = startX;
			this.shopGridStartY_Mouse = startY;
			this.towerCountX_Mouse = TLCountX;
			this.towerCountY_Mouse = TLCountY;
			
			for(int x = 0; x < TLCountX; x++) {
				for(int y = 0; y < TLCountY; y++) {
					if(Tower.towerList[x*TLCountY + y] != null) {
						// Tower Image
						g.drawImage(Tower.towerList[x*TLCountY + y].texture, startX + (x*Screen.gridWidth), startY + (y*Screen.gridHeight), (int) gridWidth, (int) gridHeight, null);
					
						// Zašedne vìž, pokud na ní není dostatek penìz
						if(Tower.towerList[x*TLCountY + y].cost > this.user.player.money) {
							g.setColor(new Color(68, 0, 68, 100));
							g.fillRect(startX + (x*Screen.gridWidth), startY + (y*Screen.gridHeight), (int) gridWidth, (int) gridHeight);
						}
					
					}
					
					// Shop grid
					g.setColor(Color.black);
					g.drawRect(startX + (x*Screen.gridWidth), startY + (y*Screen.gridHeight), Screen.gridWidth, Screen.gridHeight);
				}
			}
			
			// Health and money
				String health = "Health: " + user.player.health;
				String money = "Money: " + user.player.money;
						
				g.drawRect(Screen.gridWidth/2, this.frame.getHeight() - gapY + 5, Screen.gridWidth*4, Screen.gridHeight);
				g.drawString(health, Screen.gridWidth*2 - health.length(), this.frame.getHeight() - gapY + Screen.gridHeight);
						
				g.drawRect(Screen.gridWidth/2, this.frame.getHeight() - gapY + Screen.gridHeight + 5, Screen.gridWidth*4, Screen.gridHeight);
				g.drawString(money, Screen.gridWidth*2 - health.length(), this.frame.getHeight() - gapY + Screen.gridHeight + Screen.gridHeight);
						
				g.drawRect(Screen.gridWidth/2, this.frame.getHeight() - gapY + 2*Screen.gridHeight + 5, Screen.gridWidth*4, Screen.gridHeight);
				g.drawString(money, Screen.gridWidth*2 - health.length(), this.frame.getHeight() - gapY + 2*Screen.gridHeight + Screen.gridHeight);
						
				// Tower scrool list num. 1
				g.drawRect(Screen.gridWidth/2 + Screen.gridWidth*4 + 5, this.frame.getHeight() - gapY + 5, Screen.gridWidth, 3*Screen.gridHeight);
		}
		
		// Umístìní vìže		
		for(int x = 0; x < Screen.gridCountX; x++){
			for(int y = 0; y < Screen.gridCountY; y++) {
				if(towerMap[x][y] != null) {
					int centerX = (int) gridWidth + x*(int) gridWidth - towerMap[x][y].range*(int) gridWidth;
					int centerY = (int) gridHeight + y*(int) gridHeight - towerMap[x][y].range*(int) gridHeight;
					
					g.setColor(Color.gray);
					g.drawOval(centerX, centerY, towerMap[x][y].range*2*(int) gridWidth + (int) gridWidth, towerMap[x][y].range*2*(int) gridHeight + (int) gridHeight);
					g.setColor(new Color(64, 64, 64, 64));
					g.fillOval(centerX, centerY, towerMap[x][y].range*2*(int) gridWidth + (int) gridWidth, towerMap[x][y].range*2*(int) gridHeight + (int) gridHeight);
					g.drawImage(Tower.towerList[towerMap[x][y].id].texture, (int) gridWidth + x*(int)gridWidth, (int) gridHeight + y*(int) gridHeight, (int) gridWidth, (int) gridHeight, null);
				}
			}
		}
		
		// HAND
		if(hand != 0 && Tower.towerList[hand-1] != null) {
			g.drawImage(Tower.towerList[hand-1].texture, this.handXPos - (int) Screen.gridWidth/2, this.handYPos - (int) Screen.gridHeight/2, (int) Screen.gridWidth, (int) Screen.gridHeight, null);
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
		int xPos = (int) (x /gridWidth);
		int yPos = (int) (y/ gridHeight);
		
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
					if(e.getXOnScreen() >= shopGridStartX_Mouse && e.getXOnScreen() <= shopGridStartX_Mouse*(1 + gridWidth)) {
						// Jestli je myš umístìna nìkdy v shopu_Pozice X
						if(e.getYOnScreen() >= shopGridStartY_Mouse && e.getYOnScreen() <= shopGridStartY_Mouse*(1 + gridHeight)) {
							// Tower 1
							if(e.getXOnScreen() >= shopGridStartX_Mouse && e.getXOnScreen() <= shopGridStartX_Mouse+gridWidth && e.getYOnScreen() >= shopGridStartY_Mouse && e.getYOnScreen() <= shopGridStartY_Mouse+gridHeight) {
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