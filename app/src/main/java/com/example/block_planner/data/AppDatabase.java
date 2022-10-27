package com.example.block_planner.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.block_planner.model.Tasks;

@Database(entities = {Tasks.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TasksDao tasksDao();
}
