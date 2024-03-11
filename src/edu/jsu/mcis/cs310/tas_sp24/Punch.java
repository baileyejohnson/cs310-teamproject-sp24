/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.jsu.mcis.cs310.tas_sp24;

/**
 *
 * @author utsav
 */
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import edu.jsu.mcis.cs310.tas_sp24.Shift;
import java.time.LocalTime;

public class Punch {

    private Integer id;
    private Integer terminalid;
    private Badge badge;
    private EventType punchType;
    private LocalDateTime originalTimestamp;
    private PunchAdjustmentType adjustedTimestamp; 
    private Shift shift;
    private Shift start;
    private Shift end;
    private LocalTime shiftStart;

    // Constructor for new punches
    public Punch(Integer terminalid, Badge badge, EventType punchType) {
        this.id = null;
        this.terminalid = terminalid;
        this.badge = badge;
        this.punchType = punchType;
        this.originalTimestamp =LocalDateTime.now();
        this.adjustedTimestamp = null;
    }

   
    public Punch(Integer id, Integer terminalid, Badge badge, LocalDateTime originalTimestamp, EventType punchType) {
        this.id = id;
        this.terminalid = terminalid;
        this.badge = badge;
        this.originalTimestamp = originalTimestamp;
        this.punchType = punchType;
    }
    
    public void adjust(Shift shift){
        this.shift = shift;
    }
    
    public LocalTime printAdjusted(){
        return shiftStart;
    }
    
    public Integer getId() {
        return id;
    }

    public Integer getTerminalid() {
        return terminalid;
    }

    public Badge getBadge() {
        return badge;
    }

    public EventType getPunchtype() {
        return punchType;
    }

    public LocalDateTime getOriginaltimestamp() {
        return originalTimestamp;
    }

    public PunchAdjustmentType getAdjustedTimestamp() {
        return adjustedTimestamp;
    }

    public String printOriginal() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        DayOfWeek dayOfTheWeek = originalTimestamp.getDayOfWeek(); 
        

        StringBuilder build = new StringBuilder();
        build.append("#").append(badge.getId()).append(" ");
        build.append(punchType).append(": ");
        build.append(dayOfTheWeek.name().substring(0, 3)).append(" ");
       
        build.append(originalTimestamp.format(format));
        
        return build.toString();
    }

    @Override
    public String toString() {
        return printOriginal();
    }
}
