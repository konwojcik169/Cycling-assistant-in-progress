package com.example.cyclistassistant;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewingServices extends Activity {

    DBHelper myDb;

    static int itemSelected;
    static int itemSelectedUpdate;
    static boolean isSelected;

    int licznik;

    Button b6_deleteService, b7_updateService;

    private ListView listView;
    private ServicesAdapter sAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewing_services);

        myDb = new DBHelper(this);

        String bId, bDate, bDistance, bPropulsion, bSuspension, bBraking, bWheels, bOther, bCost;

        b6_deleteService = findViewById(R.id.button6);
        b7_updateService = findViewById(R.id.button7);

        listView = findViewById(R.id.services_list);
        final ArrayList<Services> servicesList = new ArrayList<>();


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

     while (res.moveToNext()){
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

            servicesList.add(new Services(R.drawable.baseline_calendar_today_black_18dp, bId, bDate,
                    bDistance, bPropulsion, bSuspension, bBraking, bWheels, bOther, bCost));

            bufId.delete(0,20);
            bufDate.delete(0,200);
            bufDistance.delete(0,200);
            bufPropulsion.delete(0,200);
            bufSuspension.delete(0,200);
            bufBraking.delete(0,200);
            bufWheels.delete(0,200);
            bufOther.delete(0,200);
            bufCost.delete(0,200);

            licznik ++;
     }

        sAdapter = new ServicesAdapter(this, servicesList);
        listView.setAdapter(sAdapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        itemSelected = position;
                        itemSelectedUpdate = position;
                        isSelected = true;
                        listView.setSelector(android.R.color.holo_orange_dark);
//
//                        if(position == 0) {
//                            Toast.makeText(getApplicationContext(), "You chose Chuck Norris", Toast.LENGTH_LONG).show();
//                        } else if (position == 1) {
//                            Toast.makeText(getApplicationContext(), "You chose Jackie Chan", Toast.LENGTH_LONG).show();
//                        } else {
//                            Toast.makeText(getApplicationContext(), "You chose Steven Seagal", Toast.LENGTH_LONG).show();
//                        }

                    }
                }
        );

        b6_deleteService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSelected) {
                    servicesList.remove(itemSelected);
                    sAdapter.notifyDataSetChanged();
                    TextView tv11 = listView.findViewById(R.id.textView11);

                    Integer deletedRows = myDb.deleteRowOfDB(tv11.getText().toString());

                    if(deletedRows > 0){
                        Toast.makeText(ViewingServices.this, "Data deleted",
                                Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(ViewingServices.this, "Data not deleted!!!" + tv11,
                                Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(ViewingServices.this, "Select service to be deleted",
                            Toast.LENGTH_LONG).show();
                }
                    itemSelected = 0;
                    isSelected = false;
                    listView.setSelector(android.R.color.transparent);
            }
        });

        b7_updateService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSelected) {

//                    TextView tv = listView.findViewById(R.id.textView);
//                    String eDate = tv.getText().toString();

                    Intent i = new Intent(ViewingServices.this, UpdatingServices.class);
//                    i.putExtra("eDate", eDate);
                    startActivity(i);
                } else {
                Toast.makeText(ViewingServices.this, "Select service to be updated",
                        Toast.LENGTH_LONG).show();
            }
            itemSelected = 0;
            isSelected = false;
            listView.setSelector(android.R.color.transparent);
            }
        });

    }


}
