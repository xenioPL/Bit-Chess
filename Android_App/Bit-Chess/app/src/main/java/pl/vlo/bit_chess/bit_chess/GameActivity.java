package pl.vlo.bit_chess.bit_chess;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    FloatingActionButton fab, fab1, fab2, fab3;
    TextView text1, text2, text3;
    LinearLayout fabLayout1, fabLayout2, fabLayout3;
    boolean isFABOpen;
    private final int ANIMATION_TIME = 300;

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if(getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            fab.setVisibility(View.GONE);
            fabLayout1.setVisibility(View.GONE);
            fabLayout2.setVisibility(View.GONE);
            fabLayout3.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        isFABOpen = false;

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab3);

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeFABMenu();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_game, new GamesHistoryFragment())
                        .addToBackStack("historyOfGames")
                        .commit();
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeFABMenu();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_game, new ColorsFragment())
                        .addToBackStack("colors")
                        .commit();
            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeFABMenu();
                Intent i = new Intent(getApplication(), SettingsActivity.class);
                startActivity(i);
            }
        });

        fabLayout1 = (LinearLayout) findViewById(R.id.fabLayout1);
        fabLayout2 = (LinearLayout) findViewById(R.id.fabLayout2);
        fabLayout3 = (LinearLayout) findViewById(R.id.fabLayout3);

        fab.setVisibility(View.VISIBLE);
        fabLayout1.setVisibility(View.VISIBLE);
        fabLayout2.setVisibility(View.VISIBLE);
        fabLayout3.setVisibility(View.VISIBLE);

        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);

        text1.setVisibility(View.GONE);
        text2.setVisibility(View.GONE);
        text3.setVisibility(View.GONE);

        fabLayout1.setTranslationX(fab.getTranslationX());
        fabLayout2.setTranslationX(fab.getTranslationX());
        fabLayout3.setTranslationX(fab.getTranslationX());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_game, new ChooseEnemyFragment())
                .commit();

    }

    private void showFABMenu(){
        isFABOpen=true;
        fabLayout1.setVisibility(View.VISIBLE);
        fabLayout2.setVisibility(View.VISIBLE);
        fabLayout3.setVisibility(View.VISIBLE);

        fabLayout1.animate().setDuration(ANIMATION_TIME)
                .translationY(fab.getTranslationY() - getResources().getDimension(R.dimen.standard_80))
                .translationX(fab.getTranslationX());

        fabLayout2.animate().setDuration(ANIMATION_TIME)
                .translationY(fab.getTranslationY() - getResources().getDimension(R.dimen.standard_40))
                .translationX(fab.getTranslationX() - getResources().getDimension(R.dimen.standard_40));

        fabLayout3.animate().setDuration(ANIMATION_TIME)
                .translationY(fab.getTranslationY())
                .translationX(fab.getTranslationX() - getResources().getDimension(R.dimen.standard_80))
                .withEndAction(new Runnable() {
            @Override
            public void run() {
                text1.setVisibility(View.VISIBLE);
                text2.setVisibility(View.VISIBLE);
                text3.setVisibility(View.VISIBLE);
            }
        });


    }

    private void closeFABMenu(){
        isFABOpen=false;

        text1.setVisibility(View.GONE);
        text2.setVisibility(View.GONE);
        text3.setVisibility(View.GONE);

        fabLayout1.animate().setDuration(ANIMATION_TIME)
                .translationY(fab.getTranslationY())
                .translationX(fab.getTranslationX());

        fabLayout2.animate().setDuration(ANIMATION_TIME)
                .translationY(fab.getTranslationY())
                .translationX(fab.getTranslationX());

        fabLayout3.animate().setDuration(ANIMATION_TIME)
                .translationY(fab.getTranslationY())
                .translationX(fab.getTranslationX())
                .withEndAction(new Runnable() {
            @Override
            public void run() {
                fabLayout1.setVisibility(View.GONE);
                fabLayout2.setVisibility(View.GONE);
                fabLayout3.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(!isFABOpen){
            FragmentManager fm = getSupportFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
                if (fm.getBackStackEntryCount() == 1) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    getSupportActionBar().setDisplayShowHomeEnabled(false);
                    fab.setVisibility(View.VISIBLE);
                    fabLayout1.setVisibility(View.VISIBLE);
                    fabLayout2.setVisibility(View.VISIBLE);
                    fabLayout3.setVisibility(View.VISIBLE);
                }
            }
        }else{
            closeFABMenu();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home: {
                FragmentManager fm = getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                    if (fm.getBackStackEntryCount() == 1) {
                        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                        getSupportActionBar().setDisplayShowHomeEnabled(false);
                        fab.setVisibility(View.VISIBLE);
                        fabLayout1.setVisibility(View.VISIBLE);
                        fabLayout2.setVisibility(View.VISIBLE);
                        fabLayout3.setVisibility(View.VISIBLE);
                    }
                }
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}