package com.example.simsim.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.*;
import android.widget.*;

import com.example.simsim.database.DatabaseConstantInterface;
import com.example.simsim.debug.Debug;
import com.example.simsim.entities.EntityAdapter;
import com.example.simsim.interfaces.AuthenticationInterface;


public class LoginActivity extends Activity implements UIConstantInterface, DatabaseConstantInterface {

    private AuthenticationInterface authenticationInterface;

    private EditText editTextPhoneNumber;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
                .build());

        /*//permit to use the network in main UI thread.
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        authenticationInterface = new EntityAdapter();

        editTextPhoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonLogin = (Button) findViewById(R.id.btnLogin);
        buttonRegister = (TextView) findViewById(R.id.link_to_register);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String primaryPhoneNumber = editTextPhoneNumber.getText().toString();
                String password = editTextPassword.getText().toString();
                try{

                    if(authenticate(primaryPhoneNumber, password) == true){

                        //Debug.loadDataFromDB(primaryPhoneNumber);
                        authenticationInterface.loadDataFromDB(primaryPhoneNumber);

                        Intent intent = new Intent();
                        if(authenticationInterface.getUserState().equals(USER_STATE_HOST)){
                            intent.setClass(LoginActivity.this, HostMainActivity.class);
                        }
                        else{
                            intent.setClass(LoginActivity.this, GuestMainActivity.class);
                        }
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(LoginActivity.this, MESSAGE_LOGIN_WRONG_PASSWORD,
                                Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e){
                    Toast.makeText(LoginActivity.this, MESSAGE_LOGIN_EXCEPTION,
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    //authenticate the username and password
    private boolean authenticate(String primaryPhoneNumber, String password) throws Exception{
        return authenticationInterface.authenticate(primaryPhoneNumber, password);
    }

}
