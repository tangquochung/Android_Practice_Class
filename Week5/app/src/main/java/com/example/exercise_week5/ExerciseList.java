package com.example.exercise_week5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExerciseList extends AppCompatActivity {

    Button btn_ex1;
    Button btn_ex2;
    Button btn_alarm;
    Button btn_TV_Android;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);

        btn_ex1 = (Button) findViewById(R.id.btn_ex1);
        btn_ex2 = (Button) findViewById(R.id.btn_ex2);
        btn_alarm = (Button) findViewById(R.id.btn_alarm);
        btn_TV_Android = (Button) findViewById(R.id.btn_TV_Android);

        btn_ex1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ex1Intent = new Intent(ExerciseList.this, MainActivity.class);
                startActivity(ex1Intent);
            }
        });

        btn_ex2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ex2Intent = new Intent(ExerciseList.this, Exercise2.class);
                startActivity(ex2Intent);
            }
        });

        btn_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent alarmIntent = new Intent(ExerciseList.this, Alarm.class);
                startActivity(alarmIntent);
            }
        });

        btn_TV_Android.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TVAndroidAppIntent = new Intent(ExerciseList.this, TVAndroidApp.class);
                startActivity(TVAndroidAppIntent);

            }
        });
    }
}