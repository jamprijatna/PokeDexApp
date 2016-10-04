package com.example.jamesprijatna.pokeimg;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by jamesprijatna on 2/10/16.
 */

public class StartActivity extends Activity {

    private SQLiteDatabase mydatabase;
    private SharedPreferences prefs = null;

    private Button start;
    private Button team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.startlayout);

        prefs = getSharedPreferences("com.example.jamesprijatna.pokeimg", MODE_PRIVATE);

        start = (Button) findViewById(R.id.button);
        team = (Button) findViewById(R.id.button2);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(StartActivity.this, MainActivity.class);
                StartActivity.this.startActivity(myIntent);

            }
        });

        team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(StartActivity.this, GymActivity.class);
                StartActivity.this.startActivity(myIntent);

            }
        });

    }

    /** Checks if the App is initialised for the first time and populates the DB with the .csv file
     *  http://stackoverflow.com/a/13237848
     */

    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstrun", true)) {
            this.deleteDatabase("pokeDB");
            mydatabase = openOrCreateDatabase("pokeDB", MODE_PRIVATE, null);

            mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Pokemon(ID INTEGER, Pokemon VARCHAR, HP VARCHAR, AP VARCHAR, DP VARCHAR, Height VARCHAR, Weight VARCHAR, Male VARCHAR, Female VARCHAR, Element VARCHAR, Element2 VARCHAR, Ability VARCHAR, Ability2 VARCHAR, EvoFrom VARCHAR, EvoTo VARCHAR, Desc VARCHAR, SA VARCHAR, SD VARCHAR, Spd VARCHAR, Total VARCHAR, Gym VARCHAR);");

            InputStream inputStream = getResources().openRawResource(R.raw.pokemons2);

            BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";
            String tableName = "Pokemon";
            String columns = "ID, Pokemon, HP, AP, DP, Height, Weight, Male, Female, Element, Element2, Ability, Ability2, EvoFrom, EvoTo, Desc, SA, SD, Spd, Total, Gym";
            String str1 = "INSERT INTO " + tableName + " (" + columns + ") values(";
            String str2 = ");";

            mydatabase.beginTransaction();
            try {
                while ((line = buffer.readLine()) != null) {
                    StringBuilder sb = new StringBuilder(str1);
                    String[] str = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                    sb.append("'" + str[0] + "',");
                    sb.append("'" + str[1] + "',");
                    sb.append("'" + str[2] + "',");
                    sb.append("'" + str[3] + "',");
                    sb.append("'" + str[4] + "',");
                    sb.append("'" + str[5] + "',");
                    sb.append("'" + str[6] + "',");
                    sb.append("'" + str[7] + "',");
                    sb.append("'" + str[8] + "',");
                    sb.append("'" + str[9] + "',");
                    sb.append("'" + str[10] + "',");
                    sb.append("'" + str[11] + "',");
                    sb.append("'" + str[12] + "',");
                    sb.append("'" + str[13] + "',");
                    sb.append("'" + str[14] + "',");
                    sb.append("'" + str[15] + "',");
                    sb.append("'" + str[16] + "',");
                    sb.append("'" + str[17] + "',");
                    sb.append("'" + str[18] + "',");
                    sb.append("'" + str[19] + "',");
                    sb.append("'" + str[20] + "'");

                    sb.append(str2);
                    mydatabase.execSQL(sb.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            mydatabase.setTransactionSuccessful();
            mydatabase.endTransaction();

            prefs.edit().putBoolean("firstrun", false).commit();
        }
    }

}
