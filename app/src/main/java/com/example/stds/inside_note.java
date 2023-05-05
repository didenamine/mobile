package com.example.stds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class inside_note extends AppCompatActivity {
DBhelper DB = new DBhelper(this);
    public static Button delete ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_note);
        TextView title = findViewById(R.id.title_place);
        TextView note = findViewById(R.id.note_place);
        title.setText(DB.get_read_title());
        note.setText(DB.get_read_note());

}
    }