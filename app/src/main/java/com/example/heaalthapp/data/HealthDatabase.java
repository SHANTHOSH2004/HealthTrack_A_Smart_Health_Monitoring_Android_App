package com.example.heaalthapp.data;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.example.heaalthapp.data.dao.HealthMetricsDao;
import com.example.heaalthapp.data.entity.HealthMetrics;
import com.example.heaalthapp.util.DateConverter;

@Database(entities = {HealthMetrics.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class HealthDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "health_database";
    private static HealthDatabase instance;

    public abstract HealthMetricsDao healthMetricsDao();

    public static synchronized HealthDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                context.getApplicationContext(),
                HealthDatabase.class,
                DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
        }
        return instance;
    }
} 