package core;

/**
 * Hr·Ë v levelu.
 * 
 * @author Petr
 * 
 */
public class Player {

	public int health;
	public int money;

	public Player(User user) {
		this.health = user.startingHealth;
		this.money = user.startingMoney;
	}

}
