package encounter;

import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;

import party.Monster;
import quest.InvalidQuestFileException;

public class CombatEncounter extends PlotEncounter{

	private ArrayList<Monster> monsters;
	
	public CombatEncounter (Node plot) throws InvalidQuestFileException {
		super(plot);
		NodeList list = plot.getChildNodes();
		for (int a = 0; a < list.getLength(); a++) {
			if (list.item(a).getNodeName().equals("monster")) {
				monsters.add(new Monster(list.item(a)));
			}
		}
	}
	
	public CombatEncounter () {
		super();
	}
	
	public CombatEncounter (Attributes atts) {
		super(Integer.parseInt(atts.getValue("x")), Integer.parseInt(atts.getValue("y")), atts.getValue("actBy"));
	}
	
	public ArrayList<Monster> getMonsters() {
		return monsters;
	}
	
	public void addMonster (Monster mon) {
		monsters.add(mon);
	}
}
