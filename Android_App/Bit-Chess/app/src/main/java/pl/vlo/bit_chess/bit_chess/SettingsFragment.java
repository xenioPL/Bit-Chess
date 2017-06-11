package pl.vlo.bit_chess.bit_chess;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by Yoshimoo12 on 2017-05-11.
 */

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.fragment_settings);
    }

}
