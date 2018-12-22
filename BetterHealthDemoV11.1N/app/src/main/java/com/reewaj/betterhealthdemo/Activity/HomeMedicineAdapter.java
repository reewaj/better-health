package com.reewaj.betterhealthdemo.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.reewaj.betterhealthdemo.Database.DatabaseHandlerReminder;
import com.reewaj.betterhealthdemo.R;

import java.util.ArrayList;

public class HomeMedicineAdapter extends RecyclerView.Adapter<HomeMedicineAdapter.ViewHolder> {

    public Context mContext;
    public ArrayList<ModelMedicineReminder> mList;
    Button btn_deleterem;
    Button delete;

    HomeMedicineAdapter(Context context, ArrayList<ModelMedicineReminder> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public HomeMedicineAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.fragment_home_card, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeMedicineAdapter.ViewHolder holder, final int position) {

        final ModelMedicineReminder modelMedicineReminder = mList.get(position);

        ImageView image = holder.item_image;
        TextView time, medicinename;
        time = holder.time_display;
        medicinename = holder.medicine_name_display;


        //image.setImageResource(mList.get(position).getImage());
        time.setText(modelMedicineReminder.getTime());
//        medicinename.setText(medicineitem.getNotes());
        medicinename.setText(modelMedicineReminder.getMedicine_name());

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem(modelMedicineReminder.getReminderid(),position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {

        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView item_image;
        public TextView time_display, medicine_name_display;

        /* ImageView btn_tick;*/

        public ViewHolder(View itemView) {
            super(itemView);

            item_image = itemView.findViewById(R.id.item_image);
            time_display = itemView.findViewById(R.id.time_display);
            medicine_name_display = itemView.findViewById(R.id.medicine_name_display);
            delete = itemView.findViewById(R.id.btn_deleterem);

            /*            btn_tick = itemView.findViewById(R.id.btn_tick);*/
        }
    }
    public void deleteItem(final int id, final int position){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.confirmation_dialog, null);

        Button noButton = (Button) view.findViewById(R.id.noButton);
        Button yesButton = (Button) view.findViewById(R.id.yesButton);

        alertDialogBuilder.setView(view);
        final Dialog dialog = alertDialogBuilder.create();
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
                DatabaseHandlerReminder db = new DatabaseHandlerReminder(mContext);
                //delete item
                db.deleteReminder(id);
                mList.remove(position);
                notifyDataSetChanged();

                dialog.dismiss();


            }
        });
    }

}