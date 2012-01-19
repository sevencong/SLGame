package worldMap;

import java.util.ArrayList;

import encounter.PlotEncounter;


public class Map {
	private int partyX;
	private int partyY;
	private int camX;
	private int camY;
	private int zoom;
	private ArrayList<int[]> questMarkers;
	
	
	public char[][] getView() {	//call this method to get all the things that the camera can see. Do something else to align camera with map
		char[][] data = new char[zoom][zoom];
		if (partyX - camX < zoom && partyX - camX > 0 && partyY - camY < zoom && partyY - camY > 0)	//checking party coordinates are within drawing distance
			data[partyX-camX][partyY-camY] = 'p';	//draw party
		for (int[] quest : questMarkers) {	//draw quest markers
			if (quest[0] - camX < zoom && quest[0] - camX > 0 && quest[1] - camY < zoom && quest[1] - camY > 0) {
				if (data[quest[0]-camX][quest[1]-camX] == 'p') {
					data[quest[0]-camX][quest[1]-camX] = 'r';
				} else {
					data[quest[0]-camX][quest[1]-camX] = 'q';
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
}
