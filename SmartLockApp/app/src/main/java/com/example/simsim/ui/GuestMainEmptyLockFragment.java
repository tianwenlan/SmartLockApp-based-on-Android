package com.example.simsim.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Created by Steven on 15/7/18.
 */
public class GuestMainEmptyLockFragment extends Fragment {

    private GuestFragmentCallBackInterface guestFragmentCallBackInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_empty_lock_list,container,false);

        Button button=(Button)view.findViewById(R.id.requestAccessButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guestFragmentCallBackInterface.getGuestMainActivity().setChoiceItem(4);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(guestFragmentCallBackInterface == null){
            guestFragmentCallBackInterface =(GuestFragmentCallBackInterface)activity;
        }
    }
}
