package com.example.cyclistassistant;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    DBHelper myDb;

    Button b1, b2, b3, b5_view_all_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DBHelper(this);

        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b5_view_all_data = findViewById(R.id.button5);

        viewAll();
    }

    public void startActivity(View v){
        Intent i = new Intent(this, AddingServices.class);
        startActivity(i);
    }

    public void startActivity2(View v){
        Intent i = new Intent(this, ViewingServices.class);
        startActivity(i);
    }

        public void viewAll(){
        b5_view_all_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res = myDb.getAllData();
                if(res.getCount() == 0){
                    // show message
                    showMessage("Error", "Nothing found");
                    return;
                } else {
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("Id :" + res.getString(0) + "\n");
                        buffer.append("Date :" + res.getString(1) + "\n");
                        buffer.append("Distance :" + res.getString(2) + "\n");
                        buffer.append("Propulsion :" + res.getString(3) + "\n");
                        buffer.append("Suspension/frame :" + res.getString(4) + "\n");
                        buffer.append("Braking system :" + res.getString(5) + "\n");
                        buffer.append("Wheels :" + res.getString(6) + "\n");
                        buffer.append("Other :" + res.getString(7) + "\n");
                        buffer.append("Cost :" + res.getString(8) + "\n\n");
                    }

                    // show all data
                    showMessage("Data", buffer.toString());
                }
            }
        });
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
