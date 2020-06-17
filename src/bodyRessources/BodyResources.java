package bodyRessources;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

public class BodyResources {

    private Map<ResourceType, Double> resources;

    public BodyResources() {
        resources = new HashMap<>();

        for(ResourceType type : ResourceType.values()) {
            resources.put(type, 10.0); // FIXME: Peut-être ne pas forcément commencer à 10 pour toutes les ressources ?
        }
    }

    public double getResourceAmount(ResourceType type)
    {
        return resources.get(type);
    }

    public void setResourceAmount(ResourceType type, double amount) {
        resources.put(type, amount);
    }

    public void consume(ResourceType type, Double amount) {
        if(getResourceAmount(type) >= amount)
        {
            resources.replace(type, resources.get(type) - amount);//TODO : Trouver une formulation plus élégante ?
        }
        else
        {
            resources.replace(type, 0.0);
        }
    }

    public void refill(ResourceType type, Double amount) {
        resources.replace(type, resources.get(type) + amount);
    }

    public void refill(BodyResources newResources)
    {
        resources.replaceAll((ResourceType type, Double oldAmount) -> oldAmount + newResources.resources.get(type));
    }

    public BodyResources splitShare(BodyResources destinationContainer, double percentage)
    {
        if(percentage > 1 || percentage <0)
        {
            throw new InvalidParameterException("Ratio must be between 0 and 1");
        }
        BodyResources share = new BodyResources();
        for(Map.Entry<ResourceType, Double> resource : destinationContainer.resources.entrySet())
        {
            share.resources.put(resource.getKey(), resource.getValue()*percentage);
        }

        return share;
    }

    @Override
    public String toString() {
        String str = "";

        for(Map.Entry<ResourceType, Double> resource : resources.entrySet()) {
            str += resource.getKey() + ": " + resource.getValue() + "\n";
        }

        return str;
    }
}
