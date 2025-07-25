package com.nirmalasaini.emicalculator;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.content.Intent;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageButton buttonDrawerToggle;

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        buttonDrawerToggle = findViewById(R.id.buttonDrawerToggle);
        navigationView = findViewById(R.id.navigationView);

        buttonDrawerToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.open();
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.Home) {
                    Toast.makeText(MainActivity.this, "Already at Home Screen", Toast.LENGTH_SHORT).show();
                }

                else if (itemId == R.id.SendWhatsapp) {
                    Intent intent = new Intent(MainActivity.this, send_whatsapp.class);
                    startActivity(intent);
                }

                else if (itemId == R.id.CalculateCash) {
                    Intent intent = new Intent(MainActivity.this, Calculate_Cash.class);
                    startActivity(intent);
                }

                else if (itemId == R.id.CalculateEMI) {
                    Intent intent = new Intent(MainActivity.this, Calculate_EMI.class);
                    startActivity(intent);
                }

                else if (itemId == R.id.CalculateINT) {
                    Intent intent = new Intent(MainActivity.this, calculate_interest.class);
                    startActivity(intent);
                }


                if (itemId == R.id.ContactUs) {
                    Intent intent = new Intent(MainActivity.this, Contact_Us.class);
                    startActivity(intent);
                }


                drawerLayout.close();
                return false;
            }
        });
    }
}