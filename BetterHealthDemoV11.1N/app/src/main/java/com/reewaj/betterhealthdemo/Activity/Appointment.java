package com.reewaj.betterhealthdemo.Activity;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.reewaj.betterhealthdemo.Database.DatabaseHandlerAppointment;
import com.reewaj.betterhealthdemo.R;
import com.reewaj.betterhealthdemo.Model.ModelClass;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.widget.Toast;

public class Appointment extends AppCompatActivity {

    TextView appointmentdate, appointmenttime;
    Button Add_datebutton, Add_timebutton;


    Calendar c;
    DatePickerDialog dpd;
    TimePickerDialog tpd;

    //database work
    Button saveButton;
    EditText docName, docNumber;
    DatabaseHandlerAppointment db;
    AlertDialog dialog;
    int day, month, year;
    SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment);

        appointmentdate = (TextView) findViewById(R.id.appoinmentDate);
        Add_datebutton = (Button) findViewById(R.id.appodate);
        appointmenttime = (TextView) findViewById(R.id.appoinmentTime);
        Add_timebutton = (Button) findViewById(R.id.appotime);
        db = new DatabaseHandlerAppointment(this);

        Add_datebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c = Calendar.getInstance();
                day = c.get(Calendar.DAY_OF_MONTH);
                month = c.get(Calendar.MONTH);
                year = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(Appointment.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                        year++;
                        appointmentdate.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);
                    }

                }, year, month, day);
                dpd.show();
            }
        });

        Add_timebutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR);
                int minute = c.get(Calendar.MINUTE);

                tpd = new TimePickerDialog(Appointment.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int thour, int tminute) {
                        //time.setText(thour + ":" + tminute);
                        String am_pm;
                        if (thour < 12) {
                            am_pm = "AM";
                            appointmenttime.setText(thour + ":" + tminute + " " + am_pm);
                        } else if (thour == 12) {
                            am_pm = "PM";
                            appointmenttime.setText(thour + ":" + tminute + " " + am_pm);
                        } else {
                            am_pm = "PM";
                            appointmenttime.setText(thour + ":" + tminute + " " + am_pm);
                        }
                    }
                }, hour, minute, false);
                tpd.show();

            }
        });

      /*  Spinner myspinner = (Spinner) findViewById(R.id.reminder);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Appointment.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.reminder));
        myAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        myspinner.setAdapter(myAdapter);
*/
        //database work
        docName = (EditText) findViewById(R.id.doctorName);
        docNumber = (EditText) findViewById(R.id.doctorNumber);
        saveButton = (Button) findViewById(R.id.saveButton);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!docName.getText().toString().isEmpty()
                        || !docNumber.getText().toString().isEmpty()
                        || !appointmentdate.getText().toString().isEmpty()
                        || !appointmenttime.getText().toString().isEmpty()
                        ) { //also include appointment date,time ...
                    if (!docNumber.getText().toString().isEmpty()) {
                        if (docNumber.getText().toString().length() > 7 && docNumber.getText().toString().length() < 11) {
                            saveAppointment();
                        } else {
                            Toast.makeText(Appointment.this, "plese enter valid phone number", Toast.LENGTH_SHORT).show();

                        }
                    }//check d
                    else {
                        saveAppointment();
                    }
                }//checking fields
                else {
                    Toast.makeText(Appointment.this, "plese enter few details", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


    //database work getting value
    public void saveAppointment() {
        ModelClass modelClass = new ModelClass();

        //taking values
        String name = docName.getText().toString();
        String number = docNumber.getText().toString();
        String date = appointmentdate.getText().toString();
        String time = appointmenttime.getText().toString();


        //sending value to model class
     /*   modelClass.setDoctor_name(name);
        modelClass.setDoctor_number(number);
        modelClass.setAppointment_date(date);
      //  modelClass.setAppointment_time(time);
        Log.i("check","Date: "+modelClass.get123456790=

      ?Appointment_date());


        db.addAppointment(modelClass);

        Log.d("Sumit", ""+db);

        //it works if dialogbox is used to add information
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                dialog.dismiss();
//
//                //start a new activity
//                startActivity();
//            }
//        })
        startActivity(new Intent(Appointment.this,ViewAppointmentActivity.class));*/

        ContentValues values = new ContentValues();
        values.put("doctor_name", name);
        values.put("doctor_number", number);
        values.put("appointment_date", date);
        values.put("appointment_time", time);

        //Log.i("tag","Appointment Date: "+modelClass.getAppointment_date());

        db.addAppointment(values);
        SetAlarmReminder setAlarm = new SetAlarmReminder(Appointment.this);
        setAlarm.setAlarm();

        Toast.makeText(Appointment.this, "data inserted", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(Appointment.this, ViewAppointmentActivity.class));


    }

}
