package com.example.touch_2;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class Graphics extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GraphicsView(this));
    }
}