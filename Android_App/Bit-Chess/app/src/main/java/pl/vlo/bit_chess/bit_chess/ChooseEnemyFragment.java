package pl.vlo.bit_chess.bit_chess;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Yoshimoo12 on 2017-05-29.
 */

public class ChooseEnemyFragment extends Fragment {

    private View view;
    private Button pvp, pve;
    private Typeface font;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.choose_enemy, container, false);

        initViews();
        executeMethods();

        return view;
    }

    private void initViews(){
        pvp = (Button) view.findViewById(R.id.pvp);
        pve = (Button) view.findViewById(R.id.pve);
        font = Typeface.createFromAsset(getActivity().getAssets(),"GearsOfPeace.ttf");
    }

    private void executeMethods(){
        pvp.setTypeface(font);
        pvp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), Clocks.class);
                startActivity(i);
            }
        });

        pve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), Clocks.class);
                startActivity(i);
            }
        });

        pve.setTypeface(font);
    }

    private void showToken(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_token, null);
        dialogBuilder.setView(dialogView);

        TextView t = (TextView) dialogView.findViewById(R.id.token);
        t.setText("4382");
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }
}
