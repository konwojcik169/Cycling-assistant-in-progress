package com.example.cyclistassistant;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdatingServices extends Activity {
    DBHelper myDb;

    private ListView listView;
    private ServicesAdapter sAdapter;

    String eDate;

    Button b8_updateService;

    EditText et12_date, et13_distance, et14_propulsion, et15_suspension, et16_braking, et17_wheels,
            et18_other, et19_cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updating_services);

        myDb = new DBHelper(this);

        String bId, bDate, bDistance, bPropulsion, bSuspension, bBraking, bWheels, bOther, bCost;


        et12_date = findViewById(R.id.editText12);
        et13_distance = findViewById(R.id.editText13);
        et14_propulsion = findViewById(R.id.editText14);
        et15_suspension = findViewById(R.id.editText15);
        et16_braking = findViewById(R.id.editText16);
        et17_wheels = findViewById(R.id.editText17);
        et18_other = findViewById(R.id.editText18);
        et19_cost = findViewById(R.id.editText19);

//        eDate = getIntent().
//        et12_date.setText(eDate);

        b8_updateService = findViewById(R.id.button8);

        Cursor res = myDb.getAllData();

        if(res.getCount() == 0){
            // show message
//            showMessage("Error", "Nothinf data found");
            Toast.makeText(getApplicationContext(), "Nothing found", Toast.LENGTH_LONG).show();
            return;
        }
        StringBuffer bufId = new StringBuffer();
        StringBuffer bufDate = new StringBuffer();
        StringBuffer bufDistance = new StringBuffer();
        StringBuffer bufPropulsion = new StringBuffer();
        StringBuffer bufSuspension = new StringBuffer();
        StringBuffer bufBraking = new StringBuffer();
        StringBuffer bufWheels = new StringBuffer();
        StringBuffer bufOther = new StringBuffer();
        StringBuffer bufCost = new StringBuffer();

            res.moveToPosition(ViewingServices.itemSelectedUpdate);

            bufId.append(res.getString(0));
            bufDate.append("Date: " + res.getString(1));
            bufDistance.append("Distance:\n" + res.getString(2) + " km");
            bufPropulsion.append("Propulsion:\n" + res.getString(3) );
            bufSuspension.append("Suspension/frame:\n" + res.getString(4));
            bufBraking.append("Braking system:\n" + res.getString(5));
            bufWheels.append("Wheels:\n" + res.getString(6));
            bufOther.append("Other:\n" + res.getString(7));
            bufCost.append("Cost:\n" + res.getString(8) + " PLN\n");

            bId = bufId.toString();
            bDate = bufDate.toString();
            bDistance = bufDistance.toString();
            bPropulsion = bufPropulsion.toString();
            bSuspension = bufSuspension.toString();
            bBraking = bufBraking.toString();
            bWheels = bufWheels.toString();
            bOther = bufOther.toString();
            bCost = bufCost.toString();

            et12_date.setText(bDate);
            et13_distance.setText(bDistance);
            et14_propulsion.setText(bPropulsion);
            et15_suspension.setText(bSuspension);
            et16_braking.setText(bBraking);
            et17_wheels.setText(bWheels);
            et18_other.setText(bOther);
            et19_cost.setText(bCost);


//            servicesList.add(new Services(R.drawable.baseline_calendar_today_black_18dp, bId, bDate,
//                    bDistance, bPropulsion, bSuspension, bBraking, bWheels, bOther, bCost));

//            bufId.delete(0,20);
            bufDate.delete(0,200);
            bufDistance.delete(0,200);
            bufPropulsion.delete(0,200);
            bufSuspension.delete(0,200);
            bufBraking.delete(0,200);
            bufWheels.delete(0,200);
            bufOther.delete(0,200);
            bufCost.delete(0,200);






        b8_updateService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isUpdate = myDb.updateData("14", et12_date.getText().toString(),
                        et13_distance.getText().toString(), et14_propulsion.getText().toString(),
                        et15_suspension.getText().toString(), et16_braking.getText().toString(),
                        et17_wheels.getText().toString(), et18_other.getText().toString(),
                        et19_cost.getText().toString());
                if (isUpdate == true) {
                    Toast.makeText(UpdatingServices.this, "Data updated",
                            Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(UpdatingServices.this, MainActivity.class);
                    startActivity(i);
                }else
                    Toast.makeText(UpdatingServices.this, "Data not updated!!!",
                            Toast.LENGTH_SHORT).show();
            }
        });

        ViewingServices.itemSelectedUpdate = 0;
    }
}
