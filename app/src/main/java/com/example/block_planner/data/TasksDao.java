package com.example.block_planner.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.block_planner.model.Tasks;

import java.util.List;

@Dao
public interface TasksDao {
    @Query("SELECT * FROM Tasks")
    List<Tasks> getAll();

    @Query("SELECT * FROM Tasks")
    LiveData<List<Tasks>> getAllLiveData();

    @Query("SELECT * FROM Tasks WHERE uid IN (:noteIds)")
    List<Tasks> loadAllByIds(int[] noteIds);

    @Query("SELECT * FROM Tasks WHERE uid = :uid LIMIT 1")
    Tasks findById(int uid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Tasks note);

    @Update
    void update(Tasks note);

    @Delete
    void delete(Tasks note);

}
