package com.example.cyclistassistant;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewingServices extends Activity {

    private ListView listView;
    private ServicesAdapter sAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewing_services);

        listView = findViewById(R.id.services_list);
        ArrayList<Services> servicesList = new ArrayList<>();
        servicesList.add(new Services("2019-02-15","2500", "-lancuch",
                "-","-tarcze","-piasty", "-",
                "200"));
        servicesList.add(new Services("2019-03-15","2700", "-kaseta",
                "-serwis amora","-klocki","-centrowanko",
                "-wym. grpiow","400"));
        servicesList.add(new Services("2019-04-25","2900", "-",
                "-spawanko","-klocki","-piasty", "dzwonek",
                "600"));

        sAdapter = new ServicesAdapter(this, servicesList);
        listView.setAdapter(sAdapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if(position == 0) {
                            Toast.makeText(getApplicationContext(), "You chose Chuck Norris", Toast.LENGTH_LONG).show();
                        } else if (position == 1) {
                            Toast.makeText(getApplicationContext(), "You chose Jackie Chan", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "You chose Steven Seagal", Toast.LENGTH_LONG).show();
                        }

                    }
                }
        );
    }
}
