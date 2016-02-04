package com.example.simsim.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Steven on 15/7/18.
 */
public class GuestRepeatActivity extends Activity {

    private ListView repeatLV;
    private ImageView customIV;
    private String items[]={"Never", "Every Day","Every Week","Every 2 Weeks","Every Month"
                                ,"Every Year"};
    private ArrayList<HashMap<String, String>> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_repeat);

        repeatLV=(ListView)findViewById(R.id.repeatLV);
        customIV=(ImageView)findViewById(R.id.customIV);

        arrayList=new ArrayList<HashMap<String, String>>();
        for(int i=0;i<items.length;i++){
            HashMap<String, String> map=new HashMap<>();
            map.put("name",items[i]);
            arrayList.add(map);
        }

        SimpleAdapter adapter=new SimpleAdapter(this, arrayList, R.layout.item_guest_repeat_list,
                new String[]{"name"},new int[]{R.id.repeatItemTV});
        repeatLV.setAdapter(adapter);

        customIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GuestRepeatActivity.this, GuestCustomRepeatActivity.class);
                startActivity(intent);
            }
        });
    }
}
