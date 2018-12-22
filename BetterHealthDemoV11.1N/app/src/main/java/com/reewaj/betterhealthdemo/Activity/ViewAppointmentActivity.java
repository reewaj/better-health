package com.reewaj.betterhealthdemo.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.reewaj.betterhealthdemo.Database.DatabaseHandlerAppointment;
import com.reewaj.betterhealthdemo.R;
import com.reewaj.betterhealthdemo.UI.RecycleViewAdapter;
import com.reewaj.betterhealthdemo.Model.ModelClass;

import java.util.ArrayList;
import java.util.List;

public class ViewAppointmentActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecycleViewAdapter recycleViewAdapter;
    private List<ModelClass> list;
    private List<ModelClass> appointmentList;
    private DatabaseHandlerAppointment db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_appointment);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//            }
//        });

        db= new DatabaseHandlerAppointment(this);

        recyclerView=  findViewById(R.id.recycleView);
        //to ensure all the items are fixed in place
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();
        appointmentList =new ArrayList<>();

        //get items from database
        appointmentList = db.getAppointmentList();

        for(ModelClass mc : appointmentList){
            ModelClass modelClass=new ModelClass();
            modelClass.setDoctor_name(mc.getDoctor_name());
            modelClass.setDoctor_number(mc.getDoctor_number());
            modelClass.setAppointment_date(mc.getAppointment_date());
            modelClass.setAppointment_time(mc.getAppointment_time());

            modelClass.setId(mc.getId());

            list.add(modelClass);
        }


        recycleViewAdapter=new RecycleViewAdapter(this,db.getAppointmentList());
        recyclerView.setAdapter(recycleViewAdapter);
        recycleViewAdapter.notifyDataSetChanged();//notify system the system has changed
    }

}
