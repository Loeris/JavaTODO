package com.example.block_planner.tasks;

import android.app.Activity;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.example.block_planner.App;
import com.example.block_planner.R;
import com.example.block_planner.model.Tasks;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.TasksViewHolder>{
    private SortedList<Tasks> sortedList;

    public Adapter() {

        sortedList = new SortedList<>(Tasks.class, new SortedList.Callback<Tasks>() {
            @Override
            public int compare(Tasks o1, Tasks o2) {
                if (!o2.done && o1.done) {
                    return 1;
                }
                if (o2.done && !o1.done) {
                    return -1;
                }
                return (int) (o2.timestamp - o1.timestamp);
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);
            }

            @Override
            public boolean areContentsTheSame(Tasks oldItem, Tasks newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areItemsTheSame(Tasks item1, Tasks item2) {
                return item1.uid == item2.uid;
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }
        });
    }


    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TasksViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tasks_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TasksViewHolder holder, int position) {
        holder.bind(sortedList.get(position));
    }

    @Override
    public int getItemCount() {
        return sortedList.size();
    }

    public void setItems(List<Tasks> tasks) {
        sortedList.replaceAll(tasks);
    }

    static class TasksViewHolder extends RecyclerView.ViewHolder {

        TextView tasksText;
        CheckBox completed;
        View delete;

        Tasks tasks;

        boolean silentUpdate;

        public TasksViewHolder(@NonNull final View itemView) {
            super(itemView);

            tasksText = itemView.findViewById(R.id.note_text);
            completed = itemView.findViewById(R.id.completed);
            delete = itemView.findViewById(R.id.delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TasksDetailsActivity.start((Activity) itemView.getContext(), tasks);
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    App.getInstance().getTasksDao().delete(tasks);
                }
            });

            completed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                    if (!silentUpdate) {
                        tasks.done = checked;
                        App.getInstance().getTasksDao().update(tasks);
                    }
                    updateStrokeOut();
                }
            });


        }

        public void bind(Tasks tasks) {
            this.tasks = tasks;

            tasksText.setText(tasks.text);
            updateStrokeOut();

            silentUpdate = true;
            completed.setChecked(tasks.done);
            silentUpdate = false;
        }

        private void updateStrokeOut() {
            if (tasks.done) {
                tasksText.setPaintFlags(tasksText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                tasksText.setPaintFlags(tasksText.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            }
        }
    }
}
