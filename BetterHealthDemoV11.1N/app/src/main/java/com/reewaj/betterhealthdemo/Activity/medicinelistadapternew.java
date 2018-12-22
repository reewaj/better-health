package com.reewaj.betterhealthdemo.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.reewaj.betterhealthdemo.Database.DatabaseHandlerStock;
import com.reewaj.betterhealthdemo.R;

import java.util.ArrayList;

public class medicinelistadapternew extends ArrayAdapter<ModelMedicine> {
    Context context;
    public ImageView item_image;
    public TextView item_name,item_notes,item_remmed,purchaseDate;
    private AlertDialog.Builder alertDialogBuilder;
    LayoutInflater inflater;
    AlertDialog dialog;
    Button btn_edit,btn_delete;
    ArrayList<ModelMedicine>list;
    public medicinelistadapternew(@NonNull Context context, ArrayList<ModelMedicine>list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.medicinelistcard,null);
        item_image=view.findViewById(R.id.item_image);
        item_name=view.findViewById(R.id.item_name);
        item_notes=view.findViewById(R.id.item_notes);
        item_remmed=view.findViewById(R.id.item_remmed);
        btn_edit=view.findViewById(R.id.btn_edit);
        btn_delete=view.findViewById(R.id.btn_delete);

        ModelMedicine info = getItem(position);

        item_name.setText(info.getMedicineName());
        //   notes.setText(medicineitem.getPurchaseDate());
        item_notes.setText(info.getNotes());
        item_remmed.setText(String.valueOf(info.getQuantity()));
        if(info.getMedicineImage()!=null)
            item_image.setImageBitmap(ImageConversion.getBitmap(info.getMedicineImage()));

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelMedicine modelMedicine= list.get(position);
                deleteItem(modelMedicine.getMedicineId(),position);
            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelMedicine modelMedicine = list.get(position);
                Intent intent = new Intent(context,UpdateMedicineList.class);
                intent.putExtra("modelmedicineid",modelMedicine.getMedicineId());
                context.startActivity(intent);
            }
        });

        return view;
    }

    public void editItem(int position){

    }

    public void deleteItem(final int id, final int position){
        alertDialogBuilder = new AlertDialog.Builder(context);

        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.confirmation_dialog, null);

        Button noButton = (Button) view.findViewById(R.id.noButton);
        Button yesButton = (Button) view.findViewById(R.id.yesButton);

        alertDialogBuilder.setView(view);
        dialog = alertDialogBuilder.create();
        dialog.show();


        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete the item.
                DatabaseHandlerStock db = new DatabaseHandlerStock(context);
                //delete item
                db.deleteMedicine(id);
                list.remove(position);
                notifyDataSetChanged();

                dialog.dismiss();


            }
        });
    }
}
