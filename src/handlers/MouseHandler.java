package handlers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import core.Screen;

public class MouseHandler implements MouseListener, MouseMotionListener {
	private Screen screen;
	private Screen.MouseHeld mouseHeld;
	
	public MouseHandler(Screen screen) {
		this.screen = screen;
		this.mouseHeld = this.screen.new MouseHeld();
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		this.mouseHeld.mouseDown(e);
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	// MOUSE MOTION LISTENER
	public void mouseDragged(MouseEvent e) {
		this.mouseHeld.mouseMoved(e);
	}

	public void mouseMoved(MouseEvent e) {
		this.mouseHeld.mouseMoved(e);
	}

}
