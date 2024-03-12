/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.jsu.mcis.cs310.tas_sp24;

/**
 *
 * @author egmck
 */
public class Department {
    private  int id, terminalid;
    private  String description;

    //Constructor for creating a Department object 
    public Department(int id, String description, int terminalid) {
        this.id = id;
        this.description = description;
        this.terminalid = terminalid;
    }
    
   //Getter Methods
    public int getId(){
        return id;
    }
    
    public String getDescription(){
        return description;
    }
    
    public int getTerminalId(){
        return terminalid;
    }
    
    //Generates a string representation of the Department object, including ID, description, and terminal ID.
    @Override
    public String toString(){
        StringBuilder dpString = new StringBuilder();
        
        dpString.append('#').append(id).append(' ');
        dpString.append('(').append(description).append(')');
        dpString.append(',').append(" Terminal ID: ").append(terminalid);
        
        return dpString.toString();
        
    }
}
