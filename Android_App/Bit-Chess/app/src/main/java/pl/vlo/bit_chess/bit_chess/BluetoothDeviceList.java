package pl.vlo.bit_chess.bit_chess;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Yoshimoo12 on 2017-05-16.
 */

public class BluetoothDeviceList extends Fragment {

    private BluetoothDeviceListAdapter adapter;
    private ArrayList<BluetoothDevice> devices;
    private Typeface GearsOfPeaceFont;
    private ArrayList<BluetoothDevice> unpairedDevicesList;

    private ListView list;
    private TextView empty;

    private View view;

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bluetooth_device_list, container, false);

        initializeViews();
        executeMethods();

        return view;
    }

    private void initializeViews(){
        unpairedDevicesList = new ArrayList<>();
        devices = new ArrayList<>();
        GearsOfPeaceFont = Typeface.createFromAsset(getActivity().getAssets(),"GearsOfPeace.ttf");
        adapter = new BluetoothDeviceListAdapter(getContext(), devices);
        list = (ListView) view.findViewById(R.id.bluetooth_device_list);
        empty = (TextView) view.findViewById(R.id.empty);

    }

    private void executeMethods(){
        devices.addAll(getPairedDevices());
        empty.setTypeface(GearsOfPeaceFont);
        list.setEmptyView(empty);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BluetoothDevice bd = (BluetoothDevice) adapter.getItem(position);
                String address = bd.getAddress();
                saveAddress(address);
                //connected
                Toast.makeText(getContext(), "Connected", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getContext(), GameActivity.class);
                startActivity(i);
            }
        });
        //detectDevices();
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

    /*rivate void detectDevices(){
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        bluetoothAdapter.startDiscovery();

        BroadcastReceiver receiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if(BluetoothDevice.ACTION_FOUND.equals(action)){
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    unpairedDevicesList.add(device);
                }
            }
        };

    }
    */

    private void saveAddress(String address){
        SharedPreferences sp = getActivity().getSharedPreferences("bit-chess", Activity.MODE_PRIVATE);
        SharedPreferences.Editor spEdit = sp.edit();
        spEdit.putString("address", address);
        spEdit.apply();
    }
}
