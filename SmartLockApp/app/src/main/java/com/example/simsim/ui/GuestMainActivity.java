package com.example.simsim.ui;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class GuestMainActivity extends FragmentActivity implements GuestFragmentCallBackInterface {

    //fragments
    private Fragment emptyLockListFM;
    private Fragment lockListFM;
    private Fragment eventsListFM;
    private Fragment historyListFM;
    private Fragment step1RequestAccessFM;
    private Fragment step2RequestAccessFM;
    private Fragment editProfileFM;

    //image view in navigation bar
    private ImageView lock_image;
    private ImageView events_image;
    private ImageView history_image;
    private ImageView personal_image;

    //fragment manager;
    private FragmentManager fmanager;
    //title bar
    private TextView titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //permit to use the network in main UI thread.
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_guest_main);

        fmanager=getSupportFragmentManager();
        initView();
    }

    private void initView() {
        lock_image=(ImageView)findViewById(R.id.lock_image);
        events_image=(ImageView)findViewById(R.id.events_image);
        history_image=(ImageView)findViewById(R.id.history_image);
        personal_image=(ImageView)findViewById(R.id.personal_image);
        titleBar=(TextView)findViewById(R.id.titleBar);

        //initial state of fragment
        setChoiceItem(1);


        lock_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lock_image.setImageResource(R.drawable.lock_clicked);
                setChoiceItem(1);

            }
        });
        events_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                events_image.setImageResource(R.drawable.events_clicked);
                setChoiceItem(2);
            }
        });
        history_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChoiceItem(3);
            }
        });
        personal_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChoiceItem(6);
            }
        });
    }

    public void setChoiceItem(int choice) {
        FragmentTransaction transaction=fmanager.beginTransaction();
        //clear choice
        clearChoice();
        //hide the fragments
        hideFragments(transaction);

        switch (choice){
            case 0:
                lock_image.setImageResource(R.drawable.lock_clicked);
                titleBar.setText("Locks");
                if(emptyLockListFM == null){
                    emptyLockListFM=new GuestMainEmptyLockFragment();
                    transaction.add(R.id.content,emptyLockListFM);
                }else{
                    transaction.show(emptyLockListFM);
                }
                titleBar.setVisibility(View.VISIBLE);
                break;
            case 1:
                lock_image.setImageResource(R.drawable.lock_clicked);
                titleBar.setText("Locks");
                /*if(lockListFM==null){
                    lockListFM=new GuestMainLockFragment();
                    transaction.add(R.id.content,lockListFM);
                }else{
                    transaction.show(lockListFM);
                }*/
                lockListFM=new GuestMainLockFragment();
                transaction.replace(R.id.content,lockListFM);
                titleBar.setVisibility(View.VISIBLE);
                break;
            case 2:
                events_image.setImageResource(R.drawable.events_clicked);
                titleBar.setText("Future Events");
                /*if(eventsListFM==null){
                    eventsListFM=new GuestMainEventsFragment();
                    transaction.add(R.id.content, eventsListFM);
                }else{
                    transaction.show(eventsListFM);
                }*/
                eventsListFM=new GuestMainEventsFragment();
                transaction.replace(R.id.content,eventsListFM);
                titleBar.setVisibility(View.VISIBLE);
                break;
            case 3:
                history_image.setImageResource(R.drawable.history_clicked);
                titleBar.setText("History");
                /*if(historyListFM==null){
                    historyListFM=new HostHistoryFragment();
                    transaction.add(R.id.content, historyListFM);
                }else{
                    transaction.show(historyListFM);
                }*/
                historyListFM=new HostHistoryFragment();
                transaction.replace(R.id.content,historyListFM);
                titleBar.setVisibility(View.GONE);
                break;
            case 4:
                lock_image.setImageResource(R.drawable.lock_clicked);
                titleBar.setText("Request Access");
                /*if(step1RequestAccessFM == null){
                    step1RequestAccessFM=new GuestMainStep1RAFragment();
                    transaction.add(R.id.content, step1RequestAccessFM);
                }else{
                    transaction.show(step1RequestAccessFM);
                }*/
                step1RequestAccessFM=new GuestMainStep1RAFragment();
                transaction.replace(R.id.content,step1RequestAccessFM);
                titleBar.setVisibility(View.VISIBLE);
                break;
            case 5:
                lock_image.setImageResource(R.drawable.lock_clicked);
                titleBar.setText("Request Access");
                /*if(step2RequestAccessFM==null){
                    step2RequestAccessFM=new GuestMainStep2RAFragment();
                    transaction.add(R.id.content, step2RequestAccessFM);
                }else{
                    transaction.show(step2RequestAccessFM);
                }*/
                step2RequestAccessFM=new GuestMainStep2RAFragment();
                transaction.replace(R.id.content, step2RequestAccessFM);
                titleBar.setVisibility(View.VISIBLE);
                break;
            case 6:
                titleBar.setText("Edit Profile");
                /*if(editProfileFM==null){
                    editProfileFM=new HostEditProfileFragment();
                    transaction.add(R.id.content,editProfileFM);
                }else{
                    transaction.show(editProfileFM);
                }*/
                editProfileFM=new HostEditProfileFragment();
                transaction.replace(R.id.content,editProfileFM);
                titleBar.setVisibility(View.GONE);
                break;

        }
        transaction.commit();
    }

    //change the images in navigation bar to normal ones
    private void clearChoice() {
        lock_image.setImageResource(R.drawable.lock_normal);
        events_image.setImageResource(R.drawable.events_normal);
        history_image.setImageResource(R.drawable.history_normal);
    }

    //hide all the fragments
    private void hideFragments(FragmentTransaction transaction){
        if(lockListFM != null){
            transaction.hide(lockListFM);
        }
        if(emptyLockListFM != null){
            transaction.hide(emptyLockListFM);
        }
        if(eventsListFM != null){
            transaction.hide(eventsListFM);
        }
        if(historyListFM != null){
            transaction.hide(historyListFM);
        }
        if(step1RequestAccessFM != null){
            transaction.hide(step1RequestAccessFM);
        }
        if(step2RequestAccessFM != null){
            transaction.hide(step2RequestAccessFM);
        }
        if(editProfileFM != null){
            transaction.hide(editProfileFM);
        }
    }

    public GuestMainActivity getGuestMainActivity() {
        return this;
    }

    @Override
    public void sendSpaceID(int spaceID) {
        //start a create profile activity
    }
}
