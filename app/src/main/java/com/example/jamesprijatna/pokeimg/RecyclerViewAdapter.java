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
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private List<Pokemon> pokeList;
    private Context context;

    public RecyclerViewAdapter(Context context, List<Pokemon> pokeList) {
        this.pokeList = pokeList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mylist, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.pokeName.setText(pokeList.get(position).getName());
        holder.pokeImg.setImageResource(pokeList.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return this.pokeList.size();
    }
}