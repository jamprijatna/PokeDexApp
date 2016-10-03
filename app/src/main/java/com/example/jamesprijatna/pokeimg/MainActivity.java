package com.example.jamesprijatna.pokeimg;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private GridLayoutManager glm;
    List<Pokemon> allItems = new ArrayList<Pokemon>();

    private Button filter;
    private Spinner spinner;
    private SQLiteDatabase mydatabase;

    private Parcelable listState;

    private String[] elements = new String[]{"-all-", "Fire", "Water", "Grass", "Poison", "Electric", "Ghost", "Fighting", "Bug", "Dragon", "Ice", "Ground", "Normal", "Psychic", "Flying"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button filter = (Button) findViewById(R.id.filter);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, elements);
        spinner.setAdapter(adapter);

        //Start DB Code
        //End DB Code

        mydatabase = openOrCreateDatabase("pokeDB", MODE_PRIVATE, null);

        //Start Data Retrieval Code
        Cursor resultSet = mydatabase.rawQuery("Select ID, Pokemon, Element from Pokemon", null);

        if (resultSet.moveToFirst()) {

            do {
                int id = resultSet.getInt(0);
                String name = resultSet.getString(1);
                String element = resultSet.getString(2);
                String imgid = "pic" + id;

                Pokemon pokems = new Pokemon(id, getResources().getIdentifier(imgid, "drawable", getPackageName()), name, element);
                allItems.add(pokems);

            }
            while (resultSet.moveToNext());
        }
        //End Retrieval Code

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                allItems.clear();

                String elm = spinner.getSelectedItem().toString();

                if(elm.equals("-all-")) {

                    Cursor rs = mydatabase.rawQuery("Select ID, Pokemon, Element from Pokemon", null);

                    if (rs.moveToFirst())

                    {
                        do {
                            int id = rs.getInt(0);
                            String name = rs.getString(1);
                            String element = rs.getString(2);
                            String imgid = "pic" + id;

                            Pokemon pokems = new Pokemon(id, getResources().getIdentifier(imgid, "drawable", getPackageName()), name, element);
                            allItems.add(pokems);

                        }
                        while (rs.moveToNext());

                    }
                } else {

                    Cursor rs = mydatabase.rawQuery("Select ID, Pokemon, Element from Pokemon where Element = " + "'" + elm + "'" +" OR Element2 = "+ "'" + elm + "'", null);

                    if (rs.moveToFirst())

                    {
                        do {
                            int id = rs.getInt(0);
                            String name = rs.getString(1);
                            String element = rs.getString(2);
                            String imgid = "pic" + id;

                            Pokemon pokems = new Pokemon(id, getResources().getIdentifier(imgid, "drawable", getPackageName()), name, element);
                            allItems.add(pokems);

                        }
                        while (rs.moveToNext());

                    }
                }

                //Start Placement Code
                glm = new GridLayoutManager(MainActivity.this, 3);

                CustomRecyclerView rView;
                rView = (CustomRecyclerView) findViewById(R.id.mylist);

                rView.setHasFixedSize(true);
                rView.setLayoutManager(glm);

                RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(MainActivity.this, allItems);
                rView.setAdapter(rcAdapter);

                //End Placement Code
            }
        });

        //Start Placement Code
        glm = new GridLayoutManager(MainActivity.this, 3);

        CustomRecyclerView rView;
        rView = (CustomRecyclerView) findViewById(R.id.mylist);

        rView.setHasFixedSize(true);
        rView.setLayoutManager(glm);

        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(MainActivity.this, allItems);
        rView.setAdapter(rcAdapter);

        //End Placement Code

    }

    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putParcelable("key", glm.onSaveInstanceState());
    }

    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);

        listState = state.getParcelable("key");
    }

    @Override
    protected void onPause() {
        super.onPause();
        glm.onSaveInstanceState();

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (listState != null) {
            glm.onRestoreInstanceState(listState);
        }
    }


}
