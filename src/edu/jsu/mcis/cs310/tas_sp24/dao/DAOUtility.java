package edu.jsu.mcis.cs310.tas_sp24.dao;

import java.time.*;
import java.util.*;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import com.github.cliftonlabs.json_simple.*;
import edu.jsu.mcis.cs310.tas_sp24.EventType;
import edu.jsu.mcis.cs310.tas_sp24.Punch;
import edu.jsu.mcis.cs310.tas_sp24.PunchAdjustmentType;
import edu.jsu.mcis.cs310.tas_sp24.Shift;

import edu.jsu.mcis.cs310.tas_sp24.Punch;


/**
 * 
 * Utility class for DAOs.  This is a final, non-constructable class containing
 * common DAO logic and other repeated and/or standardized code, refactored into
 * individual static methods.
 * 
 */
public final class DAOUtility {
    
     public static int calculateTotalMinutes(ArrayList<Punch> dailypunchlist, Shift shift) {
        long totalMinutes = 0;
        long shiftDuration = 0;


        boolean shiftStarted = false;
        boolean shiftEnded = false;
        boolean timeoutEncountered = false;

        Punch shiftStart = null;
        Punch shiftEnd = null;

        for (Punch punch : dailypunchlist) {
            EventType punchType = punch.getPunchtype();
            PunchAdjustmentType adjustmentType = punch.getAdjustedTimestamp();

            // Check if the punch is related to lunch, then skip
            if (adjustmentType == PunchAdjustmentType.LUNCH_START || adjustmentType == PunchAdjustmentType.LUNCH_STOP) {
                continue;
            }

            // Process shift start and stop punches
            if (adjustmentType == PunchAdjustmentType.SHIFT_START) {
                shiftStart = punch;
                shiftStarted = true;
            } else if (adjustmentType == PunchAdjustmentType.SHIFT_STOP) {
                shiftEnd = punch;
                shiftEnded = true;
            }

            // Process clock in and clock out punches
            if (punchType == EventType.CLOCK_IN && !shiftStarted) {
                shiftStart = punch;
                shiftStarted = true;
            } else if (punchType == EventType.CLOCK_OUT && !shiftEnded) {
                shiftEnd = punch;
                shiftEnded = true;
            } else if (punchType == EventType.TIME_OUT) {
                timeoutEncountered = true;
                break; // Stop processing further punches if timeout encountered
            }

            // If both shift start and stop are recorded, calculate shift duration
            if (shiftStarted && shiftEnded) {
                shiftDuration = (shiftEnd.getAdjustedTimestamp().getTime() - shiftStart.getAdjustedTimestamp().getTime()) / (60 * 1000);
                break; // Stop processing further punches
            }
        }

        // If timeout encountered or either shift start or stop is missing, return 0
        if (timeoutEncountered || !shiftStarted || !shiftEnded) {
            return 0;
        }

        // Deduct lunch break if applicable
        if (shiftDuration > shift.getLunchThreshold()) {
            totalMinutes = shiftDuration - shift.getLunchDuration().toMinutes();
        } else {
            totalMinutes = shiftDuration;
        }

        return (int) totalMinutes;
    }
}

