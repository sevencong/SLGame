package quest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.imageio.ImageIO;
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
import java.io.*;

import worldMap.Marker;


public class Quest {

	private ArrayList<PlotEncounter> encounters;
	private HashMap<String, Boolean> questBits;
	private ArrayList<Marker> markers;
	private ArrayList<JournalEntry> entries;
	private QuestLoader handler;
	private BufferedImage partyPic;
	private BufferedImage markerPic;

	public Quest (String fileName) throws IOException, ParserConfigurationException, SAXException, InvalidQuestFileException {
		loadFromFile(new File(fileName));	//set up and parse xml file
		encounters = handler.encounters;
		questBits = handler.questBits;
		markers = handler.markers;
		entries = handler.entries;
		partyPic = ImageIO.read(new File("party.jpg"));
		markerPic = ImageIO.read(new File("marker.jpg"));
	}

	public BufferedImage getPartyPic() {
		return partyPic;
	}

	public BufferedImage getMarkerPic() {
		return markerPic;
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
		String str = readFileAsString(file);
		String doc = "";

		doc=str;
		System.out.println(doc);

		InputSource in = new InputSource(new ByteArrayInputStream(doc.getBytes()));
		XMLReader xr = XMLReaderFactory.createXMLReader();
		handler = new QuestLoader();
		xr.setContentHandler(handler);
		xr.setErrorHandler(handler);
		xr.parse(in);
	}

	private String readFileAsString(File file) throws java.io.IOException {
		StringBuffer fileData = new StringBuffer(1000);
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}
		reader.close();
		return fileData.toString();
	}

}
