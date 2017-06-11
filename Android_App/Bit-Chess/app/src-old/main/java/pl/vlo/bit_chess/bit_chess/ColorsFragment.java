package pl.vlo.bit_chess.bit_chess;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Yoshimoo12 on 2017-04-15.
 */

public class ColorsFragment extends Fragment {

    View view;
    ColorsAdapter colorsAdapter;
    final static int COLOR_ACTIVITY_CODE = 1;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.colors_layout, container, false);

        colorsAdapter = new ColorsAdapter(view.getContext());
        ListView lv = (ListView) view.findViewById(R.id.colors_list);

        colorsAdapter.addElement(Color.rgb(0, 0, 0), "Kolor pierwszego gracza");
        colorsAdapter.addElement(Color.rgb(255, 255, 255), "Kolor drugiego gracza");
        colorsAdapter.addElement(Color.rgb(0, 200, 0), "Kolor możliwych ruchów");
        colorsAdapter.addElement(Color.rgb(200, 0, 0), "Kolor zagrożenia");

        lv.setAdapter(colorsAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startChooseColorActivity(colorsAdapter.getColor(i), i);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (COLOR_ACTIVITY_CODE) : {
                if (resultCode == Activity.RESULT_OK) {
                    int color = data.getIntExtra("color", 0);
                    int position = data.getIntExtra("position", 0);
                    colorsAdapter.changeColor(position, color);
                    colorsAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Zapisano", Toast.LENGTH_SHORT).show();
                }
                else if(resultCode == Activity.RESULT_CANCELED){
                    Toast.makeText(getContext(), "Anulowano", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }


    private void startChooseColorActivity(int color, int position){
        Intent colorChooseIntent = new Intent(getContext(), ChooseColorActivity.class);
        colorChooseIntent.putExtra("color", color);
        colorChooseIntent.putExtra("position", position);
        startActivityForResult(colorChooseIntent, COLOR_ACTIVITY_CODE);
    }

}
