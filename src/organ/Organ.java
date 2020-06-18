package organ;

import bodyRessources.BodyResources;
import bodyRessources.ResourceType;
import mediator.Brain;

import java.util.Map;

/**
 * This class is an abstraction of an organ
 */
public abstract class Organ {
    private static final double TIME_INCREMENT = 1000;//1 sec
    //TODO : Move this somewhere else ?
    // Create scheduler (per organ or a global one so that organs are synchronized ?) do we need a "Body" class ?

    protected Brain brain;

    /* Ressources */
    //TODO : Should organs have a resource maximum ?
    private BodyResources resources;

    /**
     * Constructor
     * @param brain the brain
     */
    public Organ(Brain brain){
        this.brain = brain;
        resources = new BodyResources();
    }

    /**
     * The size factor
     * @return the size factor
     */
    public double getSizeFactor()
    {
        return 1;
    }

    /**
     * Consume resources
     */
    public void consumeResources()
    {
        // FIXME: :(
        //activityLevel.consumeResources(this.resources, this.getSizeFactor());
    }

    /**
     * Get resources of this organ
     * @return the resources
     */
    public BodyResources getResources(){
        return resources;
    }

    /**
     * Get its brain
     * @return the brain
     */
    public Brain getBrain(){
        return brain;
    }

    /**
     * Refill this organ resources of a given resources
     * @param resources the resources
     */
    public void refill(BodyResources resources){
        this.resources.refill(resources);
    }

    /**
     * Notify the display of an update
     * @param toDisplay the string to display
     */
    public void notifyDisplay(String toDisplay) {
        brain.updateOrganDisplay(this, toDisplay);
    }
}
