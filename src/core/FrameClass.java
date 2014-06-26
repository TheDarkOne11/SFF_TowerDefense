package core;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/* Tower Defense podle uživatele ScratchForFun.*/
public class FrameClass extends JFrame {
	
	public static void main(String[] args) {
		new FrameClass();
	}
	
	public FrameClass() {
		this.setTitle("SFF's Castle Tower Defense");
		this.setSize(800, 600);
		this.setVisible(true);
		this.addWindowListener(new CloseWindow_WindowAdapter());
		
		Screen screen = new Screen(this);
		this.add(screen);
	}
	
	class CloseWindow_WindowAdapter extends WindowAdapter {

		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
		
	}
}
