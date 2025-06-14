package com.example.heaalthapp.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.heaalthapp.data.entity.HealthMetrics;
import com.example.heaalthapp.repository.HealthRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HealthViewModel extends AndroidViewModel {
    private final HealthRepository repository;
    private final LiveData<List<HealthMetrics>> allMetrics;

    public HealthViewModel(Application application) {
        super(application);
        repository = new HealthRepository(application);
        allMetrics = repository.getAllMetrics();
    }

    public void insert(HealthMetrics healthMetrics) {
        repository.insert(healthMetrics);
    }

    public void update(HealthMetrics healthMetrics) {
        repository.update(healthMetrics);
    }

    public void delete(HealthMetrics healthMetrics) {
        repository.delete(healthMetrics);
    }

    public LiveData<List<HealthMetrics>> getAllMetrics() {
        return allMetrics;
    }

    public LiveData<List<HealthMetrics>> getMetricsForToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date startDate = calendar.getTime();
        
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date endDate = calendar.getTime();
        
        return repository.getMetricsBetweenDates(startDate, endDate);
    }

    public LiveData<Double> getAverageHeartRate(Date startDate, Date endDate) {
        return repository.getAverageHeartRate(startDate, endDate);
    }

    public LiveData<Double> getAverageSleepDuration(Date startDate, Date endDate) {
        return repository.getAverageSleepDuration(startDate, endDate);
    }

    public LiveData<List<HealthMetrics>> getAbnormalHeartRates() {
        return repository.getAbnormalHeartRates(100, 60); // Example thresholds
    }

    public LiveData<List<HealthMetrics>> getAbnormalBloodPressure() {
        return repository.getAbnormalBloodPressure(140); // Example threshold
    }
} 