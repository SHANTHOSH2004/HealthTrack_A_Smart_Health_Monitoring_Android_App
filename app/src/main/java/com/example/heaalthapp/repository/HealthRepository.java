package com.example.heaalthapp.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.heaalthapp.data.HealthDatabase;
import com.example.heaalthapp.data.dao.HealthMetricsDao;
import com.example.heaalthapp.data.entity.HealthMetrics;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HealthRepository {
    private final HealthMetricsDao healthMetricsDao;
    private final ExecutorService executorService;

    public HealthRepository(Application application) {
        HealthDatabase database = HealthDatabase.getInstance(application);
        healthMetricsDao = database.healthMetricsDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public void insert(HealthMetrics healthMetrics) {
        executorService.execute(() -> healthMetricsDao.insert(healthMetrics));
    }

    public void update(HealthMetrics healthMetrics) {
        executorService.execute(() -> healthMetricsDao.update(healthMetrics));
    }

    public void delete(HealthMetrics healthMetrics) {
        executorService.execute(() -> healthMetricsDao.delete(healthMetrics));
    }

    public LiveData<List<HealthMetrics>> getAllMetrics() {
        return healthMetricsDao.getAllMetrics();
    }

    public LiveData<List<HealthMetrics>> getMetricsBetweenDates(Date startDate, Date endDate) {
        return healthMetricsDao.getMetricsBetweenDates(startDate, endDate);
    }

    public LiveData<Double> getAverageHeartRate(Date startDate, Date endDate) {
        return healthMetricsDao.getAverageHeartRate(startDate, endDate);
    }

    public LiveData<Double> getAverageSleepDuration(Date startDate, Date endDate) {
        return healthMetricsDao.getAverageSleepDuration(startDate, endDate);
    }

    public LiveData<List<HealthMetrics>> getAbnormalHeartRates(int threshold, int minThreshold) {
        return healthMetricsDao.getAbnormalHeartRates(threshold, minThreshold);
    }

    public LiveData<List<HealthMetrics>> getAbnormalBloodPressure(int threshold) {
        return healthMetricsDao.getAbnormalBloodPressure(threshold);
    }
} 