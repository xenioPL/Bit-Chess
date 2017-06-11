package pl.vlo.bit_chess.bit_chess;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Yoshimoo12 on 2017-04-15.
 */

public class ColorsFragment extends Fragment {

    private View view;
    private ColorsAdapter colorsAdapter;
    private ListView lv;
    private Typeface font;
    private String COLORS_PREFS_CODE = "ColorsArrayList";

    final static int COLOR_ACTIVITY_CODE = 1;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.colors_layout, container, false);

        //((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        initViews();
        executeMethods();

        loadColors();

        return view;
    }

    private void initViews(){
        font = Typeface.createFromAsset(getActivity().getAssets(),"GearsOfPeace.ttf");
        colorsAdapter = new ColorsAdapter(view.getContext(), font);
        lv = (ListView) view.findViewById(R.id.colors_list);
    }

    private void executeMethods(){
        colorsAdapter.addElement(Color.rgb(0, 0, 0), "first player");
        colorsAdapter.addElement(Color.rgb(255, 255, 255), "second player");
        colorsAdapter.addElement(Color.rgb(0, 200, 0), "possible moves");
        colorsAdapter.addElement(Color.rgb(200, 0, 0), "dangers");

        lv.setAdapter(colorsAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startChooseColorActivity(colorsAdapter.getColor(i), i);
            }
        });
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
                    saveColors();
                    lv.setAdapter(colorsAdapter);
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

    private void saveColors(){
        Gson gson = new Gson();
        SharedPreferences sp = getActivity().getSharedPreferences("bit-chess", Activity.MODE_PRIVATE);
        SharedPreferences.Editor spEdit = sp.edit();

        String json = gson.toJson(colorsAdapter.getArrayOfColors());
        spEdit.putString(COLORS_PREFS_CODE, json);
        spEdit.apply();
    }

    private void loadColors(){
        Gson gson = new Gson();
        SharedPreferences sp = getActivity().getSharedPreferences("bit-chess", Activity.MODE_PRIVATE);
        String json = sp.getString(COLORS_PREFS_CODE, "");
        if(json.equals(""))return;
        Type type = new TypeToken<ArrayList<Integer>>() {}.getType();
        ArrayList<Integer> arrayList = gson.fromJson(json, type);
        colorsAdapter.setArrayOfColors(arrayList);
    }

}
