package com.reewaj.betterhealthdemo.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.reewaj.betterhealthdemo.Database.DatabaseHandlerStock;
import com.reewaj.betterhealthdemo.R;

import java.util.ArrayList;

public class medicinelist extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseHandlerStock databaseHandlerMedicine;
    ArrayList<ModelMedicine> medicinelists;
    int modelmedicineid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicinelist);

        recyclerView= findViewById(R.id.rv);
        databaseHandlerMedicine = new DatabaseHandlerStock(this);

        modelmedicineid = getIntent().getIntExtra("modelmedicineid",0);

        medicinelists = new ArrayList<>();

        /*medicinelists.add(new ModelMedicine(R.mipmap.ic_launcher_foreground_medicine1,"plaxin","for blood pressure, daily three times","32"));
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
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        RecyclerView.LayoutManager rvLiLayoutManager=layoutManager;
        recyclerView.setLayoutManager(rvLiLayoutManager);

        medicinelistadapter adapter =new medicinelistadapter(this,databaseHandlerMedicine.getStockMedicine());


recyclerView.setAdapter(adapter);

    }
}
