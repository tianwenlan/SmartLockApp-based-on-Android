package com.example.simsim.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.simsim.entities.EntityAdapter;
import com.example.simsim.entities.Property;
import com.example.simsim.interfaces.GuestEventInterface;

import java.util.List;

/**
 * Created by Steven on 15/7/18.
 */
public class GuestMainStep2RAFragment extends Fragment {

    private GuestFragmentCallBackInterface guestFragmentCallBackInterface;
    private GuestEventInterface guestEventInterface;
    private List<Property> listProperties;
    private GridView showProperties;//show a gird view of properties.
    private int hostID;

    private TextView requestHostName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_guest_step2_request_access,container,false);
        guestEventInterface=new EntityAdapter();
        requestHostName=(TextView)view.findViewById(R.id.requestHostNameTV);

        //get host id
        hostID=guestEventInterface.getHostId();
        //set the host id to text view
        requestHostName.setText(""+hostID);
        //get list of properties
        listProperties=guestEventInterface.getHostPropertyList(hostID);

        showProperties=(GridView)view.findViewById(R.id.gridOfSpaces);

        MyAdapter adapter=new MyAdapter(listProperties);
        showProperties.setAdapter(adapter);

        showProperties.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Property property=(Property)parent.getItemAtPosition(position);
                List<Integer> listOfLock=guestEventInterface.getHostLockIdList(property);
                int lockId=listOfLock.get(0);
                guestEventInterface.setNewLockActivityLockId(lockId);
                guestEventInterface.setNewLockActivityHostId(guestEventInterface.getHostId());
                guestEventInterface.setNewLockActivityGuestId(guestEventInterface.getGuestId());

                Intent intent=new Intent(guestFragmentCallBackInterface.getGuestMainActivity(), GuestAddEventActivity.class);
                startActivity(intent);
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

    private class MyAdapter extends ArrayAdapter<Property>{

        public MyAdapter(List<Property> list) {
            super(getActivity(), R.layout.item_guest_spaces,list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView==null){
                convertView=getActivity().getLayoutInflater()
                        .inflate(R.layout.item_guest_spaces,null);
            }

            Property property=getItem(position);
            ImageView showSpaceIV=(ImageView)convertView.findViewById(R.id.showSpaceIV);
            TextView showSpaceTV=(TextView)convertView.findViewById(R.id.showSpaceTV);
            showSpaceIV.setImageResource(R.drawable.example1);
            showSpaceTV.setText(property.getDescription());

            return convertView;
        }
    }
}
