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
import com.example.simsim.entities.LockActivity;
import com.example.simsim.interfaces.HistoryInterface;

import java.util.List;

public class HostHistoryFragment extends Fragment {

    private HistoryInterface historyInterface;
    private List<LockActivity> historyList;

    private ListView listViewHistory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_host_history, container, false);
        listViewHistory = (ListView) view.findViewById(R.id.listViewHistory);

        historyInterface = new EntityAdapter();
        historyList = getHistoryLockActivity();

        HistoryListAdapter adapter = new HistoryListAdapter(historyList);
        listViewHistory.setAdapter(adapter);

        return view;
    }

    private List<LockActivity> getHistoryLockActivity() {
        return historyInterface.getHistoryLockActivity();
    }

    private class HistoryListAdapter extends ArrayAdapter<LockActivity> {
        public HistoryListAdapter(List<LockActivity> historyList) {
            super(getActivity(), R.layout.item_host_history, historyList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // if we weren't given a view, inflate one
            if (null == convertView) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.item_host_history, null);
            }

            TextView textViewLock = (TextView) convertView.findViewById(R.id.textViewLock);
            TextView textViewHost = (TextView) convertView.findViewById(R.id.textViewHost);
            TextView textViewGuest
                    = (TextView) convertView.findViewById(R.id.textViewtSpaceLockName);
            TextView textViewTime = (TextView) convertView.findViewById(R.id.textViewTime);

            LockActivity lockActivity = getItem(position);
            textViewLock.setText(Integer.toString(lockActivity.getLockId()));
            textViewHost.setText(Integer.toString(lockActivity.getHostId()));
            textViewGuest.setText(Integer.toString(lockActivity.getGuestId()));
            textViewTime.setText(lockActivity.getAccessStartTime()
                    + " -> " + lockActivity.getAccessEndTime());

            return convertView;
        }
    }
}
