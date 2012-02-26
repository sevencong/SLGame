package quest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import journal.JournalEntry;
import worldMap.Marker;
import encounter.CombatEncounter;
import encounter.PlotEncounter;
import encounter.TalkEncounter;
import party.Item;
import party.Monster;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class QuestLoader extends DefaultHandler {
	
	public ArrayList<PlotEncounter> encounters;
	public HashMap<String, Boolean> questBits;
	public ArrayList<Marker> markers;
	public ArrayList<JournalEntry> entries;
	public Object store;
	public Object subStore;
	public String chars;

	@SuppressWarnings("unused")
	public QuestLoader () throws IOException, InvalidQuestFileException, SAXException {
		super();
                encounters = new ArrayList<PlotEncounter>();
                questBits = new HashMap<String, Boolean>();
                markers = new ArrayList<Marker>();
                entries = new ArrayList<JournalEntry>();
	}
	
	public void startElement (String uri, String name, String qName, Attributes atts) {
            System.out.println("Found " + name + " with " + atts.toString());
            try {
		switch (name.charAt(0)) {
			case 'q': break;
			case 'c': store = new CombatEncounter(atts); break;
			case 'm': subStore = new Monster(atts); chars = "m"; break;
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
            } catch (NullPointerException e) {
                System.out.println("start error with " + name + " sS " + subStore.toString() + " s " + store.toString());
                e.printStackTrace();
            }
	}
	
	public void characters (char[] array, int start, int length) {
		String blah = new String(Arrays.copyOfRange(array, start, start+length));

		chars = chars + blah;
	}
	
	public void endElement (String uri, String name, String qName) throws SAXException{
            try {
		switch (name.charAt(0)) {
		case 'q': break;
		case 'c': encounters.add((CombatEncounter) store); break;
		case 'm': if (chars.charAt(0) == 'm') {((Monster) subStore).setName(chars.substring(1)); ((CombatEncounter) store).addMonster((Monster) subStore);} else {throw new SAXException("Invalid quest file at " + qName);} break;
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
            } catch (NullPointerException e) {
                System.out.println("end error with " + name + " sS " + subStore.toString() + " s " + store.toString() + " " + e.getMessage());
                e.printStackTrace();
            }
	}
}
