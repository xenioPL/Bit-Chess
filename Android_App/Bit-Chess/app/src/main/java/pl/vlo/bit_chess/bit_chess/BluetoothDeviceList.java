package pl.vlo.bit_chess.bit_chess;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Yoshimoo12 on 2017-05-16.
 */

public class BluetoothDeviceList extends Fragment {

    View view;
   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bluetooth_device_list, container, false);

        ArrayList<BluetoothDevice> devices = new ArrayList<>();

        devices.addAll(getPairedDevices());

        Typeface GearsOfPeaceFont = Typeface.createFromAsset(getActivity().getAssets(),"GearsOfPeace.ttf");

        final BluetoothDeviceListAdapter adapter = new BluetoothDeviceListAdapter(getContext(), devices);
        ListView list = (ListView) view.findViewById(R.id.bluetooth_device_list);
        TextView empty = (TextView) view.findViewById(R.id.empty);
        empty.setTypeface(GearsOfPeaceFont);
        list.setEmptyView(empty);
        list.setAdapter(adapter);
       list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Get the device MAC address, the last 17 chars in the View
               BluetoothDevice bd = (BluetoothDevice) adapter.getItem(position);
               String address = bd.getAddress();
               // Make an intent to start next activity.
               Intent i = new Intent(getContext(), Game.class);
               //Change the activity.
               i.putExtra("ADDRESS", address); //this will be received at ledControl (class) Activity
               startActivity(i);
           }
       });

        return view;
    }

    private ArrayList<BluetoothDevice> getPairedDevices(){
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(!mBluetoothAdapter.isEnabled()) return null;
        ArrayList<BluetoothDevice> pairedDevices = new ArrayList<>();
        pairedDevices.addAll(mBluetoothAdapter.getBondedDevices());

        /*
        if (pairedDevices.size() > 0) {
            // There are paired devices. Get the name and address of each paired device.
            for (BluetoothDevice device : pairedDevices) {
                //names.add(device.getName());
                //MACs.add(device.getAddress()); // MAC address
            }
        }*/
        return pairedDevices;
    }

    private boolean searchForDevices(){
        return false;
    }
}
