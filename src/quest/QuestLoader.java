package quest;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.HashMap;

import journal.JournalEntry;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import party.Item;
import party.Monster;

import worldMap.Marker;

import encounter.CombatEncounter;
import encounter.PlotEncounter;
import encounter.TalkEncounter;

public class QuestLoader extends DefaultHandler {
	
	public ArrayList<PlotEncounter> encounters;
	public HashMap<String, Boolean> questBits;
	public ArrayList<Marker> markers;
	public ArrayList<JournalEntry> entries;
	public Object store;
	public String chars;

	@SuppressWarnings("unused")
	public QuestLoader () throws IOException, InvalidQuestFileException, SAXException {
		super();
	}
	
	public void startElement (String uri, String name, String qName, Attributes atts) {
		switch (name.charAt(0)) {
			case 'q': break;
			case 'c': store = new CombatEncounter(atts); break;
			//case 'm': ((CombatEncounter) store).addMonster(new Monster()); break;
			//case 'l': ((PlotEncounter) store).addLoot(new Item()); break;
			case 'a': chars = "a"; break;
			case 'd': chars = "d"; break;
			case 'p': store = new TalkEncounter(atts); break;
			case 't': chars = "t"; break;
			case 'M': store = new Marker(atts); chars = "M"; break;
			case 'b': chars = "b"; break;
			default: break;
		}
	}
}
