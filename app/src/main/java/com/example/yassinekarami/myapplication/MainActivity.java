package com.example.yassinekarami.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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



        //Make the phone's visibility
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(discoverableIntent);


        if (myBluetoothAdapter == null)
        {
            Toast.makeText(getApplicationContext(), "Bluetooth non activé !", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if (!myBluetoothAdapter.isEnabled())
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

        if (myBluetoothAdapter != null) {

            // Display all the bonded Device
            devices = myBluetoothAdapter.getBondedDevices();
            for (BluetoothDevice blueDevice : devices) {
                Toast.makeText(getApplicationContext(), "Device = " + blueDevice.getName(), Toast.LENGTH_SHORT).show();
            }

            if (!devices.isEmpty()){
                // conection is DONE
                myBluetoothDevice = myBluetoothAdapter.getRemoteDevice(macAdress);
                if (myBluetoothDevice != null) {
                    Toast.makeText(getApplicationContext(), "connected to " +
                            "  " + myBluetoothDevice.getAddress() + " " + myBluetoothDevice.getAddress(), Toast.LENGTH_LONG).show();

                 // socket creation
                    ConnectedThread ct = new ConnectedThread(myBluetoothDevice);
                    ct.run();
                }
            }
        }
    }

    // the thread to create a FUCKING SOCKET
    private class ConnectedThread extends  Thread {

        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;


        public ConnectedThread(BluetoothDevice device) {
            // Use a temporary object that is later assigned to mmSocket
            // because mmSocket is final.
            BluetoothSocket tmp = null;
            mmDevice = device;

            try {
                // Get a BluetoothSocket to connect with the given BluetoothDevice.
                // MY_UUID is the app's UUID string, also used in the server code.
                tmp = device.createRfcommSocketToServiceRecord(UUID.fromString(myUUID));
            } catch (IOException e) { }
            mmSocket = tmp;
        }

        public void run() {
            // Cancel discovery because it otherwise slows down the connection.
            myBluetoothAdapter.cancelDiscovery();

            try {
                // Connect to the remote device through the socket. This call blocks
                // until it succeeds or throws an exception.
                mmSocket.connect();

                Toast.makeText(getApplicationContext(), "ergerg" +mmSocket.getRemoteDevice().getName(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Socket connected", Toast.LENGTH_LONG).show();
            } catch (IOException connectException) {
                // Unable to connect; close the socket and return.
               

                Toast.makeText(getApplicationContext(), "Socket not connected", Toast.LENGTH_LONG).show();
                try {
                    mmSocket.close();
                } catch (IOException closeException) {

                }
                return;
            }
            // The connection attempt succeeded. Perform work associated with
            // the connection in a separate thread.

        }

        // Closes the client socket and causes the thread to finish.
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {

            }
        }
    }

    // The output stream will be returned even if the socket is not yet connected,
    // but operations on that stream will throw IOException until the associated socket is connected.
    public void sendMessageClick(View view) throws IOException {
        if (myBluetoothSocket.isConnected()){


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
