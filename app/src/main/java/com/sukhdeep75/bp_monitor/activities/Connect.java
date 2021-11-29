package com.sukhdeep75.bp_monitor.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.sukhdeep75.bp_monitor.R;

import java.util.ArrayList;
import java.util.Set;

public class Connect extends AppCompatActivity {
    Button scan;
    ListView paired_lv,scan_lv;
    BluetoothAdapter myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    ArrayList<String> stringArrayList = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;


    int REQUEST_ENABLED_BT = 1;

    public void list_paired_devices()
    {
        Set<BluetoothDevice> bt=myBluetoothAdapter.getBondedDevices();
        String[] string=new String[bt.size()];
        int index = 0;

        if(bt.size()>0)
        {
            for(BluetoothDevice device:bt)
            {
                string[index]= device.getName();
                index++;
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,string);
            paired_lv.setVisibility(View.VISIBLE);
            paired_lv.setAdapter(arrayAdapter);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        list_paired_devices();
    }
}