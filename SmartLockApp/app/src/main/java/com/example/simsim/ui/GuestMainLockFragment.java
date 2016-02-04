package com.example.simsim.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simsim.entities.EntityAdapter;
import com.example.simsim.entities.Lock;
import com.example.simsim.interfaces.GuestLockInterface;

import java.util.List;


/**
 * Created by Steven on 15/7/18.
 */
public class GuestMainLockFragment extends Fragment {

    private GuestFragmentCallBackInterface guestFragmentCallBackInterface;
    private Lock clickedLock;//the lock that the user clicked and wanted open or lock.
    private ListView locksLV;
    private GuestLockInterface guestLockInterface;
    private ClientLockListAdapter adapter;

    private List<Lock> lockList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_guestmain_lock,container,false);

        locksLV=(ListView)view.findViewById(R.id.locksLV);
        guestLockInterface=new EntityAdapter();
        lockList=getLockList();

        adapter=new ClientLockListAdapter(lockList);
        locksLV.setAdapter(adapter);

        locksLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clickedLock = (Lock) parent.getItemAtPosition(position);
                boolean state = clickedLock.isLocked();

                if (state) {
                    openLock(clickedLock);
                    Toast.makeText(guestFragmentCallBackInterface.getGuestMainActivity()
                            , "the lock is open", Toast.LENGTH_SHORT).show();
                } else {
                    closeLock(clickedLock);
                    Toast.makeText(guestFragmentCallBackInterface.getGuestMainActivity()
                            , "the lock is closed", Toast.LENGTH_SHORT).show();
                }
                adapter.notifyDataSetChanged();
            }
        });


        return view;
    }

    /*@Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }*/

    public void onAttach(Activity activity){
        super.onAttach(activity);
        if(guestFragmentCallBackInterface == null){
            guestFragmentCallBackInterface =(GuestFragmentCallBackInterface)activity;
        }
    }

    // get all of the available locks for the guest, which are permitted by the host and confirmed by this guest.
    public List<Lock> getLockList(){
        return guestLockInterface.getClientLockList();
    }

    public  boolean openLock(Lock lock){
        lock.setIsLocked(false);
        return guestLockInterface.openLock(lock);
    }

    public boolean closeLock(Lock lock){
        lock.setIsLocked(true);
        return guestLockInterface.closeLock(lock);
    }

    private class ClientLockListAdapter extends ArrayAdapter<Lock>{

        public ClientLockListAdapter(List<Lock> list){
            super(getActivity(), R.layout.item_guest_locks_list,list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView==null){
                convertView=getActivity().getLayoutInflater()
                        .inflate(R.layout.item_guest_locks_list,null);
            }
            Lock lock2=getItem(position);
            String despt=lock2.getDescription();
            String lockState;
            if(lock2.isLocked()){
                lockState="Closed";
            }else{
                lockState="Open";
            }
            TextView description=(TextView)convertView.findViewById(R.id.nameLockTV);
            TextView state=(TextView)convertView.findViewById(R.id.stateLockTV);
            description.setText(despt);
            state.setText(lockState);

            return convertView;
        }
    }
}
