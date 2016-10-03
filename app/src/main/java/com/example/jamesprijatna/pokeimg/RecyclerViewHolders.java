package com.example.jamesprijatna.pokeimg;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jamesprijatna on 19/9/16.
 */
public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView pokeName;
    public ImageView pokeImg;
    private Context context;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        context = itemView.getContext();
        itemView.setOnClickListener(this);
        pokeName = (TextView) itemView.findViewById(R.id.item);
        pokeImg = (ImageView) itemView.findViewById(R.id.icon);
    }

    @Override
    public void onClick(View view) {

        String test = ((TextView) view.findViewById(R.id.item)).getText().toString();

        Toast.makeText(context, test, Toast.LENGTH_SHORT).show();

        Intent myIntent = new Intent(view.getContext(), EntryActivity.class);
        myIntent.putExtra("Name", test);
        context.startActivity(myIntent);
    }
}

