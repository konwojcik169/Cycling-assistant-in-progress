package com.example.cyclistassistant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ServicesAdapter extends ArrayAdapter<Services> {
    private Context myContext;
    private List<Services> servicesList;

    public ServicesAdapter(Context context, ArrayList<Services> list) {
        super(context, 0, list);
        myContext = context;
        servicesList = list;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(myContext).inflate(R.layout.list_services, parent, false);
        Services currentService = servicesList.get(position);
        TextView tv1 = listItem.findViewById(R.id.textView);
        tv1.setText(currentService.getDate());
        TextView tv2 = listItem.findViewById(R.id.textView2);
        tv2.setText(currentService.getDistance());
        TextView tv3 = listItem.findViewById(R.id.textView3);
        tv3.setText(currentService.getPropulsionService());
        TextView tv4 = listItem.findViewById(R.id.textView4);
        tv4.setText(currentService.getSuspensionService());
        TextView tv5 = listItem.findViewById(R.id.textView5);
        tv5.setText(currentService.getBrakingService());
        TextView tv6 = listItem.findViewById(R.id.textView6);
        tv6.setText(currentService.getWheelsService());
        TextView tv7 = listItem.findViewById(R.id.textView7);
        tv7.setText(currentService.getOtherRepairs());
        TextView tv8 = listItem.findViewById(R.id.textView8);
        tv8.setText(currentService.getCost());
        return listItem;
    }
}
