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
    private  int id, termid;
    private  String description;

    public Department(int id, String description, int termid) {
        this.id = id;
        this.description = description;
        this.termid = termid;
    }
    
    public Department(int termid){
        this.termid = termid;
    }
    public int getId(){
        return id;
    }
    public String getDescription(){
        return description;
    }
    public int getTermid(){
        return termid;
    }
    
    @Override
    public String toString(){
        StringBuilder dpString = new StringBuilder();
        dpString.append('#').append(id).append(' ');
        dpString.append('(').append(description).append(')');
        dpString.append(',').append(termid).append(')');
        return dpString.toString();
    }
}
