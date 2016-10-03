package com.example.jamesprijatna.pokeimg;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by jamesprijatna on 19/9/16.
 */
public class RecyclerViewHolders2 extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView pokeName;
    public ImageView pokeImg;
    private Context context;
    public TextView pokeElem;
    public TextView pokeStat;
    public ProgressBar pokeProgress;

    public RecyclerViewHolders2(View itemView) {
        super(itemView);
        context = itemView.getContext();
        itemView.setOnClickListener(this);
        pokeName = (TextView) itemView.findViewById(R.id.item);
        pokeImg = (ImageView) itemView.findViewById(R.id.icon);
        pokeElem = (TextView) itemView.findViewById(R.id.elem);
        pokeStat = (TextView) itemView.findViewById(R.id.stat);
        pokeProgress = (ProgressBar) itemView.findViewById(R.id.progressBar);

    }

    @Override
    public void onClick(View view) {

        String tes = ((TextView) view.findViewById(R.id.item)).getText().toString();

        String[] parts = tes.split(". ");

        String test = parts[1];


        Toast.makeText(context, test, Toast.LENGTH_SHORT).show();

        Intent myIntent = new Intent(view.getContext(), EntryActivity.class);
        myIntent.putExtra("Name", test);
        context.startActivity(myIntent);
    }
}

