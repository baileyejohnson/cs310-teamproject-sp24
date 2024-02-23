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
    private final String id, description;
    private final String termid;

    public Department(String id, String description, String termid) {
        this.id = id;
        this.description = description;
        this.termid = termid;
    }
    public String getId(){
        return id;
    }
    public String getDescription(){
        return description;
    }
    public String getTermid(){
        return termid;
    }
    
    @Override
    public String toString(){
        StringBuilder dpString = new StringBuilder();
        dpString.append('#').append(id).append(' ');
        dpString.append('(').append(description).append(')');
        dpString.append('#').append(termid).append(')');
        return dpString.toString();
    }
}
