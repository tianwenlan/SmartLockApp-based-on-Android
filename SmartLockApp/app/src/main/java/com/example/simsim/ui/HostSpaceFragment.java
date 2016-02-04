package com.example.simsim.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.simsim.entities.EntityAdapter;
import com.example.simsim.entities.Property;
import com.example.simsim.interfaces.HostSpaceInterface;

import java.util.List;


public class HostSpaceFragment extends Fragment {

    private HostSpaceInterface hostSpaceInterface;
    private List<Property> propertyList;
    private PropertyListAdapter adapter;

    private ListView listViewSpace;
    private Button buttonAddSpace;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_host_space,container,false);
        listViewSpace = (ListView) view.findViewById(R.id.listViewSpace);
        buttonAddSpace = (Button) view.findViewById(R.id.buttonAddSpace);

        hostSpaceInterface = new EntityAdapter();
        propertyList = getPropertyList();

        adapter = new PropertyListAdapter(propertyList);
        listViewSpace.setAdapter(adapter);
        listViewSpace.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                Property property = (Property) listView.getItemAtPosition(position);
                Intent intent = new Intent();
                intent.setClass(getActivity(), HostSpaceLockActivity.class);
                intent.putExtra("property", property);
                startActivity(intent);
            }
        });
        listViewSpace.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                Property property = (Property) listView.getItemAtPosition(position);
                Intent intent = new Intent();
                intent.setClass(getActivity(), HostSpaceSettingsActivity.class);
                intent.putExtra("operation", "update");
                intent.putExtra("property", property);
                startActivityForResult(intent, 1);
                return true;
            }
        });

        buttonAddSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), HostSpaceSettingsActivity.class);
                intent.putExtra("operation", "insert");
                startActivityForResult(intent, 0);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0 && resultCode == 0) {
            propertyList.add((Property)intent.getSerializableExtra("property"));
        }
        if (requestCode == 1 && resultCode == 0) {
            propertyList.remove(intent.getSerializableExtra("property"));
        }
    }

    private List<Property> getPropertyList(){
        return hostSpaceInterface.getPropertyList();
    }

    private class PropertyListAdapter extends ArrayAdapter<Property> {
        public PropertyListAdapter(List<Property> propertyList) {
            super(getActivity(), R.layout.item_host_space, propertyList);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.item_host_space, null);
            }

            Property property = getItem(position);
            TextView textViewPropertyName
                    = (TextView) convertView.findViewById(R.id.textViewtSpaceLockName);
            TextView textViewPropertyLockNumber
                    = (TextView) convertView.findViewById(R.id.textViewPropertyLockNumber);

            textViewPropertyName.setText(property.getDescription());
            textViewPropertyLockNumber.setText(
                    Integer.toString(hostSpaceInterface.getLockNumberOfProperty(property))
                    + " Lock(s)");

            return convertView;
        }
    }
}
