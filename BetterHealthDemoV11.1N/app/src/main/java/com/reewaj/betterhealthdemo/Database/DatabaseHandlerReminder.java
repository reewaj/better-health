package com.reewaj.betterhealthdemo.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.reewaj.betterhealthdemo.Activity.ModelMedicineReminder;

import java.util.ArrayList;

public class DatabaseHandlerReminder extends SQLiteOpenHelper {
    static String database="BetterHealth";
    static int version=1;
    String table = "CREATE TABLE if not exists `medicinereminder` " +
            "( `reminderid` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
            "`userid` INTEGER, " +
            "`medicinename` TEXT, " +
            "`remindertime` TEXT, `image` BLOB, `reminderstart` TEXT, `reminderend` TEXT, " +
            "`instruction` TEXT, `interval` TEXT, `medicineid` INTEGER )";

    public DatabaseHandlerReminder(Context context) {
        super(context, database, null, 1);
        getWritableDatabase().execSQL(table);
    }

    public void addMedicineReminder(ContentValues contentValues)
    {
        getWritableDatabase().insert("medicinereminder","",contentValues);
    }
    public ArrayList<ModelMedicineReminder> getMedicineReminder()
    {
        String sql = "Select * from medicinereminder";
        Cursor c = getReadableDatabase().rawQuery(sql,null);
        ArrayList<ModelMedicineReminder> list = new ArrayList<>();
        while(c.moveToNext())
        {
            ModelMedicineReminder info = new ModelMedicineReminder();
            info.setTime(c.getString(c.getColumnIndex("remindertime")));//remindertime
            info.setDate(c.getString(c.getColumnIndex("reminderstart")));
            info.setMedicine_name(c.getString(c.getColumnIndex("medicinename")));
            info.setReminderid(c.getInt(c.getColumnIndex("reminderid")));
            list.add(info);
        }
        return list;
    }
    public void deleteReminder(int id)
    {
        getWritableDatabase().delete("medicinereminder","reminderid="+id,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
