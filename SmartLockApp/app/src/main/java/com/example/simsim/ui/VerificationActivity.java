package com.example.simsim.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.simsim.database.DatabaseConstantInterface;
import com.example.simsim.entities.EntityAdapter;
import com.example.simsim.interfaces.RegistrationInterface;


public class VerificationActivity extends Activity
        implements UIConstantInterface, DatabaseConstantInterface{

    private RegistrationInterface registrationInterface;

    private EditText editTextVerificationCode;
    private Button buttonConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
                .build());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        registrationInterface = new EntityAdapter();

        editTextVerificationCode = (EditText) findViewById(R.id.editTextVerificationCode);
        buttonConfirm = (Button) findViewById(R.id.confirm);

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sendVerificationCode(editTextVerificationCode.getText().toString())){
                    try{
                        if(registrationInterface.insertUser()){
                            Intent intent = new Intent();
                            if(registrationInterface.getUserState().equals(USER_STATE_HOST)){
                                intent.setClass(VerificationActivity.this, HostMainActivity.class);
                            }
                            else{
                                intent.setClass(VerificationActivity.this, GuestMainActivity.class);
                            }
                            Toast.makeText(VerificationActivity.this, MESSAGE_REGISTRATION_SUCCESS,
                                    Toast.LENGTH_LONG).show();
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(VerificationActivity.this, MESSAGE_REGISTRATION_FAILURE,
                                    Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e){
                        Toast.makeText(VerificationActivity.this, MESSAGE_REGISTRATION_EXCEPTION,
                                Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(VerificationActivity.this, MESSAGE_REGISTRATION_WRONG_CODE,
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //Verify code, insert User to DB, set userId to User object in Information.
    // If successful, return true.
    public boolean sendVerificationCode(String verificationCode){
        // TBD
        return true;
    }
}
