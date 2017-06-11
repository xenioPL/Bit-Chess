package pl.vlo.bit_chess.bit_chess;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Yoshimoo12 on 2017-06-03.
 */

public class GamesHistoryFragment extends Fragment{

    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.games_history_layout, container, false);

        GamesHistoryAdapter adapter = new GamesHistoryAdapter(getContext());
        ListView gamesList = (ListView) view.findViewById(R.id.gamesList);
        TextView emptyView = (TextView) view.findViewById(R.id.emptyViewHistory);
        gamesList.setEmptyView(emptyView);
        //adapter.setGamesList(loadGames());
        //adapter.add(game);
        gamesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_game, new GameChessboard())
                        .addToBackStack("gameChessboard")
                        .commit();
            }
        });
        gamesList.setAdapter(adapter);

        return view;
    }

    private ArrayList<ChessGame> loadGames(){
        Gson g = new Gson();
        SharedPreferences sp = getActivity().getSharedPreferences("bit-chess", Context.MODE_PRIVATE);
        String json = sp.getString("games", "");
        if(json.equals(""))return null;
        Type type = new TypeToken<ArrayList<ChessGame>>() {}.getType();
        return g.fromJson(json, type);
    }
}
