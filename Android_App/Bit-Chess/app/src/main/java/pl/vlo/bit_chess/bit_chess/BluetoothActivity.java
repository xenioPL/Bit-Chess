package pl.vlo.bit_chess.bit_chess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BluetoothActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        setUpViews();
    }

    private void setUpViews(){
        getSupportFragmentManager().beginTransaction().add(R.id.placeholder_bluetooth, new BluetoothStart()).commit();
    }
}
