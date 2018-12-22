package com.reewaj.betterhealthdemo.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reewaj.betterhealthdemo.Database.DatabaseHandlerReminder;
import com.reewaj.betterhealthdemo.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    RecyclerView rv_home;
    ArrayList<ModelMedicineReminder> medicinereminder;
    DatabaseHandlerReminder databaseHandlerReminder;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_list,null);
        rv_home = view.findViewById(R.id.rv_home);
        medicinereminder = new ArrayList<>();
        databaseHandlerReminder = new DatabaseHandlerReminder(getActivity());

        /*medicinereminder.add(new ModelMedicineReminder(R.mipmap.ic_launcher_foreground_medicine1,"plaxin","for blood pressure, daily three times","32"));
        medicinereminder.add(new ModelMedicineReminder(R.mipmap.ic_launcher_foreground_medicine2,"socin","is for stomache, evening one time","51"));
        medicinereminder.add(new ModelMedicineReminder(R.mipmap.ic_launcher_foreground_medicine3,"alpram","for headache, before dinner","30"));
        medicinereminder.add(new ModelMedicineReminder(R.mipmap.ic_launcher_foreground_medicine1,"plaxin","for blood pressure, daily three times","32"));
        medicinereminder.add(new ModelMedicineReminder(R.mipmap.ic_launcher_foreground_medicine2,"socin","is for stomache, evening one time","51"));
        medicinereminder.add(new ModelMedicineReminder(R.mipmap.ic_launcher_foreground_medicine3,"alpram","for headache, before dinner","30"));
        medicinereminder.add(new ModelMedicineReminder(R.mipmap.ic_launcher_foreground_medicine1,"plaxin","for blood pressure, daily three times","32"));
        medicinereminder.add(new ModelMedicineReminder(R.mipmap.ic_launcher_foreground_medicine2,"socin","is for stomache, evening one time","51"));
        medicinereminder.add(new ModelMedicineReminder(R.mipmap.ic_launcher_foreground_medicine3,"alpram","for headache, before dinner","30"));
        medicinereminder.add(new ModelMedicineReminder(R.mipmap.ic_launcher_foreground_medicine1,"plaxin","for blood pressure, daily three times","32"));
        medicinereminder.add(new ModelMedicineReminder(R.mipmap.ic_launcher_foreground_medicine2,"socin","is for stomache, evening one time","51"));
        medicinereminder.add(new ModelMedicineReminder(R.mipmap.ic_launcher_foreground_medicine3,"alpram","for headache, before dinner","30"));*/

        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        RecyclerView.LayoutManager rvLiLayoutManager=layoutManager;
        rv_home.setLayoutManager(rvLiLayoutManager);

        HomeMedicineAdapter adapter =new HomeMedicineAdapter(getActivity(),databaseHandlerReminder.getMedicineReminder());


        rv_home.setAdapter(adapter);

        return view;
    }
}
