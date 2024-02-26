/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.jsu.mcis.cs310.tas_sp24;

/**
 *
 * @author utsav
 */
import java.time.LocalTime;
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
    private Integer lunchDuration;
    private Integer shiftDuration;

    // Constructor accepting a Map<String, String> for shift-related information
    public Shift(Map<String, String> shiftInfo) {
        // Retrieve values from the map and convert them to their native types
        this.id = Integer.parseInt(shiftInfo.get("id"));
        this.description = shiftInfo.get("description");
        this.shiftStart = LocalTime.parse(shiftInfo.get("shiftstart"));
        this.shiftStop = LocalTime.parse(shiftInfo.get("shiftstop"));
        this.roundInterval = Integer.parseInt(shiftInfo.get("roundinterval"));
        this.gracePeriod = Integer.parseInt(shiftInfo.get("graceperiod"));
        this.dockPenalty = Integer.parseInt(shiftInfo.get("dockpenalty"));
        this.lunchStart = LocalTime.parse(shiftInfo.get("lunchstart"));
        this.lunchStop = LocalTime.parse(shiftInfo.get("lunchstop"));
        this.lunchThreshold = Integer.parseInt(shiftInfo.get("lunchthreshold"));
        this.lunchDuration = Integer.parseInt(shiftInfo.get("lunchduration"));
        this.shiftDuration = Integer.parseInt(shiftInfo.get("shiftduration"));
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

    public Integer getLunchDuration() {
        return lunchDuration;
    }

    public Integer getShiftDuration() {
        return shiftDuration;
    }

    @Override
    public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Shift{")
            .append("id=").append(id)
            .append(", description='").append(description).append('\'')
            .append(", shiftStart=").append(shiftStart)
            .append(", shiftStop=").append(shiftStop)
            .append(", roundInterval=").append(roundInterval)
            .append(", gracePeriod=").append(gracePeriod)
            .append(", dockPenalty=").append(dockPenalty)
            .append(", lunchStart=").append(lunchStart)
            .append(", lunchStop=").append(lunchStop)
            .append(", lunchThreshold=").append(lunchThreshold)
            .append(", lunchDuration=").append(lunchDuration)
            .append(", shiftDuration=").append(shiftDuration)
            .append('}');
    
    return builder.toString();
    }
}
