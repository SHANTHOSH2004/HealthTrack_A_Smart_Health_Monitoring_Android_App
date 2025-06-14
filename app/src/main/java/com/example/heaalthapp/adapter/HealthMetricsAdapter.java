package com.example.heaalthapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.heaalthapp.R;
import com.example.heaalthapp.data.entity.HealthMetrics;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class HealthMetricsAdapter extends ListAdapter<HealthMetrics, HealthMetricsAdapter.HealthMetricsViewHolder> {
    private final OnItemClickListener listener;
    private final SimpleDateFormat dateFormat;

    public interface OnItemClickListener {
        void onItemClick(HealthMetrics healthMetrics);
    }

    public HealthMetricsAdapter(OnItemClickListener listener) {
        super(new DiffUtil.ItemCallback<HealthMetrics>() {
            @Override
            public boolean areItemsTheSame(@NonNull HealthMetrics oldItem, @NonNull HealthMetrics newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull HealthMetrics oldItem, @NonNull HealthMetrics newItem) {
                return oldItem.getTimestamp().equals(newItem.getTimestamp()) &&
                       oldItem.getHeartRate() == newItem.getHeartRate() &&
                       oldItem.getSystolicPressure() == newItem.getSystolicPressure() &&
                       oldItem.getDiastolicPressure() == newItem.getDiastolicPressure() &&
                       oldItem.getSleepDuration() == newItem.getSleepDuration() &&
                       oldItem.getSleepQuality() == newItem.getSleepQuality() &&
                       oldItem.getWaterIntake() == newItem.getWaterIntake() &&
                       oldItem.getExerciseDuration() == newItem.getExerciseDuration() &&
                       oldItem.getExerciseType().equals(newItem.getExerciseType());
            }
        });
        this.listener = listener;
        this.dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault());
    }

    @NonNull
    @Override
    public HealthMetricsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_health_metrics, parent, false);
        return new HealthMetricsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HealthMetricsViewHolder holder, int position) {
        HealthMetrics current = getItem(position);
        holder.bind(current, listener);
    }

    class HealthMetricsViewHolder extends RecyclerView.ViewHolder {
        private final TextView timestampText;
        private final TextView heartRateText;
        private final TextView bloodPressureText;
        private final TextView sleepText;
        private final TextView waterIntakeText;
        private final TextView exerciseText;

        public HealthMetricsViewHolder(@NonNull View itemView) {
            super(itemView);
            timestampText = itemView.findViewById(R.id.timestamp_text);
            heartRateText = itemView.findViewById(R.id.heart_rate_text);
            bloodPressureText = itemView.findViewById(R.id.blood_pressure_text);
            sleepText = itemView.findViewById(R.id.sleep_text);
            waterIntakeText = itemView.findViewById(R.id.water_intake_text);
            exerciseText = itemView.findViewById(R.id.exercise_text);
        }

        public void bind(HealthMetrics healthMetrics, OnItemClickListener listener) {
            timestampText.setText(dateFormat.format(healthMetrics.getTimestamp()));
            heartRateText.setText(String.format(Locale.getDefault(), "Heart Rate: %d BPM", healthMetrics.getHeartRate()));
            bloodPressureText.setText(String.format(Locale.getDefault(), "Blood Pressure: %d/%d mmHg",
                    healthMetrics.getSystolicPressure(), healthMetrics.getDiastolicPressure()));
            sleepText.setText(String.format(Locale.getDefault(), "Sleep: %d min (Quality: %d/10)",
                    healthMetrics.getSleepDuration(), healthMetrics.getSleepQuality()));
            waterIntakeText.setText(String.format(Locale.getDefault(), "Water Intake: %d ml", healthMetrics.getWaterIntake()));
            exerciseText.setText(String.format(Locale.getDefault(), "Exercise: %d min - %s",
                    healthMetrics.getExerciseDuration(), healthMetrics.getExerciseType()));

            itemView.setOnClickListener(v -> listener.onItemClick(healthMetrics));
        }
    }
} 