package com.example.simsim.ui;


import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.simsim.database.DatabaseConstantInterface;
import com.example.simsim.entities.EntityAdapter;
import com.example.simsim.interfaces.ProfileInterface;
import com.example.simsim.local.HttpConnection;

public class HostEditProfileFragment extends Fragment
        implements DatabaseConstantInterface, UIConstantInterface{

    private ProfileInterface profileInterface;
    private int yearOfBirth;
    private int monthOfBirth;
    private int dateOfBirth;
    private String icon;

    private ImageView imageViewAvatar;
    private EditText editTextBirthday;
    private RadioButton radioButtonMale;
    private RadioButton radioButtonFemale;
    private EditText editTextEmail;
    private EditText editTextZipCode;
    private Button buttonConfirm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        profileInterface = new EntityAdapter();
        yearOfBirth = Integer.parseInt(profileInterface.getDateOfBirth().split("-")[0]);
        monthOfBirth = Integer.parseInt(profileInterface.getDateOfBirth().split("-")[1]);
        dateOfBirth = Integer.parseInt(profileInterface.getDateOfBirth().split("-")[2]);
        icon = profileInterface.getIcon();

        imageViewAvatar = (ImageView) view.findViewById(R.id.imageViewPropertyImage);
        editTextBirthday = (EditText) view.findViewById(R.id.editTextBirthday);
        radioButtonMale = (RadioButton) view.findViewById(R.id.radioButtonMale);
        radioButtonFemale = (RadioButton) view.findViewById(R.id.radioButtonFemale);
        editTextEmail = (EditText) view.findViewById(R.id.editTextEmail);
        editTextZipCode = (EditText) view.findViewById(R.id.editTextZipCode);
        buttonConfirm = (Button) view.findViewById(R.id.buttonConfirm);

        imageViewAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TBD
            }
        });

        editTextBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        yearOfBirth = year;
                        monthOfBirth = monthOfYear + 1;
                        dateOfBirth = dayOfMonth;
                        editTextBirthday
                                .setText(yearOfBirth + "-" + monthOfBirth + "-" + dateOfBirth);
                    }
                }, yearOfBirth, monthOfBirth - 1, dateOfBirth).show();
            }
        });

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    updateProfile(yearOfBirth + "-" + monthOfBirth + "-" + dateOfBirth,
                            radioButtonMale.isChecked() ? USER_GENDER_MALE : USER_GENDER_FEMALE,
                            editTextEmail.getText().toString(),
                            Integer.parseInt(editTextZipCode.getText().toString()),
                            icon);
                    Toast.makeText(getActivity(), MESSAGE_PROFILE_UPDATE_SUCCESS,
                            Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getActivity(), MESSAGE_PROFILE_UPDATE_EXCEPTION,
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        showProfile();
        return view;
    }

    //show the profile info that is already in db.
    public void showProfile(){
        try{
            Bitmap bitmap = HttpConnection.getHttpBitmap(icon);
            if(bitmap != null) imageViewAvatar.setImageBitmap(bitmap);
        } catch (Exception e){
            Toast.makeText(getActivity(), MESSAGE_PROFILE_LOAD_AVATAR_EXCEPTION,
                    Toast.LENGTH_LONG).show();
        }
        editTextBirthday.setText(yearOfBirth + "-" + monthOfBirth + "-" + dateOfBirth);
        if(profileInterface.getGender().equals(USER_GENDER_MALE)) {
            radioButtonMale.setChecked(true);
            radioButtonFemale.setChecked(false);
        }
        else{
            radioButtonMale.setChecked(false);
            radioButtonFemale.setChecked(true);
        }
        editTextEmail.setText(profileInterface.getEmailAddress());
        editTextZipCode.setText(Integer.toString(profileInterface.getZipCode()));
    }

    public void updateProfile(String dataOfBirth, String gender, String emailAddress,
                              int zipCode, String icon) throws Exception{
        profileInterface.updateProfile(dataOfBirth, gender, emailAddress, zipCode, icon);
    }

}
