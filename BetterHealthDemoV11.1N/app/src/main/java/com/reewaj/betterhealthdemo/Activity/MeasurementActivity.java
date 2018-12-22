package com.reewaj.betterhealthdemo.Activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.reewaj.betterhealthdemo.R;

public class MeasurementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement);
        String[] measurementItems = {"Blood Pressure",
                "Blood Sugar",
                "Body Weight",
                "Heart Rate"};
        ListAdapter listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, measurementItems);
        ListView listView = (ListView) findViewById(R.id.measurement_menu);
        listView.setAdapter(listAdapter);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
//                if(position == 0){
//                    Toast.makeText(getActivity(),"This option is not available now", Toast.LENGTH_SHORT).show();
//                }else if(position == 1){
//
//                }else if(position == 2){
//
//                }
//            }
//        });


        // setContentView(R.layout.activity_measurement);
    }
}