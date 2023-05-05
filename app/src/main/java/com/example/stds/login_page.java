package com.example.stds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class login_page extends AppCompatActivity {
    DBhelper DB = new DBhelper(this);
    //public static String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        //sign up text that will take u to the sign up page
        TextView signup = findViewById(R.id.login_page_sign);

        EditText username = findViewById(R.id.login_page_username);
        //name=username.getText().toString();

        EditText password = findViewById(R.id.login_page_password);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup_intent = new Intent(login_page.this,signup_page.class);
                startActivity(signup_intent);
            }
        });
        Button login = findViewById(R.id.login_page_login) ;
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DB.checkuser(username.getText().toString(),password.getText().toString())==1){
                    DB.setCurentUser(username.getText().toString());
                    Toast.makeText(login_page.this, "Welcome "+username.getText().toString(), Toast.LENGTH_SHORT).show();
                    Intent home_intent = new Intent(login_page.this,home_page.class);
                    startActivity(home_intent);

                }
                else {
                    Toast.makeText(login_page.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });





    }

    @Override
    public void onBackPressed() {

    }
}