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

import worldMap.Marker;

import encounter.PlotEncounter;

public class QuestLoader extends DefaultHandler {
	
	public ArrayList<PlotEncounter> encounters;
	public HashMap<String, Boolean> questBits;
	public ArrayList<Marker> markers;
	public ArrayList<JournalEntry> entries;

	@SuppressWarnings("unused")
	public QuestLoader () throws IOException, InvalidQuestFileException, SAXException {
		super();
	}
	
	public void startElement (String uri, String name, String qName, Attributes atts) {
		switch (name.charAt(0)) {
			case 'q':  break;
			case 'c':  break;
			case 'm':  break;
			case 'l':  break;
			case 'a':  break;
			case 'd':  break;
			case 'p':  break;
			case 't':  break;
			case 'M':  break;
			case 'b':  break;
			default: break;
		}
	}
}
