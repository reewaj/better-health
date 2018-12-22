package com.reewaj.betterhealthdemo.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.reewaj.betterhealthdemo.Activity.ModelMedicine;

import java.util.ArrayList;

public class DatabaseHandlerStock extends SQLiteOpenHelper {

    static String database = "BetterHealth";
    static int version = 1;
    String table = "CREATE TABLE if not exists `stockmedicine` " +
            "( `userid` INTEGER," +
            " `medicinename` TEXT," +
            " `medicineid` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
            "`image` BLOB, `quantity` INTEGER, `purchasedate` TEXT, `consumptionfrequency` INTEGER, " +
            "`notes` TEXT, `stockfordays` INTEGER, `stockfortimes` INTEGER, `stockenddate` TEXT )";

    public DatabaseHandlerStock(Context context) {
        super(context, database, null, version);
        getWritableDatabase().execSQL(table);
    }

    public void addStockMedicince(ContentValues contentValues) {
        getWritableDatabase().insert("stockmedicine", "", contentValues);
    }

    public ArrayList<ModelMedicine> getStockEndDate() {
        String sql = "SELECT stockenddate from stockmedicine";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        ArrayList<ModelMedicine> stockEndDateList = new ArrayList<>();
        while (c.moveToNext()) {
            ModelMedicine stockEndDaysInfo = new ModelMedicine();
            stockEndDaysInfo.getEndStockDate();
            stockEndDateList.add(stockEndDaysInfo);
        }
        return stockEndDateList;
    }

    public ArrayList<ModelMedicine> getStockMedicine() {
        String sql = "Select * from stockmedicine";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        ArrayList<ModelMedicine> list = new ArrayList<>();
        while (c.moveToNext()) {
            ModelMedicine info = new ModelMedicine();
            info.setMedicineId(c.getInt(c.getColumnIndex("medicineid")));
            info.setMedicineName(c.getString(c.getColumnIndex("medicinename")));
            info.setNotes(c.getString(c.getColumnIndex("notes")));
            info.setQuantity(c.getInt(c.getColumnIndex("quantity")));
            info.setConsumptionFrequency(c.getInt(c.getColumnIndex("consumptionfrequency")));
            info.setStockfordays(c.getInt(c.getColumnIndex("stockfordays")));
            info.setStockfortimes(c.getInt(c.getColumnIndex("stockfortimes")));
            info.setEndStockDate(c.getString(c.getColumnIndex("stockenddate")));
            info.setPurchaseDate(c.getString(c.getColumnIndex("purchasedate")));
            info.setMedicineImage(c.getBlob(c.getColumnIndex("image")));
            if ((c.getBlob(c.getColumnIndex("image"))) != null)
                Log.i("tag", "Inside setMedicineImage");

            list.add(info);
        }
        return list;
    }

    public ModelMedicine getParticularMedicine(int medicineid) {
        String sql = "Select * from stockmedicine where medicineid=" + medicineid;
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        ModelMedicine info = new ModelMedicine();
        while (c.moveToNext()) {
            info.setMedicineId(c.getInt(c.getColumnIndex("medicineid")));
            info.setMedicineName(c.getString(c.getColumnIndex("medicinename")));
            info.setNotes(c.getString(c.getColumnIndex("notes")));
            info.setQuantity(c.getInt(c.getColumnIndex("quantity")));
            info.setConsumptionFrequency(c.getInt(c.getColumnIndex("consumptionfrequency")));
            info.setStockfordays(c.getInt(c.getColumnIndex("stockfordays")));
            info.setStockfortimes(c.getInt(c.getColumnIndex("stockfortimes")));
            info.setPurchaseDate(c.getString(c.getColumnIndex("purchasedate")));
            info.setEndStockDate(c.getString(c.getColumnIndex("stockenddate")));
            info.setMedicineImage(c.getBlob(c.getColumnIndex("image")));

        }
        return info;
    }


    public void updateTable(ContentValues contentValues, int medicineid) {
        getWritableDatabase().update("stockmedicine", contentValues, "medicineid=?", new String[]{String.valueOf(medicineid)});
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void deleteMedicine(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("stockmedicine", "medicineid=?", new String[]{String.valueOf(id)});

        db.close();


    }
}
