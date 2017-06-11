package pl.vlo.bit_chess.bit_chess;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

public class ChooseColorActivity extends AppCompatActivity {
    int red = 0;
    int green = 0;
    int blue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_change_layout);

        setUpViews();
        load();

    }

    private void cancel(){
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    private void saveAndBack(){
        Intent resultIntent = new Intent();
    // TODO Add extras or a data URI to this intent as appropriate.
        resultIntent.putExtra("color", Color.rgb(red, green, blue));
        resultIntent.putExtra("position", getIntent().getIntExtra("position", 0));
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    private void load(){

        Intent i = getIntent();
        int color = i.getIntExtra("color", 0);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);

        final SeekBar redSeekBar = (SeekBar) findViewById(R.id.redSeekBar);
        final SeekBar greenSeekBar = (SeekBar) findViewById(R.id.greenSeekBar);
        final SeekBar blueSeekBar = (SeekBar) findViewById(R.id.blueSeekBar);
        final ImageView image = (ImageView) findViewById(R.id.image);

        redSeekBar.setProgress(r);
        greenSeekBar.setProgress(g);
        blueSeekBar.setProgress(b);
        image.setColorFilter(Color.rgb(r, g, b));
    }


    private void setUpViews() {
        final SeekBar redSeekBar = (SeekBar) findViewById(R.id.redSeekBar);
        final SeekBar greenSeekBar = (SeekBar) findViewById(R.id.greenSeekBar);
        final SeekBar blueSeekBar = (SeekBar) findViewById(R.id.blueSeekBar);
        final ImageView image = (ImageView) findViewById(R.id.image);
        final EditText redText = (EditText) findViewById(R.id.redText);
        final EditText greenText = (EditText) findViewById(R.id.greenText);
        final EditText blueText = (EditText) findViewById(R.id.blueText);
        final Button save = (Button) findViewById(R.id.saveButton);
        final Button cancel = (Button) findViewById(R.id.cancelButton);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAndBack();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });

        redText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) return;
                int value = Integer.parseInt(charSequence.toString());
                if (value < 0) {
                    redSeekBar.setProgress(0);
                    redText.setText("0");
                } else if (value > 255) {
                    redSeekBar.setProgress(255);
                    redText.setText("255");
                } else redSeekBar.setProgress(value);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        redSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                redText.setText(String.valueOf(i));
                red = i;
                image.setColorFilter(Color.rgb(red, green, blue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        greenText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) return;
                int value = Integer.parseInt(charSequence.toString());
                if (value < 0) {
                    greenSeekBar.setProgress(0);
                    greenText.setText("0");
                } else if (value > 255) {
                    greenSeekBar.setProgress(255);
                    greenText.setText("255");
                } else greenSeekBar.setProgress(value);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        greenSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                greenText.setText(String.valueOf(i));
                green = i;
                image.setColorFilter(Color.rgb(red, green, blue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        blueText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) return;
                int value = Integer.parseInt(charSequence.toString());
                if (value < 0) {
                    blueSeekBar.setProgress(0);
                    blueText.setText("0");
                } else if (value > 255) {
                    blueSeekBar.setProgress(255);
                    blueText.setText("255");
                } else blueSeekBar.setProgress(value);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        blueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                blueText.setText(String.valueOf(i));
                blue = i;
                image.setColorFilter(Color.rgb(red, green, blue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
