package pl.vlo.bit_chess.bit_chess;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Yoshimoo12 on 2017-04-15.
 */

class ColorsAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;

    ColorsAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return Math.min(mColors.size(), mString.size());
    }

    public Object getItem(int position) {
        return position;
    }

    public int getColor(int position){
        return mColors.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.colors_element, null);

            TextView textView = (TextView) view.findViewById(R.id.colors_name);
            ImageView imageView = (ImageView) view.findViewById(R.id.colors_color);

            imageView.setBackgroundColor(mColors.get(position));
            textView.setText(mString.get(position));

        return view;
    }

    public void addElement(int color, String name){
        mColors.add(color);
        mString.add(name);
    }

    public void changeColor(int position, int color){
        mColors.set(position, color);
    }

    private ArrayList<Integer> mColors = new ArrayList<>();
    private ArrayList<String> mString =  new ArrayList<>();

}

