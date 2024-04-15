package edu.jsu.mcis.cs310.tas_sp24;

public class Badge {
    
    /**
     * Represents a Badge containing an ID and description.
     */
    private final String id, description;

    public Badge(String id, String description) {
        this.id = id;
        this.description = description;
    }

    /**
     * Gets the ID of the Badge
     * @return The badge ID
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the description of the Badge
     * @return 
     */
    public String getDescription() {
        return description;
    }

    /**
     * Generates a string representation of Badge
     * @return 
     */
    @Override
    public String toString() {

        StringBuilder string = new StringBuilder();

        string.append('#').append(id).append(' ');
        string.append('(').append(description).append(')');

        return string.toString();

    }

}
