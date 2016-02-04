package com.example.simsim.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.simsim.entities.EntityAdapter;
import com.example.simsim.entities.Property;
import com.example.simsim.interfaces.HostSpaceInterface;


public class HostSpaceSettingsActivity extends Activity implements UIConstantInterface{

    private HostSpaceInterface hostSpaceInterface;
    private boolean isNew;
    private Property property;

    private EditText editTextPropertyName;
    private EditText editTextPropertyAddress;
    private EditText editTextPropertyZipCode;
    private EditText editTextPropertyCity;
    private EditText editTextPropertyState;
    private EditText editTextPropertyCountry;
    private Button buttonSave;
    private Button buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_space_settings);

        hostSpaceInterface = new EntityAdapter();
        Intent intent = getIntent();
        isNew = intent.getStringExtra("operation").equals("insert") ? true : false;
        if(isNew) {
            property = new Property();
        }
        else property = (Property) intent.getSerializableExtra("property");

        editTextPropertyName = (EditText) findViewById(R.id.editTextPropertyName);
        editTextPropertyAddress = (EditText) findViewById(R.id.editTextPropertyAddress);
        editTextPropertyZipCode = (EditText) findViewById(R.id.editTextPropertyZipCode);
        editTextPropertyCity = (EditText) findViewById(R.id.editTextPropertyCity);
        editTextPropertyState = (EditText) findViewById(R.id.editTextPropertyState);
        editTextPropertyCountry = (EditText) findViewById(R.id.editTextPropertyCountry);
        buttonSave = (Button) findViewById(R.id.buttonPropertySave);
        buttonDelete = (Button) findViewById(R.id.buttonPropertyDelete);

        editTextPropertyName.setText(property.getDescription());
        editTextPropertyAddress.setText(property.getAddress());
        int zipCode = property.getZipCode();
        if(zipCode != 0) editTextPropertyZipCode.setText(Integer.toString(property.getZipCode()));
        else editTextPropertyZipCode.setText("");
        editTextPropertyCity.setText(property.getCity());
        editTextPropertyState.setText(property.getState());
        editTextPropertyCountry.setText(property.getCountry());

        if(isNew) {
            buttonDelete.setEnabled(false);
            buttonDelete.setVisibility(View.GONE);
        }

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNew) insertProperty(property);
                else updateProperty(property);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProperty(property);
            }
        });

        // To be removed
        buttonDelete.setEnabled(false);
        buttonDelete.setVisibility(View.GONE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            setResult(-1);
            finish();
            return false;
        }
        return false;
    }

    private void insertProperty(Property property){
        try{
            property.setUserId(hostSpaceInterface.getUserId());
            property.setDescription(editTextPropertyName.getText().toString());
            property.setAddress(editTextPropertyAddress.getText().toString());
            property.setZipCode(Integer.parseInt(editTextPropertyZipCode.getText().toString()));
            property.setCity(editTextPropertyCity.getText().toString());
            property.setState(editTextPropertyState.getText().toString());
            property.setCountry(editTextPropertyCountry.getText().toString());

            hostSpaceInterface.insertProperty(property);
            Toast.makeText(HostSpaceSettingsActivity.this, MESSAGE_UPDATE_SUCCESS,
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent();
            intent.putExtra("property", property);
            setResult(0, intent);
            finish();
        } catch (Exception e){
            Toast.makeText(HostSpaceSettingsActivity.this, MESSAGE_UPDATE_EXCEPTION,
                    Toast.LENGTH_LONG).show();
        }
    }

    private void updateProperty(Property property) {
        try {
            property.setDescription(editTextPropertyName.getText().toString());
            property.setAddress(editTextPropertyAddress.getText().toString());
            property.setZipCode(Integer.parseInt(editTextPropertyZipCode.getText().toString()));
            property.setCity(editTextPropertyCity.getText().toString());
            property.setState(editTextPropertyState.getText().toString());
            property.setCountry(editTextPropertyCountry.getText().toString());

            hostSpaceInterface.updateProperty(property);
            Toast.makeText(HostSpaceSettingsActivity.this, MESSAGE_UPDATE_SUCCESS,
                    Toast.LENGTH_LONG).show();

            setResult(1);
        } catch (Exception e){
            Toast.makeText(HostSpaceSettingsActivity.this, MESSAGE_UPDATE_EXCEPTION,
                    Toast.LENGTH_LONG).show();
        }
    }

    private void deleteProperty(Property property){
        try{
            hostSpaceInterface.deleteProperty(property);
            Toast.makeText(HostSpaceSettingsActivity.this, MESSAGE_DELETE_SUCCESS,
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent();
            intent.putExtra("property", property);
            setResult(0, intent);
            finish();
        } catch (Exception e){
            Toast.makeText(HostSpaceSettingsActivity.this, MESSAGE_DELETE_EXCEPTION,
                    Toast.LENGTH_LONG).show();
        }
    }

}
