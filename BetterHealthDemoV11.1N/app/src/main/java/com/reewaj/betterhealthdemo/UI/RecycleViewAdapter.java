package com.reewaj.betterhealthdemo.UI;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.reewaj.betterhealthdemo.Database.DatabaseHandlerAppointment;
import com.reewaj.betterhealthdemo.R;
import com.reewaj.betterhealthdemo.Model.ModelClass;

import java.util.List;


public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>{
    private Context context;
    private List<ModelClass> mcList;
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog dialog;
    private LayoutInflater inflater;
    DatabaseHandlerAppointment db;
    public RecycleViewAdapter(Context context, List<ModelClass> modelClass) {
        this.context = context;
        this.mcList = modelClass;
    }
    /*
public class  RecycleViewAdapter extends ArrayAdapter<ModelClass> {


    private Context context;
    private List<ModelClass> mcList;
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog dialog;
    private LayoutInflater inflater;

    //copy
    public TextView doctorName;
    public TextView doctorNumber;
    public Button editButton;
    public Button deleteButton;
    public int id;
    public TextView appointmentdate;


    public RecycleViewAdapter(Context context, List<ModelClass> modelClass) {
        super(context,0,modelClass);
        this.context = context;
        this.mcList = modelClass;
    }


    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_row_appointment,null);

        ModelClass info = getItem(position);
        doctorName=(TextView) view.findViewById(R.id.doctorName);
        doctorNumber=(TextView) view.findViewById(R.id.doctorNumber);
        appointmentdate=(TextView) view.findViewById(R.id.appodate);
//            appointmenttime=(TextView) view.findViewById(R.id.appoTime);

        editButton=(Button)view.findViewById(R.id.editButton);
        deleteButton=(Button)view.findViewById(R.id.deleteButton);

        doctorName.setText(info.getDoctor_name());
        doctorNumber.setText(info.getDoctor_number());





  deleteButton.setOnClickListener(

        editButton.setOnClickListener(new View.OnClickListener() {
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
*/
    @NonNull
    @Override
    public RecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_appointment,parent,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter.ViewHolder holder, int position) {
        ModelClass modelClass=mcList.get(position);
        holder.doctorName.setText(modelClass.getDoctor_name());
        holder.doctorNumber.setText(modelClass.getDoctor_number());
        holder.appointmentdate.setText(modelClass.getAppointment_date());
        holder.appointmenttime.setText(modelClass.getAppointment_time());


    }

    @Override
    public int getItemCount() {

        return mcList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView doctorName;
        public TextView doctorNumber;
        public Button editButton;
        public Button deleteButton;
        public int id;
        public TextView appointmentdate;
        public TextView appointmenttime;


        public ViewHolder(View view,Context ctx) {
            super(view);
            context=ctx;
            doctorName=(TextView) view.findViewById(R.id.doctorName);
            doctorNumber=(TextView) view.findViewById(R.id.doctorNumber);
            appointmentdate=view.findViewById(R.id.appodate);
            appointmenttime=(TextView) view.findViewById(R.id.appotime);

            editButton=(Button)view.findViewById(R.id.editButton);
            deleteButton=(Button)view.findViewById(R.id.deleteButton);

            editButton.setOnClickListener(this);
            deleteButton.setOnClickListener(this);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position =getAdapterPosition();

                    ModelClass modelClass=mcList.get(position);
                    // Intent intent=new Intent(context,)


                }
            });
        }



        @Override
        public void onClick(View v) {
            db=new DatabaseHandlerAppointment(context);

            switch (v.getId()) {
                case R.id.editButton:
                    int position = getAdapterPosition();
                    ModelClass modelClass= mcList.get(position);
//                    makePhoneCall(modelClass.getId());
                    //makePhoneCall(v);
//                    String nos="9813131313";
                    String number=db.getNumber(modelClass.getId());
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:"+number));
                    v.getContext().startActivity(callIntent);


                    break;
                case R.id.deleteButton:
                    position = getAdapterPosition();
                    modelClass= mcList.get(position);
                    deleteItem(modelClass.getId());

                    break;
            }
        }

        public void deleteItem(final int id){
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
                    DatabaseHandlerAppointment db = new DatabaseHandlerAppointment(context);
                    //delete item
                    db.deleteAppointment(id);
                    mcList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());

                    dialog.dismiss();


                }
            });
        }

        public void editItem(final ModelClass modelClass){

        }
    }

}
