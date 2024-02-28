/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.jsu.mcis.cs310.tas_sp24;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author utsav
 */
public class Employee {
    private Integer id;
    private String firstname, middlename, lastname;
    private LocalDate active;
    private Badge badge;
    private Department department;
    private Shift shift;
    private EmployeeType employeeType;


public Employee(Integer id, String firstname, String middlename, String lastname, LocalDate active, Badge badge, 
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
    
    public LocalDate getActive() {
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
    
  

    @Override
    public String toString() {
       StringBuilder empstring = new StringBuilder();
         empstring.append("ID #").append(id).append(": ").append(lastname).append(", ").append(firstname)
                  .append(" ").append(middlename).append(" (#").append(badge.getId()).append("), Type: ") 
                  .append(employeeType).append(", Department: ").append(department.getDescription()).append(", Active: ").append(active);
            
         
        return empstring.toString();
    }
}