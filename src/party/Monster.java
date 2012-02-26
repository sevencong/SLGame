package party;

import java.util.ArrayList;
import java.util.Arrays;

import org.w3c.dom.Node;
import org.xml.sax.Attributes;

public class Monster {
	
	private String name;
	private int hits;
	private String immunity;
	ArrayList<String> calls;
	

	public Monster(Node item) {
		// TODO Auto-generated constructor stub
	}

	public Monster() {
		//placeholder constructor
	}
	
	public Monster(Attributes atts) {
		hits = Integer.parseInt(atts.getValue("hits"));
		immunity = atts.getValue("immunity");
		calls = new ArrayList<String>(Arrays.asList(atts.getValue("calls").split("|")));
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
