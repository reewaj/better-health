package com.reewaj.betterhealthdemo.Activity;

public class ModelMedicineReminder {

    private byte[] image;
    private String time, medicine_name, date;

    public int getReminderid() {
        return reminderid;
    }

    public void setReminderid(int reminderid) {
        this.reminderid = reminderid;
    }

    int reminderid;

    public ModelMedicineReminder(byte[] image, String name, String medicine_name) {

        this.image = image;
        this.medicine_name = name;
        this.time = time;
    }
    public ModelMedicineReminder()
    {

    }

    public String getTime()
    {
        return time;
    }
    public void setTime(String time) {

        this.time = time;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
