package pl.vlo.bit_chess.bit_chess;

import android.content.Context;
import android.widget.Chronometer;

/**
 * Created by Yoga on 29.05.2017.
 */

public class Chronometer2 extends Chronometer {

    private int minutes, seconds;

    public Chronometer2(Context context, int minutes, int seconds) {
        super(context);
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public void subtractSecond(){
        if(seconds!=0) seconds--;
        else{
            minutes--;
            seconds=59;
        }
    }

    public String getTimeText(){
        return minutes+":"+seconds;
    }

    public int getSeconds(){
        return seconds;
    }

    public int getMinutes(){
        return minutes;
    }

}
