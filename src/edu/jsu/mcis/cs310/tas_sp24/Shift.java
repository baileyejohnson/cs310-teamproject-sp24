/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.jsu.mcis.cs310.tas_sp24;

/**
 *
 * @author utsav
 */
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Shift {
    private Integer id;
    private String description;
    private LocalTime shiftStart;
    private LocalTime shiftStop;
    private Integer roundInterval;
    private Integer gracePeriod;
    private Integer dockPenalty;
    private LocalTime lunchStart;
    private LocalTime lunchStop;
    private Integer lunchThreshold;
    private Duration lunchDuration;
    private Duration shiftDuration;

    // Constructor accepting a Map<String, String> for shift-related information
    public Shift(HashMap<String, String> shiftInfo) {
        // Retrieve values from the map and convert them to their native types
        this.id = Integer.valueOf((String) shiftInfo.get("id"));
        this.description = (String) shiftInfo.get("description");
        this.shiftStart = LocalTime.parse((String) shiftInfo.get("shiftStart"));
        this.shiftStop = LocalTime.parse((String) shiftInfo.get("shiftStop"));
        this.roundInterval = Integer.parseInt((String) shiftInfo.get("roundInterval"));
        this.gracePeriod = Integer.parseInt((String) shiftInfo.get("gracePeriod"));
        this.dockPenalty = Integer.parseInt((String) shiftInfo.get("dockPenalty"));
        this.lunchStart = LocalTime.parse((String) shiftInfo.get("lunchStart"));
        this.lunchStop = LocalTime.parse((String) shiftInfo.get("lunchStop"));
        this.lunchThreshold = Integer.parseInt((String) shiftInfo.get("lunchThreshold"));
        this.lunchDuration = Duration.between(lunchStart,lunchStop);
        this.shiftDuration = Duration.between(shiftStart,shiftStop);
    }

    // Getters for instance fields
    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LocalTime getShiftStart() {
        return shiftStart;
    }

    public LocalTime getShiftStop() {
        return shiftStop;
    }

    public Integer getRoundInterval() {
        return roundInterval;
    }

    public Integer getGracePeriod() {
        return gracePeriod;
    }

    public Integer getDockPenalty() {
        return dockPenalty;
    }

    public LocalTime getLunchStart() {
        return lunchStart;
    }

    public LocalTime getLunchStop() {
        return lunchStop;
    }

    public Integer getLunchThreshold() {
        return lunchThreshold;
    }

    public Duration getLunchDuration() {
        return lunchDuration;
    }

    public Duration getShiftDuration() {
        return shiftDuration;
    }

    @Override
    public String toString() {
    StringBuilder builder = new StringBuilder();
     builder.append(description).append(": ")
        .append(shiftStart).append(" - ")
        .append(shiftStop).append(" (")
        .append(shiftDuration.toMinutes()).append(" minutes); Lunch: ")
        .append(lunchStart).append(" - ")
        .append(lunchStop).append(" (")
        .append(lunchDuration.toMinutes()).append(" minutes)");
      
    
    return builder.toString();
    }
}
