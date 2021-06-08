package com.example.lab_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ActivityDevInfo extends AppCompatActivity {
    private TextView Aboute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev_info);
        Aboute = (TextView)findViewById(R.id.textView_aboute);
        Aboute.setText("Hi, my name is Vitali and I am the developer of this program :)\n" +
                "Email : vtkp83@gmail.com\n" +
                "Phone Number : +38(068)-367-33-38");
    }
}