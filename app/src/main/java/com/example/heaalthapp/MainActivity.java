package com.example.heaalthapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.heaalthapp.adapter.HealthMetricsAdapter;
import com.example.heaalthapp.data.entity.HealthMetrics;
import com.example.heaalthapp.databinding.ActivityMainBinding;
import com.example.heaalthapp.databinding.DialogAddMetricsBinding;
import com.example.heaalthapp.viewmodel.HealthViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private HealthViewModel healthViewModel;
    private HealthMetricsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupRecyclerView();
        setupViewModel();
        setupFab();
    }

    private void setupRecyclerView() {
        adapter = new HealthMetricsAdapter(healthMetrics -> {
            // Handle item click if needed
            Toast.makeText(this, "Selected: " + healthMetrics.getTimestamp(), Toast.LENGTH_SHORT).show();
        });

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
    }

    private void setupViewModel() {
        healthViewModel = new ViewModelProvider(this).get(HealthViewModel.class);
        healthViewModel.getAllMetrics().observe(this, healthMetrics -> {
            adapter.submitList(healthMetrics);
        });
    }

    private void setupFab() {
        binding.fabAddMetrics.setOnClickListener(v -> showAddMetricsDialog());
    }

    private void showAddMetricsDialog() {
        DialogAddMetricsBinding dialogBinding = DialogAddMetricsBinding.inflate(LayoutInflater.from(this));
        
        AlertDialog dialog = new MaterialAlertDialogBuilder(this)
                .setTitle("Add Health Metrics")
                .setView(dialogBinding.getRoot())
                .setPositiveButton("Save", null)
                .setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss())
                .create();

        dialog.setOnShowListener(dialogInterface -> {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
                if (validateInput(dialogBinding)) {
                    saveHealthMetrics(dialogBinding);
                    dialog.dismiss();
                }
            });
        });

        dialog.show();
    }

    private boolean validateInput(DialogAddMetricsBinding binding) {
        try {
            int heartRate = Integer.parseInt(binding.heartRateInput.getText().toString());
            int systolicPressure = Integer.parseInt(binding.systolicPressureInput.getText().toString());
            int diastolicPressure = Integer.parseInt(binding.diastolicPressureInput.getText().toString());
            int sleepDuration = Integer.parseInt(binding.sleepDurationInput.getText().toString());
            int sleepQuality = Integer.parseInt(binding.sleepQualityInput.getText().toString());
            int waterIntake = Integer.parseInt(binding.waterIntakeInput.getText().toString());
            int exerciseDuration = Integer.parseInt(binding.exerciseDurationInput.getText().toString());
            String exerciseType = binding.exerciseTypeInput.getText().toString();

            if (heartRate <= 0 || systolicPressure <= 0 || diastolicPressure <= 0 ||
                sleepDuration <= 0 || sleepQuality < 1 || sleepQuality > 10 ||
                waterIntake <= 0 || exerciseDuration <= 0 || exerciseType.isEmpty()) {
                Toast.makeText(this, "Please enter valid values", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please fill all fields with valid numbers", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void saveHealthMetrics(DialogAddMetricsBinding binding) {
        int heartRate = Integer.parseInt(binding.heartRateInput.getText().toString());
        int systolicPressure = Integer.parseInt(binding.systolicPressureInput.getText().toString());
        int diastolicPressure = Integer.parseInt(binding.diastolicPressureInput.getText().toString());
        int sleepDuration = Integer.parseInt(binding.sleepDurationInput.getText().toString());
        int sleepQuality = Integer.parseInt(binding.sleepQualityInput.getText().toString());
        int waterIntake = Integer.parseInt(binding.waterIntakeInput.getText().toString());
        int exerciseDuration = Integer.parseInt(binding.exerciseDurationInput.getText().toString());
        String exerciseType = binding.exerciseTypeInput.getText().toString();

        HealthMetrics healthMetrics = new HealthMetrics(
                new Date(),
                heartRate,
                systolicPressure,
                diastolicPressure,
                sleepDuration,
                sleepQuality,
                waterIntake,
                exerciseDuration,
                exerciseType
        );

        healthViewModel.insert(healthMetrics);
        Toast.makeText(this, "Health metrics saved", Toast.LENGTH_SHORT).show();
    }
}