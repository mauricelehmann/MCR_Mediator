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

public class EventsParserHandler extends DefaultHandler {
    private Event currentEvent = null;
    private Action currentAction = null;
    private Effect currentEffect = null;

    private String elementValue;
    private ArrayList<Event> events = new ArrayList<>();
    private HashMap<String, Action> actions = new HashMap();

    public void startDocument() throws SAXException {
        System.out.println("Loading events...");
    }

    public void endDocument() throws SAXException {
        System.out.println("events loaded.");
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException  {
        switch (qName) {
            case "event":
                currentEvent = new Event();
                break;
            case "action":
                parseActionAttributes(attributes);
                break;
            //FIXME: maybe there is too much code duplication between each effect parsing
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

    public void characters(char[] ch, int start, int length) throws SAXException {
        elementValue = new String(ch, start, length);
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

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

    private void parseEventActionAttributes(Attributes attributes) {
        if(attributes == null) {
            return;
        }

        for(int att = 0; att < attributes.getLength(); ++att) {
            switch (attributes.getQName(att)) {
                case "ref":
                    String actionName = attributes.getValue(att);
                    currentEvent.addAction(actions.get(actionName));
                    break;
            }
        }
    }

    private void parseDieEffectAttributes(Attributes attributes) {
        if(attributes == null) {
            return;
        }

        double probability = 1.;

        for(int att = 0;  att < attributes.getLength(); ++att) {
            switch (attributes.getQName(att)) {
                case "probability":
                    probability = Double.parseDouble(attributes.getValue(att));
                    break;
            }
        }

        currentEffect = new DieEffect(probability);
    }

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

    private void parseStressEffectAttributes(Attributes attributes) {
        if(attributes == null) {
            return;
        }

        double probability = 1.;

        for(int att = 0;  att < attributes.getLength(); ++att) {
            switch (attributes.getQName(att)) {
                case "probability":
                    probability = Double.parseDouble(attributes.getValue(att));
                    break;
            }
        }

        currentEffect = new StressEffect(probability);
    }

    private void parseRunEffectAttributes(Attributes attributes) {
        if(attributes == null) {
            return;
        }

        double probability = 1.;

        for(int att = 0;  att < attributes.getLength(); ++att) {
            switch (attributes.getQName(att)) {
                case "probability":
                    probability = Double.parseDouble(attributes.getValue(att));
                    break;
            }
        }

        currentEffect = new RunEffect(probability);
    }
}
