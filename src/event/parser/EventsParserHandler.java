package event.parser;

import java.util.ArrayList;
import java.util.HashMap;

import bodyRessources.BodyResources;
import bodyRessources.ResourceType;
import event.Event;
import event.action.*;
import event.effect.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * This class handles the parsing of an events config file
 */
public class EventsParserHandler extends DefaultHandler {
    private Event currentEvent = null;
    private Action currentAction = null;
    private Effect currentEffect = null;
    private String elementValue;
    private ArrayList<Event> events = new ArrayList<>();
    private HashMap<String, Action> actions = new HashMap();

    /**
     * Handle the start of the document parsing
     * @throws SAXException exception
     */
    public void startDocument() throws SAXException {
        System.out.println("Loading events...");
    }

    /**
     * Handle the end of the document parsing
     * @throws SAXException
     */
    public void endDocument() throws SAXException {
        System.out.println("events loaded.");
    }

    /**
     * Handle the start of an XML element
     * @param uri the element uri
     * @param localName the element local name
     * @param qName the element qName
     * @param attributes attributes of the element
     * @throws SAXException
     */
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException  {
        switch (qName) {
            case "event":
                currentEvent = new Event("");
                break;
            case "action":
                parseActionAttributes(attributes);
                break;
            case "dieEffect":
                parseDieEffectAttributes(attributes);
                break;
            case "eatEffect":
                parseEatEffectAttributes(attributes);
                break;
            case "runEffect":
                parseRunEffectAttributes(attributes);
                break;
            case "stressEffect":
                parseStressEffectAttributes(attributes);
                break;
            case "eventAction":
                parseEventActionAttributes(attributes);
                break;
        }
    }

    /**
     * Handle the end of an element
     * @param uri the element uri
     * @param localName the element local name
     * @param qName the element qName
     * @throws SAXException
     */
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "event":
                events.add(currentEvent);
                break;
            case "description":
                currentEvent.setDescription(elementValue);
                break;
            case "hallucination":
                currentEvent.setHallucination(elementValue);
                break;
            case "action":
                actions.put(currentAction.getName(), currentAction);
                break;
            case "dieEffect":
            case "eatEffect":
            case "runEffect":
            case "stressEffect":
                currentAction.addEffect(currentEffect);
                break;
        }
    }

    /**
     * Fetch characters into the current element value
     * @param ch characters
     * @param start the start of the string
     * @param length the length of the string
     * @throws SAXException
     */
    public void characters(char[] ch, int start, int length) throws SAXException {
        elementValue = new String(ch, start, length);
    }

    /**
     * Get the parsed events
     * @return the events
     */
    public ArrayList<Event> getEvents() {
        return events;
    }

    /**
     * Parse attributes of an action element
     * @param attributes the attributes
     */
    private void parseActionAttributes(Attributes attributes) {
        if(attributes == null) {
            return;
        }

        String name = "";

        for(int att = 0; att < attributes.getLength(); ++att) {
            switch (attributes.getQName(att)) {
                case "id":
                    name = attributes.getValue(att);
                    break;
                case "type":
                    switch(attributes.getValue(att)) {
                        case "eat":
                            currentAction = new EatAction(name);
                            break;
                        case "fight":
                            currentAction = new FightAction(name);
                            break;
                        case "flee":
                            currentAction = new FleeAction(name);
                            break;
                        case "ignore":
                            currentAction = new IgnoreAction(name);
                            break;
                    }
            }
        }
    }

    /**
     * Parse attributes of an event's action element
     * @param attributes the attributes
     */
    private void parseEventActionAttributes(Attributes attributes) {
        if(attributes == null) {
            return;
        }

        for(int att = 0; att < attributes.getLength(); ++att) {
            if ("ref".equals(attributes.getQName(att))) {
                String actionName = attributes.getValue(att);
                currentEvent.addAction(actions.get(actionName));
                break;
            }
        }
    }

    /**
     * Parse attributes of the die effect element
     * @param attributes the attributes
     */
    private void parseDieEffectAttributes(Attributes attributes) {
        if(attributes == null) {
            return;
        }
        currentEffect = new DieEffect(getProbability(attributes));
    }

    /**
     * Parse attributes of the eat effect element
     * @param attributes the attributes
     */
    private void parseEatEffectAttributes(Attributes attributes) {
        if(attributes == null) {
            return;
        }

        double probability = 1.;
        int caffein = 0;
        int alcohol = 0;
        int psychdedelic = 0;
        int protein = 0;

        for(int att = 0;  att < attributes.getLength(); ++att) {
            switch (attributes.getQName(att)) {
                case "probability":
                    probability = Double.parseDouble(attributes.getValue(att));
                    break;
                case "alcohol":
                    alcohol = Integer.parseInt(attributes.getValue(att));
                    break;
                case "caffein":
                    caffein = Integer.parseInt(attributes.getValue(att));
                    break;
                case "psychedelic":
                    psychdedelic = Integer.parseInt(attributes.getValue(att));
                    break;
                case "protein":
                    protein = Integer.parseInt(attributes.getValue(att));
                    break;
            }
        }

        BodyResources ressources = new BodyResources();
        ressources.fill(ResourceType.Caffein, (double)caffein);
        ressources.fill(ResourceType.Alcohol, (double)alcohol);
        ressources.fill(ResourceType.Psychedelic, (double)psychdedelic);
        ressources.fill(ResourceType.Protein, (double)protein);


        currentEffect = new EatEffect(probability, ressources);
    }

    private double getProbability(Attributes attributes){
        double probability = 1.;
        for(int att = 0;  att < attributes.getLength(); ++att) {
            if(attributes.getQName(att).equals("probability")) {
                probability = Double.parseDouble(attributes.getValue(att));
                break;
            }
        }
        return probability;
    }

    /**
     * Parse attributes of the stress effect element
     * @param attributes the attributes
     */
    private void parseStressEffectAttributes(Attributes attributes) {
        if(attributes == null) {
            return;
        }

        currentEffect = new StressEffect(getProbability(attributes));
    }

    /**
     * Parse attributes of the run effect element
     * @param attributes the attributes
     */
    private void parseRunEffectAttributes(Attributes attributes) {
        if(attributes == null) {
            return;
        }

        currentEffect = new RunEffect(getProbability(attributes));
    }
}
