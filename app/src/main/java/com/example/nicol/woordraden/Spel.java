package com.example.nicol.woordraden;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Objects;
import java.util.Random;

public class Spel extends AppCompatActivity {
    String woorden[] = new String[]{"Aardappel", "Banaan", "Pannenkoek","Haardvuur","Schoolboeken","Computermuis","Pingpongtafel","Vaatwasser","Televisietoestel","Polshorloge"};
    String GekozenWoord = kiesWoord(woorden);
    TextView OplossingTextVieuw;
    EditText IngaveEditText;
    Button RaadButton;
    Button VolgendeButton;
    TextView ControleTextVieuw;
    Button OpnieuwButton;

    private String kiesWoord(String woorden[]){
        Random rn = new Random();
        int RandomCijfer = rn.nextInt(woorden.length);
        String GekozenWoord = woorden[RandomCijfer];
        return  GekozenWoord;
    }

    private String setLettersDoorElkaar(String GekozenWoord){
        Random rn = new Random();
        String DoorElkaarWoord="";
        int AantalLetters = GekozenWoord.length();
        int NogLettersOver = GekozenWoord.length();
        for(int i= 0;i<= AantalLetters-1;i++) {
           int RandomCijfer= rn.nextInt(NogLettersOver);
            DoorElkaarWoord += GekozenWoord.substring(RandomCijfer, RandomCijfer+1);
            GekozenWoord = GekozenWoord.substring(0,RandomCijfer) + GekozenWoord.substring(RandomCijfer+1);
            NogLettersOver -= 1;
        }
        return DoorElkaarWoord;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        OplossingTextVieuw = (TextView) findViewById(R.id.LettersLabel);
        IngaveEditText = (EditText) findViewById(R.id.IngaveTextbox);
        RaadButton = (Button) findViewById(R.id.RadenButton);
        RaadButton.setOnClickListener(new RaadButtonClick());
        VolgendeButton = (Button) findViewById(R.id.volgendeButton);
        VolgendeButton.setOnClickListener(new VolgendeButtonClick());
        ControleTextVieuw = (TextView) findViewById(R.id.ControletextView);
        OpnieuwButton = (Button) findViewById(R.id.OpnieuwButton) ;
        OpnieuwButton.setOnClickListener(new OpnieuwButtonClick());
        OplossingTextVieuw.setText(setLettersDoorElkaar(GekozenWoord));
    }

    class RaadButtonClick implements View.OnClickListener {
        public void onClick(View view){
            if (Objects.equals(IngaveEditText.getText().toString().toLowerCase(), GekozenWoord.toLowerCase())) {
                OplossingTextVieuw.setText(GekozenWoord);
                RaadButton.setVisibility(View.INVISIBLE);
                VolgendeButton.setVisibility(View.VISIBLE);
                ControleTextVieuw.setText("Correct");
                ControleTextVieuw.setTextColor(Color.GREEN);
            } else {
                ControleTextVieuw.setText("Niet Correct");
                ControleTextVieuw.setTextColor(Color.RED);
                OpnieuwButton.setVisibility(View.VISIBLE);
                RaadButton.setVisibility(View.INVISIBLE);
            }
            IngaveEditText.setText("");
        }
    }

    class VolgendeButtonClick implements View.OnClickListener {
        public void onClick(View view){
            GekozenWoord = kiesWoord(woorden);
            OplossingTextVieuw.setText(setLettersDoorElkaar(GekozenWoord));
            VolgendeButton.setVisibility(View.INVISIBLE);
            RaadButton.setVisibility(View.VISIBLE);
            IngaveEditText.setText("");
            ControleTextVieuw.setText("");
        }
    }

    class OpnieuwButtonClick implements View.OnClickListener {
        public void onClick(View view){
            ControleTextVieuw.setText("");
            OpnieuwButton.setVisibility(View.INVISIBLE);
            RaadButton.setVisibility(View.VISIBLE);
            IngaveEditText.setText("");
        }

    }
    }


