package core;

public class Player {
	
	int health;
	int money;

	public Player(User user) {
		this.health = user.startingHealth;
		this.money = user.startingMoney;
	}

}
