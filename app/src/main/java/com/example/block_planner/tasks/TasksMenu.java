package com.example.block_planner.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.block_planner.Notes;
import com.example.block_planner.R;
import com.example.block_planner.Timer;
import com.example.block_planner.model.Tasks;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TasksMenu extends AppCompatActivity {
    private Button notes_tab_btn;
    private Button tasks_tab_btn;
    private Button timer_tab_btn;

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks);
        notes_tab_btn = findViewById(R.id.notes_tab_btn);
        tasks_tab_btn = findViewById(R.id.tasks_tab_btn);
        timer_tab_btn = findViewById(R.id.timer_tab_btn);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        final Adapter adapter = new Adapter();
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.addTaskBtn);

        notes_tab_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TasksMenu.this, Notes.class);
                startActivity(intent);
            }
        });

        tasks_tab_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TasksMenu.this, TasksMenu.class);
                startActivity(intent);
            }
        });

        timer_tab_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TasksMenu.this, Timer.class);
                startActivity(intent);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TasksDetailsActivity.start(TasksMenu.this, null);
            }
        });

        MainViewTasksModel mainViewTasksModel = ViewModelProviders.of(this).get(MainViewTasksModel.class);
        mainViewTasksModel.getTasksLiveData().observe(this, new Observer<List<Tasks>>() {
            @Override
            public void onChanged(List<Tasks> tasks) {
                adapter.setItems(tasks);
            }
        });
    }
}