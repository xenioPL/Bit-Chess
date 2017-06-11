package pl.vlo.bit_chess.bit_chess;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Yoshimoo12 on 2017-05-25.
 */

public class ChessboardAdapter extends BaseAdapter {

    private int[] chessboard;
    private int[] figures;
    private Context context;
    private int highlightedField = -1;
    private LayoutInflater inflater;

    ChessboardAdapter(Context context, int[] chessboard, int[] figures){
        this.context = context;
        this.chessboard = chessboard;
        this.figures = figures;
        this.inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return this.chessboard.length;
    }

    @Override
    public Object getItem(int i) {
        if(i >= 0 && i < this.chessboard.length) return this.chessboard[i];
        else return -1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if(convertView!=null)return convertView;
        else {
            View view = inflater.inflate(R.layout.chess_field, null);
            ImageView img = (ImageView) view.findViewById(R.id.field_image);
            img.setBackgroundColor(ContextCompat.getColor(context, this.chessboard[i]));
            if(this.figures[i]!=0)img.setImageResource(figure(figures[i]));
            return view;
        }
    }

    public void highlightField(int a){
        if(a >= 0 & a < 64) {
            if(highlightedField == a){
                changeFieldColor(a, ChessboardAdapter.emptyChessboard[a]);
                return;
            }
            if(highlightedField != -1){
                changeFieldColor(highlightedField, ChessboardAdapter.emptyChessboard[highlightedField]);
            }
            highlightedField = a;
            changeFieldColor(a, R.color.highlightField);
            notifyDataSetChanged();
        }
    }

    public void moveFigure(int a, int b){
        if(a < 0 | a > 63 | b < 0 | b > 63 | this.figures[b]!=0 | this.figures[a]==0){
            Toast.makeText(context, "Wrong move", Toast.LENGTH_SHORT).show();
        }
        else{
            this.figures[b] = this.figures[a];
            this.figures[a] = 0;
            notifyDataSetChanged();
        }
    }

    private int figure(int num){
        if(num == 0) return 0;
        else if(num == -1) return R.drawable.pawn_black;
        else if(num == -2) return R.drawable.knight_black;
        else if(num == -3) return R.drawable.rook_black;
        else if(num == -4) return R.drawable.bishop_black;
        else if(num == -5) return R.drawable.queen_black;
        else if(num == -6) return R.drawable.king_black;
        else if(num == 1) return R.drawable.pawn_white;
        else if(num == 2) return R.drawable.knight_white;
        else if(num == 3) return R.drawable.rook_white;
        else if(num == 4) return R.drawable.bishop_white;
        else if(num == 5) return R.drawable.queen_white;
        else if(num == 6) return R.drawable.king_white;
        return -1;
    }

    private void changeFieldColor(int a, int color){
        if(a>=0 & a < 64) this.chessboard[a] = color;
        notifyDataSetChanged();
    }

    // 0 - empty
    // - black / + white
    // 1 - pawn
    // 2 - knight / horse
    // 3 - rook / tower
    // 4 - bishop
    // 5 - queen
    // 6 - king

    static int emptyFigures[] = {
            0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0
    };

    static int emptyChessboard[] = {
            R.color.whiteField, R.color.blackField, R.color.whiteField, R.color.blackField,
            R.color.whiteField, R.color.blackField, R.color.whiteField, R.color.blackField,

            R.color.blackField, R.color.whiteField, R.color.blackField, R.color.whiteField,
            R.color.blackField, R.color.whiteField, R.color.blackField, R.color.whiteField,

            R.color.whiteField, R.color.blackField, R.color.whiteField, R.color.blackField,
            R.color.whiteField, R.color.blackField, R.color.whiteField, R.color.blackField,

            R.color.blackField, R.color.whiteField, R.color.blackField, R.color.whiteField,
            R.color.blackField, R.color.whiteField, R.color.blackField, R.color.whiteField,

            R.color.whiteField, R.color.blackField, R.color.whiteField, R.color.blackField,
            R.color.whiteField, R.color.blackField, R.color.whiteField, R.color.blackField,

            R.color.blackField, R.color.whiteField, R.color.blackField, R.color.whiteField,
            R.color.blackField, R.color.whiteField, R.color.blackField, R.color.whiteField,

            R.color.whiteField, R.color.blackField, R.color.whiteField, R.color.blackField,
            R.color.whiteField, R.color.blackField, R.color.whiteField, R.color.blackField,

            R.color.blackField, R.color.whiteField, R.color.blackField, R.color.whiteField,
            R.color.blackField, R.color.whiteField, R.color.blackField, R.color.whiteField,
    };

}
