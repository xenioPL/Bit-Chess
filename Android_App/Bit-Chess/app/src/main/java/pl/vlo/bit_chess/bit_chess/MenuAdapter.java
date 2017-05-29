package pl.vlo.bit_chess.bit_chess;

import android.content.Context;
import android.content.res.Resources;
import android.media.Image;
import android.support.v4.app.FragmentManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Yoshimoo12 on 2017-04-15.
 */

class MenuAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;

    MenuAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return Math.min(mImage.size(), Math.min(mImage.size(), mListeners.size()));
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            view = inflater.inflate(R.layout.menu_element, parent, false);
            Resources r = Resources.getSystem();
            float density = context.getResources().getDisplayMetrics().density;
            float xdp = (r.getDisplayMetrics().widthPixels*0.96F) / density;
            float px = xdp/2 * density;

            TextView textView = (TextView) view.findViewById(R.id.menu_elem_name);
            textView.setText(mString.get(position));

            ImageButton imageButton = (ImageButton) view.findViewById(R.id.menu_elem_image);
            view.setLayoutParams(new GridView.LayoutParams((int)px,(int)(px*1.4F)));
            imageButton.setOnClickListener(mListeners.get(position));
            imageButton.setImageResource(mImage.get(position));
        } else {
            view = convertView;
        }
        return view;
    }

    void addElement(Integer image, String name, View.OnClickListener onClick){
        mImage.add(image);
        mString.add(name);
        mListeners.add(onClick);
    }

    // references to our images
    private ArrayList<Integer> mImage = new ArrayList<>();

    private ArrayList<String> mString = new ArrayList<>();

    private ArrayList<android.view.View.OnClickListener> mListeners = new ArrayList<>();

}

