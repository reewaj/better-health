package com.reewaj.betterhealthdemo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.reewaj.betterhealthdemo.R;

public class MoreFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        String[] moreItems =
                {"Measurement",
                        "Appointments",
                        "View Stock",
                        "Standard Rates",
                        "Banned Drugs",
                        "Settings"};

        ListView listView = (ListView) view.findViewById(R.id.more_menu);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                moreItems

        );
        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    Intent myintent = new Intent(view.getContext(),MeasurementActivity.class);
                    startActivityForResult(myintent, 0);

                }else if (position == 1){
                   // Toast.makeText(getActivity(), " ViewAppointment will be available soon", Toast.LENGTH_SHORT).show();
                  Intent myintent = new Intent(view.getContext(),ViewAppointmentActivity.class);
                   startActivityForResult(myintent, 0);

               }else if(position == 2){
                    Toast.makeText(getActivity(), " View Stock will be available soon", Toast.LENGTH_SHORT).show();
//                    Intent myintent = new Intent(view.getContext(),.class);
//                    startActivityForResult(myintent, 0);

                }else if(position == 3){
                    //Toast.makeText(getActivity(), "Standard rates will be available soon", Toast.LENGTH_SHORT).show();
                    Intent myintent = new Intent(view.getContext(),StandardRates.class);
                   startActivityForResult(myintent, 0);

                }else if(position == 4){
                    //Toast.makeText(getActivity(), "Banned Drugs will be available soon", Toast.LENGTH_SHORT).show();
                    Intent myintent = new Intent(view.getContext(),BannedDrugs.class);
                    startActivityForResult(myintent, 0);

                }
                else if(position == 5){
                    Toast.makeText(getActivity(), "Settings will be available soon", Toast.LENGTH_SHORT).show();

                }


            }
        });
        return view;
    }
}


