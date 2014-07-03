package core;

/**
 * ��et, p�es kter� se zap�n� aplikace.
 * @author Petr
 *
 */
public class User {
	private Screen screen;
	Player player;
	int startingMoney = 300;
	int startingHealth = 100;

	public User(Screen screen) {
		this.screen = screen;
		
		this.screen.gameState = 0;	//Sets scene to main menu
	}
	
	public void createPlayer() {
		this.player = new Player(this);
	}

}
