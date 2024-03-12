/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.jsu.mcis.cs310.tas_sp24;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author utsav
 */
public class Employee {
    private Integer id;
    private String firstname, middlename, lastname;
    private LocalDateTime active;
    private Badge badge;
    private Department department;
    private Shift shift;
    private EmployeeType employeeType;

// Constructor for creating an Employee object with specified parameters.
public Employee(Integer id, String firstname, String middlename, String lastname, LocalDateTime active, Badge badge, 
            Department department, Shift shift, EmployeeType employeeType) 
    {
        this.id = id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.active = active;
        this.badge = badge;
        this.department = department;
        this.shift = shift;
        this.employeeType=employeeType;
    }
    
    //Getter Methods
    public Integer getId() {
        return id;
    }
    
    public String getFirstname() {
        return firstname;
    }
    
    public String getMiddlename() {
        return middlename;
    }
	
    public String getLastname() {
        return lastname;
    }
    
    public LocalDateTime getActive() {
        return active;
    }
    
    public Badge getBadge() {
        return badge;
    }
    
    public Department getDepartment() {
        return department;
    }
    
    public Shift getShift() {
        return shift;
    }
    
    public EmployeeType getEmployeeType() {
        return employeeType;
    }
    
  
    //Generates a string representation of the Employee object, including ID, name, badge, type, department, and active status.
    @Override
    public String toString() {
        
       StringBuilder employeestring = new StringBuilder();
       
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        
         employeestring.append("ID #").append(id)
                       .append(": ")
                       .append(lastname)
                       .append(", ")
                       .append(firstname)
                       .append(" ")
                       .append(middlename)
                       .append(" (#")
                       .append(badge.getId())
                       .append("), Type: ") 
                       .append(employeeType)
                       .append(", Department: ")
                       .append(department.getDescription())
                       .append(", Active: ")
                       .append(active.format(format));
            
         
        return employeestring.toString();
        
    }
}
