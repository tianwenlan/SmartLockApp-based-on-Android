package com.example.simsim.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.simsim.entities.LockActivity;
import com.example.simsim.interfaces.HistoryInterface;

import java.util.List;

/**
 * Created by Steven on 15/7/18.
 */
public class GuestMainHistoryFragment extends Fragment {

    private HistoryInterface historyInterface;
    private ListView historyList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_guest_main_history,container,false);

        return view;
    }

    public List<LockActivity> getHistoryLockActivity(){


        return null;
    }

}
