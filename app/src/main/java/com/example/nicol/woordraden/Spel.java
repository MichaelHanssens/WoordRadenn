package com.example.nicol.woordraden;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Spel extends AppCompatActivity {
    String woorden[] = new String[]{"Aardappel", "Banaan", "Pannenkoek","Haardvuur","Schoolboeken","Computermuis","Pingpongtafel","Vaatwasser","Televisietoestel","Polshorloge"};
    String GekozenWoord = kiesWoord(woorden);
    TextView Oplossing;
    EditText Ingave;
    Button Raad;
    Button Volgende;
    TextView Controle;
    Button Opnieuw;
    private String kiesWoord(String woorden[]){
        Random rn = new Random();
        int RandomCijfer = rn.nextInt(woorden.length);
        String GekozenWoord = woorden[RandomCijfer];
        return  GekozenWoord;
    }
    private String setWoordDoorElkaar(String GekozenWoord){
        Random rn = new Random();
        String DoorElkaarWoord="";
        ArrayList GebruikteGetallen = new ArrayList();
        int AantalLetters = GekozenWoord.length();
        for(int i= 0;i<= AantalLetters-1;i++) {
            int RandomCijfer;
            do {
                RandomCijfer = rn.nextInt(AantalLetters);
            } while (GebruikteGetallen.contains(RandomCijfer));
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
        Oplossing = (TextView) findViewById(R.id.LettersLabel);
        Ingave = (EditText) findViewById(R.id.IngaveTextbox);
        Raad = (Button) findViewById(R.id.RadenButton);
        Volgende = (Button) findViewById(R.id.volgendeButton);
        Controle = (TextView) findViewById(R.id.ControletextView);
        Opnieuw = (Button) findViewById(R.id.OpnieuwButton) ;
        Oplossing.setText(setWoordDoorElkaar(GekozenWoord));
        Raad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Objects.equals(Ingave.getText().toString().toLowerCase(), GekozenWoord.toLowerCase())) {
                    Oplossing.setText(GekozenWoord);
                    Raad.setVisibility(View.INVISIBLE);
                    Volgende.setVisibility(View.VISIBLE);
                    Controle.setText("Correct");
                    Controle.setTextColor(Color.GREEN);
                } else {
                    Controle.setText("Niet Correct");
                    Controle.setTextColor(Color.RED);
                    Opnieuw.setVisibility(View.VISIBLE);
                    Raad.setVisibility(View.INVISIBLE);
                }
                Ingave.setText("");
            }
        });
        Volgende.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GekozenWoord = kiesWoord(woorden);
                Oplossing.setText(setWoordDoorElkaar(GekozenWoord));
                Volgende.setVisibility(View.INVISIBLE);
                Raad.setVisibility(View.VISIBLE);
                Ingave.setText("");
                Controle.setText("");
            }
        });
        Opnieuw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Controle.setText("");
                Opnieuw.setVisibility(View.INVISIBLE);
               Controle.setText("");
                Raad.setVisibility(View.VISIBLE);
            }
        });
    }

}
