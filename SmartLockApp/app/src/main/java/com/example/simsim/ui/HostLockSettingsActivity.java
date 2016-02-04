package com.example.simsim.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simsim.database.DatabaseConstantInterface;
import com.example.simsim.entities.EntityAdapter;
import com.example.simsim.entities.Lock;
import com.example.simsim.entities.Property;
import com.example.simsim.interfaces.HostSpaceInterface;

public class HostLockSettingsActivity extends Activity
        implements UIConstantInterface, DatabaseConstantInterface{

    private HostSpaceInterface hostSpaceInterface;
    private boolean isNew;
    private Property property;
    private Lock lock;

    private EditText editTextLockName;
    private TextView textViewLockID;
    private SeekBar seekBarLockPower;
    private SeekBar seekBarLockRotationEndPoint;
    private EditText editTextLockStartAngle;
    private EditText editTextLockEndAngle;
    private RelativeLayout relativeLayoutLockDirection;
    private TextView textViewLockDirection;
    private Button buttonLockSave;
    private Button buttonLockDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_lock_settings);

        hostSpaceInterface = new EntityAdapter();
        Intent intent = getIntent();
        isNew = intent.getStringExtra("operation").equals("insert") ? true : false;
        property = (Property) intent.getSerializableExtra("property");
        if(isNew){
            lock = new Lock();
            lock.setRotationDirection(LOCK_DIRECTION_CLOCKWISE);
        }
        else lock = (Lock) intent.getSerializableExtra("lock");

        editTextLockName = (EditText) findViewById(R.id.textViewtSpaceLockName);
        textViewLockID = (TextView) findViewById(R.id.textViewLockID);
        seekBarLockPower = (SeekBar) findViewById(R.id.seekBarLockPower);
        seekBarLockRotationEndPoint = (SeekBar) findViewById(R.id.seekBarLockRotationEndpoint);
        editTextLockStartAngle = (EditText) findViewById(R.id.editTextLockStartAngle);
        editTextLockEndAngle = (EditText) findViewById(R.id.editTextLockEndAngle);
        relativeLayoutLockDirection
                = (RelativeLayout) findViewById(R.id.relativeLayoutLockDirection);
        textViewLockDirection = (TextView) findViewById(R.id.textViewLockDirection);
        buttonLockSave = (Button) findViewById(R.id.buttonLockSave);
        buttonLockDelete = (Button) findViewById(R.id.buttonLockDelete);

        editTextLockName.setText(lock.getDescription());
        int lockId = lock.getLockId();
        if(lockId != 0) textViewLockID.setText(Integer.toString(lock.getLockId()));
        else textViewLockID.setText("");
        seekBarLockPower.setProgress((int)lock.getLockPower());
        seekBarLockRotationEndPoint.setProgress((int)lock.getRotationEndPoints());
        editTextLockStartAngle.setText(Double.toString(lock.getLockStartAngle()));
        editTextLockEndAngle.setText(Double.toString(lock.getLockEndAngle()));
        textViewLockDirection.setText(lock.getRotationDirection());

        relativeLayoutLockDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textViewLockDirection.getText().toString().equals(LOCK_DIRECTION_CLOCKWISE)){
                    textViewLockDirection.setText(LOCK_DIRECTION_COUNTERCLOCKWISE);
                }
                else textViewLockDirection.setText(LOCK_DIRECTION_CLOCKWISE);
            }
        });

        if(isNew) {
            buttonLockDelete.setEnabled(false);
            buttonLockDelete.setVisibility(View.GONE);
        }

        buttonLockSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNew) insertLock(property, lock);
                else updateLock(property, lock);
            }
        });

        buttonLockDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteLock(property, lock);
            }
        });

        // To be removed
        buttonLockDelete.setEnabled(false);
        buttonLockDelete.setVisibility(View.GONE);
    }

    public void insertLock(Property property, Lock lock){
        try{
            lock.setIsLocked(false);
            lock.setLockPower(seekBarLockPower.getProgress());
            lock.setLockStartAngle(Double.parseDouble(editTextLockStartAngle.getText().toString()));
            lock.setLockEndAngle(Double.parseDouble(editTextLockEndAngle.getText().toString()));
            lock.setDescription(editTextLockName.getText().toString());
            lock.setRotationDirection(textViewLockDirection.getText().toString());
            lock.setRotationEndPoints(seekBarLockRotationEndPoint.getProgress());

            hostSpaceInterface.insertLock(property, lock);
            Toast.makeText(HostLockSettingsActivity.this, MESSAGE_UPDATE_SUCCESS,
                    Toast.LENGTH_LONG).show();

            finish();
        } catch (Exception e){
            Toast.makeText(HostLockSettingsActivity.this, MESSAGE_UPDATE_EXCEPTION,
                    Toast.LENGTH_LONG).show();
        }
    }

    public void updateLock(Property property, Lock lock){
        try{
            lock.setIsLocked(false);
            lock.setLockPower(seekBarLockPower.getProgress());
            lock.setLockStartAngle(Double.parseDouble(editTextLockStartAngle.getText().toString()));
            lock.setLockEndAngle(Double.parseDouble(editTextLockEndAngle.getText().toString()));
            lock.setDescription(editTextLockName.getText().toString());
            lock.setRotationDirection(textViewLockDirection.getText().toString());
            lock.setRotationEndPoints(seekBarLockRotationEndPoint.getProgress());

            hostSpaceInterface.updateLock(property, lock);
            Toast.makeText(HostLockSettingsActivity.this, MESSAGE_UPDATE_SUCCESS,
                    Toast.LENGTH_LONG).show();
        } catch (Exception e){
            Toast.makeText(HostLockSettingsActivity.this, MESSAGE_UPDATE_EXCEPTION,
                    Toast.LENGTH_LONG).show();
        }
    }

    public void deleteLock(Property property, Lock lock){
        try{
            hostSpaceInterface.deleteLock(property, lock);
            Toast.makeText(HostLockSettingsActivity.this, MESSAGE_DELETE_SUCCESS,
                    Toast.LENGTH_LONG).show();
        } catch (Exception e){
            Toast.makeText(HostLockSettingsActivity.this, MESSAGE_UPDATE_EXCEPTION,
                    Toast.LENGTH_LONG).show();
        }
    }

}
