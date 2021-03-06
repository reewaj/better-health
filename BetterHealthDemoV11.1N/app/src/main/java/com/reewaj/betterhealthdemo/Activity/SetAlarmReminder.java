package com.reewaj.betterhealthdemo.Activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.reewaj.betterhealthdemo.Database.DatabaseHandlerAppointment;
import com.reewaj.betterhealthdemo.Model.ModelClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.ALARM_SERVICE;

public class SetAlarmReminder {
    long apptfinal;
    Context context;
    DatabaseHandlerAppointment databaseHandlerAppointment;

    SetAlarmReminder(Context context) {
        this.context = context;
    }

    public void setAlarm() {

        Intent notificationIntent = new Intent(context, Receiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 4, notificationIntent, 0);
        AlarmManager manager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

        databaseHandlerAppointment = new DatabaseHandlerAppointment(this.context);

        ArrayList<ModelClass> list = databaseHandlerAppointment.getAppointmentList();
        for (ModelClass modelClass : list) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.US);
            String appointment_date = modelClass.getAppointment_date() + " " + modelClass.getAppointment_time();
            Log.i("notification", "Time: " + appointment_date);
            Date finaldate = null;
            try {
                finaldate = simpleDateFormat.parse(appointment_date);
                apptfinal = finaldate.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        manager.set(AlarmManager.RTC_WAKEUP, (apptfinal - (1000 * 60 * 60 * 2)), pendingIntent);
        //repeat the alarm with developer dev=fined time interval;
        //manager.setRepeating();

    }
}
