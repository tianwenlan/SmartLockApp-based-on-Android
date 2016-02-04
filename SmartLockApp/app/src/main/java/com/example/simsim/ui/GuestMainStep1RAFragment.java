package com.example.simsim.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.simsim.entities.EntityAdapter;
import com.example.simsim.interfaces.GuestEventInterface;


/**
 * Created by Steven on 15/7/18.
 */
public class GuestMainStep1RAFragment extends Fragment {

    private GuestFragmentCallBackInterface guestFragmentCallBackInterface;
    //use the guestLockInterface to call its loadHostIdFromDB(String primaryPhoneNumber) method
    //to get the  host id from database and assign the value to the static hostId in Information.
    private GuestEventInterface guestEventInterface;

    private EditText hostPhone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_guest_step1_request_access, container, false);

        hostPhone=(EditText)view.findViewById(R.id.hostPhoneET);
        guestEventInterface=new EntityAdapter();

        Button search=(Button)view.findViewById(R.id.searchHostPhoneButton);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNum = hostPhone.getText().toString();
                if (phoneNum == null || phoneNum.equals("")) {
                    hostPhone.setText("");
                    Toast.makeText(guestFragmentCallBackInterface.getGuestMainActivity()
                            , "please type into a valid phone number!", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        Double.parseDouble(phoneNum);
                        if(guestEventInterface.loadHostIdFromDB(phoneNum)){
                            guestFragmentCallBackInterface.getGuestMainActivity().setChoiceItem(5);

                        }else{
                            Toast.makeText(guestFragmentCallBackInterface.getGuestMainActivity(),"please check the phone number!",Toast.LENGTH_SHORT).show();
                        }
                        hostPhone.setText("");
                    } catch (Exception e) {
                        hostPhone.setText("");
                        Toast.makeText(guestFragmentCallBackInterface.getGuestMainActivity()
                                , "please type into a valid phone number!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(guestFragmentCallBackInterface ==null){

            guestFragmentCallBackInterface =(GuestFragmentCallBackInterface)activity;

        }
    }
}
