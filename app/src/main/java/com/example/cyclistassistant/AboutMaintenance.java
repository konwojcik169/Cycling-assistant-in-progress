package com.example.cyclistassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AboutMaintenance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_maintenance);
    }

    public void startActivity5(View v){
        Intent i = new Intent(this, PropulsionMaintenance.class);
        startActivity(i);
    }

    public void startActivity6(View v){
        Intent i = new Intent(this, SuspensionMaintenance.class);
        startActivity(i);
    }

    public void startActivity7(View v){
        Intent i = new Intent(this, BrakingMaintenance.class);
        startActivity(i);
    }

    public void startActivity8(View v){
        Intent i = new Intent(this, WheelsMaintenance.class);
        startActivity(i);
    }
}
