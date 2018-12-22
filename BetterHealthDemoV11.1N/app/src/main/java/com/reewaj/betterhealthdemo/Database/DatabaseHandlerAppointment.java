package com.reewaj.betterhealthdemo.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.reewaj.betterhealthdemo.Model.ModelClass;

import java.util.ArrayList;

public class DatabaseHandlerAppointment extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "BetterHealth";
/*
    private final String SQL_CREATE_ENTRIES = "CREATE TABLE if not exists '" + Constants.AppointmentEntry.TABLE_NAME + "' ('" +
            Constants.AppointmentEntry.TABLE_ID + "' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE , '" +
            Constants.AppointmentEntry.COLUMN_DOCTOR_NAME + "' TEXT, '" +
            Constants.AppointmentEntry.COLUMN_APPOINTMENT_DATE + "' TEXT, '" +
            //           Constants.AppointmentEntry.COLUMN_APPOINTMENT_TIME + "' TEXT, '" +
            Constants.AppointmentEntry.COLUMN_DOCTOR_NUMBER + "' TEXT " +
            ");";*/

    String table="CREATE TABLE if not exists`appointment` ( `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " `userid` INTEGER, " +
            " `doctor_name` TEXT, " +
            "`doctor_number` TEXT," +
            " `appointment_time` TEXT, " +
            "`appointment_date` TEXT" +
            //       " `note` TEXT " +
            ")";

    public DatabaseHandlerAppointment(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        getWritableDatabase().execSQL(table);
    }


    /*
        super(context, database, null, 1);
        getWritableDatabase().execSQL(table);*/
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

   /* public void addAppointment(ModelClass modelClass) {
        ContentValues values = new ContentValues();
        values.put("doctor_name", modelClass.getDoctor_name());
        values.put("doctor_number", modelClass.getDoctor_number());
        values.put("appointment_date", modelClass.getAppointment_date());
        values.put("appointment_time", modelClass.getAppointment_time());
       // values.put("note", modelClass.no());
        // values.put(Constants.AppointmentEntry.COLUMN_APPOINTMENT_TIME, modelClass.getAppointment_time());


        SQLiteDatabase db = this.getWritableDatabase();//or Readable

        db.insert("appointment", null, values);
    }
*/


    public void addAppointment(ContentValues contentValues){
        getWritableDatabase().insert("appointment","",contentValues);
    }

    public ArrayList<ModelClass> getAppointmentList(){
        ArrayList<ModelClass> list = new ArrayList<>();

        String query = "Select * from appointment";
        Cursor cursor = getReadableDatabase().rawQuery(query,null);

        while (cursor.moveToNext()) {
            ModelClass modelClass = new ModelClass();
            modelClass.setId(cursor.getInt(cursor.getColumnIndex("id")));
            modelClass.setDoctor_name(cursor.getString(cursor.getColumnIndex("doctor_name")));
            modelClass.setDoctor_number(cursor.getString(cursor.getColumnIndex("doctor_number")));
            modelClass.setAppointment_date(cursor.getString(cursor.getColumnIndex("appointment_date")));
            modelClass.setAppointment_time(cursor.getString(cursor.getColumnIndex("appointment_time")));

            list.add(modelClass);
        }

        cursor.close();
        return list;
    }
    //
//
//    public void upgradeAppointment(){
//
//    }
//
    public void deleteAppointment(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("appointment", "id = ?",
                new String[] {String.valueOf(id)});

        db.close();

    }
//
//    public int getAppointmentCount(){
//        String count= "Select * from " + Constants.AppointmentEntry.TABLE_NAME;
//        SQLiteDatabase db=this.getReadableDatabase();
//        Cursor cursor=db.rawQuery(count,null);
//
//        return cursor.getCount();
//    }

    public String getNumber(int docid) {
        String sql = "Select doctor_number from appointment where id=" + docid;
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        String info=null;
        //ModelClass info = new ModelClass();
/*
        while(c.moveToNext()){
            info.setDoctor_number(c.getString(c.getColumnIndex("doctor_number")));

        }*/
        if (c.moveToFirst()) {
            do{
                info = c.getString(c.getColumnIndex("doctor_number"));

            }while(c.moveToNext());

        }
        return info;
    }

}












