package com.example.stds;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

public class start_page extends AppCompatActivity {
    DBhelper DB = new DBhelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        Button start = findViewById(R.id.start);



        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int user = DB.checkCurentUser();
                if (user==0){
                    Intent intent  = new Intent(getApplicationContext(),login_page.class);
                startActivity(intent);
                }
                else{
                    Intent intent  = new Intent(getApplicationContext(),home_page.class);

                    startActivity(intent);

                }
            }
        });
    }
}