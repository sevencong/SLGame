package worldMap;

import java.util.ArrayList;

import encounter.PlotEncounter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Map {
	private int partyX;
	private int partyY;
	private int camX;
	private int camY;
	private int zoom;
	private ArrayList<int[]> questMarkers;
	private int[] size;



	public Map(int x, int y, ArrayList<int[]> coords) {
		partyX = 0;
		partyY = 0;
		camY = 0;
		camY = 0;
		zoom = Math.min(x, y);
		size = new int[2];
		size[0] = x;
		size[1] = y;
                questMarkers = new ArrayList<int[]>();
	}

	public Map() {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	public char[][] getView() {	//call this method to get all the things that the camera can see. Do something else to align camera with map

		char[][] data = new char[zoom][zoom];
		if (partyX - camX < zoom && partyX - camX >= 0 && partyY - camY < zoom && partyY - camY >= 0) {	//checking party coordinates are within drawing distance
			data[partyX-camX][partyY-camY] = 'p';	//draw party
                        //System.out.println("adding party");
                }
		for (int[] quest : questMarkers) {	//draw quest markers
			if (quest[0] - camX < zoom && quest[0] - camX >= 0 && quest[1] - camY < zoom && quest[1] - camY >= 0) {
				if (data[quest[0]-camX][quest[1]-camX] == 'p') {
					data[quest[0]-camX][quest[1]-camX] = 'r';
                                       // System.out.println("adding both");
                                        
				} else {
					data[quest[0]-camX][quest[1]-camX] = 'q';
                                       //  System.out.println("adding both");
				}
			}
		}
		return data;
	}

	public int[] getCamCoords() {
		int[] ret = {camX, camY}; 
		return ret;
	}

	public void movePartyTo(int X, int Y) {
		partyX = X;
		partyY = Y;
	}

	public void moveParty(double dir, int len) {
		int xMove = len * (int) Math.round(Math.cos(dir));
		int yMove = len * (int) Math.round(Math.sin(dir));

		partyX += xMove;
		partyY += yMove;
	}
        
        public void moveParty(int x, int y) {
           // System.out.println("Moving party " + Integer.toString(x) + ", " + Integer.toString(y));
            partyX += x;
            partyY += y;
        }
}
