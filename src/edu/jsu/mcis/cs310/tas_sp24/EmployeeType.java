package edu.jsu.mcis.cs310.tas_sp24;
/**
 * Represents different types of employees, including "Temporary / Part-Time" and "Full-Time"
 * 
 */
public enum EmployeeType {

    PART_TIME("Temporary / Part-Time"),
    FULL_TIME("Full-Time");
    private final String description;
    /**
     * Constructs an EmployeeType with the specified description
     * @param d The description of the employee type.
     */
    private EmployeeType(String d) {
        description = d;
    }
    /**
     * Generates a string representation for Employee type.
     * @return a string of employee type.
     */
    @Override
    public String toString() {
        return description;
    }
    
}
