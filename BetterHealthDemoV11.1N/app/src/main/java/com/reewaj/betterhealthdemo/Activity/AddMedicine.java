package com.reewaj.betterhealthdemo.Activity;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.reewaj.betterhealthdemo.Database.DatabaseHandlerReminder;
import com.reewaj.betterhealthdemo.R;

import java.util.Calendar;

public class AddMedicine extends AppCompatActivity {

    Calendar c;
    DatePickerDialog dpd;
    TimePickerDialog tpd;
    NumberPicker npd;
    //##use proper name for all variables
    //database work
    private EditText medicineName;
    private EditText MedicineDosage;
    private EditText medicineStartFrom;
    private EditText medicineTime;
    private Spinner MedicineInterval;
    private Spinner MedicineInstruction;
    private Spinner medicineDuration;
    private Button saveButton;
    private Spinner dur;
    private EditText durdate;
    private String days;
    int day, month, year;
    DatabaseHandlerReminder databaseHandlerReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_medicine);

        medicineName = (EditText) findViewById(R.id.medicineName);
        medicineTime = (EditText) findViewById((R.id.time));
        medicineStartFrom = (EditText) findViewById(R.id.startsFrom);
        saveButton = findViewById(R.id.saveButton);
        databaseHandlerReminder = new DatabaseHandlerReminder(this);
        dur=(Spinner)findViewById(R.id.duration);
        durdate=(EditText)findViewById(R.id.dur_time) ;

        // name.setOnClickListener(new View.OnClickListener() {
        //   @Override
        //public void onClick(View v) {
        //  Intent Intent = new Intent(AddMedicine.this, medicinelist.class);
        //startActivity(Intent);
        // }
        //});

        medicineTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR);
                int minute = c.get(Calendar.MINUTE);

                tpd = new TimePickerDialog(AddMedicine.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int thour, int tminute) {
                        medicineTime.setText(thour + ":" + tminute);
                        String am_pm;
                        if (thour < 12) {
                            am_pm = "AM";
                            medicineTime.setText(thour + ":" + tminute + " " + am_pm);
                        } else if (thour == 12) {
                            am_pm = "PM";
                            medicineTime.setText(thour + ":" + tminute + " " + am_pm);
                        } else {
                            am_pm = "PM";
                            medicineTime.setText(thour + ":" + tminute + " " + am_pm);
                        }
                    }
                }, hour, minute, false);
                tpd.show();

                //medicineTime.setText(hour+":"+minute);

            }
        });

        medicineStartFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c = Calendar.getInstance();
                day = c.get(Calendar.DAY_OF_MONTH);
                month = c.get(Calendar.MONTH);
                year = c.get(Calendar.YEAR);


                dpd = new DatePickerDialog(AddMedicine.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                        year++;
                        medicineStartFrom.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);
                    }

                }, year, month, day);
                dpd.show();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("medicinename",medicineName.getText().toString());
                contentValues.put("remindertime",medicineTime.getText().toString());
                contentValues.put("reminderstart",medicineStartFrom.getText().toString());

                databaseHandlerReminder.addMedicineReminder(contentValues);
                Toast.makeText(AddMedicine.this,"Reminder has been added",Toast.LENGTH_SHORT).show();
            }
        });


        dur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("Set end date"))
                {

                    c = Calendar.getInstance();
                    day = c.get(Calendar.DAY_OF_MONTH);
                    month = c.get(Calendar.MONTH);
                    year = c.get(Calendar.YEAR);


                    dpd = new DatePickerDialog(AddMedicine.this, new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                            year++;
                            durdate.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);
                        }

                    }, year, month, day);
                    dpd.show();
                }

                else if(selectedItem.equals("Set number of days")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddMedicine.this);
                    builder.setTitle("Set no of days");

// Set up the input
                    final EditText input = new EditText(AddMedicine.this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                    input.setInputType(InputType.TYPE_CLASS_NUMBER);
                    builder.setView(input);

// Set up the buttons
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            days= input.getText().toString();
                            durdate.setText(days);
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.show();
                }

                else{
                    durdate.setHint("Forever");
                    durdate.setText("");
                }
            }
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });

    }
}
