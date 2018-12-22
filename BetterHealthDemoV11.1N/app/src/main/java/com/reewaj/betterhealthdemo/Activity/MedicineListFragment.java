package com.reewaj.betterhealthdemo.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.reewaj.betterhealthdemo.Database.DatabaseHandlerStock;
import com.reewaj.betterhealthdemo.R;

import java.util.ArrayList;

public class MedicineListFragment extends Fragment {
    RecyclerView rv;
    ListView listView;
    DatabaseHandlerStock databaseHandlerMedicine;
    ArrayList<ModelMedicine> medicinelists;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_list_new,null);
        listView = view.findViewById(R.id.listview);
        medicinelists = new ArrayList<>();
        databaseHandlerMedicine = new DatabaseHandlerStock(getActivity());


       /* medicinelists.add(new ModelMedicine(R.mipmap.ic_launcher_foreground_medicine1,"plaxin","for blood pressure, daily three times","32"));
        medicinelists.add(new ModelMedicine(R.mipmap.ic_launcher_foreground_medicine2,"socin","is for stomache, evening one time","51"));
        medicinelists.add(new ModelMedicine(R.mipmap.ic_launcher_foreground_medicine3,"alpram","for headache, before dinner","30"));
        medicinelists.add(new ModelMedicine(R.mipmap.ic_launcher_foreground_medicine1,"plaxin","for blood pressure, daily three times","32"));
        medicinelists.add(new ModelMedicine(R.mipmap.ic_launcher_foreground_medicine2,"socin","is for stomache, evening one time","51"));
        medicinelists.add(new ModelMedicine(R.mipmap.ic_launcher_foreground_medicine3,"alpram","for headache, before dinner","30"));
        medicinelists.add(new ModelMedicine(R.mipmap.ic_launcher_foreground_medicine1,"plaxin","for blood pressure, daily three times","32"));
        medicinelists.add(new ModelMedicine(R.mipmap.ic_launcher_foreground_medicine2,"socin","is for stomache, evening one time","51"));
        medicinelists.add(new ModelMedicine(R.mipmap.ic_launcher_foreground_medicine3,"alpram","for headache, before dinner","30"));
        medicinelists.add(new ModelMedicine(R.mipmap.ic_launcher_foreground_medicine1,"plaxin","for blood pressure, daily three times","32"));
        medicinelists.add(new ModelMedicine(R.mipmap.ic_launcher_foreground_medicine2,"socin","is for stomache, evening one time","51"));
        medicinelists.add(new ModelMedicine(R.mipmap.ic_launcher_foreground_medicine3,"alpram","for headache, before dinner","30"));
*/
        /*LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        RecyclerView.LayoutManager rvLiLayoutManager=layoutManager;
        rv.setLayoutManager(rvLiLayoutManager);*/

        medicinelistadapternew adapter =new medicinelistadapternew(getActivity(),databaseHandlerMedicine.getStockMedicine());


        listView.setAdapter(adapter);

        return view;
    }
}
