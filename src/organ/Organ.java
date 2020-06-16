package organ;

import bodyRessources.BodyRessources;
import bodyRessources.ResourceType;
import mediator.Brain;

import java.util.Map;



abstract class ActivityLevel
{
    public abstract Map<ResourceType, Double> getConsumptionRates();
    //TODO : figure out balanced consumption values for all activityLevels and resources in subclasses

    public void consumeResources(BodyRessources resources, double sizeFactor) {
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
    private BodyRessources ressources;
    private ActivityLevel activityLevel;

    public Organ(Brain mediator){
        this.mediator = mediator;
        ressources = new BodyRessources();
    }

    public double getSizeFactor()
    {
        return 1;
    }

    public void consumeResources()
    {
        activityLevel.consumeResources(this.ressources, this.getSizeFactor());
    }

    public BodyRessources getRessources(){
        return ressources;
    }

    public Brain getMediator(){
        return mediator;
    }

    public void refill(BodyRessources resources){
        ressources.refill(resources);
    }
}
