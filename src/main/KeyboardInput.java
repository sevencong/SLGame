package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import worldMap.Map;

public class KeyboardInput implements KeyListener {
	
	private boolean[] direction;

	public KeyboardInput() {
		// TODO Auto-generated constructor stub
		direction = new boolean[4];
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
		case KeyEvent.VK_DOWN:	direction[0] = true; break;
		case KeyEvent.VK_LEFT:	direction[1] = true; break;
		case KeyEvent.VK_RIGHT:	direction[2] = true; break;
		case KeyEvent.VK_UP:	direction[3] = true; break;
		default: break;
		}
 //               System.out.println("Key pressed, moving " + e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
		case KeyEvent.VK_DOWN:	direction[0] = false; break;
		case KeyEvent.VK_LEFT:	direction[1] = false; break;
		case KeyEvent.VK_RIGHT:	direction[2] = false; break;
		case KeyEvent.VK_UP:	direction[3] = false; break;
		default: break;
		}
	}
	
	public boolean[] getDirection() {
		return direction;
	}

}
