package com.example.stds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class travel_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel_page);

        DBhelper db=new DBhelper(this);

        Button bs=findViewById(R.id.bs);
        bs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et=findViewById(R.id.et);
                String to_do= String.valueOf(et.getText());
                boolean b=db.insertTravelUser(db.getCurentUser(),to_do,0);
                if(b==true){
                    Toast.makeText(travel_page.this, "added", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(travel_page.this,travel_items.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(travel_page.this, "failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }



}