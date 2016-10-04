package com.example.jamesprijatna.pokeimg;

/**
 * Created by jamesprijatna on 20/9/16.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

//http://stackoverflow.com/a/30747742

public class CustomRecyclerView extends RecyclerView {

    Context context;

    public CustomRecyclerView(Context context) {
        super(context);
        this.context = context;
    }

    public CustomRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {

        velocityY *= 0.7;
        return super.fling(velocityX, velocityY);
    }

}