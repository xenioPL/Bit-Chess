package pl.vlo.bit_chess.bit_chess;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.Date;


public class GameClock extends Fragment {

    View view;
    Chronometer2 ch1, ch2;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.game_clock, container, false);

        ch1 = (Chronometer2) view.findViewById(R.id.chronometer1);
        ch2 = (Chronometer2) view.findViewById(R.id.chronometer2);

       // ch1.setFormat("00:00");
        ch1.setBase(SystemClock.elapsedRealtime());
        ch1.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                chronometer
            }
        });

        //ch2.setFormat("00:00");
        ch2.setBase(SystemClock.elapsedRealtime());

        ch1.start();

        return view;
    }
}