package pl.vlo.bit_chess.bit_chess;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_settings);

        getFragmentManager().beginTransaction().replace(R.id.content_settings, new SettingsFragment()).commit();
    }
}
