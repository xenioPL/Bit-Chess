package pl.vlo.bit_chess.bit_chess;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.UUID;

public class Game extends AppCompatActivity {

    //SeekBar brightness;
    String address = null;
    boolean dioda = false;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        address = getIntent().getStringExtra("ADDRESS");
        TextView t = (TextView) findViewById(R.id.address);
        t.setText(address);

        BluetoothAsync ba = new BluetoothAsync();
        ba.setAddress(address);
        ba.execute();
        Button bt = (Button) findViewById(R.id.btntest);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s;
                if (dioda) {
                    s = "0";
                    dioda = false;
                } else {
                    s = "1";
                    dioda = true;

                }
                try {
                    btSocket.getOutputStream().write(s.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private class BluetoothAsync extends AsyncTask<Void, Void, Void> {

        String address = null;
        private boolean ConnectSuccess = true;

        public void setAddress(String s){
            this.address = s;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(Void... devices) //while the progress dialog is shown, the connection is done in background
        {

            try {
                if (btSocket == null || !isBtConnected) {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                    BluetoothDevice device = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
                    btSocket = device.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();//start connection
                }
            } catch (IOException e) {
                ConnectSuccess = false;//if the try failed, you can check the exception here
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) //after the doInBackground, it checks if everything went fine
        {
            super.onPostExecute(result);

            if (!ConnectSuccess) {
                //finish();
            } else {
                isBtConnected = true;
            }
        }
        }
}

