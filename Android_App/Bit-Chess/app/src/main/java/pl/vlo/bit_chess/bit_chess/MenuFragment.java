package pl.vlo.bit_chess.bit_chess;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Yoshimoo12 on 2017-04-15.
 */

public class MenuFragment extends Fragment {

    View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.menu_layout, container, false);

        MenuAdapter menuAdapter = new MenuAdapter(view.getContext());
        GridView lv = (GridView) view.findViewById(R.id.menu_grid);
        lv.setAdapter(menuAdapter);

        menuAdapter.addElement(R.drawable.ic_figura, "SZACHY",
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        menuAdapter.addElement(R.drawable.ic_pad, "GRY",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getFragmentManager().beginTransaction().replace(R.id.content_main, new GameClock()).addToBackStack("clock").commit();
                    }
                });
        menuAdapter.addElement(R.drawable.ic_pad, "KOLORYSTYKA",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FragmentManager fm = getFragmentManager();
                        fm.beginTransaction().replace(R.id.content_main, new ColorsFragment()).addToBackStack("Menu").commit();
                    }
                });

        menuAdapter.addElement(R.drawable.ic_ustawienia, "USTAWIENIA",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), SettingsActivity.class);
                        startActivity(intent);
                    }
                });

        menuAdapter.notifyDataSetChanged();

        //loadData();
        //setVariables();
        //executeFunctions();

        return view;
    }

}
