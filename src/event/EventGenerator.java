package event;

import javax.xml.parsers.*;
import event.parser.EventsParserHandler;
import org.xml.sax.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents an event generator
 * that will generates/gives events loaded
 * from a file
 */
public class EventGenerator {
    static private final String BASE_PATH = "events/";
    static private Random random = new Random(); // TODO: maybe moves this into GameManager
    private ArrayList<Event> events;

    /**
     * Constructor
     * @param eventsFile the events config file
     */
    public EventGenerator(String eventsFile) {
        events = parse(eventsFile);
    }

    /**
     * Gets an event from the loaded of events
     * @return the event
     */
    public Event generate() {
        Event event = null;

        if(!events.isEmpty()) {
            // Choose the next event randomly
            int randomEventPos = random.nextInt(events.size());
            event = events.get(randomEventPos);
        }

        return event;
    }

    /**
     * Load events from a file
     * @param filename the file name
     * @return a list of events
     */
    static private ArrayList<Event> parse(String filename) {
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
