package organ;

import bodyRessources.BodyResources;
import bodyRessources.ResourceType;
import mediator.Brain;

import java.util.Arrays;
import java.util.Timer;

public abstract class Organ {
    //TODO : Move this somewhere else ?
    // Create scheduler (per organ or a global one so that organs are synchronized ?) do we need a "Body" class ?
    private static final double TIME_INCREMENT = 1000;//1 sec

    protected Brain mediator;

    /* Ressources */
    //TODO : Should organs have a resource maximum ?
    private BodyResources resources;

    protected double activityFactor;

    public Organ(Brain mediator){
        this.mediator = mediator;
        resources = new BodyResources(Arrays.asList(ResourceType.Oxygen, ResourceType.Protein), getSize()/2);
        activityFactor = 1;
    }

    public double getSize()
    {
        return 100;
    }

    public void consumeResources() {
        resources.consume((getSize()/100)*activityFactor);
        double oxygenLevel = resources.getResourceAmount(ResourceType.Oxygen);
        if(oxygenLevel < this.getSize()/20)
        {
            mediator.askOxygen(0.2*this.getSize()-oxygenLevel);
        }
        //TODO : Add similar behavior for protein ?
    }

    public BodyResources getResources(){
        return resources;
    }

    public Brain getMediator(){
        return mediator;
    }

    public void refill(BodyResources resources){
        this.resources.refill(resources);
    }

    public void notifyDisplay(String toDisplay){
        mediator.updateOrganDisplay(this, toDisplay);
    }

}
