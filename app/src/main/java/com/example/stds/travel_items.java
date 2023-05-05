package com.example.stds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class travel_items extends AppCompatActivity {
    DBhelper db;
    ArrayList<String> To_Do;
    CustomAdapter customAdapter;
    RecyclerView rv;
    public static  TextView empty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_items);
        db=new DBhelper(this);
        rv=findViewById(R.id.rv);
        To_Do=new ArrayList<>();
        StoreTo_Do();
        customAdapter= new CustomAdapter(travel_items.this, To_Do);
        rv.setAdapter(customAdapter);
        rv.setLayoutManager(new LinearLayoutManager(travel_items.this));
         empty = findViewById(R.id.travel_empty);
        if (db.check_todo(db.getCurentUser())==0)
        {
            empty.setText("Empty");
        }

    }
    @SuppressLint("Range")
    void StoreTo_Do() {
        db=new DBhelper(this);
        String name=db.getCurentUser();
        Cursor cursor = db.readTravelUser(name);
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data !", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                To_Do.add(cursor.getString(cursor.getColumnIndex("To_Do")));
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu,menu);
        return  true ;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_note);
        Intent i = new Intent(getApplicationContext(),travel_page.class);
        startActivity(i);

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        recreate();
        Intent intent = new Intent(getApplicationContext(),home_page.class);
        startActivity(intent);
    }
}