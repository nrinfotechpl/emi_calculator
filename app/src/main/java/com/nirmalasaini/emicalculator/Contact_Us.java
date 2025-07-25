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

import java.net.URLEncoder;

public class Contact_Us extends AppCompatActivity {

    EditText etPhoneNumber;
    Button btnSendWhatsapp;

    DrawerLayout drawerLayout;
    ImageButton buttonDrawerToggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

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
                    Intent intent = new Intent(Contact_Us.this, MainActivity.class);
                    startActivity(intent);
                }
                else if (itemId == R.id.SendWhatsapp) {
                    Intent intent = new Intent(Contact_Us.this, send_whatsapp.class);
                    startActivity(intent);
                }
                else if (itemId == R.id.CalculateCash) {
                    Intent intent = new Intent(Contact_Us.this, Calculate_Cash.class);
                    startActivity(intent);
                }
                else if (itemId == R.id.CalculateEMI) {
                    Intent intent = new Intent(Contact_Us.this, Calculate_EMI.class);
                    startActivity(intent);
                }
                else if (itemId == R.id.ContactUs) {
                    Toast.makeText(Contact_Us.this, "Already on Contact Us screen", Toast.LENGTH_SHORT).show();
                }

                drawerLayout.close();
                return true;
            }
        });


    }
}
