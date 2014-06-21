package enemies;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Enemy {
	public static final Enemy[] enemyList = new Enemy[200];
	
	//TODO V budoucnu ud�lat n�co jako basicPrice, basicHealth atd. Hod� se, a� budu m�t v�ce typ� nep��tel
	public static final Enemy slime = new EnemySlime(0, 5, 2, 10, 3, 4).getTextureFile("EnemySlime");
	
	public String textureFile;
	public Image texture;
	
	public int id;
	public int price;
	public int health;
	public int damage;
	public double speed;
	public double attackSpeed;
	
	public Enemy(int id, int price, int health, int damage, double speed, double attackSpeed) {
		if(enemyList[id] != null) {
			System.out.println("[Enemy Initialization] Two enemies with same id.");
		} else {
			enemyList[id] = this;
			this.id = id;
			this.price = price;
			this.health = health;
			this.damage = damage;
			this.speed = speed;
			this.attackSpeed = attackSpeed;
		}
	}
	
	public Enemy getTextureFile(String textureFile) {
		this.textureFile = textureFile;
		if(!this.textureFile.contains(".png")) this.textureFile += ".png";
		
		this.texture = new ImageIcon("res/enemy/" + this.textureFile).getImage();
		
		return this;
	}
	
	public static void startup() {
		
	}
	
}
