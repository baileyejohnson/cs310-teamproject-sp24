package edu.jsu.mcis.cs310.tas_sp24;

public class Badge {
    
    // establishes id and description objects
    
    private final String id, description;

    public Badge(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

        // adds characters
    
    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();

        s.append('#').append(id).append(' ');
        s.append('(').append(description).append(')');

        return s.toString();

    }

}
