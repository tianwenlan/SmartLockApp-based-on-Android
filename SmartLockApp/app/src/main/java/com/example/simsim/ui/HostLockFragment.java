package com.example.simsim.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.simsim.entities.EntityAdapter;
import com.example.simsim.entities.Lock;
import com.example.simsim.interfaces.HostLockInterface;

import java.util.List;

public class HostLockFragment extends Fragment {

    private HostLockInterface hostLockInterface;
    private List<Lock> lockList;

    private ListView listViewLock;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_host_lock,container,false);
        listViewLock =(ListView) view.findViewById(R.id.listViewLock);

        hostLockInterface = new EntityAdapter();
        lockList = getLockList();

        LockListAdapter adapter = new LockListAdapter(lockList);
        listViewLock.setAdapter(adapter);

        return view;
    }

    private List<Lock> getLockList(){
        return hostLockInterface.getAllLockList();
    }

    private class LockListAdapter extends ArrayAdapter<Lock> {
        public LockListAdapter(List<Lock> lockList) {
            super(getActivity(), R.layout.item_host_lock, lockList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // if we weren't given a view, inflate one
            if (null == convertView) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.item_host_lock, null);
            }

            Lock lock = getItem(position);
            TextView textViewSidValue = (TextView)convertView.findViewById(R.id.textViewtSpaceLockName);
            textViewSidValue.setText(lock.getDescription());
            return convertView;
        }
    }

}
