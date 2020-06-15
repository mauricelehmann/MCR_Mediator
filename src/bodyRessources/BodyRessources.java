package bodyRessources;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

public class BodyRessources {

    private Map<ResourceType, Double> resources;

    public BodyRessources() {
        resources = new HashMap<>();
        resources.put(ResourceType.Oxygen, 10.0);
    }

    public void consume(ResourceType type, Double amount) {
        resources.replace(type, resources.get(type) - amount);//TODO : Trouver une formulation plus élégante ?
    }

    public void refill(ResourceType type, Double amount) {
        resources.replace(type, resources.get(type) + amount);
    }

    public void refill(BodyRessources newResources)
    {
        resources.replaceAll((ResourceType type, Double oldAmount) -> oldAmount + newResources.resources.get(type));
    }

    public BodyRessources splitShare(double percentage)
    {
        if(percentage > 1 || percentage <0)
        {
            throw new InvalidParameterException("Ratio must be between 0 and 1");
        }
        BodyRessources share = new BodyRessources();
        for(Map.Entry<ResourceType, Double> resource : resources.entrySet())
        {
            share.resources.put(resource.getKey(), resource.getValue()*percentage);
        }

        return share;
    }
}
