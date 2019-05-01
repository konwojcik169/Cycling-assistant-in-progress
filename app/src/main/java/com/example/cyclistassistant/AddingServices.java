package com.example.cyclistassistant;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class AddingServices extends Activity  {
    DBHelper myDb;

    EditText et1_date, et2_distance, et3_propulsion, et4_suspension, et5_braking, et6_wheels,
            et7_other, et8_cost;
    Button b4_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_services);

        myDb = new DBHelper(this);

        et1_date = findViewById(R.id.editText);
        et2_distance = findViewById(R.id.editText2);
        et3_propulsion = findViewById(R.id.editText3);
        et4_suspension = findViewById(R.id.editText4);
        et5_braking = findViewById(R.id.editText5);
        et6_wheels = findViewById(R.id.editText6);
        et7_other = findViewById(R.id.editText7);
        et8_cost = findViewById(R.id.editText8);

        b4_add = findViewById(R.id.button4);


//        b5_view_all_data = findViewById(R.id.button5);

//        viewAll();

        addData();
    }



    public void addData(){
        b4_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                int distanceNumber = Integer.parseInt(et2_distance.getText().toString());
//                int costNumber = Integer.parseInt(et8_cost.getText().toString());

                boolean isInserted = myDb.insertdata(et1_date.getText().toString(), et2_distance.getText().toString(),
                        et3_propulsion.getText().toString(), et4_suspension.getText().toString(),
                        et5_braking.getText().toString(), et6_wheels.getText().toString(),
                        et7_other.getText().toString(), et8_cost.getText().toString());

                    if (isInserted == true) {
                        Toast.makeText(AddingServices.this, "Data inserted",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddingServices.this, "Data not inserted",
                                Toast.LENGTH_SHORT).show();
                    }

            }
        });
    }



}
