package organ;

import bodyRessources.BodyResources;
import bodyRessources.ResourceType;
import mediator.Brain;
import java.util.Arrays;


/**
 * This class is an abstraction of an organ
 */
public abstract class Organ {

    private static final double TIME_INCREMENT = 1000;//1 sec

    protected Brain brain;
    private BodyResources resources;

    protected double activityFactor;

    public Organ(Brain mediator){
        this.brain = mediator;
        resources = new BodyResources(Arrays.asList(ResourceType.Oxygen, ResourceType.Protein), getSize()/5);
        activityFactor = 1;
    }

    /**
     * The size factor
     * @return the size factor
     */
    public double getSize()
    {
        return 100;
    }

    /**
     * Consume resources
     */
    public void consumeResources() {
        resources.consume(ResourceType.Oxygen, getSize() * activityFactor / 50);
        resources.consume(ResourceType.Protein, getSize() * activityFactor / 1000);
        double oxygenLevel = resources.getResourceAmount(ResourceType.Oxygen);
        double proteinLevel = resources.getResourceAmount(ResourceType.Protein);
        if (oxygenLevel < this.getSize() / 20) {
            if (oxygenLevel < 0) {
                brain.die();
            }
            brain.askOxygen(0.2 * this.getSize() - oxygenLevel);
        }
        if(oxygenLevel <= 0)
        {
            brain.die();
        }
        if(proteinLevel <= 0)
        {
            brain.die();
        }
        notifyDisplay("");
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
