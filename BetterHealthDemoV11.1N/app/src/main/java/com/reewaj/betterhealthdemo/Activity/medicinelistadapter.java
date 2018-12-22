package com.reewaj.betterhealthdemo.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.reewaj.betterhealthdemo.Database.DatabaseHandlerStock;
import com.reewaj.betterhealthdemo.R;

import java.util.ArrayList;

public class medicinelistadapter extends RecyclerView.Adapter<medicinelistadapter.ViewHolder>{
    Button btn_edit,btn_delete;

    private Context mContext;
    private AlertDialog.Builder alertDialogBuilder;
    LayoutInflater inflater;
    AlertDialog dialog;
    private ArrayList<ModelMedicine> mList;

    public medicinelistadapter(Context context, ArrayList<ModelMedicine>list){
        mContext=context;
        mList =list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.medicinelistcard, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {

        ModelMedicine medicineitem=mList.get(position);

        ImageView image = holder.item_image;
        TextView name,notes,remmed;
        name=holder.item_name;
        notes=holder.item_notes;
        remmed=holder.item_remmed;

        /*btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler(Looper.getMainLooper());

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ModelMedicine modelMedicine = mList.get(position);
                        Intent intent = new Intent(mContext,UpdateMedicineList.class);
                        intent.putExtra("modelmedicineid",modelMedicine.getMedicineId());
                        mContext.startActivity(intent);
                    }
                }, 1000 );

            }
        });*/

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelMedicine modelMedicine= mList.get(position);
                deleteItem(modelMedicine.getMedicineId(),position);
            }
        });

        /*image.setImageResource(mList.get(position).getImage());*/
        name.setText(medicineitem.getMedicineName());
     //   notes.setText(medicineitem.getPurchaseDate());
        notes.setText(medicineitem.getNotes());
        remmed.setText(Integer.toString(medicineitem.getQuantity()));
//        remmed.setText(medicineitem.getQuantity());


    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView item_image;
        public  TextView item_name,item_notes,item_remmed,purchaseDate;


        public ViewHolder(View itemView) {
            super(itemView);

    item_image=itemView.findViewById(R.id.item_image);
    item_name=itemView.findViewById(R.id.item_name);
    item_notes=itemView.findViewById(R.id.item_notes);
    item_remmed=itemView.findViewById(R.id.item_remmed);
    btn_edit=itemView.findViewById(R.id.btn_edit);
    btn_delete=itemView.findViewById(R.id.btn_delete);


        }
    }

   /* public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_delete:
                int position = getAdapterPosition();
                ModelMedicine modelMedicine= mList.get(position);
                editItem(modelMedicine);


                break;
            case R.id.btn_edit:
                position = getAdapterPosition();
                modelMedicine= mList.get(position);
                deleteItem(modelMedicine.getMedicineId());

                break;
        }
    }*/

    public void deleteItem(final int id, final int position){
        alertDialogBuilder = new AlertDialog.Builder(mContext);

        inflater = LayoutInflater.from(mContext);
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
                DatabaseHandlerStock db = new DatabaseHandlerStock(mContext);
                //delete item
                db.deleteMedicine(id);
                mList.remove(position);
                notifyItemRemoved(position);

                dialog.dismiss();


            }
        });
    }

    public void editItem(final ModelMedicine modelMedicine, int position){


    }

}
