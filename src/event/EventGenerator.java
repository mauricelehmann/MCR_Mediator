package event;

import javax.management.RuntimeErrorException;
import javax.xml.parsers.*;
import org.xml.sax.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class EventGenerator {
    static private final String BASE_PATH = "events/";
    static private Random random = new Random();

    private ArrayList<Event> events;

    public EventGenerator(String eventsFile) {
        events = parse(eventsFile);
    }

    public Event generate() {
        Event event = null;

        if(!events.isEmpty() && events != null) {
            // Choose the next event randomly
            int randomEventPos = random.nextInt(events.size());
            event = events.get(randomEventPos);
        }

        return event;
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
