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
    private PunchAdjustmentType adjustmenttype; 
    private LocalDateTime adjustedTimestamp;
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
        this.adjustmenttype = null;
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
      
        LocalDateTime origtimestamp = originalTimestamp;

        adjustmenttype = null;
        adjustedTimestamp = null;
        boolean isWeekend = false;
        DayOfWeek day = origtimestamp.getDayOfWeek();

        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            isWeekend = true;
        }

        int interval = shift.getRoundInterval();
        int grace = shift.getGracePeriod();
        int dock = shift.getDockPenalty();

        LocalTime shiftStart = shift.getShiftStart();
        LocalTime shiftStop = shift.getShiftStop();
        LocalTime lunchStart = shift.getLunchStart();
        LocalTime lunchStop = shift.getLunchStop();

        LocalDateTime shiftStartDateTime = origtimestamp.with(shiftStart);
        LocalDateTime shiftStopDateTime = origtimestamp.with(shiftStop);
        LocalDateTime lunchStartDateTime = origtimestamp.with(lunchStart);
        LocalDateTime lunchStopDateTime = origtimestamp.with(lunchStop);

        LocalDateTime shiftStartInterval = shiftStartDateTime.minusMinutes(interval);
        LocalDateTime shiftStartGrace = shiftStartDateTime.plusMinutes(grace);
        LocalDateTime shiftStartDock = shiftStartDateTime.plusMinutes(dock);

        LocalDateTime shiftStopInterval = shiftStopDateTime.plusMinutes(interval);
        LocalDateTime shiftStopGrace = shiftStopDateTime.minusMinutes(grace);
        LocalDateTime shiftStopDock = shiftStopDateTime.minusMinutes(dock);

        if (punchType == EventType.CLOCK_IN) {
            if (origtimestamp.isAfter(shiftStartInterval.minusSeconds(1)) && origtimestamp.isBefore(shiftStartDateTime)) {
                adjustedTimestamp = shiftStartDateTime;
                adjustmenttype = PunchAdjustmentType.SHIFT_START;
            } else if (origtimestamp.isAfter(shiftStartDateTime) && origtimestamp.isBefore(shiftStartGrace)) {
                adjustedTimestamp = shiftStartDateTime;
                adjustmenttype = PunchAdjustmentType.SHIFT_START;
            } else if (origtimestamp.isAfter(shiftStartGrace) && origtimestamp.isBefore(shiftStartDock.plusSeconds(1))) {
                adjustedTimestamp = shiftStartDock;
                adjustmenttype = PunchAdjustmentType.SHIFT_DOCK;
            } else if (!isWeekend && origtimestamp.isAfter(lunchStartDateTime) && origtimestamp.isBefore(lunchStopDateTime)) {
                adjustedTimestamp = lunchStopDateTime;
                adjustmenttype = PunchAdjustmentType.LUNCH_STOP;
            }
        } else if (punchType == EventType.CLOCK_OUT || punchType == EventType.TIME_OUT) {
            if (origtimestamp.isAfter(shiftStopDateTime) && origtimestamp.isBefore(shiftStopInterval.plusSeconds(1))) {
                adjustedTimestamp = shiftStopDateTime;
                adjustmenttype= PunchAdjustmentType.SHIFT_STOP;
            } else if (origtimestamp.isBefore(shiftStopDateTime) && origtimestamp.isAfter(shiftStopGrace)) {
                adjustedTimestamp = shiftStopDateTime;
                adjustmenttype = PunchAdjustmentType.SHIFT_STOP;
            } else if (origtimestamp.isBefore(shiftStopGrace) && origtimestamp.isAfter(shiftStopDock.minusSeconds(1))) {
                adjustedTimestamp = shiftStopDock;
                adjustmenttype = PunchAdjustmentType.SHIFT_DOCK;
            } else if (!isWeekend && origtimestamp.isAfter(lunchStartDateTime) && origtimestamp.isBefore(lunchStopDateTime)) {
                adjustedTimestamp = lunchStartDateTime;
                adjustmenttype = PunchAdjustmentType.LUNCH_START;
            }
        }
        
        if (adjustmenttype == null) {
	    int adjustedMinute;
            int minutes = origtimestamp.getMinute();
            
            if((minutes % interval) < (interval / 2)){
                adjustedMinute = (Math.round(minutes / interval) * interval);
            }else{
                adjustedMinute = (Math.round(minutes / interval) * interval) + interval;
            }
            
           
            if ((adjustedMinute / 60) == 1) {
	        adjustmenttype = PunchAdjustmentType.INTERVAL_ROUND;
	        adjustedTimestamp = origtimestamp.withHour(origtimestamp.getHour() + 1).withMinute(0).withSecond(0).withNano(0);
	    } else {
	        adjustmenttype = PunchAdjustmentType.INTERVAL_ROUND;
	        adjustedTimestamp = origtimestamp.withMinute(adjustedMinute).withSecond(0).withNano(0);
	    }
            if((originalTimestamp.getMinute() == adjustedTimestamp.getMinute() ) && (originalTimestamp.getHour() == adjustedTimestamp.getHour())){
                adjustedTimestamp = origtimestamp.withSecond(0).withNano(0);
                adjustmenttype = PunchAdjustmentType.NONE;
            }
           
	}
        
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

    public PunchAdjustmentType getAdjustmentType() {
        return adjustmenttype;
    }
    
    public LocalDateTime getAdjustedTimestamp(){
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
    
      public String printAdjusted(){
        StringBuilder build = new StringBuilder();
        
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        DayOfWeek dayOfTheWeek = originalTimestamp.getDayOfWeek(); 
    
        build.append("#")
             .append(badge.getId()).append(" ")
             .append(punchType).append(": ")
             .append(dayOfTheWeek.name().substring(0, 3))
             .append(" ")
             .append(adjustedTimestamp.format(format))
             .append(" (").append(adjustmenttype).append(")");
    
        return build.toString();
 
    }
    
    // Prints the adjusted timestamp of the punch.
    /*public LocalTime printAdjusted(){
        return shiftStart;
    }*/
    
    
    @Override
    public String toString() {
        return printOriginal();
    }
}
