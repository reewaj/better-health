package com.reewaj.betterhealthdemo.Model;

public class ModelClass {


    public int id;
    public String doctor_name;
    public String doctor_number;
    public String appointment_date;
    public String appointment_time;

    //default constructor
    public ModelClass() {
        //created as this class need to be called
    }


    public ModelClass(int id, String doctor_name, String doctor_number, String appointment_date, String appointment_time) {
        this.id = id;
        this.doctor_name = doctor_name;
        this.doctor_number = doctor_number;
        this.appointment_date = appointment_date;
        this.appointment_time = appointment_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getDoctor_number() {
        return doctor_number;
    }

    public void setDoctor_number(String doctor_number) {
        this.doctor_number = doctor_number;
    }

    public String getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(String appointment_date) {
        this.appointment_date = appointment_date;
    }

    public String getAppointment_time() {
        return appointment_time;
    }

    public void setAppointment_time(String appointment_time) {
        this.appointment_time = appointment_time;
    }


}
