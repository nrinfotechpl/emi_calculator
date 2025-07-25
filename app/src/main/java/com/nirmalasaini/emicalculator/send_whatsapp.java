package com.nirmalasaini.emicalculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.net.URLEncoder;

public class send_whatsapp extends AppCompatActivity {

    TextInputEditText etPhoneNumber;
    Button btnSendWhatsapp;

    DrawerLayout drawerLayout;
    ImageButton buttonDrawerToggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_whatsapp);

        // Initialize views
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        btnSendWhatsapp = findViewById(R.id.btnSendWhatsapp);
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
                    Intent intent = new Intent(send_whatsapp.this, MainActivity.class);
                    startActivity(intent);
                }
                else if (itemId == R.id.SendWhatsapp) {
                    Toast.makeText(send_whatsapp.this, "Already on WhatsApp screen", Toast.LENGTH_SHORT).show();
                }
                else if (itemId == R.id.CalculateCash) {
                    Intent intent = new Intent(send_whatsapp.this, Calculate_Cash.class);
                    startActivity(intent);
                }
                else if (itemId == R.id.CalculateEMI) {
                    Intent intent = new Intent(send_whatsapp.this, Calculate_EMI.class);
                    startActivity(intent);
                } else if (itemId == R.id.ContactUs) {
                    Intent intent = new Intent(send_whatsapp.this, Contact_Us.class);
                    startActivity(intent);
                }

                drawerLayout.close();
                return true;
            }
        });

        // WhatsApp send button
        btnSendWhatsapp.setOnClickListener(v -> {
            String phoneNumber = etPhoneNumber.getText().toString().trim();

            if (phoneNumber.length() < 10 || !phoneNumber.matches("\\d+")) {
                Toast.makeText(this, "Please enter a valid phone number", Toast.LENGTH_SHORT).show();
                return;
            }

            String message = "Hello";
            try {
                String url = "https://api.whatsapp.com/send?phone=91" + phoneNumber + "&text=" + URLEncoder.encode(message, "UTF-8");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, "WhatsApp not installed or error occurred", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });
    }
}
