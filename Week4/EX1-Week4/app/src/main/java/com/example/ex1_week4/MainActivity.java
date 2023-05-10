package com.example.ex1_week4;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends Activity {

    EditText editData;
    Button readDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get references to UI elements
        editData = (EditText) findViewById(R.id.editText);
        readDataButton = (Button) findViewById(R.id.readData);

        // Set up click listener for the "ReadData" button
        readDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data;
                InputStream in = getResources().openRawResource(R.raw.myfile);
                InputStreamReader inreader = new InputStreamReader(in);
                BufferedReader bufreader = new BufferedReader(inreader);
                StringBuilder builder = new StringBuilder();
                try {
                    while ((data = bufreader.readLine()) != null) {
                        builder.append(data);
                        builder.append("\n"); // Add line break
                    }
                    in.close();
                    editData.setText(builder.toString());
                } catch (IOException ex) {
                    Log.e("ERROR", ex.getMessage());
                }
            }
        });
    }
}