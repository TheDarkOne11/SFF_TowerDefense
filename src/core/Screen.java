package core;

import handlers.KeyHandler;
import handlers.MouseHandler;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import level.Level;
import level.LevelFile;
import levelMaker.LevelMaker;
import lib.PathVar;
import tower.Tower;
import enemy.Enemy;
import enemy.EnemyAI;
import enemy.EnemyMove;
import enemy.Wave;

//TODO Epizoda 30

/** Hlavn� t��da, kter� celou hru povede. */
public class Screen extends JPanel implements Runnable {

	Thread thread = new Thread(this);
	FrameClass frame;
	private int fps = 0;
	public static int updatesPerSec = 25;
	boolean running;

	// Main Grid
	public static int gridCountX;
	public static int gridCountY;
	/* Velikost x a y je stejn� */
	public static double gridSize = 1;

	// Shop Grid
	public static double shopGridStartX, shopGridStartY;
	public static int shopGridCountX = 20;
	public static int shopGridCountY = 2;

	// Mouse
	/**
	 * 1 = v� v ruce
	 */
	public int handTower = 0;
	public static int handTerrain = -1;
	public static int handYPos = 0;
	public static int handXPos = 0;

	// Level maker
	LevelMaker levelMaker;

	// Map and Levels
	Level level;
	LevelFile levelFile;

	// Ulo�eny v�echny hodnoty pozic gridu z LevelFile
	public static int[][] map;

	// Ulo�eny v�echny hodnoty pozic v��
	public Tower[][] towerMap;
	public static LinkedList<Image> terrain = new LinkedList<Image>();

	// Enemy
	/** Kolik nep��tel na map� je na map� v jednu chv�li. */
	public EnemyMove[] enemyMap = new EnemyMove[200];
	private int enemies = 0;
	public Wave wave;
	EnemyAI enemyAI;

	/**
	 * 0. Menu 
	 * <p>
	 * 1. In Game 
	 * <p>
	 * 2. Level maker
	 * */
	public int gameState;

	/** Account */
	private User user;

