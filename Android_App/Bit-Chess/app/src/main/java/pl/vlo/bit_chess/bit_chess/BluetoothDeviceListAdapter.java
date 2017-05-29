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
import java.util.zip.Inflater;

/**
 * Created by Yoshimoo12 on 2017-05-18.
 */

public class BluetoothDeviceListAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<BluetoothDevice> device;

    BluetoothDeviceListAdapter(Context context, ArrayList<BluetoothDevice> device){
        this.context = context;
        this.inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.device = device;
    }

    @Override
    public int getCount() {
        return Math.max(device.size(), 6);
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
        View view = inflater.inflate(R.layout.device_element, null);

        Typeface GearsOfPeaceFont = Typeface.createFromAsset(context.getAssets(),"GearsOfPeace.ttf");
        TextView name = (TextView) view.findViewById(R.id.device_name);
        name.setTypeface(GearsOfPeaceFont);
        if(device.size()<=position){
            name.setText("BIT-CHESS-"+position);
        }
        else{
            String deviceName = device.get(position).getName();
            name.setText(deviceName);
        }
        return view;
    }
}
