package com.example.stds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class first_page extends AppCompatActivity {
    DBhelper DB = new DBhelper(this);
    public static  TextView first_page_empty;
    //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,values);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_page);
      first_page_empty = findViewById(R.id.first_page_empty);
        String user = DB.getCurentUser();

        if (DB.check_notes(user)==0){
            first_page_empty.setText("Empty");
        }
        else{
            first_page_empty.setText("");
        }
        RecyclerView rec = findViewById(R.id.rec);
        List<first_page_notes_items> items= new ArrayList<first_page_notes_items>();
        //Here we add the nots
        items = DB.getNotes(user);
        rec.setLayoutManager(new LinearLayoutManager(this));
        rec.setAdapter(new first_page_notes_adapter(getApplicationContext(),items));
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
        Intent i = new Intent(first_page.this,first_page_notes.class);
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