package enemy;

import java.awt.Image;

import javax.swing.ImageIcon;

import lib.EnemyVar;
import lib.PathVar;
import enemy.types.EnemySlime;

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
	public int points;	// How much is this enemy worth for spawning. Level must be <= to spawn.

	public static final Enemy slime = new EnemySlime(EnemyVar.slimeId, EnemyVar.basicPoint, EnemyVar.basicReward, EnemyVar.basicHealth, EnemyVar.basicDamage, EnemyVar.basicSpeed*2, EnemyVar.basicAttackSpeed).getTextureFile("EnemySlime");
	public static final Enemy strong = new EnemySlime(EnemyVar.strongId, EnemyVar.basicPoint, EnemyVar.basicReward*2, EnemyVar.basicHealth*4, (int) (EnemyVar.basicDamage*1.5), EnemyVar.basicSpeed/2, EnemyVar.basicAttackSpeed/2).getTextureFile("EnemyStrong");

	public Enemy(int id, int point, int reward, int health, int damage, double speed, double attackSpeed) {
		if (enemyList[id] != null) {
			System.out.println("[Enemy Initialization] Two enemies with same id.");
		} else {
			enemyList[id] = this;
			this.id = id;
			this.points = point;
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
