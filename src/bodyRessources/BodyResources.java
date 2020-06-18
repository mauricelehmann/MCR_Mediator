package bodyRessources;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BodyResources {

    private Map<ResourceType, Double> resources;

    public BodyResources() {
        resources = new HashMap<>();

        for(ResourceType type : ResourceType.values()) {
            resources.put(type, 0.0);
        }
    }

    public BodyResources(List<ResourceType> resourceTypes, double startAmount) {
        resources = new HashMap<>();

        for(ResourceType type : resourceTypes)
        {
            resources.put(type, startAmount);
        }
    }

    public double getResourceAmount(ResourceType type)
    {
        return resources.get(type);
    }

    public void consume(double amount)
    {
        resources.replaceAll((ResourceType type, Double oldAmount) -> oldAmount - amount);
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

    public void fill(ResourceType type, Double amount) {
        resources.replace(type, (resources.containsKey(type) ? resources.get(type) : 0) + amount);
    }

    public void refill(BodyResources newResources)
    {
        resources.replaceAll((ResourceType type, Double oldAmount) -> oldAmount + newResources.resources.get(type));
    }

    public BodyResources splitShare(BodyResources destinationContainer, double portion)
    {
        if(portion > 1 || portion <0)
        {
            throw new InvalidParameterException("Ratio must be between 0 and 1");
        }
        BodyResources share = new BodyResources();
        for(Map.Entry<ResourceType, Double> resource : destinationContainer.resources.entrySet())
        {
            this.consume(resource.getKey(), resource.getValue()*portion);
            share.resources.put(resource.getKey(), resource.getValue()*portion);
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
