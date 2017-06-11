package pl.vlo.bit_chess.bit_chess;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;

/**
 * Created by Yoshimoo12 on 2017-05-29.
 */

public class GameChessboard extends Fragment {

    private ChessboardAdapter adapter;
    private GridView grid;
    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.chessboard, container, false);

        initViews();
        executeMethods();

        ImageButton next = (ImageButton) view.findViewById(R.id.arrow_next);
        ImageButton back = (ImageButton) view.findViewById(R.id.arrow_back);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        refresh(0);


        return view;
    }

    private void refresh(int move){
        ChessboardAdapter adapter;
        adapter = new ChessboardAdapter(getContext(), ChessboardAdapter.emptyChessboard, ChessboardAdapter.emptyFigures);
        grid.setAdapter(adapter);
    }

    private void initViews(){
        int chessboard[] = ChessboardAdapter.emptyChessboard;
        int figures[] = ChessboardAdapter.emptyFigures;

        adapter = new ChessboardAdapter(getContext(), chessboard, figures);
        grid = (GridView) view.findViewById(R.id.chessboard);
    }

    private void executeMethods(){
        grid.setAdapter(adapter);
        /*grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                //((ChessboardAdapter) grid.getAdapter()).moveFigure(5, 1);
                //((ChessboardAdapter) grid.getAdapter()).highlightField(pos);
                Toast.makeText(getContext(), pos +"", Toast.LENGTH_SHORT).show();
                //adapter.notifyDataSetChanged();
            }
        });*/
    }

}
