package organ;

import bodyRessources.BodyResources;
import bodyRessources.ResourceType;
import mediator.Brain;

import java.util.Map;

abstract class ActivityLevel
{
    public abstract Map<ResourceType, Double> getConsumptionRates();
    //TODO : figure out balanced consumption values for all activityLevels and resources in subclasses

    public void consumeResources(BodyResources resources, double sizeFactor) {
        for(Map.Entry<ResourceType, Double> entry : getConsumptionRates().entrySet())
        {
            resources.consume(entry.getKey(), sizeFactor*entry.getValue());
        }
    }
}

class LowActivity extends ActivityLevel {
    @Override
    public Map<ResourceType, Double> getConsumptionRates() {
        return null;
    }
}

class MediumActivity extends ActivityLevel {
    @Override
    public Map<ResourceType, Double> getConsumptionRates() {
        return null;
    }
}

public abstract class Organ {

    private static final double TIME_INCREMENT = 1000;//1 sec
    //TODO : Move this somewhere else ?
    // Create scheduler (per organ or a global one so that organs are synchronized ?) do we need a "Body" class ?

    protected Brain mediator;

    /* Ressources */
    //TODO : Should organs have a resource maximum ?
    private BodyResources resources;
    private ActivityLevel activityLevel;

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
        activityLevel.consumeResources(this.resources, this.getSizeFactor());
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
