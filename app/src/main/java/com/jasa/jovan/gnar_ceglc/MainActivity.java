package com.jasa.jovan.gnar_ceglc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.jasa.jovan.gnar_ceglc.baza.SQLPomocnik;

public class MainActivity extends AppCompatActivity {

    private static SQLPomocnik pomocnik;
    private static MainActivity instance;

    // Gumbi
    private Button gumbVnesiStroske;
    private Button gumbVnesiPrihodke;
    private Button gumbNastavitve;
    private Button gumbPorocilo;

    // Vnosi
    private EditText vnosKolicineStroskov;
    private EditText vnosTipStroskov;
    private EditText vnosPrihodkov;

    // Seznam
    private Spinner spustTipStroskov;

    // Pogledi
    private Intent pogledNastavitve;
    private Intent pogledPorocilo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;
        pomocnik = new SQLPomocnik(this);
        nastaviKomponente();
        listenerji();
        nastaviSeznam();
    }

    public static MainActivity getInstance() {
        return instance;
    }

    public SQLPomocnik getPomocnik() {
        return pomocnik;
    }

    private void nastaviKomponente(){
        gumbVnesiStroske = findViewById(R.id.gumb_vnesiStroske);
        gumbVnesiPrihodke = findViewById(R.id.gumb_vnesiPrihodke);
        gumbNastavitve = findViewById(R.id.gumb_nastavitve);
        gumbPorocilo = findViewById(R.id.gumb_porocilo);
        vnosKolicineStroskov = findViewById(R.id.vnos_kolicinaStroskov);
        vnosTipStroskov = findViewById(R.id.vnos_tipStroskov);
        vnosPrihodkov = findViewById(R.id.vnos_prihodkov);
        spustTipStroskov = findViewById(R.id.spust_tipStroskov);
        pogledNastavitve = new Intent(MainActivity.this, NastavitveActivity.class);
        pogledPorocilo = new Intent(MainActivity.this, PorociloActivity.class);
    }

    private void listenerji(){
        // ------------ Vnese stroske -------------- //
        gumbVnesiStroske.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vnosTipStroskov.length() == 0){
                    String tip = spustTipStroskov.getSelectedItem().toString();
                    int kolicina = Integer.parseInt(vnosKolicineStroskov.getText().toString());
                    Stroski.vnesiStroske(tip, kolicina);
                    vnosKolicineStroskov.setText("");
                }
                else{
                    String tip = vnosTipStroskov.getText().toString();
                    int kolicina = Integer.parseInt(vnosKolicineStroskov.getText().toString());
                    Stroski.vnesiStroske(tip, kolicina);
                    vnosKolicineStroskov.setText("");
                    vnosTipStroskov.setText("");
                    nastaviSeznam();
                }
            }
        });

        // ------------ Vnese prihodke -------------- //
        gumbVnesiPrihodke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int kolicina = Integer.parseInt(vnosPrihodkov.getText().toString());
                Prihodki.vnesiPrihodke(kolicina);
                vnosPrihodkov.setText("");
            }
        });

        gumbNastavitve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(pogledNastavitve);
            }
        });

        gumbPorocilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(pogledPorocilo);
            }
        });

    }

    // Posodobi seznam tipa stroskov
    private void nastaviSeznam(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, Stroski.dobiTipeStroskov());
        spustTipStroskov.setAdapter(adapter);
    }

}
