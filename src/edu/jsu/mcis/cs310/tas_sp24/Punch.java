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
    private LocalDateTime adjustedTime;
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

    // Constructor for existing punches with specified ID and timestamps
    public Punch(Integer id, Integer terminalid, Badge badge, LocalDateTime originalTimestamp, EventType punchType) {
        this.id = id;
        this.terminalid = terminalid;
        this.badge = badge;
        this.originalTimestamp = originalTimestamp;
        this.punchType = punchType;
    }
    
    // Adjusts the punch timestamp based on the provided shift information.
    public void adjust(Shift shift){
        this.shift = shift;
        boolean adjusted = false;
        boolean weekend = false;
        
        int shiftroundint = shift.getRoundInterval();

        
        LocalDateTime ShiftStart = originalTimestamp.toLocalDate().atTime(shift.getShiftStart());
        LocalDateTime ShiftEnd = originalTimestamp.toLocalDate().atTime(shift.getShiftStop());
        LocalDateTime LunchStart = originalTimestamp.toLocalDate().atTime(shift.getLunchStart());
        LocalDateTime LunchEnd = originalTimestamp.toLocalDate().atTime(shift.getLunchStop());
        
        LocalDateTime GraceStart = ShiftStart.plusMinutes(shift.getGracePeriod());
        LocalDateTime GraceStop = ShiftEnd.minusMinutes(shift.getGracePeriod());
        
        LocalDateTime ShiftStartLeft = ShiftStart.minusMinutes(shiftroundint);
        LocalDateTime ShiftStartRight = ShiftStart.minusMinutes(shiftroundint);
        
        LocalDateTime ShiftEndLeft = ShiftEnd.minusMinutes(shiftroundint);
        LocalDateTime ShiftEndRight = ShiftEnd.plusMinutes(shiftroundint);
        
        if(adjustedTime == null){
            adjustedTime = originalTimestamp;
        }
        
        //Checks weekend
        if(originalTimestamp.getDayOfWeek() == DayOfWeek.SATURDAY || originalTimestamp.getDayOfWeek() == DayOfWeek.SUNDAY){
            adjustedTimestamp = adjustedTimestamp.NONE;
            weekend = true;
            adjusted = true;
            
        }
        
        //Check Clock In
        if (adjusted == false && punchType == punchType.CLOCK_IN){
            if(originalTimestamp.isAfter(ShiftStartLeft) && originalTimestamp.isBefore(ShiftStart)){
                adjustedTimestamp = adjustedTimestamp.SHIFT_START;
                adjustedTime = ShiftStartLeft;
                adjusted = true;
            }
            else if(originalTimestamp.isAfter(ShiftStart) && originalTimestamp.isBefore(GraceStart)){
                adjustedTimestamp = adjustedTimestamp.SHIFT_START;
                adjustedTime = ShiftStart;
                adjusted = true;
            }
            else if(originalTimestamp.isEqual(ShiftStartRight) || originalTimestamp.isAfter(GraceStart) && originalTimestamp.isBefore(ShiftStartRight)){
                adjustedTimestamp = adjustedTimestamp.SHIFT_DOCK;
                adjustedTime = ShiftStartRight;
                adjusted = true;
            }
            else if(originalTimestamp.isAfter(LunchStart) && originalTimestamp.isBefore(LunchEnd)){
                adjustedTimestamp = adjustedTimestamp.LUNCH_STOP;
                adjustedTime = LunchEnd;
                adjusted = true;
            }
        }
        if(adjusted == false && punchType == punchType.CLOCK_OUT || punchType == punchType.TIME_OUT){
            if (originalTimestamp.isBefore(ShiftEndRight) && originalTimestamp.isAfter(ShiftEnd)){
                adjustedTimestamp = adjustedTimestamp.SHIFT_STOP;
                adjustedTime = ShiftEnd;
                adjusted = true;
            }
            else if(originalTimestamp.isBefore(ShiftEnd) && originalTimestamp.isAfter(GraceStop)){
                adjustedTimestamp = adjustedTimestamp.SHIFT_STOP;
                adjustedTime = ShiftEnd;
                adjusted = true;
            }
            else if(originalTimestamp.isEqual(ShiftEndLeft) || originalTimestamp.isBefore(GraceStop) && originalTimestamp.isAfter(ShiftEndLeft)){
                adjustedTimestamp = adjustedTimestamp.SHIFT_DOCK;
                adjustedTime = ShiftEndLeft;
                adjusted = true;
            }
            else if(originalTimestamp.isAfter(LunchEnd) && originalTimestamp.isBefore(LunchEnd)){
                adjustedTimestamp = adjustedTimestamp.LUNCH_START;
                adjustedTime = LunchStart;
                adjusted = true;
            }
        }
        if((adjusted == false || weekend == true) && originalTimestamp.getMinute() % shift.getRoundInterval() == 0){
                adjustedTimestamp = adjustedTimestamp.NONE;
                adjustedTime = originalTimestamp.withSecond(0).withNano(0);
                adjusted = true;
        }
        
        if(adjusted == false || weekend == true){
            int roundMultiplier = originalTimestamp.getMinute();
            LocalDateTime intervalRound = originalTimestamp.withMinute((shiftroundint * roundMultiplier) + (shiftroundint / 2));
            
            
            if (originalTimestamp.isBefore(intervalRound)){
                adjustedTimestamp = adjustedTimestamp.INTERVAL_ROUND;
                adjustedTime = originalTimestamp.withMinute(0).plusMinutes(shiftroundint * roundMultiplier);
                adjusted = true;
            }
            else{
                adjustedTimestamp = adjustedTimestamp.INTERVAL_ROUND;
                adjustedTime = originalTimestamp.withMinute(0).plusMinutes(shiftroundint * roundMultiplier + 1);
                adjusted = true;
            }
            
            adjustedTime = adjustedTime.withSecond(0).withNano(0);
        }
        if (adjusted = false){
            System.err.println("There appears to be an error.");
        }
    }
    
    
    // Prints the adjusted timestamp of the punch.
    public LocalTime printAdjusted(){
        return shiftStart;
    }
    
    // Getter methods
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
    
    // Prints the original timestamp of the punch in a formatted string.
    public String printOriginal() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        DayOfWeek dayOfTheWeek = originalTimestamp.getDayOfWeek(); 
        
        StringBuilder build = new StringBuilder();
        
        build.append("#").append(badge.getId())
             .append(" ")
             .append(punchType).append(": ")
             .append(dayOfTheWeek.name().substring(0, 3))
             .append(" ")
             .append(originalTimestamp.format(format));
        
        return build.toString();
    }

    @Override
    public String toString() {
        return printOriginal();
    }
}
