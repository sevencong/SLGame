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
			case 'm': ((CombatEncounter) store).addMonster(new Monster()); break;
			case 'l': ((PlotEncounter) store).addLoot(new Item()); break;
			case 'a': chars = "a"; break;
			case 'd': chars = "d"; break;
			case 'p': store = new TalkEncounter(atts); break;
			case 't': chars = "t"; break;
			case 'M': store = new Marker(atts); chars = "M"; break;
			case 'b': chars = "b"; break;
			case 'j': store = new JournalEntry(atts); chars = "j"; break;
			default: break;
		}
	}
	
	public void characters (char[] array, int start, int length) {
		String blah = "";
		for (int a = start; a < start+length; a++) {
			blah += array[a];
		}
		chars = chars + blah;
	}
	
	public void endElement (String uri, String name, String qName) throws SAXException{
		switch (name.charAt(0)) {
		case 'q': break;
		case 'c': encounters.add((CombatEncounter) store); break;
		case 'm': ((CombatEncounter) store).addMonster(new Monster()); break;
		case 'l': ((PlotEncounter) store).addLoot(new Item()); break;
		case 'a': if (chars.charAt(0) == 'a') {((PlotEncounter) store).activates(chars.substring(1));} else {throw new SAXException("Invalid quest file at " + qName);} break;
		case 'd': if (chars.charAt(0) == 'd') {((PlotEncounter)store).deactivates(chars.substring(1));} else {throw new SAXException("Invalid quest file at " + qName);} break;
		case 'p': encounters.add((TalkEncounter) store); break;
		case 't': if (chars.charAt(0) == 't') {((TalkEncounter) store).addText(chars.substring(1));} else {throw new SAXException("Invalid quest file at " + qName);} break;
		case 'M': if (chars.charAt(0) == 'M') {((Marker) store).setQuestName(chars.substring(1));} else {throw new SAXException("Invalid quest file at " + name);} markers.add((Marker) store); break;
		case 'b': if (chars.charAt(0) == 'b') {questBits.put(chars.substring(1), false);} else {throw new SAXException("Invalid quest file at " + name);} break;
		case 'j': if (chars.charAt(0) == 'j') {((JournalEntry) store).setText(chars); entries.add((JournalEntry) store);} else {throw new SAXException("Invalid quest file at " + name);} break; 
		default: throw new SAXException("Invalid quest file at " + name);
		}
	}
}
