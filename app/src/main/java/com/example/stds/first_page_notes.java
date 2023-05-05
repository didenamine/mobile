package com.example.stds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.badge.BadgeUtils;

public class first_page_notes extends AppCompatActivity {
DBhelper DB = new DBhelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_page_notes);
        EditText Title = findViewById(R.id.add_note_first_page_title);
        EditText Note = findViewById(R.id.add_note_first_page_note);
        Button Add = findViewById(R.id.add_note_first_page_add);

        // TODO : fix the background image (the button doesnt fit )
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Tit = Title.getText().toString();
                String Not = Note.getText().toString();
                String user  = DB.getCurentUser();
                if (Not.length()!=0 || Tit.length()!=0){

                    DB.Add_note(Tit,Not,user);
                    Toast.makeText(first_page_notes.this, "Note added!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),first_page.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(first_page_notes.this, "You have to fill the both fields of text!", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }


}