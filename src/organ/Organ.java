package organ;

import bodyRessources.BodyResources;
import bodyRessources.ResourceType;
import mediator.Brain;

import java.util.Map;

public abstract class Organ {

    private static final double TIME_INCREMENT = 1000;//1 sec
    //TODO : Move this somewhere else ?
    // Create scheduler (per organ or a global one so that organs are synchronized ?) do we need a "Body" class ?

    protected Brain mediator;

    //TODO : Should organs have a resource maximum ?
    private BodyResources resources;

    public Organ(Brain mediator){
        this.mediator = mediator;
        resources = new BodyResources();
    }

    public double getSizeFactor()
    {
        return 1;
    }

    public void consumeResources()
    {
        resources.consume(ResourceType.Oxygen, 10.0);
        resources.consume(ResourceType.Protein, 10.0);
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
