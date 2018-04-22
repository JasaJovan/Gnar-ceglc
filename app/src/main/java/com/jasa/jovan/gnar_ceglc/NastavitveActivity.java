package com.jasa.jovan.gnar_ceglc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NastavitveActivity extends AppCompatActivity {

    private TextView pokaziLimit;

    private EditText vnosPrihranek;
    private EditText vnosLimita;

    private Button gumbIzracunLimita;
    private Button gumbVnesiLimit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nastavitve);
        nastaviKomponente();
        listenerji();
    }

    private void nastaviKomponente(){
        pokaziLimit = findViewById(R.id.text_limit);
        vnosPrihranek = findViewById(R.id.vnos_prihranek);
        gumbIzracunLimita = findViewById(R.id.gumb_izracunLimita);
        vnosLimita = findViewById(R.id.vnos_limit);
        gumbVnesiLimit = findViewById(R.id.gumb_vnesiLimit);
    }

    private void listenerji(){
        gumbIzracunLimita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Izracun limita
                if(vnosPrihranek.length() != 0){
                    int prihranek = Integer.parseInt(vnosPrihranek.getText().toString());
                    int limit = Limit.izracunajLimit(prihranek);
                    Limit.vnesiLimit(limit); // Vnese za danes limit
                    pokaziLimit.setText("Limit: " + Limit.dobiLimitDanes());
                    vnosPrihranek.setText("");
                }
            }
        });

        gumbVnesiLimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Nastavi limit
                if(vnosLimita.length() != 0){
                    int limit = Integer.parseInt(vnosLimita.getText().toString());
                    Limit.vnesiLimit(limit); // Vnese za danes limit
                    pokaziLimit.setText("Limit: " + Limit.dobiLimitDanes());
                    vnosLimita.setText("");
                }
            }
        });

    }

}
