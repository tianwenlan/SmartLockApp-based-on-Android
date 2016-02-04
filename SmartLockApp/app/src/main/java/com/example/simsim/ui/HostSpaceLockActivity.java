package com.example.simsim.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.simsim.entities.EntityAdapter;
import com.example.simsim.entities.Lock;
import com.example.simsim.entities.Property;
import com.example.simsim.interfaces.HostSpaceInterface;

import java.util.List;

public class HostSpaceLockActivity extends Activity {

    private HostSpaceInterface hostSpaceInterface;
    private Property property;
    private List<Lock> lockList;
    private LockListAdapter adapter;

    private ListView listViewSpaceLock;
    private Button buttonAddLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_space_lock);

        Intent intent = getIntent();
        property = (Property) intent.getSerializableExtra("property");
        hostSpaceInterface = new EntityAdapter();
        lockList = getLockList(property);

        listViewSpaceLock = (ListView) findViewById(R.id.listViewSpaceLock);
        buttonAddLock = (Button) findViewById(R.id.buttonAddLock);

        adapter = new LockListAdapter(lockList);
        listViewSpaceLock.setAdapter(adapter);
        listViewSpaceLock.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                Lock lock = (Lock) listView.getItemAtPosition(position);
                Intent intent = new Intent();
                intent.setClass(HostSpaceLockActivity.this, HostLockSettingsActivity.class);
                intent.putExtra("operation", "update");
                intent.putExtra("property", property);
                intent.putExtra("lock", lock);
                startActivity(intent);
            }
        });

        buttonAddLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(HostSpaceLockActivity.this, HostLockSettingsActivity.class);
                intent.putExtra("operation", "insert");
                intent.putExtra("property", property);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    private List<Lock> getLockList(Property property){
        return hostSpaceInterface.getLockList(property);
    }

    private class LockListAdapter extends ArrayAdapter<Lock> {
        public LockListAdapter(List<Lock> lockList) {
            super(HostSpaceLockActivity.this, R.layout.item_host_lock, lockList);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = getLayoutInflater()
                        .inflate(R.layout.item_host_lock, null);
            }

            Lock lock = getItem(position);
            TextView textViewtSpaceLockName
                    = (TextView) convertView.findViewById(R.id.textViewtSpaceLockName);
            textViewtSpaceLockName.setText(lock.getDescription());

            return convertView;
        }
    }
}
