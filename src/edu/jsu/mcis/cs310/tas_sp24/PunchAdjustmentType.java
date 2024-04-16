package edu.jsu.mcis.cs310.tas_sp24;

/**
 * Represents different types of punch adjustments that can occur.
 * 
 */
public enum PunchAdjustmentType {

    NONE("None"),
    SHIFT_START("Shift Start"),
    SHIFT_STOP("Shift Stop"),
    SHIFT_DOCK("Shift Dock"),
    LUNCH_START("Lunch Start"),
    LUNCH_STOP("Lunch Stop"),
    INTERVAL_ROUND("Interval Round");

    private final String description;
    /**
     * Constructs a PunchAdjustmentType with the specified description.
     * @param d The description of the punch adjustment.
     */
    private PunchAdjustmentType(String d) {
        description = d;
    }
    /**
     * Generates a string representation of the PunchAdjustmentType.
     * @return The description of the Punch adjustment type.
     */
    @Override
    public String toString() {
        return description;
    }

}
