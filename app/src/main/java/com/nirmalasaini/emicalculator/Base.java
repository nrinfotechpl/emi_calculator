package com.nirmalasaini.emicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import androidx.core.view.GravityCompat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Base extends AppCompatActivity {

    protected DrawerLayout drawerLayout;
    protected NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);  // Use base layout

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        setupDrawerMenu();
    }

    protected void setContentLayout(int layoutResID) {
        FrameLayout frameLayout = findViewById(R.id.contentFrame);
        getLayoutInflater().inflate(layoutResID, frameLayout, true);
    }

    private void setupDrawerMenu() {
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            drawerLayout.closeDrawers();

            int id = menuItem.getItemId();

            if (id == R.id.Home) {
                startActivity(new Intent(this, MainActivity.class));
            } else if (id == R.id.SendWhatsapp) {
                startActivity(new Intent(this, send_whatsapp.class));
            }


            return true;
        });
    }
}
