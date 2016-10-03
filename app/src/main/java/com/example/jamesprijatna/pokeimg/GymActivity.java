package com.example.jamesprijatna.pokeimg;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jamesprijatna on 2/10/16.
 */
public class GymActivity extends Activity {

    private GridLayoutManager glm;
    private SQLiteDatabase mydatabase;
    List<Pokemon> allItems = new ArrayList<Pokemon>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gymlayout);

        mydatabase = openOrCreateDatabase("pokeDB", MODE_PRIVATE, null);

        Cursor rs = mydatabase.rawQuery("Select ID, Pokemon, Element, Total from Pokemon where Gym = 'yes'", null);

        if (rs.moveToFirst())

        {
            do {
                int id = rs.getInt(0);
                String name = rs.getString(1);
                String element = rs.getString(2);
                String total = rs.getString(3);
                String imgid = "pic" + id;
                String elembg = "r"+element.toLowerCase();

                Pokemon pokems = new Pokemon(id, getResources().getIdentifier(imgid, "drawable", getPackageName()), name, element, total, getResources().getIdentifier(elembg, "drawable", getPackageName()));
                allItems.add(pokems);

            }
            while (rs.moveToNext());

        }

        glm = new GridLayoutManager(GymActivity.this, 1);

        CustomRecyclerView rView;
        rView = (CustomRecyclerView) findViewById(R.id.listView);

        rView.setHasFixedSize(true);
        rView.setLayoutManager(glm);

        RecyclerViewAdapter2 rcAdapter = new RecyclerViewAdapter2(GymActivity.this, allItems);
        rView.setAdapter(rcAdapter);

    }

}
