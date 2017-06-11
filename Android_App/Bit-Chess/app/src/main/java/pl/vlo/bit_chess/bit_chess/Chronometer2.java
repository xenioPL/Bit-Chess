package pl.vlo.bit_chess.bit_chess;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Chronometer;

/**
 * Created by Yoga on 29.05.2017.
 */

public class Chronometer2 extends Chronometer {

    private int minutes, seconds;
    private boolean paused;
    private boolean running;

    public Chronometer2(Context context) {
        super(context);
        running = false;
        minutes = 0;
        seconds = 0;
        paused = false;
    }

    public Chronometer2(Context context, AttributeSet attrs) {
        super(context, attrs);
        running = false;
        minutes = 0;
        seconds = 0;
    }

    @Override
    public void start() {
        super.start();
        running = true;
    }

    public void pause(){
        if(!paused){
            this.paused = true;
            this.stop();
        }
        else{
            this.paused = false;
            if(running) this.start();
        }
    }

    public void switchRun(){
        if(this.isRunning()) this.stop();
        else this.start();
    }

    @Override
    public void stop() {
        super.stop();
        running = false;
    }

    public boolean isRunning(){
        return running;
    }

    public void subtractSecond(){
        if(seconds>0) seconds--;
        else if(seconds==0){
            minutes--;
            seconds=59;
        }
    }

    public String getTimeText(){
        String time = "";
        if(minutes<10) time += "0";
        time += minutes;
        time += ":";
        if(seconds<10) time += "0";
        time += seconds;
        return time;
    }

    public void setSeconds(int seconds){
        this.seconds = seconds;
    }

    public void setMinutes(int minutes){
        this.minutes = minutes;
    }

    public int getSeconds(){
        return seconds;
    }

    public int getMinutes(){
        return minutes;
    }
}
