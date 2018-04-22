package com.jasa.jovan.gnar_ceglc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PorociloActivity extends AppCompatActivity {

    private TextView pokaziDnevniStrosek;
    private TextView pokaziDnevniPrihodek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porocilo);

        nastaviKomponente();
        pokaziDnevneStroske();
        pokaziDnevnePrihodke();
    }

    private void nastaviKomponente(){
        pokaziDnevniStrosek = findViewById(R.id.text_dnevniStroski);
        pokaziDnevniPrihodek = findViewById(R.id.text_dnevniPrihodki);
    }

    private void pokaziDnevneStroske(){
        int strosek = Stroski.dobiStroskeDanes();
        pokaziDnevniStrosek.setText("Danasnji stroski: " + strosek);
    }

    private void pokaziDnevnePrihodke(){
        int prihodek = Prihodki.dobiPrihodekDanes();
        pokaziDnevniPrihodek.setText("Danasnji prihodek: " + prihodek);
    }

}
