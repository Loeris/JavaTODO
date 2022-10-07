package com.example.block_planner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Notes extends AppCompatActivity {
    private Button notes_tab_btn;
    private Button tasks_tab_btn;
    private Button timer_tab_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes);
        notes_tab_btn = findViewById(R.id.notes_tab_btn);
        tasks_tab_btn = findViewById(R.id.tasks_tab_btn);
        timer_tab_btn = findViewById(R.id.timer_tab_btn);

        notes_tab_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Notes.this, Notes.class);
                startActivity(intent);
            }
        });

        tasks_tab_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Notes.this, Tasks.class);
                startActivity(intent);
            }
        });

        timer_tab_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Notes.this, Timer.class);
                startActivity(intent);
            }
        });
    }
}