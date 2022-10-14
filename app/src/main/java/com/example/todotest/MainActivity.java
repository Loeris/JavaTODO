
package com.example.todotest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.start_fragment,new notes());

        bottomNavigationView.setSelectedItemId(R.id.notes);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId())
                {
                    case R.id.notes:{
                        fragment = new notes();
                        break;}
                    case R.id.timer:{
                        fragment = new timer();
                        break;}
                    case R.id.tasks:{
                        fragment = new tasks();
                        break;}
                    case R.id.account:{
                        fragment = new account();
                        break;}
                }
                assert fragment != null;
                getSupportFragmentManager().beginTransaction().replace(R.id.start_fragment,fragment).commit();
                return true; // Когда возвращается true появляется анимация кнопок
            }
        });
    }

}
