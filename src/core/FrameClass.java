package core;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Okno a posluchaèe.
 * 
 * @author Petr
 * 
 */
public class FrameClass extends JFrame {

	public static void main(String[] args) {
		// Ochrana proti obèasné bílé obrazovce
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new FrameClass();
			}
		});
	}

	public FrameClass() {
		this.setTitle("SFF's Castle Tower Defense");
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setVisible(true);
		this.addWindowListener(new CloseWindow_WindowAdapter());

		Screen screen = new Screen(this);

		this.add(screen);
	}

	public int getWidth() {
		return (int) (super.getWidth() + this.getPreferredSize().getWidth());
	}

	public int getHeight() {
		return (int) (super.getHeight() + this.getPreferredSize().getHeight());
	}

	class CloseWindow_WindowAdapter extends WindowAdapter {

		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}

	}
}
