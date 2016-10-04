package com.example.jamesprijatna.pokeimg;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by jamesprijatna on 12/9/16.
 */
public class EntryActivity extends Activity {

    private TextView text;

    private TextView hptext;
    private TextView aptext;
    private TextView dptext;
    private TextView satext;
    private TextView sdtext;
    private TextView spdtext;
    private TextView ttltext;

    private TextView heighttext;
    private TextView weighttext;
    private TextView gendertext;
    private TextView gendertext2;
    private TextView elementText;
    private TextView elementText2;
    private TextView abilityText;
    private TextView abilityText2;
    private TextView abilitylabel;

    private TextView descText;
    private ImageView image;
    private Button next;
    private Button previous;
    private GridView list;

    private ProgressBar progHP;
    private ProgressBar progAP;
    private ProgressBar progDP;
    private ProgressBar progSA;
    private ProgressBar progSD;
    private ProgressBar progSpd;

    private Button add;
    private Button remove;
    private Button view;


    private int id;
    private int ide;

    private String t;

    private String n;

    private String fromName;
    private String toName;
    private ArrayList<String> poke;
    private ArrayList<Integer> pok;
    private SQLiteDatabase mydatabase;
    private String name;
    private String[] itemname;
    private Integer[] img;
    private String imgid;
    private String iconid;

    private Context context;

    public static Activity act;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        act = this;

        poke = new ArrayList<>();
        pok = new ArrayList<>();

