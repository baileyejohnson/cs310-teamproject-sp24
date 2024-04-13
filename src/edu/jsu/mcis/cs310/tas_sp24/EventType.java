  package edu.jsu.mcis.cs310.tas_sp24;
/**
 * Represents different types of punch events, including clock in, clock out, and time out
 * 
 */
public enum EventType {

    CLOCK_OUT("CLOCK OUT"),
    CLOCK_IN("CLOCK IN"),
    TIME_OUT("TIME OUT");

    private final String description;
    /**
     * Constructs an EventType with the specified description
     * @param d The description of the punch event type
     */
    private EventType(String d) {
        description = d;
    }
    /**
     * Generates a string representation of the Event type
     * @return 
     */
    @Override
    public String toString() {
        return description;
    }

}
