package com.example.simsim.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.simsim.database.DatabaseConstantInterface;
import com.example.simsim.entities.EntityAdapter;
import com.example.simsim.interfaces.GuestEventInterface;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Steven on 15/7/18.
 */
public class GuestAddEventActivity extends Activity implements DatabaseConstantInterface {

    private GuestEventInterface guestEventInterface;

    private EditText startDate;
    private EditText endDate;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_add_event);
        guestEventInterface=new EntityAdapter();

        startDate=(EditText)findViewById(R.id.startDate);
        endDate=(EditText)findViewById(R.id.endDate);
        save=(Button)findViewById(R.id.saveActivityInfoButton);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String startDateStr=startDate.getText().toString();
                String endDateStr=endDate.getText().toString();

                if(startDateStr==null || endDateStr==null || startDateStr.equals("") || endDateStr.equals("")){
                    Toast.makeText(GuestAddEventActivity.this
                            ,"please enter the starting and ending date", Toast.LENGTH_SHORT).show();
                }else{
                    setNewLockActivityAccessStartTime(startDateStr);
                    setNewLockActivityAccessEndTime(endDateStr);
                    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
                    setNewLockActivityRequestAccessTimestamp(df.format(new Date()));
                    setNewLockActivityRequestStatus(LOCK_ACTIVITY_REQUEST_STATUS_ACCEPT);
                    insertNewLockActivity();

                    Toast.makeText(GuestAddEventActivity.this,"add new lock activity successfully!",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(GuestAddEventActivity.this,GuestMainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void setNewLockActivityAccessStartTime(String accessStartTime){
        guestEventInterface.setNewLockActivityAccessStartTime(accessStartTime);
    }
    public void setNewLockActivityAccessEndTime(String accessEndTime){
        guestEventInterface.setNewLockActivityAccessEndTime(accessEndTime);
    }
    public void setNewLockActivityRequestAccessTimestamp(String requestAccessTimestamp){
        guestEventInterface.setNewLockActivityRequestAccessTimestamp(requestAccessTimestamp);
    }
    public void setNewLockActivityRequestStatus(String requestStatus){
        guestEventInterface.setNewLockActivityRequestStatus(requestStatus);
    }
    public void setNewLockActivityAlert(int alert){

    }
    // Insert new lockActivity to DB, set lockActivityId to LockActivity object in Information.
    public void insertNewLockActivity(){
        try {
            guestEventInterface.insertNewLockActivity();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
