package edu.jsu.mcis.cs310.tas_sp24;

public class Badge {

    private final String id, description;

    public Badge(String id, String description) {
        this.id = id;
        this.description = description;
    }

    //Makes a Getter method for the ID from the database and returns it as a string
    public String getId() {
        return id;
    }

    ///Makes a Getter method for the Description and returns the description string variable
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {

        StringBuilder string = new StringBuilder();

        string.append('#').append(id).append(' ');
        string.append('(').append(description).append(')');

        return string.toString();

    }

}
