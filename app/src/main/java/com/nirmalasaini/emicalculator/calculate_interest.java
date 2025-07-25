package com.nirmalasaini.emicalculator;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class calculate_interest extends Base {

    ImageButton buttonDrawerToggle;

    EditText startDate, endDate, loanAmount, interestRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_calculate_interest);


        buttonDrawerToggle = findViewById(R.id.buttonDrawerToggle);

        buttonDrawerToggle.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        startDate = findViewById(R.id.startDate);
        endDate = findViewById(R.id.endDate);

        // Disable Keyboard
        startDate.setFocusable(false);
        startDate.setClickable(true);

        endDate.setFocusable(false);
        endDate.setClickable(true);

        // Start Date Picker
        startDate.setOnClickListener(v -> showDatePicker(startDate));

        // End Date Picker
        endDate.setOnClickListener(v -> showDatePicker(endDate));
    }

    private void showDatePicker(EditText targetField) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String formattedDate = String.format("%02d/%02d/%d",
                            selectedDay, selectedMonth + 1, selectedYear);
                    targetField.setText(formattedDate);
                },
                year, month, day
        );

        datePickerDialog.show();
    }
}


