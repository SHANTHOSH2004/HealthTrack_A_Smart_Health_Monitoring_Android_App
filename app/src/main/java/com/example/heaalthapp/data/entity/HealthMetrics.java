package com.example.heaalthapp.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import com.example.heaalthapp.util.DateConverter;
import java.util.Date;

@Entity(tableName = "health_metrics")
@TypeConverters(DateConverter.class)
public class HealthMetrics {
    @PrimaryKey(autoGenerate = true)
    private long id;
    
    private Date timestamp;
    private int heartRate;
    private int systolicPressure;
    private int diastolicPressure;
    private int sleepDuration;
    private int sleepQuality;
    private int waterIntake;
    private int exerciseDuration;
    private String exerciseType;

    public HealthMetrics(Date timestamp, int heartRate, int systolicPressure, 
                        int diastolicPressure, int sleepDuration, int sleepQuality,
                        int waterIntake, int exerciseDuration, String exerciseType) {
        this.timestamp = timestamp;
        this.heartRate = heartRate;
        this.systolicPressure = systolicPressure;
        this.diastolicPressure = diastolicPressure;
        this.sleepDuration = sleepDuration;
        this.sleepQuality = sleepQuality;
        this.waterIntake = waterIntake;
        this.exerciseDuration = exerciseDuration;
        this.exerciseType = exerciseType;
    }

    // Getters and Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    
    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
    
    public int getHeartRate() { return heartRate; }
    public void setHeartRate(int heartRate) { this.heartRate = heartRate; }
    
    public int getSystolicPressure() { return systolicPressure; }
    public void setSystolicPressure(int systolicPressure) { this.systolicPressure = systolicPressure; }
    
    public int getDiastolicPressure() { return diastolicPressure; }
    public void setDiastolicPressure(int diastolicPressure) { this.diastolicPressure = diastolicPressure; }
    
    public int getSleepDuration() { return sleepDuration; }
    public void setSleepDuration(int sleepDuration) { this.sleepDuration = sleepDuration; }
    
    public int getSleepQuality() { return sleepQuality; }
    public void setSleepQuality(int sleepQuality) { this.sleepQuality = sleepQuality; }
    
    public int getWaterIntake() { return waterIntake; }
    public void setWaterIntake(int waterIntake) { this.waterIntake = waterIntake; }
    
    public int getExerciseDuration() { return exerciseDuration; }
    public void setExerciseDuration(int exerciseDuration) { this.exerciseDuration = exerciseDuration; }
    
    public String getExerciseType() { return exerciseType; }
    public void setExerciseType(String exerciseType) { this.exerciseType = exerciseType; }
} 