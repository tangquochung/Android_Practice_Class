package com.example.homework_week4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Ex2 extends AppCompatActivity {
    Button btn_read, btn_write;
    EditText edt_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex2);

        btn_read = (Button) findViewById(R.id.btn_read_ex2);
        btn_write = (Button) findViewById(R.id.btn_write_ex2);
        edt_data = (EditText) findViewById(R.id.edt_data_ex2);

        btn_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fileInputStream = openFileInput("it_plot.txt");
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                    String data = "";
                    StringBuilder builder = new StringBuilder();
                    while ((data = bufferedReader.readLine()) != null) {
                        builder.append(data);
                        builder.append("\n");
                    }
                    fileInputStream.close();
                    edt_data.setText(builder.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream outputStream = openFileOutput("it_plot.txt", 0);
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                    outputStreamWriter.write(edt_data.getText().toString());
                    outputStreamWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}