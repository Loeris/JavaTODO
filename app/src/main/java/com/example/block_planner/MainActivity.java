package com.example.block_planner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.block_planner.tasks.TasksMenu;

public class MainActivity extends AppCompatActivity {

    private TextView result_info;
    private Button notes_tab_btn;
    private Button tasks_tab_btn;
    private Button timer_tab_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notes_tab_btn = findViewById(R.id.notes_tab_btn);
        tasks_tab_btn = findViewById(R.id.tasks_tab_btn);
        timer_tab_btn = findViewById(R.id.timer_tab_btn);

        notes_tab_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Notes.class);
                startActivity(intent);
            }
        });

        tasks_tab_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TasksMenu.class);
                startActivity(intent);
            }
        });

        timer_tab_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Timer.class);
                startActivity(intent);
            }
        });
    }
}