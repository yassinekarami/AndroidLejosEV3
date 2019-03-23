package com.example.yassinekarami.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_CODE_ENABLE_BLUETOOTH = 0;
    BluetoothAdapter myBluetoothAdapter = null; // the entry-point for all Bluetooth interaction
    BluetoothDevice myBluetoothDevice = null;   // lets you create a connection with the respective device
    BluetoothSocket myBluetoothSocket = null;  // socket to send / receive messages

    private Set<BluetoothDevice> devices;
    String macAdress = "00:16:53:60:2F:C4";
    String message ="Hello World";
    Button sendBtn;
    EditText macAdressText;
    TextView text;

    boolean connect = false;

    private final String myUUID = "00000000-0000-1000-8000-00805F9B34FB" ;
    private OutputStream outputStream;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendBtn = (Button)findViewById(R.id.btn);
        macAdressText = (EditText)findViewById(R.id.macTextField);
        text = (TextView)findViewById(R.id.textView);
        myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        //Make the phone's visibility
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(discoverableIntent);


        if (bluetoothAdapter == null)
        {
            Toast.makeText(getApplicationContext(), "Bluetooth non activé !", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if (!bluetoothAdapter.isEnabled())
            {
                Toast.makeText(getApplicationContext(), "Bluetooth non activé !", Toast.LENGTH_SHORT).show();
                // Possibilité 1 :
                Intent activeBlueTooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(activeBlueTooth, REQUEST_CODE_ENABLE_BLUETOOTH);
                // ou Possibilité 2:
                //bluetoothAdapter.enable();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Bluetooth activé", Toast.LENGTH_SHORT).show();
            }
        }


        if (bluetoothAdapter != null) {
            devices = bluetoothAdapter.getBondedDevices();
            // Display all the bonded Device
            for (BluetoothDevice blueDevice : devices) {
                Toast.makeText(getApplicationContext(), "Device = " + blueDevice.getName(), Toast.LENGTH_SHORT).show();
            }

            // conection is DONE
            myBluetoothDevice = myBluetoothAdapter.getRemoteDevice(macAdress);
            if (myBluetoothDevice != null) {
                Toast.makeText(getApplicationContext(), "connected to " +
                        "  " + myBluetoothDevice.getAddress() + " " + myBluetoothDevice.getAddress(), Toast.LENGTH_LONG).show();
            }

            // socket creation
            try {
                BluetoothSocket myBluetoothSocket = myBluetoothDevice.createRfcommSocketToServiceRecord(UUID.fromString(myUUID));
                myBluetoothSocket.connect();


            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }


    public void write(String s) throws IOException {
        outputStream.write(s.getBytes());
    }


    // The output stream will be returned even if the socket is not yet connected,
    // but operations on that stream will throw IOException until the associated socket is connected.
    public void sendMessageClick(View view) throws IOException {
        if (myBluetoothSocket.isConnected()){

            outputStream = myBluetoothSocket.getOutputStream();
            outputStream.write(macAdressText.getText().toString().getBytes());
            outputStream.flush();
            Toast.makeText(getApplicationContext(),"socket sent", Toast.LENGTH_LONG).show();
        }

    }















    // Possibilité 1 :
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != REQUEST_CODE_ENABLE_BLUETOOTH)
            return;
        if (resultCode == RESULT_OK)
        {
            Toast.makeText(getApplicationContext(), "Bluetooth activé", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Bluetooth non activé !", Toast.LENGTH_SHORT).show();
        }
    }

    private final BroadcastReceiver bluetoothReceiver = new BroadcastReceiver()
    {
        public void onReceive(Context context, Intent intent)
        {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action))
            {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Toast.makeText(getApplicationContext(), "Nouveau périphérique : " + device.getName(), Toast.LENGTH_SHORT).show();
            }
        }
    };


}
