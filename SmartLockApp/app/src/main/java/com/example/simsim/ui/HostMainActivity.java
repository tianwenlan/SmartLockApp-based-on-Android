package com.example.simsim.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;


public class HostMainActivity extends FragmentActivity {

    private Fragment hostSpaceFragment;
    private Fragment hostLockFragment;
    private Fragment hostEventFragment;
    private Fragment hostHistoryFragment;
    private Fragment editProfileFragment;

    private ImageView space_image;
    private ImageView lock_image;
    private ImageView events_image;
    private ImageView history_image;
    private ImageView personal_image;

    private FragmentManager fmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_main);
        fmanager = getSupportFragmentManager();
        initView();
    }

    private void initView() {
        space_image = (ImageView)findViewById(R.id.space_image);
        lock_image = (ImageView)findViewById(R.id.lock_image);
        events_image = (ImageView)findViewById(R.id.events_image);
        history_image = (ImageView)findViewById(R.id.history_image);
        personal_image = (ImageView)findViewById(R.id.personal_image);

        setChoiceItem(0);

        space_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChoiceItem(0);
            }
        });
        lock_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChoiceItem(1);
            }
        });
        events_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                setChoiceItem(4);
            }
        });
    }

    public void setChoiceItem(int choice) {
        FragmentTransaction transaction=fmanager.beginTransaction();
        clearChoice();
        hideFragments(transaction);

        switch (choice){
            case 0:
                if(hostSpaceFragment == null){
                    hostSpaceFragment = new HostSpaceFragment();
                    transaction.add(R.id.content, hostSpaceFragment);
                }else{
                    transaction.show(hostSpaceFragment);
                }
                break;
            case 1:
                if(hostLockFragment == null){
                    hostLockFragment = new HostLockFragment();
                    transaction.add(R.id.content, hostLockFragment);
                }else{
                    transaction.show(hostLockFragment);
                }
                break;
            case 2:
                if(hostEventFragment == null){
                    hostEventFragment = new HostEventFragment();
                    transaction.add(R.id.content, hostEventFragment);
                }else{
                    transaction.show(hostEventFragment);
                }
                break;
            case 3:
                if(hostHistoryFragment == null){
                    hostHistoryFragment = new HostHistoryFragment();
                    transaction.add(R.id.content, hostHistoryFragment);
                }else{
                    transaction.show(hostHistoryFragment);
                }
                break;
            case 4:
                if(editProfileFragment == null){
                    editProfileFragment = new HostEditProfileFragment();
                    transaction.add(R.id.content, editProfileFragment);
                }else{
                    transaction.show(editProfileFragment);
                }
                break;
            default:
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
        if(hostSpaceFragment != null) transaction.hide(hostSpaceFragment);
        if(hostLockFragment != null) transaction.hide(hostLockFragment);
        if(hostEventFragment != null) transaction.hide(hostEventFragment);
        if(hostHistoryFragment != null) transaction.hide(hostHistoryFragment);
        if(editProfileFragment != null) transaction.hide(editProfileFragment);
    }

}
