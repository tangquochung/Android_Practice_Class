package com.example.homework_week4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Ex4 extends AppCompatActivity {

    Button btn_read, btn_write;
    EditText edt_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex4);




        btn_read = (Button) findViewById(R.id.btn_read_ex4);
        btn_write = (Button) findViewById(R.id.btn_write_ex4);
        edt_data = (EditText) findViewById(R.id.edt_data_ex4);

        btn_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath() + "it_plot.txt";
                try {
                    Scanner scanner = new Scanner(new File(sdcard));
                    String data = "";
                    while (scanner.hasNext()){
                        data += scanner.nextLine() + "\n";
                    }
                    scanner.close();
                    edt_data.setText(data + "");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath() + "it_plot.txt";

                try {
                    OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(sdcard));
                    writer.write(edt_data.getText() + "");
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}