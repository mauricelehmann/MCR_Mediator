package bodyRessources;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the resources used by the body and its organs
 */
public class BodyResources {
    private Map<ResourceType, Double> resources;

    /**
     * Constructor
     */
    public BodyResources() {
        resources = new HashMap<>();

        for(ResourceType type : ResourceType.values()) {
            resources.put(type, 10.0); // FIXME: Peut-être ne pas forcément commencer à 10 pour toutes les ressources ?
        }
    }

    /**
     * Get resource amount of a resource type
     * @param type the type of resource
     * @return the resource amount of this resource type
     */
    public double getResourceAmount(ResourceType type)
    {
        return resources.get(type);
    }

    /**
     * Set the resource amount for a resource type
     * @param type the resource type
     * @param amount the amount
     */
    public void setResourceAmount(ResourceType type, double amount) {
        resources.put(type, amount);
    }

    /**
     * Consume a specified amount of a resource type
     * @param type the type
     * @param amount the amount
     */
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

    /**
     * Refill a specified amount of a resource type
     * @param type the type
     * @param amount the amount
     */
    public void refill(ResourceType type, Double amount) {
        resources.replace(type, resources.get(type) + amount);
    }

    /**
     * Refill all resources with given resources
     * @param newResources the resources to add
     */
    public void refill(BodyResources newResources)
    {
        resources.replaceAll((ResourceType type, Double oldAmount) -> oldAmount + newResources.resources.get(type));
    }

    /**
     * Gives a share of those bodyRessources to a destination bodyRessources
     * @param destinationContainer the destination
     * @param percentage the share's percentage
     * @return the share of resources
     */
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

    /**
     * Get a string representation
     * @return the string
     */
    @Override
    public String toString() {
        String str = "";

        for(Map.Entry<ResourceType, Double> resource : resources.entrySet()) {
            str += resource.getKey() + ": " + resource.getValue() + "\n";
        }

        return str;
    }
}
