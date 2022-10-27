package com.example.block_planner;

import android.app.Application;

import androidx.room.Room;

import com.example.block_planner.data.AppDatabase;
import com.example.block_planner.data.TasksDao;

public class App extends Application {
    private AppDatabase database;
    private TasksDao tasksDao;

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        database = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "app-db-name")
                .allowMainThreadQueries()
                .build();

        tasksDao = database.tasksDao();
    }

    public AppDatabase getDatabase() {
        return database;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    public TasksDao getTasksDao() {
        return tasksDao;
    }

    public void setTasksDao(TasksDao tasksDao) {
        this.tasksDao = tasksDao;
    }
}
