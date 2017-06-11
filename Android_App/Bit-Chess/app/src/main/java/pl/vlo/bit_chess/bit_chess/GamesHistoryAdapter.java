package pl.vlo.bit_chess.bit_chess;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Yoshimoo12 on 2017-06-03.
 */

public class GamesHistoryAdapter extends BaseAdapter {

    private ArrayList<ChessGame> gamesList;
    private Context context;
    private LayoutInflater inflater;

    GamesHistoryAdapter(Context context){
        gamesList =  new ArrayList<>();
        this.context = context;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return gamesList.size();
    }

    @Override
    public Object getItem(int i) {
        return gamesList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View OldView, ViewGroup viewGroup) {
        if(OldView!=null) return OldView;
        else{
            View view = inflater.inflate(R.layout.games_history_element, null);

            TextView text = (TextView) view.findViewById(R.id.game_name);
            text.setText(gamesList.get(i).getTitle());

            return view;
        }
    }



    public void setGamesList(ArrayList<ChessGame> games){
        gamesList = games;
        notifyDataSetChanged();
    }
}
