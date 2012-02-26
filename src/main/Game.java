package main;
import gui.GuiOne;


public class Game {

	private static Thread run;
	private static GuiOne gui;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * draw gui
		 * take keyboard input
		 * control things
		 * redraw gui
		 * 
		 */
		gui = new GuiOne();
		run = new Thread(gui);
		gui.setVisible(true);
                try {
                    run.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
		while (true) {
                    try {
			gui.refresh();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
		}
	}

}
