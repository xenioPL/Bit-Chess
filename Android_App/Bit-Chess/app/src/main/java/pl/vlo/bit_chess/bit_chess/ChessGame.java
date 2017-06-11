package pl.vlo.bit_chess.bit_chess;

import java.util.ArrayList;

/**
 * Created by Yoshimoo12 on 2017-06-03.
 */

public class ChessGame {

    private String title;
    private ArrayList<Byte[]> boards;

    ChessGame(String title){
        boards = new ArrayList<>();
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void getBoard(int pos){
        boards.get(pos);
    }

    public void addBoard(Byte[] board){
        if(board.length == 64)
            boards.add(board);
    }
}
