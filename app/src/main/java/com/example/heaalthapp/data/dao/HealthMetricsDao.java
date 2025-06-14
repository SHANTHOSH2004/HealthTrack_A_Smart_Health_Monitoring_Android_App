package com.example.heaalthapp.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.example.heaalthapp.data.entity.HealthMetrics;
import java.util.Date;
import java.util.List;

@Dao
public interface HealthMetricsDao {
    @Insert
    void insert(HealthMetrics healthMetrics);

    @Update
    void update(HealthMetrics healthMetrics);

    @Delete
    void delete(HealthMetrics healthMetrics);

    @Query("SELECT * FROM health_metrics ORDER BY timestamp DESC")
    LiveData<List<HealthMetrics>> getAllMetrics();

    @Query("SELECT * FROM health_metrics WHERE timestamp BETWEEN :startDate AND :endDate ORDER BY timestamp DESC")
    LiveData<List<HealthMetrics>> getMetricsBetweenDates(Date startDate, Date endDate);

    @Query("SELECT AVG(heartRate) FROM health_metrics WHERE timestamp BETWEEN :startDate AND :endDate")
    LiveData<Double> getAverageHeartRate(Date startDate, Date endDate);

    @Query("SELECT AVG(sleepDuration) FROM health_metrics WHERE timestamp BETWEEN :startDate AND :endDate")
    LiveData<Double> getAverageSleepDuration(Date startDate, Date endDate);

    @Query("SELECT * FROM health_metrics WHERE heartRate > :threshold OR heartRate < :minThreshold")
    LiveData<List<HealthMetrics>> getAbnormalHeartRates(int threshold, int minThreshold);

    @Query("SELECT * FROM health_metrics WHERE systolicPressure > :threshold OR diastolicPressure > :threshold")
    LiveData<List<HealthMetrics>> getAbnormalBloodPressure(int threshold);
} 