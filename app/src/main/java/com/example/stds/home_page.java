package com.example.stds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

public class home_page extends AppCompatActivity {
    DBhelper DB = new DBhelper(this);
    ActionBarDrawerToggle drawerToggle;
    DrawerLayout drawerLayout;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);


        //First page
        Button first_page= findViewById(R.id.first);
        first_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent first_intent = new Intent(home_page.this, first_page.class);

                startActivity(first_intent);
            }
        });

        //second page
        Button second_page= findViewById(R.id.second);
        second_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent travel_intent = new Intent(home_page.this, travel_items.class);
                startActivity(travel_intent);
            }
        });
        //third page
        Button third_page= findViewById(R.id.third);
        third_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent third_intent = new Intent(getApplicationContext(), third_page.class);
                startActivity(third_intent);
            }
        });
        //fourth page
        Button study_page= findViewById(R.id.fourth);
        study_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent study_intent = new Intent(getApplicationContext(), study_page.class);
                startActivity(study_intent);
            }
        });

        drawerLayout=findViewById(R.id.drawer);
        NavigationView navview=findViewById(R.id.navview);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        DrawerUtil.setDrawer(this,drawerLayout,navview,drawerToggle);
        


    }

}