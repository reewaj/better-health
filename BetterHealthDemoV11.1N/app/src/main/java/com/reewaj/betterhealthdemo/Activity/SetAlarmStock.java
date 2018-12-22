package com.reewaj.betterhealthdemo.Activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.reewaj.betterhealthdemo.Database.DatabaseHandlerAppointment;

import static android.content.Context.ALARM_SERVICE;

public class SetAlarmStock {
        DatabaseHandlerAppointment databaseHandlerAppointment;
        long apptfinal;
        Context context;


        SetAlarmStock(Context context)
        {
            this.context = context;
        }
        public void setAlarm() {

            Intent notificationIntent = new Intent(context, Receiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 4, notificationIntent, 0);
            AlarmManager manager = (AlarmManager) context.getSystemService(ALARM_SERVICE);


            /*ArrayList<ModelClass> list = databaseHandlerAppointment.getAppointmentList();
            for (ModelClass modelClass: list)
            {


                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.US);
                String appointment_date = modelClass.getAppointment_date()+" "+modelClass.getAppointment_time();
                Log.i("notification", "Time: "+appointment_date);
                Date finaldate = null;
                try {
                    finaldate = simpleDateFormat.parse(appointment_date);
                    apptfinal = finaldate.getTime() ;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
*/
            Log.i("notification","Inside SetAlarmStock");
            //check time here
            manager.set(AlarmManager.RTC_WAKEUP,(System.currentTimeMillis()+(1000*30)),pendingIntent);

        }
}
