package com.example.homework_week4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ex1 extends AppCompatActivity {
    Button btn_read;
    EditText edt_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex1);

        btn_read = (Button) findViewById(R.id.btn_read);
        edt_data = (EditText) findViewById(R.id.edt_data);

        btn_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data;
                @SuppressLint("ResourceType") InputStream inputStream = getResources().openRawResource(R.raw.it_plot);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();

                if (inputStream != null) {
                    try {
                        while ((data = bufferedReader.readLine()) != null) {
                            stringBuilder.append(data);
                            stringBuilder.append("\n");
                        }
                        inputStream.close();
                        edt_data.setText(stringBuilder.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}