package com.example.simsim.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simsim.database.DatabaseConstantInterface;
import com.example.simsim.entities.EntityAdapter;
import com.example.simsim.entities.LockActivity;
import com.example.simsim.interfaces.HostEventInterface;

import java.util.List;


public class HostEventFragment extends Fragment
        implements DatabaseConstantInterface, UIConstantInterface{

    private HostEventInterface hostEventInterface;
    private List<LockActivity> lockActivityList;

    private ListView listViewEvent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_host_event, container, false);
        listViewEvent = (ListView) view.findViewById(R.id.listViewEvent);

        hostEventInterface = new EntityAdapter();
        lockActivityList = getFutureLockActivity();

        EventListAdapter adapter = new EventListAdapter(lockActivityList);
        listViewEvent.setAdapter(adapter);

        return view;
    }

    private List<LockActivity> getFutureLockActivity() {
        return hostEventInterface.getFutureLockActivity();
    }

    private class EventListAdapter extends ArrayAdapter<LockActivity> {
        public EventListAdapter(List<LockActivity> lockActivityList) {
            super(getActivity(), R.layout.item_host_event, lockActivityList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.item_host_event, null);
            }

            LockActivity lockActivity = getItem(position);
            TextView textViewGuest
                    = (TextView) convertView.findViewById(R.id.textViewtSpaceLockName);
            TextView textViewTime = (TextView) convertView.findViewById(R.id.textViewTime);
            ImageView imageViewAccept = (ImageView) convertView.findViewById(R.id.imageViewAccept);
            ImageView imageViewReject = (ImageView) convertView.findViewById(R.id.imageViewReject);
            textViewGuest.setText(Integer.toString(lockActivity.getGuestId()));
            textViewTime.setText(lockActivity.getAccessStartTime() + " -> "
                    + lockActivity.getAccessEndTime());
            if(lockActivity.getRequestStatus().equals(LOCK_ACTIVITY_REQUEST_STATUS_ACCEPT)){
                imageViewAccept.setEnabled(false);
                imageViewAccept.setVisibility(View.INVISIBLE);
                imageViewReject.setEnabled(false);
                imageViewReject.setVisibility(View.INVISIBLE);
            }

            ItemClickListener itemClickListener = new ItemClickListener(lockActivity, convertView);
            imageViewAccept.setOnClickListener(itemClickListener);
            imageViewReject.setOnClickListener(itemClickListener);

            return convertView;
        }

        private class ItemClickListener implements View.OnClickListener {

            private LockActivity lockActivity;
            private View convertView;

            public ItemClickListener(LockActivity lockActivity, View convertView){
                this.lockActivity = lockActivity;
                this.convertView = convertView;
            }

            @Override
            public void onClick(View v) {
                ImageView imageView = (ImageView) v;
                switch (imageView.getId()){
                    case R.id.imageViewAccept:
                        lockActivity.setRequestStatus(LOCK_ACTIVITY_REQUEST_STATUS_ACCEPT);
                        break;
                    case R.id.imageViewReject:
                        lockActivity.setRequestStatus(LOCK_ACTIVITY_REQUEST_STATUS_REJECT);
                        break;
                    default:
                        break;
                }
                try{
                    hostEventInterface.updateLockActivity(lockActivity);
                    ImageView imageViewAccept
                            = (ImageView) convertView.findViewById(R.id.imageViewAccept);
                    ImageView imageViewReject
                            = (ImageView) convertView.findViewById(R.id.imageViewReject);
                    imageViewAccept.setEnabled(false);
                    imageViewAccept.setVisibility(View.INVISIBLE);
                    imageViewReject.setEnabled(false);
                    imageViewReject.setVisibility(View.INVISIBLE);
                    if(imageView.getId() == R.id.imageViewReject){
                        lockActivityList.remove(lockActivity);
                        notifyDataSetChanged();
                    }
                    Toast.makeText(getActivity(), MESSAGE_UPDATE_SUCCESS,
                            Toast.LENGTH_LONG).show();
                } catch (Exception e){
                    lockActivity.setRequestStatus(LOCK_ACTIVITY_REQUEST_STATUS_PENDING);
                    Toast.makeText(getActivity(), MESSAGE_UPDATE_EXCEPTION,
                            Toast.LENGTH_LONG).show();
                }
            }

        }
    }
}
