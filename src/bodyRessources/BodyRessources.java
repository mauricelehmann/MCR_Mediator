package bodyRessources;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

public class BodyRessources {

    private Map<ResourceType, Double> resources;

    public BodyRessources() {
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

    public void refill(BodyRessources newResources)
    {
        resources.replaceAll((ResourceType type, Double oldAmount) -> oldAmount + newResources.resources.get(type));
    }

    public BodyRessources splitShare(BodyRessources destinationContainer, double percentage)
    {
        if(percentage > 1 || percentage <0)
        {
            throw new InvalidParameterException("Ratio must be between 0 and 1");
        }
        BodyRessources share = new BodyRessources();
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
