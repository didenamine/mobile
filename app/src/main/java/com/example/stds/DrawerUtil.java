package com.example.stds;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class DrawerUtil {


    public static void setDrawer(AppCompatActivity activity, DrawerLayout drawerLayout, NavigationView navview, ActionBarDrawerToggle drawerToggle) {
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.loginb: {

                        break;
                    }
                    case R.id.logout: {
                        Toast.makeText(activity, "logged out", Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent(activity, login_page.class);
                        //activity.startActivity(intent);
                        DBhelper db = new DBhelper(activity);
                        db.deleteCurentUser();
                        db.close();

                        Intent intent = new Intent(activity, login_page.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        activity.startActivity(intent);
                        activity.finish();

                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    }

                }
                return false;
            }

        });

        }
}