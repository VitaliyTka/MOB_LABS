package com.example.lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Developer extends AppCompatActivity {
    TextView Aboute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        Aboute = (TextView)findViewById(R.id.textView_aboute);
        Aboute.setText("Hi, my name is Vitali and I am the developer of this program :)\n" +
                "Email : vtkp83@gmail.com\n" +
                "Phone Number : +38(068)-367-33-38");
    }
}