        //Start Setup
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_layout);

        mydatabase = openOrCreateDatabase("pokeDB", MODE_PRIVATE, null);

        Intent intent = getIntent();

        t = intent.getStringExtra("Name").replace("'", "''");

        Cursor cursor = mydatabase.rawQuery("Select ID from Pokemon where Pokemon = " + "'" + t + "'", null);
        cursor.moveToFirst();
        id = cursor.getInt(0);

        /*ide = intent.getIntExtra("ID", 1);
        id = ide + 1;
        imgid = "logo"+id;*/

        text = (TextView) findViewById(R.id.textView);
        descText = (TextView) findViewById(R.id.descText);

        hptext = (TextView) findViewById(R.id.hp);
        aptext = (TextView) findViewById(R.id.ap);
        dptext = (TextView) findViewById(R.id.dp);
        satext = (TextView) findViewById((R.id.sa));
        sdtext = (TextView) findViewById((R.id.sd));
        spdtext = (TextView) findViewById((R.id.spd));
        ttltext = (TextView) findViewById((R.id.totalStats));

        elementText = (TextView) findViewById(R.id.elementText);
        elementText2 = (TextView) findViewById(R.id.elementText2);
        weighttext = (TextView) findViewById(R.id.weight);
        heighttext = (TextView) findViewById(R.id.height);
        gendertext = (TextView) findViewById(R.id.gender);
        gendertext2 = (TextView) findViewById(R.id.gender2);

        abilityText = (TextView) findViewById(R.id.ability);
        abilityText2 = (TextView) findViewById(R.id.ability2);
        abilitylabel = (TextView) findViewById(R.id.ability2label);

        add = (Button) findViewById(R.id.addButton);
        remove = (Button) findViewById(R.id.removeButton);
        view = (Button) findViewById(R.id.viewButton);

        progHP = (ProgressBar) findViewById(R.id.progHP);
        progAP = (ProgressBar) findViewById(R.id.progAP);
        progDP = (ProgressBar) findViewById(R.id.progDP);
        progSA = (ProgressBar) findViewById(R.id.progSA);
        progSD = (ProgressBar) findViewById(R.id.progSD);
        progSpd = (ProgressBar) findViewById(R.id.progSpd);

        progHP.setMax(250);
        progAP.setMax(250);
        progDP.setMax(250);
        progSA.setMax(250);
        progSD.setMax(250);
        progSpd.setMax(250);

        image = (ImageView) findViewById(R.id.imageView);
        next = (Button) findViewById(R.id.next);
        previous = (Button) findViewById(R.id.previous);

        //End Setup


        //Start InitialEntry

        imgid = "logo" + id;

        getData();

        //End InitialEntry


        //Start EvoStages

        evoStagesCheck();

        //End EvoStages


        //Start NextEntry Button
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                poke.clear();
                pok.clear();

                ArrayList<String> pokem = new ArrayList<>();

                id = id + 1;
                imgid = "logo" + id;

                getData();

                poke.clear();
                evoStagesCheck();
            }
        });
        //End NextEntry Button


        //Start PreviousEntry Button
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                poke.clear();
                pok.clear();

                ArrayList<String> poken = new ArrayList<>();

                id = id - 1;
                imgid = "logo" + id;

                getData();

                evoStagesCheck();


            }
        });
        //End PreviousEntry Button

        //Start AddButton
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mydatabase.execSQL("Update Pokemon Set Gym='yes' where ID =" + id);
                gymCheck();

            }
        });
        //End AddButton


        //Start RemoveButton
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mydatabase.execSQL("Update Pokemon Set Gym='no' where ID =" + id);
                gymCheck();

            }
        });
        //End RemoveButton


        //Start ViewButton
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(EntryActivity.this, GymActivity.class);
                EntryActivity.this.startActivity(myIntent);

            }
        });
        //End ViewButton


    }


    private void evoStagesCheck() {

        if (id == 133) {

            poke.add("Eeve");
            poke.add("Vaporeon");
            poke.add("Joltreon");
            poke.add("Flareon");

            itemname = new String[poke.size()];
            itemname = poke.toArray(itemname);

            img = new Integer[4];
            img[0] = R.drawable.pic133;
            img[1] = R.drawable.pic134;
            img[2] = R.drawable.pic135;
            img[3] = R.drawable.pic136;

            CustomListAdapter adapter = new CustomListAdapter(this, itemname, img);
            list = (GridView) findViewById(R.id.evoGrid);
            list.setNumColumns(4);
            list.setAdapter(adapter);


        } else {
            Cursor cursor = mydatabase.rawQuery("Select EvoFrom, EvoTo, Pokemon from Pokemon where ID =" + id, null);

            if (cursor.getCount() == 0) {
                //do nothing
            } else {

                cursor.moveToFirst();

                iconid = "pic" + id;

                n = cursor.getString(2);

                fromName = cursor.getString(0);
                toName = cursor.getString(1);


                if (fromName.equals("-") && toName.equals("-")) {
                    poke.add(n);
                }

                if (!fromName.equals("-")) {
                    fromCheck();

                } else if (fromName.equals("-") && !toName.equals("-")) {
                    poke.add(n);
                }

                if (!fromName.equals("-") && !toName.equals("-")) {
                    poke.add(n);

                }

                if (!toName.equals("-")) {
                    toCheck();

                } else if (!fromName.equals("-") && toName.equals("-")) {
                    poke.add(n);
                }

                itemname = new String[poke.size()];
                itemname = poke.toArray(itemname);

                //img = new Integer[pok.size()];
                //img = pok.toArray(img);

                img = new Integer[poke.size()];

                for (int i = 0; i < poke.size(); i++) {
                    String s = poke.get(i).replace("'", "''");

                    Cursor cursor1 = mydatabase.rawQuery("Select ID from Pokemon where Pokemon =" + "'" + s + "'", null);
                    if (cursor1.getCount() == 0) {
                        img[i] = new Integer(R.drawable.unknown);

                    } else {
                        cursor1.moveToFirst();

                        int j = cursor1.getInt(0);

                        String jk = "pic" + j;

                        img[i] = new Integer(getResources().getIdentifier(jk, "drawable", getPackageName()));
                    }
                }

                CustomListAdapter adapter = new CustomListAdapter(this, itemname, img);
                list = (GridView) findViewById(R.id.evoGrid);
                list.setNumColumns(poke.size());
                list.setAdapter(adapter);
            }
        }
    }

    private void fromCheck() {
        Cursor cursor1 = mydatabase.rawQuery("Select EvoFrom, ID from Pokemon where Pokemon =" + "'" + fromName + "'", null);

        if (cursor1.getCount() == 0) {
            //do nothing
        } else {

            cursor1.moveToFirst();

            String fromName2 = cursor1.getString(0);
            int i = cursor1.getInt(1);

            int is = i - 1;

            String s = "pic" + is;

            if (fromName2.equals("-")) {
                //do nothing

            } else {
                poke.add(fromName2);
            }

        }

        poke.add(fromName);

    }

    private void toCheck() {
        poke.add(toName);

        Cursor cursor1 = mydatabase.rawQuery("Select EvoTo, ID from Pokemon where Pokemon =" + "'" + toName + "'", null);

        if (cursor1.getCount() == 0) {
            //do nothing
        } else {
            cursor1.moveToFirst();

            String toName2 = cursor1.getString(0);
            int i = cursor1.getInt(1);

            int is = i + 1;

            String s = "pic" + is;

            if (toName2.equals("-")) {
                //do nothing

            } else {
                poke.add(toName2);
            }
        }
    }

    private void getData() {
        Cursor resultSet = mydatabase.rawQuery("Select ID, Pokemon, Element, Element2, HP, AP, DP, Desc, Height, Weight, Male, Female, SA, SD, Spd, Total, Ability, Ability2 from Pokemon where ID =" + id, null);
        resultSet.moveToFirst();
        String name = resultSet.getString(1);
        String element = resultSet.getString(2);
        String element2 = resultSet.getString(3);
        String HP = resultSet.getString(4);
        String AP = resultSet.getString(5);
        String DP = resultSet.getString(6);
        String desc = resultSet.getString(7);
        String height = resultSet.getString(8);
        String weight = resultSet.getString(9);
        String male = resultSet.getString(10);
        String female = resultSet.getString(11);
        String SA = resultSet.getString(12);
        String SD = resultSet.getString(13);
        String Spd = resultSet.getString(14);
        String total = resultSet.getString(15);
        String ab = resultSet.getString(16);
        String ab2 = resultSet.getString(17);

        progHP.setProgress(Integer.valueOf(HP));
        progAP.setProgress(Integer.valueOf(AP));
        progDP.setProgress(Integer.valueOf(DP));
        progSA.setProgress(Integer.valueOf(SA));
        progSD.setProgress(Integer.valueOf(SD));
        progSpd.setProgress(Integer.valueOf(Spd));

        ttltext.setText(total);

        desc = desc.replace("\"", "");

        text.setText("#" + id + ". " + name);
        descText.setText(desc);

        abilityText.setText(ab);

        if (!ab2.equals("-")) {
            abilityText2.setText(ab2);
            abilitylabel.setVisibility(TextView.VISIBLE);
        } else {
            abilityText2.setText("");
            abilitylabel.setVisibility(TextView.INVISIBLE);
        }

        elementText.setText(element);
        String el = "r" + element.toLowerCase();
        elementText.setBackgroundResource(getResources().getIdentifier(el, "drawable", getPackageName()));

        if (element2.equals("-")) {
            elementText2.setBackgroundResource(0);
            elementText2.setText("");
        } else {
            elementText2.setText(element2);
            String elm = "r" + element2.toLowerCase();
            elementText2.setBackgroundResource(getResources().getIdentifier(elm, "drawable", getPackageName()));
        }

        hptext.setText(HP);
        aptext.setText(AP);
        dptext.setText(DP);
        satext.setText(SA);
        sdtext.setText(SD);
        spdtext.setText(Spd);
        ttltext.setText(total);

        DecimalFormat oneDigit = new DecimalFormat("#,##0.0");

        String pound = String.valueOf(oneDigit.format(Double.parseDouble(weight) * 2.205));

        weighttext.setText(Double.parseDouble(weight) + " kg" + "\n" + pound + " lbs.");

        double d = Double.parseDouble(height) * 39.37;
        String dd = String.valueOf(Math.round(d));

        int inches = Integer.parseInt(dd);
        int fe = inches / 12;
        int leftover = inches % 12;

        String feet = String.valueOf(Math.round(fe));
        String inch = String.valueOf(Math.round(leftover));

        heighttext.setText(Double.parseDouble(height) + " m" + "\n" + feet + "'" + inch + "\"");

        if (male.equals("-") && female.equals("-")) {
            gendertext.setText("        genderless");
            gendertext2.setText("");
            gendertext.setTextColor(Color.parseColor("#88A4AC"));
        } else {
            gendertext2.setText("F: " + female + "%");
            gendertext2.setTextColor(Color.parseColor("#CC33FF"));
            gendertext.setText("M: " + male + "%");
            gendertext.setTextColor(Color.parseColor("#0000FF"));
        }

        gymCheck();

        image.setImageResource(getResources().getIdentifier(imgid, "drawable", getPackageName()));

        if (id == 1) {
            previous.setVisibility(Button.INVISIBLE);
        } else if (id == 151) {
            next.setVisibility(Button.INVISIBLE);
        } else {
            previous.setVisibility(Button.VISIBLE);
            next.setVisibility(Button.VISIBLE);
        }
    }

    private void gymCheck() {

        Cursor resultSet = mydatabase.rawQuery("Select Gym from Pokemon where ID =" + id, null);
        resultSet.moveToFirst();

        String gym = resultSet.getString(0);

        if (gym.equals("no")) {
            remove.setVisibility(Button.INVISIBLE);
            add.setVisibility(Button.VISIBLE);
        } else if (gym.equals("yes")) {
            remove.setVisibility(Button.VISIBLE);
            add.setVisibility(Button.INVISIBLE);
        }
    }
}
