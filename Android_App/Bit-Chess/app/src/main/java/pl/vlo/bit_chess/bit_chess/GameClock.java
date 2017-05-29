package pl.vlo.bit_chess.bit_chess;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.TextView;


public class GameClock extends Fragment {

    View view;
    Chronometer ch1, ch2;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.game_clock, container, false);

        ch1 = (Chronometer) view.findViewById(R.id.chronometer1);
        ch2 = (Chronometer) view.findViewById(R.id.chronometer2);

        return view;
    }
}