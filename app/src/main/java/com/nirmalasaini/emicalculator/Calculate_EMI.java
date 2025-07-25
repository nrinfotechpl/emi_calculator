package com.nirmalasaini.emicalculator;

import android.content.Intent;
import android.os.Bundle;

import java.text.DecimalFormat;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Calculate_EMI extends AppCompatActivity {

    EditText etPrincipal, etInterest, etDuration;
    Switch switchDurationType;

    TextView tvEmi, tvTotalAmount, tvInterestAmount;
    DrawerLayout drawerLayout;
    ImageButton buttonDrawerToggle;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_emi);

        // Initialize views

        drawerLayout = findViewById(R.id.drawerLayout);
        buttonDrawerToggle = findViewById(R.id.buttonDrawerToggle);
        navigationView = findViewById(R.id.navigationView);

        // Toggle button opens drawer
        buttonDrawerToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.open();
            }
        });

        // Navigation drawer item handling
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.Home) {
                    Intent intent = new Intent(Calculate_EMI.this, MainActivity.class);
                    startActivity(intent);
                }
                else if (itemId == R.id.SendWhatsapp) {
                    Intent intent = new Intent(Calculate_EMI.this, send_whatsapp.class);
                    startActivity(intent);
                }
                else if (itemId == R.id.CalculateCash) {
                    Intent intent = new Intent(Calculate_EMI.this, Calculate_Cash.class);
                    startActivity(intent);
                }
                else if (itemId == R.id.CalculateEMI) {
                    Toast.makeText(Calculate_EMI.this, "Already on Calculate EMI screen", Toast.LENGTH_SHORT).show();
                }
                else if (itemId == R.id.ContactUs) {
                    Intent intent = new Intent(Calculate_EMI.this, Contact_Us.class);
                    startActivity(intent);
                }

                drawerLayout.close();
                return true;
            }
        });

        // Initialize UI components
        etPrincipal = findViewById(R.id.etPrincipal);
        etInterest = findViewById(R.id.etInterest);
        etDuration = findViewById(R.id.etDuration);
        switchDurationType = findViewById(R.id.switchDurationType);
        tvEmi = findViewById(R.id.tvEmi);
        tvTotalAmount = findViewById(R.id.tvTotalAmount);
        tvInterestAmount = findViewById(R.id.tvInterestAmount);

        // Add TextWatchers
        etPrincipal.addTextChangedListener(watcher);
        etInterest.addTextChangedListener(watcher);
        etDuration.addTextChangedListener(watcher);

        // Switch listener
        switchDurationType.setOnCheckedChangeListener((buttonView, isChecked) -> calculateEMI());
    }

    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            calculateEMI();
        }

        @Override
        public void afterTextChanged(Editable s) {}
    };

    private void calculateEMI() {
        double principal = parse(etPrincipal);
        double annualRate = parse(etInterest);
        int durationInput = (int) parse(etDuration);

        if (principal <= 0 || annualRate <= 0 || durationInput <= 0) {
            tvEmi.setText("EMI: ₹ 0");
            tvTotalAmount.setText("Total Amount: ₹ 0");
            tvInterestAmount.setText("Total Interest: ₹ 0");
            return;
        }

        int durationInMonths = switchDurationType.isChecked()
                ? durationInput         // Switch ON: input is in months
                : durationInput * 12;   // Switch OFF: input is in years

        double monthlyRate = annualRate / (12 * 100); // Annual % → monthly decimal

        // EMI Formula
        double emi = (principal * monthlyRate * Math.pow(1 + monthlyRate, durationInMonths)) /
                (Math.pow(1 + monthlyRate, durationInMonths) - 1);

        double totalAmount = emi * durationInMonths;
        double interestAmount = totalAmount - principal;

        DecimalFormat df = new DecimalFormat("#,##0.00");

        tvEmi.setText("EMI: ₹ " + df.format(emi));
        tvTotalAmount.setText("Total Amount: ₹ " + df.format(totalAmount));
        tvInterestAmount.setText("Total Interest: ₹ " + df.format(interestAmount));
    }

    private double parse(EditText et) {
        try {
            return Double.parseDouble(et.getText().toString().trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}