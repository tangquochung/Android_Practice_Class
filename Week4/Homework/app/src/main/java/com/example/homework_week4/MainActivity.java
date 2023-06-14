package com.example.homework_week4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    Button btn_ex1, btn_ex2, btn_ex3, btn_ex4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_ex1 = findViewById(R.id.btn_ex1);
        btn_ex2 = findViewById(R.id.btn_ex2);
        btn_ex3 = findViewById(R.id.btn_ex3);
        btn_ex4 = findViewById(R.id.btn_ex4);

        btn_ex1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ex1Intent = new Intent(MainActivity.this, Ex1.class);
                startActivity(ex1Intent);
            }
        });

        btn_ex2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ex2Intent = new Intent(MainActivity.this, Ex2.class);
                startActivity(ex2Intent);
            }
        });

        btn_ex3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ex3Intent = new Intent(MainActivity.this, Ex3.class);
                startActivity(ex3Intent);
            }
        });

        btn_ex4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ex4Intent = new Intent(MainActivity.this, Ex4.class);
                startActivity(ex4Intent);
            }
        });



    }
}