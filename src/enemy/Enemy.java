package enemy;

import java.awt.Image;

import javax.swing.ImageIcon;

import enemy.types.EnemySlime;

import lib.EnemyVar;
import lib.PathVar;

/**
 * Rodiè všech nepøátel.
 * 
 * @author Petr
 * 
 */
public class Enemy {
	public static final Enemy[] enemyList = new Enemy[200];

	public String textureFile;
	public Image texture;

	public int id;
	public int reward;
	public int health;
	public int damage;
	public double speed;
	public double attackSpeed;

	public static final Enemy slime = new EnemySlime(EnemyVar.slimeId, EnemyVar.basicReward, EnemyVar.basicHealth, EnemyVar.basicDamage, EnemyVar.basicSpeed * 5, EnemyVar.basicAttackSpeed).getTextureFile("EnemySlime");

	public Enemy(int id, int reward, int health, int damage, double speed, double attackSpeed) {
		if (enemyList[id] != null) {
			System.out.println("[Enemy Initialization] Two enemies with same id.");
		} else {
			enemyList[id] = this;
			this.id = id;
			this.reward = reward;
			this.health = health;
			this.damage = damage;
			this.speed = speed;
			this.attackSpeed = attackSpeed;
		}
	}

	public Enemy getTextureFile(String textureFile) {
		this.textureFile = textureFile;
		if (!this.textureFile.contains(".png"))
			this.textureFile += ".png";

		this.texture = new ImageIcon(PathVar.enemyPath + this.textureFile).getImage();

		return this;
	}

	public static void startup() {

	}

}
