package event;

import javax.xml.parsers.*;
import org.xml.sax.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class EventGenerator {
    static private final String BASE_PATH = "events/";

    private ArrayList<Event> events = new ArrayList<>();

    public EventGenerator(String eventsFile) {
        events = parse(eventsFile);

        for (Event event : events) {
            System.out.println(event);
        }
    }

    static private ArrayList<Event> parse(String filename) { // FIXME: change this to fileinputstream ?
        ArrayList<Event> events = new ArrayList<>();

        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setNamespaceAware(true);
            SAXParser saxParser = spf.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();
            EventsParserHandler handler = new EventsParserHandler();
            xmlReader.setContentHandler(handler);

            URL fileURL = EventGenerator.class.getClassLoader().getResource(BASE_PATH + filename);
            String path = null;

            if (fileURL != null) {
                path = fileURL.getPath();
            }

            xmlReader.parse(path);

            events = handler.getEvents();
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }

        return events;
    }
}
