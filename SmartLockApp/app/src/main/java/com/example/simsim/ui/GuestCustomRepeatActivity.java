package com.example.simsim.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Steven on 15/7/18.
 */
public class GuestCustomRepeatActivity extends Activity {

    private ListView frequencyLV;
    private ListView dateLV;

    private String frequency[]={"Frequency","Every"};
    private String dates[]={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_custom);

        frequencyLV=(ListView)findViewById(R.id.frequencyLV);
        dateLV=(ListView)findViewById(R.id.dateLV);

        ArrayList<HashMap<String,String>> list1=new ArrayList<>();
        ArrayList<HashMap<String,String>> list2=new ArrayList<>();

        for(int i=0;i<frequency.length;i++){
            HashMap<String,String> map=new HashMap<>();
            map.put("fre",frequency[i]);
            list1.add(map);
        }
        for(int i=0;i<dates.length;i++){

            HashMap<String,String> map=new HashMap<>();
            map.put("dates",dates[i]);
            list2.add(map);

        }

        SimpleAdapter freAdapter=new SimpleAdapter(this,list1, R.layout.item_guest_freq_list,
                new String[]{"fre"},new int[]{R.id.freItemTV});
        SimpleAdapter dateAdapter=new SimpleAdapter(this, list2, R.layout.item_guest_dates_list,
                new String[]{"dates"},new int[]{R.id.datesItemTV});
        frequencyLV.setAdapter(freAdapter);
        dateLV.setAdapter(dateAdapter);
    }
}
