package com.example.simsim.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.simsim.database.DatabaseConstantInterface;
import com.example.simsim.entities.EntityAdapter;
import com.example.simsim.interfaces.RegistrationInterface;


public class RegisterActivity extends Activity implements DatabaseConstantInterface{

    private RegistrationInterface registrationInterface;

    private Button buttonRegister;
    private EditText editTextUsername, editTextPassword, editTextCountry, editTextPhoneNumber;
    private RadioButton radioButtonHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registrationInterface = new EntityAdapter();

        editTextUsername = (EditText) findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.password);
        editTextCountry = (EditText) findViewById(R.id.country);
        editTextPhoneNumber = (EditText) findViewById(R.id.phoneNumber);
        buttonRegister = (Button) findViewById(R.id.btnRegister);
        radioButtonHost = (RadioButton) findViewById(R.id.radioButtonHost);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String primaryPhoneNumer = editTextPhoneNumber.getText().toString();
                String userState = radioButtonHost.isChecked() ? USER_STATE_HOST : USER_STATE_GUSET;
                registrationInterface.setBasicUserInfo(
                        editTextUsername.getText().toString(),
                        editTextPassword.getText().toString(),
                        editTextCountry.getText().toString(),
                        primaryPhoneNumer,
                        userState);
                getVerificationCode(editTextPhoneNumber.getText().toString());
                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this, VerificationActivity.class);
                startActivity(intent);
            }
        });
    }

    // Request server send an SMS message
    public void getVerificationCode(String primaryPhoneNumber){
        // TBD
    }

}
