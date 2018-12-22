package com.reewaj.betterhealthdemo.Activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.reewaj.betterhealthdemo.R;

public class EditProfileFragment extends Fragment {

    private TextInputLayout textInputUsername;
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputAge;
    Button savebttn;

    public EditProfileFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        textInputUsername = (TextInputLayout) rootview.findViewById(R.id.text_input_username);
        textInputEmail = (TextInputLayout) rootview.findViewById(R.id.text_input_email);
        textInputAge = (TextInputLayout) rootview.findViewById(R.id.text_input_age);
        savebttn = rootview.findViewById(R.id.savebttn);

        savebttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //content Values ko kaam database ma insert garna lai
            }
        });

        //
        // return inflater.inflate(R.layout.fragment_edit_profile,container,false);
        return rootview;
    }

    private boolean validateUsername() {
        String usernameInput = textInputUsername.getEditText().getText().toString().trim();

        if (usernameInput.isEmpty()) {
            textInputUsername.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 30) {
            textInputUsername.setError("Username too long");
            return false;
        } else {
            textInputUsername.setError(null);
            return true;
        }
    }

    private  boolean validateEmail(){
        String emailInput = textInputEmail.getEditText().getText().toString().trim();
        if(emailInput.isEmpty()){
            textInputEmail.setError("Field can't be empty");
            return false;
        }else{
            textInputEmail.setError(null);
            return true;
        }

    }



    private boolean validateAge() {
        String passwordInput = textInputAge.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()) {
            textInputAge.setError("Field can't be empty");
            return false;
        } else {
            textInputAge.setError(null);
            return true;
        }
    }

    public void confirmInput(View v) {

        if (!validateEmail() | !validateUsername() | !validateAge()) {
            return;
        }

        String input = "Email: " + textInputEmail.getEditText().getText().toString();
        input += "\n";
        input += "Username: " + textInputUsername.getEditText().getText().toString();
        input += "\n";
        input += "Password: " + textInputAge.getEditText().getText().toString();

        //Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }


}
