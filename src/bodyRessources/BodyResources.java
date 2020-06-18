package bodyRessources;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the resources used by the body and its organs
 */
public class BodyResources {
    private final Map<ResourceType, Double> resources;

    /**
     * Default Constructor : the container will know all resourceTypes but their amount starts at 0
     */
    public BodyResources() {
        resources = new HashMap<>();

        for(ResourceType type : ResourceType.values()) {
            resources.put(type, 0.0);
        }
    }

    /**
     * Constructor : will set all given resourceTypes with the same startAmount
     * @param resourceTypes the resource types known by the container
     * @param startAmount the initial amount for known resources
     */
    public BodyResources(List<ResourceType> resourceTypes, double startAmount) {
        resources = new HashMap<>();

        for(ResourceType type : resourceTypes)
        {
            resources.put(type, startAmount);
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
     * Set the resource amount for a resource type.If the type is unknown, it will be added
     * @param type the resource type
     * @param amount the amount
     */
    public void setResourceAmount(ResourceType type, double amount) {
        resources.put(type, amount);
    }

    /**
     * Consume (remove) a specified amount of a resource type
     * @param type the type
     * @param amount the amount
     */
    public void consume(ResourceType type, Double amount) {
        if(getResourceAmount(type) >= amount)
        {
            resources.replace(type, resources.get(type) - amount);
        }
        else
        {
            //Floors the resource value to 0
            resources.replace(type, 0.0);
        }
    }

    /**
     * Refill only the given ResourceType by amount. If the type is not known by the container, nothing happens
     * @param type the ResourceType do be filled
     * @param amount amount to be added
     */
    public void fill(ResourceType type, Double amount) {
        resources.replace(type, (resources.containsKey(type) ? resources.get(type) : 0) + amount);
    }


    /**
     * Refill all known resources with given resources. Any resource type from newResources container that is not
     * known by the recipient (no correponding key in the map) will be ignored
     * @param newResources the resources to add
     */
    public void refill(BodyResources newResources)
    {
        resources.replaceAll((ResourceType type, Double oldAmount) -> oldAmount + newResources.resources.get(type));
    }

    /**
     * Gives a share of those bodyRessources to a destination bodyRessources
     * @param destinationContainer the destination
     * @param portion the share's relative amount
     * @return the share of resources
     */
    public BodyResources splitShare(BodyResources destinationContainer, double portion)
    {
        if(portion > 1 || portion <0)
        {
            throw new InvalidParameterException("Ratio must be between 0 and 1");
        }
        BodyResources share = new BodyResources();
        for(Map.Entry<ResourceType, Double> resource : destinationContainer.resources.entrySet())
        {
            //Remove shared resources from original resource container
            this.consume(resource.getKey(), this.resources.get(resource.getKey())*portion);
            //Fill the destination container with the same amount
            share.resources.put(resource.getKey(), this.resources.get(resource.getKey())*portion);
        }
        return share;
    }

    /**
     * Get a string representation
     * @return the string
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for(Map.Entry<ResourceType, Double> resource : resources.entrySet()) {
            str.append(resource.getKey()).append(": ").append(resource.getValue()).append("\n");
        }

        return str.toString();
    }
}
