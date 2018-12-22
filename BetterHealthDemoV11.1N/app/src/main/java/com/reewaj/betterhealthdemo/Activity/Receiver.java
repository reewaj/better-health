package com.reewaj.betterhealthdemo.Activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.reewaj.betterhealthdemo.Database.DatabaseHandlerAppointment;
import com.reewaj.betterhealthdemo.R;

import static android.content.Context.NOTIFICATION_SERVICE;

public class Receiver extends BroadcastReceiver {

    DatabaseHandlerAppointment databaseHandlerAppointment;
    long apptfinal;
    @Override
    public void onReceive(Context context, Intent intent) {
        databaseHandlerAppointment = new DatabaseHandlerAppointment(context);
        notify(context);

    }
    private void notify(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 4, notificationIntent, 0);



        Notification notification = new Notification();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        notification = builder.setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_alarm_black_24dp)
                .setTicker("ticker").setWhen(System.currentTimeMillis())
                .setAutoCancel(true).setContentTitle("Reminder for your Appointment")
                .setContentText("Appointment is in 2 hours").build();

        notificationManager.notify(1010, notification);

    }
}
