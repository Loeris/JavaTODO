package com.example.block_planner.tasks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.block_planner.App;
import com.example.block_planner.R;
import com.example.block_planner.model.Tasks;

public class TasksDetailsActivity extends AppCompatActivity {
    private static final String EXTRA_TASKS = "NoteDetailsActivity.EXTRA_NOTE";

    private Tasks tasks;

    private EditText editText;

    public static void start(Activity caller, Tasks tasks) {
        Intent intent = new Intent(caller, TasksDetailsActivity.class);
        if (tasks != null) {
            intent.putExtra(EXTRA_TASKS, tasks);
        }
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_task);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setTitle(getString(R.string.task_details_title));

        editText = findViewById(R.id.taskText);

        if (getIntent().hasExtra(EXTRA_TASKS)) {
            tasks = getIntent().getParcelableExtra(EXTRA_TASKS);
            editText.setText(tasks.text);
        } else {
            tasks = new Tasks();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.saveTask:
                if (editText.getText().length() > 0) {
                    tasks.text = editText.getText().toString();
                    tasks.done = false;
                    tasks.timestamp = System.currentTimeMillis();
                    if (getIntent().hasExtra(EXTRA_TASKS)) {
                        App.getInstance().getTasksDao().update(tasks);
                    } else {
                        App.getInstance().getTasksDao().insert(tasks);
                    }
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