	public Screen(FrameClass frame) {
		thread.start(); // Za�ne run()
		this.frame = frame;
		this.frame.addKeyListener(new KeyHandler(this));
		this.frame.addMouseListener(new MouseHandler(this));
		this.frame.addMouseMotionListener(new MouseHandler(this));
	}

	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, this.frame.getWidth(), this.frame.getHeight());
		
		//TODO Vytvo�it menu s tla��tky. Implementovat na��t�n� a ukl�d�n� level� p�i spu�t�n�.
		if (gameState == 0) {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, this.frame.getWidth(), this.frame.getHeight());
		} else if (gameState == 1) {

			// Background
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, this.frame.getWidth(), this.frame.getHeight());

			drawGameGrid(g);
			drawPlayerGrid(g);

			// Enemy
			for (int i = 0; i < this.enemyMap.length; i++) {
				if (this.enemyMap[i] != null) {
					g.drawImage(this.enemyMap[i].enemy.texture, (int) (enemyMap[i].xPos + Screen.gridSize), (int) (enemyMap[i].yPos + Screen.gridSize), (int) Screen.gridSize, (int) Screen.gridSize, null);
				}
			}

			// Um�st�n� v�e
			for (int x = 0; x < Screen.gridCountX; x++) {
				for (int y = 0; y < Screen.gridCountY; y++) {
					if (towerMap[x][y] != null) {
						int centerX = (int) gridSize + x * (int) gridSize - towerMap[x][y].range * (int) gridSize;
						int centerY = (int) gridSize + y * (int) gridSize - towerMap[x][y].range * (int) gridSize;

						g.setColor(Color.gray);
						g.drawOval(centerX, centerY, towerMap[x][y].range * 2 * (int) gridSize + (int) gridSize, towerMap[x][y].range * 2 * (int) gridSize + (int) gridSize);
						g.setColor(new Color(64, 64, 64, 64));
						g.fillOval(centerX, centerY, towerMap[x][y].range * 2 * (int) gridSize + (int) gridSize, towerMap[x][y].range * 2 * (int) gridSize + (int) gridSize);
						g.drawImage(Tower.towerList[towerMap[x][y].id].texture, (int) gridSize + x * (int) gridSize, (int) gridSize + y * (int) gridSize, (int) gridSize, (int) gridSize, null);
					}
				}
			}

			// Tower in hand
			if (handTower != 0 && Tower.towerList[handTower - 1] != null) {
				drawObjectInHand(g, Tower.towerList[handTower - 1].texture);
			}

		} else if (gameState == 2) {
			double height = this.getHeight() / (LevelMaker.gridCountY + Screen.shopGridCountY + 2);
			double width = this.getWidth() / (LevelMaker.gridCountX + 6);
			Screen.gridSize = height < width ? height : width;

			// Background
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, this.frame.getWidth(), this.frame.getHeight());

			levelMaker.drawGameGrid(g);
			levelMaker.drawButtons(g);
			levelMaker.drawTerrainMenu(g);
			levelMaker.drawMarkedGameGrid(g);
			drawPlayerGrid(g);
			
			// Terrain in hand
			if (handTerrain != -1 && Screen.terrain.get(handTerrain) != null) {
				drawObjectInHand(g, Screen.terrain.get(handTerrain));
			}

		}

		// FPS
		g.setColor(Color.black);
		g.drawString(fps + "", 10, 10);
	}

	public void drawGameGrid(Graphics g) {
		g.setColor(Color.black);
		for (int x = 0; x < gridCountX; x++) {
			for (int y = 0; y < gridCountY; y++) {
				g.drawImage(terrain.get(map[x][y]), (int) (Screen.gridSize + (x * Screen.gridSize)), (int) (Screen.gridSize + (y * Screen.gridSize)), (int) Screen.gridSize, (int) Screen.gridSize, null);
				g.drawRect((int) (Screen.gridSize + (x * Screen.gridSize)), (int) (Screen.gridSize + (y * Screen.gridSize)), (int) Screen.gridSize, (int) Screen.gridSize);
			}
		}
	}

	public void drawPlayerGrid(Graphics g) {
		// Towers in shop grid
		Screen.shopGridStartX = Screen.gridSize * 6.5;
		Screen.shopGridStartY = Screen.gridSize * (Screen.gridCountY + 1.25);

		for (int x = 0; x < Screen.shopGridCountX; x++) {
			for (int y = 0; y < Screen.shopGridCountY; y++) {
				if (Tower.towerList[x * Screen.shopGridCountY + y] != null) {
					// Tower Image
					g.drawImage(Tower.towerList[x * Screen.shopGridCountY + y].texture, (int) (Screen.shopGridStartX + (x * Screen.gridSize)), (int) (Screen.shopGridStartY + (y * Screen.gridSize)), (int) gridSize, (int) gridSize, null);

					// Za�edne v�, pokud na n� nen� dostatek pen�z
					if (Tower.towerList[x * Screen.shopGridCountY + y].cost > this.user.player.money) {
						g.setColor(new Color(68, 0, 68, 100)); // Grey
						g.fillRect((int) (Screen.shopGridStartX + (x * Screen.gridSize)), (int) (Screen.shopGridStartY + (y * Screen.gridSize)), (int) gridSize, (int) gridSize);
					}

				}

				// Shop grid
				g.setColor(Color.black);
				g.drawRect((int) (Screen.shopGridStartX + (x * Screen.gridSize)), (int) (Screen.shopGridStartY + (y * Screen.gridSize)), (int) Screen.gridSize, (int) Screen.gridSize);
			}
		}

		// Health and money
		String health = "Health: " + user.player.health;
		String money = "Money: " + user.player.money;

		g.drawRect((int) Screen.gridSize, (int) (Screen.gridSize * (Screen.gridCountY + 1.25)), (int) Screen.gridSize * 4, (int) Screen.gridSize);
		g.drawString(health, (int) (Screen.gridSize * 3 - g.getFontMetrics().stringWidth(health) / 2), (int) (Screen.gridSize * (Screen.gridCountY + 1.75) + g.getFontMetrics().getHeight() / 4));

		g.drawRect((int) Screen.gridSize, (int) (Screen.gridSize * (Screen.gridCountY + 2.25)), (int) Screen.gridSize * 4, (int) Screen.gridSize);
		g.drawString(money, (int) (Screen.gridSize * 3 - g.getFontMetrics().stringWidth(money) / 2), (int) (Screen.gridSize * (Screen.gridCountY + 2.75) + g.getFontMetrics().getHeight() / 4));

		// Tower scrool list num. 1
		g.drawRect((int) (Screen.gridSize * 5.25), (int) (Screen.gridSize * (Screen.gridCountY + 1.25)), (int) Screen.gridSize, (int) Screen.gridSize * Screen.shopGridCountY);

	}
	
	public static void drawObjectInHand(Graphics g, Image texture) {
		g.drawImage(texture, handXPos - (int) Screen.gridSize / 2, handYPos - (int) Screen.gridSize / 2, (int) Screen.gridSize, (int) Screen.gridSize, null);
	}

	/**
	 * Na�te z�kladn� v�ci.
	 * 
	 * @throws IOException
	 */
	private void loadGame() {
		user = new User(this);
		levelFile = new LevelFile();
		wave = new Wave(this);
		BufferedImage terrainBufferedImage;

		try {
			terrainBufferedImage = ImageIO.read(new File(PathVar.terrainSprite));

			// P�e�te terrain soubor, z�sk� typy prost�ed�.
			terrainReading: for (int y = 0; y < 10; y++) {
				for (int x = 0; x < 10; x++) { // terrain.png je 250*250 pixel�, jeden typ krajiny je 25*25 pixel�
					Image tmp = new ImageIcon(PathVar.terrainSprite).getImage();
					terrain.addLast(createImage(new FilteredImageSource(tmp.getSource(), new CropImageFilter(x * 25, y * 25, 25, 25)))); // 25
					
					// Checks if image is empty in 4 points
					if (isImageEmpty(terrainBufferedImage, x * 25, y * 25) && isImageEmpty(terrainBufferedImage, x * 25 + 9, y * 25 + 9) && isImageEmpty(terrainBufferedImage, x * 25 + 12, y * 25 + 12) && isImageEmpty(terrainBufferedImage, x * 25 + 19, y * 25 + 19)) {
						terrain.remove(x + y * 10);
						break terrainReading;
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		running = true;
	}

	private boolean isImageEmpty(BufferedImage img, int xPos, int yPos) {
		Color emptyColor = new Color(255, 255, 255, 0);
		Color currentColor = new Color(img.getRGB(xPos, yPos), true);

		return emptyColor.equals(currentColor);
	}

	/**
	 * Za�ne samotn� hran�, na�te mapu, v�e, nep��tele atd.
	 * 
	 * @param user
	 * @param levelName
	 */
	private void startGame(User user, String levelName) {
		this.level = levelFile.getLevel(levelName);
		this.level.FindSpawnPoint();
		user.createPlayer();
		map = new int[Screen.gridCountX][Screen.gridCountY];
		this.towerMap = new Tower[Screen.gridCountX][Screen.gridCountY];
		Screen.map = this.level.map;
		this.enemyAI = new EnemyAI(this.level);

		double height = this.getHeight() / (Screen.gridCountY + Screen.shopGridCountY + 2);
		double width = this.getWidth() / (Screen.gridCountX + 5);
		Screen.gridSize = height < width ? height : width;

		this.gameState = 1; // Level 1
		this.wave.waveNumber = 0;
	}

	private void startLevelMaker(User user) {
		map = new int[100][100];
		user.createPlayer();
		double height = this.getHeight() / (LevelMaker.gridCountY + Screen.shopGridCountY + 2);
		double width = this.getWidth() / (LevelMaker.gridCountX + 6);
		Screen.gridSize = height < width ? height : width;

		levelMaker = new LevelMaker(this);
		this.gameState = 2;
	}

	public void run() {
		gameState = 0;

		long lastFrame = System.currentTimeMillis();
		int frames = 0;

		int skipTics = 1000 / updatesPerSec;
		long nextGameTick = System.currentTimeMillis();
		long sleepTime = 0;

		loadGame();

		while (running) {
			repaint();
			frames++;
			nextGameTick += skipTics;
			sleepTime = nextGameTick - System.currentTimeMillis();

			updateEnemy();
			updateTower();

			// Update ka�dou vte�inu
			if (System.currentTimeMillis() - 1000 >= lastFrame) {
				fps = frames;
				lastFrame = System.currentTimeMillis();
				frames = 0;
			}

			if (sleepTime >= 0)
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

		}
		System.exit(0);
	}
	
	/**
	 * Update pohybu a spawnov�n� nep��tel.
	 */
	private void updateEnemy() {
		// Moving
		for (int i = 0; i < this.enemyMap.length; i++) {
			if (this.enemyMap[i] != null) {
				if (!this.enemyMap[i].attack) {
					EnemyAI.enemyAIMove.move(enemyMap[i]);
				}
	
				enemyMap[i] = enemyMap[i].update();
			}
		}
	
		// Spawning
		if (wave.isEnemySpawning) {
			wave.spawnEnemies();
		}
	}

	private void updateTower() {
		for(int y = 0; y < Screen.gridCountY; y++) {
			for(int x = 0; x < Screen.gridCountX; x++) {
				try {
					if(this.towerMap[x][y] != null) {
						towerAttack(x, y);
					}
				
				// Po��d mi vyhazovalo vyj�mku, pokud jsem v konstruktoru v Tower p�etypoval AttackTimeMax a DelayTimeMax
				// na double. Toto to vy�e�ilo, ale vyj�mka se st�le vyhazuje.
				} catch(NullPointerException e) {
					System.err.println("[Screen.updateTower] NullPointerException in towerMap " +
										"after changing Tower constructor. ");
				}
			}
		}
	}
	
	/** Attacks enemy. */
	public void towerAttack(int x, int y) {
		if(this.towerMap[x][y].target == null) {
			// Find target
			if(this.towerMap[x][y].attackDelay >= this.towerMap[x][y].attackDelayMax) {
				EnemyMove currentEnemy = this.towerMap[x][y].targetEnemyNew(this.towerMap[x][y].calculateEnemyNew(this.enemyMap, x, y));

				if(currentEnemy != null) {
					currentEnemy.health -= this.towerMap[x][y].damage;
					
					this.towerMap[x][y].target = currentEnemy;
					this.towerMap[x][y].attackTime = 0;
					this.towerMap[x][y].attackDelay = 0;
					
					System.out.println("[Screen] Tower Attacking.");
				}
			} else {
				this.towerMap[x][y].attackDelay++;
			}
		} else {
			if(this.towerMap[x][y].attackTime < this.towerMap[x][y].attackTimeMax) {
				this.towerMap[x][y].attackTime++;
			} else {
				this.towerMap[x][y].target = null;
			}
		}
	}

	/**
	 * Polo�en� v�e po n�kupu.
	 * 
	 * @param x
	 * @param y
	 */
	public void placeTower(int x, int y) {
		int xPos = (int) (x / gridSize);
		int yPos = (int) (y / gridSize);

		if (xPos > 0 && xPos <= Screen.gridCountX && yPos > 0 && yPos <= Screen.gridCountY) {
			xPos--;
			yPos--;

			if (towerMap[xPos][yPos] == null && map[xPos][yPos] == 0) { // Pokud je m�sto pr�zdn�
				user.player.money -= Tower.towerList[handTower - 1].cost;
				towerMap[xPos][yPos] = (Tower) Tower.towerList[handTower - 1].clone();
			}
		}
	}

	/**
	 * Zaregistruje 1 nep��tele do enemyMap.
	 */
	public void spawnEnemy() {
		for (int i = 0; i < this.enemyMap.length; i++) {
			if (this.enemyMap[i] == null) {
				this.enemyMap[i] = new EnemyMove(Enemy.enemyList[0], level.spawnPoint);
				break;
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
			if (gameState == 1) {
				if (mouseDown && handTower == 0) {
					// Jestli je my� um�st�na n�kdy v shopu_Pozice X
					if (e.getXOnScreen() >= shopGridStartX && e.getXOnScreen() <= shopGridStartX * (1 + gridSize)) {
						// Jestli je my� um�st�na n�kdy v shopu_Pozice X
						if (e.getYOnScreen() >= shopGridStartY && e.getYOnScreen() <= shopGridStartY * (1 + gridSize)) {
							for(int i = 0; i < Tower.towerList.length; i++) {
								if (e.getXOnScreen() >= shopGridStartX + (int) (i/2)*gridSize && e.getXOnScreen() <= shopGridStartX + gridSize + (int) (i/2)*gridSize && e.getYOnScreen() >= shopGridStartY + (int) (i%2)*gridSize && e.getYOnScreen() <= shopGridStartY + gridSize + (int) (i%2)*gridSize) {
									if (user.player.money >= Tower.towerList[0].cost) {
										handTower = i+1;
									}
								}
							}
						}
					}
				}
			} else if (gameState == 2) {
				// GridCount buttons
				levelMaker.isButtonClicked(e);
			}
		}

		public void mouseDown(MouseEvent e) {
			mouseDown = true;

			if (handTower != 0) {
				placeTower(e.getXOnScreen(), e.getYOnScreen());
				handTower = 0;
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
			startLevelMaker(user);
		}

	}

}