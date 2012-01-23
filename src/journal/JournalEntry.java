package journal;

import org.w3c.dom.Node;
import org.xml.sax.Attributes;

public class JournalEntry {
	
	private String text;
	private String activatedBy;
	private String completedBy;
	private String failedBy;
	private String questName;
	private int order;

	public JournalEntry(Attributes atts) {
		order = Integer.parseInt(atts.getValue("order"));
		questName = atts.getValue("quest");
		failedBy = atts.getValue("failed");
		completedBy = atts.getValue("completed");
		activatedBy = atts.getValue("actBy");
	}

	public void setText(String chars) {
		text = chars;
	}

}
