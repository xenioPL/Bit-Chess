package pl.vlo.bit_chess.bit_chess;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Yoshimoo12 on 2017-05-18.
 */

class BluetoothDeviceListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<BluetoothDevice> device;

    BluetoothDeviceListAdapter(Context context, ArrayList<BluetoothDevice> device){
        this.context = context;
        this.inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.device = device;
    }

    @Override
    public int getCount() {
        return device.size();
    }

    @Override
    public Object getItem(int i) {
        return device.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parentGroup) {
        if(convertView!=null)return convertView;
        else {
            View view = inflater.inflate(R.layout.device_element, null);
            Typeface GearsOfPeaceFont = Typeface.createFromAsset(context.getAssets(), "GearsOfPeace.ttf");
            TextView name = (TextView) view.findViewById(R.id.device_name);
            name.setTypeface(GearsOfPeaceFont);
            String deviceName = device.get(position).getName().toUpperCase();
            name.setText(deviceName);
            return view;
        }

    }
}
