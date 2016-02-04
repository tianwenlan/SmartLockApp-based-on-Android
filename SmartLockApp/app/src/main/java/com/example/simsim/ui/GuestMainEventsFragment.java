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
public class GuestMainEventsFragment extends Fragment {

    private GuestFragmentCallBackInterface guestFragmentCallBackInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_guestmain_events,container,false);

        Button addEvent=(Button)view.findViewById(R.id.addEventButton);
        addEvent.setOnClickListener(new View.OnClickListener() {
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
