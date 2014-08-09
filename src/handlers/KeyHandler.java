package handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import core.Screen;
import core.Screen.KeyTyped;

/* T¯Ìda ziöùujÌcÌ maËk·nÌ kl·ves. */
public class KeyHandler implements KeyListener {

	private Screen screen;
	private KeyTyped keyTyped;

	public KeyHandler(Screen screen) {
		this.screen = screen;
		this.keyTyped = this.screen.new KeyTyped();
	}

	public void keyTyped(KeyEvent e) {

	}

	@SuppressWarnings("static-access")
	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();

		if (keycode == e.VK_ESCAPE) {
			this.keyTyped.keyESC();
		}

		if (keycode == e.VK_ENTER) {
			this.keyTyped.keyENTER();
		}

		if (keycode == e.VK_SPACE) {
			this.keyTyped.keySPACE();
		}

		if (keycode == e.VK_BACK_SPACE) {
			this.keyTyped.keyBACKSPACE();
		}
	}

	public void keyReleased(KeyEvent e) {

	}

}
