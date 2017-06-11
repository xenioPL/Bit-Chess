package pl.vlo.bit_chess.bit_chess;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class Clocks extends AppCompatActivity {

    private Chronometer2 ch1, ch2;
    private ImageButton back, settings;
    private Button pause;
    private Typeface font;
    protected static final int SUCCESS_CONNECT = 0;
    protected static final int MESSAGE_READ = 1;

    //private String address = null;
    private BluetoothAdapter myBluetooth = null;
    private BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    private static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    Handler mHandler;
    ConnectedThread connectedThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clocks);

        initViews();
        executeMethods();

        SharedPreferences sp = getApplication().getSharedPreferences("bit-chess", Activity.MODE_PRIVATE);
        final String address = sp.getString("address", "");

        BluetoothAsync ba = new BluetoothAsync();
        ba.setAddress(address);
        ba.execute();

        //start reading
        //connectedThread.start();

        //write
        //byte[] b = new byte[64];
        //connectedThread.write();


        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ch1.pause();
                ch2.pause();
            }
        });

        /*
        mHandler = new Handler(){
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                switch(msg.what){
                    case MESSAGE_READ:
                        byte[] readBuf = (byte[])msg.obj;
                        Log.w("tutaj dane read", readBuf.toString());
                        //int tempInt = byteToInt(readBuf[0]);
                        //int speedInt = byteToInt(readBuf[1]);
                        //int cadenceInt = byteToInt(readBuf[2]);
                        //int distanceInt = byteToInt(readBuf[3]);
                        // What to do with app specific data handling??
                }
            }
        };
        */

        //connectedThread.run();
        //byte[] b = new byte[64];
        //connectedThread.write(b);
    }

    public static int byteToInt(byte b){
        int value;
        value = b & 0xFF;
        return  value;
    }


    private void initViews(){
        font = Typeface.createFromAsset(getApplication().getAssets(),"GearsOfPeace.ttf");
        ch1 = (Chronometer2) findViewById(R.id.chronometer1);
        ch2 = (Chronometer2) findViewById(R.id.chronometer2);
        pause = (Button) findViewById(R.id.clocks_pause);
        back = (ImageButton) findViewById(R.id.clocks_back_arrow);
        settings = (ImageButton) findViewById(R.id.clocks_settings);
    }

    private void executeMethods(){
        pause.setTypeface(font);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        ch1.setSeconds(0);
        ch1.setMinutes(10);
        ch1.setTypeface(font);

        //ch1.setBase(SystemClock.elapsedRealtime());
        ch1.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                ch1.subtractSecond();
                ch1.setText(ch1.getTimeText());
            }
        });
        ch1.setText(ch1.getTimeText());

        //ch1.start();

        ch2.setSeconds(0);
        ch2.setMinutes(10);
        ch2.setTypeface(font);

        //ch2.setBase(SystemClock.elapsedRealtime());
        ch2.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                ch2.subtractSecond();
                ch2.setText(ch2.getTimeText());
            }
        });
        ch2.setText(ch2.getTimeText());
        //ch2.start();
    }


    private class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer; // buffer store for the stream
            int bytes; // bytes returned from read()
            // Keep listening to the InputStream until an exception occurs

            while (true) {
                try {
                    // Read from the InputStream
                    buffer = new byte[64];
                    bytes = mmInStream.read(buffer, 0, 1);
                    Log.w("readread", bytes+"____:" +byteArrayToString(buffer).charAt(1));
                    doIT(buffer, bytes);

                    /*Message readMsg = mHandler.obtainMessage(
                            MESSAGE_READ, bytes, -1,
                            buffer);
                    readMsg.sendToTarget();
                    */
                }
                catch (IOException e) {
                    break;
                }
            }
        }

        public void doIT(byte[] s, int bytes){
            final int bytee = bytes;
            final byte[] bufferro = s;

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(bytee != 0) {
                        String temp = byteArrayToString(bufferro);
                        if (!ch1.isRunning() && temp.charAt(1) == '2') {
                            ch1.start();
                            ch2.stop();
                        } else if (!ch2.isRunning() && temp.charAt(1) == '1') {
                            ch1.stop();
                            ch2.start();
                        }
                    }
                }
            });
        }
        /* Call this from the main activity to send data to the remote device */
        public void write(byte[] bytes) {
            try {
                mmOutStream.write(bytes);
            } catch (IOException e) { }
        }
    }

    String byteArrayToString(byte[] in) {
        char out[] = new char[in.length * 2];
        for (int i = 0; i < in.length; i++) {
            out[i * 2] = "0123456789ABCDEF".charAt((in[i] >> 4) & 15);
            out[i * 2 + 1] = "0123456789ABCDEF".charAt(in[i] & 15);
        }
        return new String(out);
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

            if (ConnectSuccess) {
                isBtConnected = true;
                connectedThread = new ConnectedThread(btSocket);
                connectedThread.start();
            }
        }
    }
}
