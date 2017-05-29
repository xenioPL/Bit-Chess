package pl.vlo.bit_chess.bit_chess;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by Yoshimoo12 on 2017-05-16.
 */

public class BluetoothStart extends Fragment {

    View view;

    public static final int REQUEST_ENABLE_BT = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bluetooth_start, container, false);

        Typeface GearsOfPeaceFont = Typeface.createFromAsset(getActivity().getAssets(),"GearsOfPeace.ttf");

        Button button = (Button) view.findViewById(R.id.bluetooth_start);
        button.setTypeface(GearsOfPeaceFont);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turnOnBluetooth();
                if(BluetoothAdapter.getDefaultAdapter().isEnabled()){
                    getFragmentManager().beginTransaction().replace(R.id.placeholder_bluetooth, new BluetoothDeviceList()).commit();
                }
            }
        });

        return view;
    }

    public void turnOnBluetooth(){
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
            // shieeeeeeeet
            Toast.makeText(getContext(), "Your device does not support bluetooth", Toast.LENGTH_LONG).show();
        }

        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }
}
