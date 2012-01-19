package encounter;

import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
	
	public ArrayList<Monster> getMonsters() {
		return monsters;
	}
}
