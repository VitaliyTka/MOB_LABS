package com.example.examproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.examproject.dialogs.MainDialog;

public class MainActivity extends AppCompatActivity {
    private ImageButton imageButtonInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageButtonInfo = (ImageButton) findViewById(R.id.imageButtonInfo);
        imageButtonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    public void openDialog(){
        MainDialog mainDialog = new MainDialog();
        mainDialog.show(getSupportFragmentManager(), "Main dialog");
    }

    public void onClickTrinarnaLogic(View view) {
        Intent intent = new Intent(this, ActivityTrinarnaLogic.class);
        startActivity(intent);
    }

    public void onClickCars(View view) {
        Intent intent = new Intent(this, ActivityCars.class);
        startActivity(intent);
    }

    public void onClickDeveloperInformation(View view) {
        Intent intent = new Intent(this, ActivityDevInfo.class);
        startActivity(intent);
    }
}