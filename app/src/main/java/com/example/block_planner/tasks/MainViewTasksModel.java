package com.example.block_planner.tasks;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.block_planner.App;
import com.example.block_planner.model.Tasks;

import java.util.List;

public class MainViewTasksModel extends ViewModel {
    private LiveData<List<Tasks>> tasksLiveData = App.getInstance().getTasksDao().getAllLiveData();

    public LiveData<List<Tasks>> getTasksLiveData() {
        return tasksLiveData;
    }
}
