package pl.vlo.bit_chess.bit_chess;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Yoshimoo12 on 2017-05-25.
 */

public class SquareImage extends android.support.v7.widget.AppCompatImageView{

        public SquareImage(Context context) {
            super(context);
        }

        public SquareImage(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public SquareImage(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }

        @Override
        public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, widthMeasureSpec); // This is the key that will make the height equivalent to its width
        }
    }
