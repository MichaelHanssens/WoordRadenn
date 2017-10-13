package com.example.nicol.woordraden;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import javax.xml.transform.dom.DOMResult;

import static com.example.nicol.woordraden.R.id.RadenButton;

public class Spel extends AppCompatActivity {
    String woorden[] = new String[]{"Aardappel", "Banaan", "Pannenkoek","Haardvuur","Schoolboeken","Computermuis","Pingpongtafel","Vaatwasser","Televisietoestel","Polshorloge"};
    String GekozenWoord = kiesWoord(woorden);
    TextView Letterslabel;
    EditText Ingave;
    Button Raad;
    Button Volgende;
    private String kiesWoord(String woorden[]){
        Random rn = new Random();
        int RandomCijfer = rn.nextInt(woorden.length);
        String GekozenWoord = woorden[RandomCijfer];
        return  GekozenWoord;
    }
    private String setWoordDoorElkaar(String woorden[],String GekozenWoord){
        Random rn = new Random();
        String DoorElkaarWoord="";
        ArrayList GebruikteGetallen = new ArrayList();
        int AantalLetters = GekozenWoord.length();
        int teller=0;
        while (teller < AantalLetters) {
            int RandomCijfer;
            do {
                RandomCijfer = rn.nextInt(AantalLetters);
            } while (GebruikteGetallen.contains(RandomCijfer));
            teller+=1;
            GebruikteGetallen.add(RandomCijfer);
            String letter = GekozenWoord.substring(RandomCijfer, RandomCijfer+1);
            DoorElkaarWoord += letter;
        }
        return DoorElkaarWoord;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Letterslabel = (TextView) findViewById(R.id.LettersLabel);
        Ingave = (EditText) findViewById(R.id.IngaveTextbox);
        Raad = (Button) findViewById(R.id.RadenButton);
        Volgende = (Button) findViewById(R.id.volgendeButton);
        Letterslabel.setText(setWoordDoorElkaar(woorden,GekozenWoord));
        Raad.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (Objects.equals(Ingave.getText().toString().toLowerCase(), GekozenWoord.toLowerCase())){
                    Letterslabel.setText(GekozenWoord);
                    Raad.setVisibility(View.INVISIBLE);
                    Volgende.setVisibility(View.VISIBLE);
                }
                Ingave.setText("");
            }
        });
        Volgende.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                GekozenWoord = kiesWoord(woorden);
                Letterslabel.setText(setWoordDoorElkaar(woorden,GekozenWoord));
                Volgende.setVisibility(View.INVISIBLE);
                Raad.setVisibility(View.VISIBLE);
                Ingave.setText("");
            }
        });
    }

}

