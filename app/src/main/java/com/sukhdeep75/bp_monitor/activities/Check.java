package com.sukhdeep75.bp_monitor.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.sukhdeep75.bp_monitor.R;

import java.util.ArrayList;
import java.util.Set;

public class Check extends AppCompatActivity {
    Button connect;
    ListView list;
    BluetoothAdapter myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    ArrayList<String> stringArrayList = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;


    int REQUEST_ENABLED_BT = 1;

    public void turn_on_bt()
    {
            if(myBluetoothAdapter == null) //check if bluetooth is enabled or not
        {
            Toast.makeText(getApplicationContext(), "Device does not support bluetooth", Toast.LENGTH_SHORT).show();
        }
            else {
        if(!myBluetoothAdapter.isEnabled()){
            Intent enableBluetoothintent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBluetoothintent,REQUEST_ENABLED_BT);
        }
    }
    }
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
            list.setVisibility(View.VISIBLE);
            list.setAdapter(arrayAdapter);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        connect = findViewById(R.id.connect_btn);
        myBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();


        connect.setOnClickListener(view1 -> {



        });

    }
}