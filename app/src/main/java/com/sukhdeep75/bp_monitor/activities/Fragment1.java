package com.sukhdeep75.bp_monitor.activities;

import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ListView;
import android.widget.ListAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sukhdeep75.bp_monitor.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.*;

public class Fragment1 extends Fragment {

    Button connect;
    ListView list;
    BluetoothAdapter myBluetoothAdapter;
    TextView bp,so2;
    String msg="";


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference Bp,So_2;
    DatabaseReference myRef;

    int REQUEST_ENABLED_BT = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_layout, container, false);
        connect = view.findViewById(R.id.connect);
        list = (ListView) view.findViewById(R.id.list);
        bp = view.findViewById(R.id.bp_text);
        so2 = view.findViewById(R.id.sp02_text);
        String sp02;

        myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

//        if (myBluetoothAdapter != null && myBluetoothAdapter.isEnabled() && myBluetoothAdapter.getProfileConnectionState(BluetoothHeadset.HEADSET) == BluetoothHeadset.STATE_CONNECTED) {
//            connect.setText("Add Logs to Database");
//
//            connect.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(bp==null || so2==null)
//                    {
//                        Toast.makeText(getContext().getApplicationContext(), "No values to add!", Toast.LENGTH_LONG).show();
//                    }
//                    else{
//                        Toast.makeText(getContext().getApplicationContext(), "Data added successfully", Toast.LENGTH_LONG).show();
//                    }
//                }
//            });
//
//        } else {
//            connect.setText("Connect your device");
//            connect.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(getContext().getApplicationContext(), "Connect device from settings", Toast.LENGTH_LONG).show();
//                }
//            });
//        }


//        connect.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bp = database.getReference("BPM");
//                Bp.setValue("123");
//                So_2 = database.getReference("Oxygen");
//                So_2.setValue("96");
//
//            }
//        });

        class SendRecieve extends Thread
        {
            String spo2="";
            String bpm="";
            private final BluetoothSocket bluetoothSocket;
            private final InputStream inputStream;
            private final OutputStream outputStream;

            public SendRecieve(BluetoothSocket socket)
            {
                bluetoothSocket=socket;
                InputStream tempin = null;
                OutputStream tempout = null;

                try {
                    tempin = bluetoothSocket.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    tempout = bluetoothSocket.getOutputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                inputStream = tempin;
                outputStream = tempout;
            }

            public void receive(View view) throws IOException {
                if(inputStream!=null){

                    for(int i=0;i<8;i++){
                        byte b=(byte) inputStream.read();
                        msg=msg+(char) b;
                    }
                }
            }

            public void setSpo2(String spo2) {
                this.spo2 = spo2;
            }
            //            public void run()
//            {
//                byte[] buffer=new byte[1024];
//                int bytes;
//
//                while(true)
//                {
//                    try {
//                        bytes=inputStream.read(buffer);
//                        Handler.obtainMessage()
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }
        }



            return view;
    }
}
