package worldMap;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;

public class Marker {
	private int x;
	private int y;
	private String activatedBy;
	private String questName;
	
	public Marker (int xx, int yy, String quest, String act) {
		x = xx;
		y = yy;
		questName = quest;
		activatedBy = act;
	}
	
	public Marker (Node marker) {
		NamedNodeMap atts = marker.getAttributes();
		
		x = Integer.parseInt(atts.getNamedItem("x").getNodeValue());
		y = Integer.parseInt(atts.getNamedItem("y").getNodeValue());
		questName = marker.getNodeName();
		activatedBy = atts.getNamedItem("actBy").getNodeValue();
	}
	
	public Marker(Attributes atts) {
		// TODO Auto-generated constructor stub
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
