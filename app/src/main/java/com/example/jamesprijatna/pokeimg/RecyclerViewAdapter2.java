package com.example.jamesprijatna.pokeimg;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by jamesprijatna on 19/9/16.
 */
public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewHolders2> {

    private List<Pokemon> pokeList;
    private Context context;

    public RecyclerViewAdapter2(Context context, List<Pokemon> pokeList) {
        this.pokeList = pokeList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders2 onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mylist3, null);
        RecyclerViewHolders2 rcv = new RecyclerViewHolders2(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders2 holder, int position) {
        String idname = "#"+pokeList.get(position).getID()+". "+pokeList.get(position).getName();
        holder.pokeName.setText(idname);
        holder.pokeImg.setImageResource(pokeList.get(position).getImg());
        holder.pokeElem.setText(pokeList.get(position).getElement());
        holder.pokeElem.setBackgroundResource(pokeList.get(position).getBg());
        holder.pokeStat.setText(pokeList.get(position).getTotal());
        holder.pokeProgress.setMax(700);
        holder.pokeProgress.setProgress(Integer.valueOf(pokeList.get(position).getTotal()));
    }

    @Override
    public int getItemCount() {
        return this.pokeList.size();
    }
}