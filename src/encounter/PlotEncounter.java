package encounter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import party.Item;
import party.Monster;
import quest.InvalidQuestFileException;

public class PlotEncounter {
	private int x;
	private int y;
	private String id;
	private String activatedBy;
	private ArrayList<Item> loot;
	private ArrayList<String> activates;
	private ArrayList<String> deactivates;
	
	public PlotEncounter (int X, int Y, String ID, String act) {
		x = X;
		y = Y;
		id = ID;
		activatedBy = act;
	}
	
	public PlotEncounter (Node plot) throws InvalidQuestFileException {
		NamedNodeMap atts = plot.getAttributes();
		
		x = Integer.parseInt(atts.getNamedItem("x").getNodeValue());
		y = Integer.parseInt(atts.getNamedItem("y").getNodeValue());
		id = plot.getNodeName();
		activatedBy = atts.getNamedItem("actBy").getNodeValue();
		
		
		NodeList list = plot.getChildNodes();
		Node current;
		String name;
		for (int a = 0; a < list.getLength(); a++) {
			current = list.item(a);
			name = current.getNodeName();
			switch (name.charAt(0)) {
				case 'l':	loot.add(new Item(current.getNodeValue())); break;
				case 'a':	activates.add(current.getNodeValue()); break;
				case 'd':	deactivates.add(current.getNodeValue()); break;
				case 't':	break;
				default: throw new InvalidQuestFileException("Invalid plot encounter " + id);
			}
		}
	}
	
	public boolean isNear (int X, int Y, HashMap<String, Boolean> bits) {
			return (X < x + 10 && X > x - 10 && Y > y + 10 && Y < y - 10) && !checkActive(bits);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public String getId() {
		return id;
	}
	
	public Boolean checkActive (HashMap<String, Boolean> bits) {
		Boolean current = null;
		
		int nextAnd = activatedBy.indexOf("&");
		int nextOr = activatedBy.indexOf("|");
		String[] spl;
		if (nextAnd > nextOr) {
			spl = activatedBy.split("|", 1);
			current = bits.get(spl[0]);
		} else {
			spl = activatedBy.split("&", 1);
			current = bits.get(spl[0]);
		}
		while(nextAnd != -1 || nextOr != -1) {
			spl = spl[1].substring(1).split(nextAnd > nextOr && nextAnd != -1 ? "&" : "|");
			if (spl[0].charAt(0) == '&') {
				current = current & bits.get(spl[0].substring(1));
			} else if (spl[1].charAt(0) == '|') {
				current = current | bits.get(spl[0].substring(1));
			} else {
				return null;
			}
			nextAnd = spl[1].substring(1).indexOf("&");
			nextOr = spl[1].substring(1).indexOf("|");
		}
		
		return current;
	}
}