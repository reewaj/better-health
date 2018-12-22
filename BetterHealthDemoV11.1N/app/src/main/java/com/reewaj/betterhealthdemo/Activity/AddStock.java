package com.reewaj.betterhealthdemo.Activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.reewaj.betterhealthdemo.R;
import com.reewaj.betterhealthdemo.Database.DatabaseHandlerStock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddStock extends AppCompatActivity {

    EditText medicineName, medicineNotes, medicineQuantity, consumption;
    ImageView imageView;
    TextView purchaseDate, stockFor;
    Button save, purdate, btncamera;
    DatePickerDialog dpd;
    DatabaseHandlerStock databaseHandlerMedicine;
    Calendar c;
    Bitmap bitmap;
    SimpleDateFormat dateParser1, dateParser2;

    int purchaseUnits, consumptionFrequency;
    String medName, stockEndDate, stockPurchaseDate, date;
    int day, month, year;
    int stockForDays, stockForTimes;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_stock);
        save = (Button) findViewById(R.id.btn_savemedname);
        medicineName = (EditText) findViewById(R.id.medicineName);
        medicineNotes = (EditText) findViewById(R.id.et_notes);
        medicineQuantity = (EditText) findViewById(R.id.quantity);
        purdate = (Button) findViewById(R.id.purDateSelector);
        purchaseDate = (TextView) findViewById(R.id.purchaseDateET);
        consumption = (EditText) findViewById(R.id.consumption);
//        calculateStock = (Button) findViewById(R.id.calculateStock);
//        stockFor = (TextView) findViewById(R.id.stockFor);

        databaseHandlerMedicine = new DatabaseHandlerStock(this);

        btncamera = (Button) findViewById(R.id.btn_camera);
        imageView = (ImageView) findViewById((R.id.image_taken));

        btncamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });

        purdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                day = c.get(Calendar.DAY_OF_MONTH);
                month = c.get(Calendar.MONTH);
                year = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(AddStock.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                        //date = mDay + "/" + (mMonth + 1) + "/" + mYear;
                        year++;
                        purchaseDate.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);
                    }

                }, year, month, day);
                dpd.show();
            }
        });

//        calculateStock.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                flag = 1;
//                boolean isValid = validateData();
////                Toast.makeText(MainActivity.this,""+isValid, Toast.LENGTH_SHORT).show();
//                if (isValid == true) {
//                    try {
//                        purchaseUnits = Integer.valueOf(medicineQuantity.getText().toString());
//                        consumptionFrequency = Integer.valueOf(consumption.getText().toString());
//                    } catch (NumberFormatException e) {
//                        e.printStackTrace();
//                    }
//                    medName = medicineName.getText().toString();
//                    stockForDays = purchaseUnits / consumptionFrequency;
//                    stockForTimes = purchaseUnits % consumptionFrequency;
//                    if (stockForDays == 0) {
//                        stockFor.setText(medName + " is available in stock for consumption upto " + stockForTimes + " times.");
//                    } else if (stockForTimes == 0) {
//                        stockFor.setText(medName + " is available in stock for consumption upto " + stockForDays + " days.");
//                    } else {
//                        stockFor.setText(medName + " is available in stock for consumption upto " + stockForDays + " days and " + stockForTimes + " times.");
//                    }
//
//                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                    //getting current date
//                    Calendar calendar = Calendar.getInstance();
//                    sdf.format(calendar.getTime());
//                    calendar.add(Calendar.DAY_OF_MONTH, stockForDays);
//                    stockEndDate = sdf.format(calendar.getTime());
//                    stockEndDateTV.setText(stockEndDate);
//                } else {
//                    validateData();
//                }
//            }
//        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValid = validateStockData();
                if (isValid == true) {
                    try {
                        purchaseUnits = Integer.valueOf(medicineQuantity.getText().toString());
                        consumptionFrequency = Integer.valueOf(consumption.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    medName = medicineName.getText().toString();
                    stockForDays = purchaseUnits / consumptionFrequency;
                    stockForTimes = purchaseUnits % consumptionFrequency;

                    date = purchaseDate.getText().toString();
                    //block to calculate stock end day
                    //with purchase date and the calculated remaining days of stock
                    {
                        stockPurchaseDate = date;
                        dateParser1 = new SimpleDateFormat("dd/MM/yyyy");
                        Calendar calendar = Calendar.getInstance();
                        try {
                            calendar.setTime(dateParser1.parse(stockPurchaseDate));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        calendar.add(Calendar.DATE, stockForDays);
                        dateParser2 = new SimpleDateFormat("dd/MM/yyyy");
                        stockEndDate = dateParser2.format(calendar.getTime());

                    }
                    ContentValues contentValues = new ContentValues();

                    contentValues.put("medicinename", medicineName.getText().toString());
                    contentValues.put("quantity", medicineQuantity.getText().toString());
                    contentValues.put("consumptionfrequency", consumption.getText().toString());
                    contentValues.put("notes", medicineNotes.getText().toString());
                    contentValues.put("stockfordays", stockForDays);
                    contentValues.put("stockfortimes", stockForTimes);
                    if (bitmap != null) {
                        contentValues.put("image", ImageConversion.getBlob(bitmap));
                        Log.i("tag", "Inside bitmap");
                    }
                    contentValues.put("purchasedate", date);
                    contentValues.put("stockenddate", stockEndDate);
                    databaseHandlerMedicine.addStockMedicince(contentValues);
                    SetAlarmStock setAlarmStock = new SetAlarmStock(AddStock.this);
                    setAlarmStock.setAlarm();
                    Toast.makeText(AddStock.this, "Stock Added!", Toast.LENGTH_LONG).show();
                } else {
                    validateStockData();
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private boolean validateStockData() {
        boolean valid = true;
        if (TextUtils.isEmpty(medicineName.getText().toString())) {
            medicineName.setError("Medicine name can't be empty.");
            medicineName.requestFocus();
            valid = false;
        } else {
            medicineName.setError(null);
        }
        if (TextUtils.isEmpty(medicineQuantity.getText().toString())) {
            medicineQuantity.setError("Quantity can't be empty.");
            medicineQuantity.requestFocus();
            valid = false;
        } else {
            medicineQuantity.setError(null);
        }
        if (TextUtils.isEmpty(consumption.getText().toString())) {
            consumption.setError("Consumption can't be empty.");
            consumption.requestFocus();
            valid = false;
        } else {
            consumption.setError(null);
        }
        if ((purchaseDate.getText().toString()).equalsIgnoreCase("Click\"Select\"")) {
            purchaseDate.setError("Please select a valid date.");
            purchaseDate.requestFocus();
            valid = false;
        }
        return valid;
    }
}