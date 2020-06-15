package mediator;
import bodyRessources.BodyRessources;
import bodyRessources.ResourceType;
import event.Event;
import organ.*;

import java.util.ArrayList;

/**
 * Abstract mediator
 */
public abstract class Brain implements BodyMediator{

    //TODO : faire un container special pour les organes?
    protected Lungs lungs;
    protected Legs legs;
    public Eyes eyes;
    public Heart heart;

    private ArrayList<Organ> organs;

    BodyRessources brainResources;
    BodyRessources bodyResources;

    private double biomass;//Sum of organ sizes

    public Brain(){
        /* Adding the organs */
        bodyResources = new BodyRessources();
        brainResources = new BodyRessources();
        this.lungs = new Lungs(this);
        this.legs = new Legs(this);
        this.eyes = new Eyes(this);
        //TODO : to refactor, it should not be the brain's resposibility to create the organs

        organs.add(lungs);
        organs.add(legs);
        organs.add(eyes);

        biomass = 0;
        for(Organ organ : organs)
        {
            biomass += organ.getSizeFactor();
        }
    }

    public void bloodFlow() {
        for (Organ organ: organs)
        {
            //Give the organ its fair share of resources
            organ.refill((bodyResources.splitShare(organ.getSizeFactor()/biomass)));
        }
    }

    public void refillBlood(ResourceType resourceType, double amount)
    {
        bodyResources.refill(resourceType, amount);
    }

    /* Abstract methods : behaviors have to be implemented in the concret brains */
    public void askOxygen() throws Exception {
        lungs.breathe();
        heart.accelerate(1.5);
    }

    public abstract void notifyEvent(Event event);
}
