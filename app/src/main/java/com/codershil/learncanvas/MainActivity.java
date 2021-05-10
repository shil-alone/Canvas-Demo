package com.codershil.learncanvas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    // creating a linear layout where i have to draw the canvas
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setting the view onto the linear layout
        linearLayout = findViewById(R.id.linearLayout);
        MyView myView = new MyView(this);
        linearLayout.addView(myView);
        
    }
}