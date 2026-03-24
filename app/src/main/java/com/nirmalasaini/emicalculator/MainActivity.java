package com.nirmalasaini.emicalculator;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageButton buttonDrawerToggle;
    LinearLayout lvEmi, lvWhatsapp, lvCash, lvInt;

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        buttonDrawerToggle = findViewById(R.id.buttonDrawerToggle);
        navigationView = findViewById(R.id.navigationView);

        lvCash = findViewById(R.id.lvCash);
        lvEmi = findViewById(R.id.lvEmi);
        lvInt = findViewById(R.id.lvInt);
        lvWhatsapp = findViewById(R.id.lvWhatsapp);

        buttonDrawerToggle.setOnClickListener(view -> drawerLayout.open());
        navigationView.setNavigationItemSelectedListener(item -> {
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
        });

        lvWhatsapp.setOnClickListener(v -> {
            Intent intent = new Intent(this, send_whatsapp.class);
            startActivity(intent);
        });
        lvEmi.setOnClickListener(v -> {
            Intent intent = new Intent(this, Calculate_EMI.class);
            startActivity(intent);
        });
        lvInt.setOnClickListener(v -> {
            Intent intent = new Intent(this, calculate_interest.class);
            startActivity(intent);
        });
        lvCash.setOnClickListener(v -> {
            Intent intent = new Intent(this, Calculate_Cash.class);
            startActivity(intent);
        });
    }
}