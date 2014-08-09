package core;

/**
 * Hr�� v levelu.
 * 
 * @author Petr
 * 
 */
public class Player {

	int health;
	int money;

	public Player(User user) {
		this.health = user.startingHealth;
		this.money = user.startingMoney;
	}

}
