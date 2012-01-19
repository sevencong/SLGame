package quest;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import journal.JournalEntry;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;

import encounter.CombatEncounter;
import encounter.PlotEncounter;
import encounter.TalkEncounter;

import worldMap.Marker;


public class Quest {

	private ArrayList<PlotEncounter> encounters;
	private HashMap<String, Boolean> questBits;
	private ArrayList<Marker> markers;
	private ArrayList<JournalEntry> entries;
	private QuestLoader handler;

	public Quest (String fileName) throws IOException, ParserConfigurationException, SAXException, InvalidQuestFileException {
			
		loadFromFile(new File(fileName));
		encounters = handler.encounters;
		questBits = handler.questBits;
		markers = handler.markers;
		entries = handler.entries;
		/*Document doc = loadFromFile(new File(fileName));
			NodeList nodes = doc.getElementsByTagName("bit");
			for (int a = 0; a < nodes.getLength(); a++) {
				questBits.put(nodes.item(a).getNodeName(), false);
			}
			nodes = doc.getElementsByTagName("marker");
			for (int a = 0; a < nodes.getLength(); a++) {
				markers.add(new questMarker(nodes.item(a)));
			}
			nodes = doc.getElementsByTagName("plot");
			for (int a = 0; a < nodes.getLength(); a++) {
				encounters.add(new TalkEncounter(nodes.item(a)));
			}
			nodes = doc.getElementsByTagName("combat");
			for (int a = 0; a < nodes.getLength(); a++) {
				encounters.add(new CombatEncounter(nodes.item(a)));
			}
			nodes = doc.getElementsByTagName("journal");
			for (int a = 0; a < nodes.getLength(); a++) {
				entries.add(new JournalEntry(nodes.item(a)));
			}*/
	}
		
	public ArrayList<PlotEncounter> getEncounters() {
		return encounters;
	}

	public HashMap<String, Boolean> getQuestBits() {
		return questBits;
	}

	public ArrayList<Marker> getMarkers() {
		return markers;
	}

	public ArrayList<JournalEntry> getEntries() {
		return entries;
	}
	
	@SuppressWarnings("null")
	public void loadFromFile(File file) throws IOException, ParserConfigurationException, SAXException, InvalidQuestFileException {
		CharBuffer content = null;
		FileReader fr = new FileReader(file);
		String doc = "";
		
		fr.read(content);
		for (char a : content.array()) {
			if (a >= 79){
				doc += a + 47;
			} else {
				doc += a - 47;
			}
		}
		
		InputSource in = new InputSource(new ByteArrayInputStream(doc.getBytes()));
		XMLReader xr = XMLReaderFactory.createXMLReader();
		handler = new QuestLoader();
		xr.setContentHandler(handler);
		xr.setErrorHandler(handler);
		xr.parse(in);
	}
	
	
}
