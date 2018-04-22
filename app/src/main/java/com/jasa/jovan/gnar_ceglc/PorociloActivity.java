package com.jasa.jovan.gnar_ceglc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class PorociloActivity extends AppCompatActivity {

    private TextView pokaziDnevniStrosek;
    private TextView pokaziDnevniPrihodek;
    private TextView pokaziMesecniStrosek;
    private TextView pokaziMesecniPrihodek;

    private PieChart chartStroski;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porocilo);

        nastaviKomponente();
        pokaziDnevneStroske();
        pokaziDnevnePrihodke();
        pokaziMesecStroske();
        pokaziMesecPrihodek();
        pokaziGrafStroskov();
    }

    private void nastaviKomponente(){
        pokaziDnevniStrosek = findViewById(R.id.text_dnevniStroski);
        pokaziDnevniPrihodek = findViewById(R.id.text_dnevniPrihodki);
        pokaziMesecniStrosek = findViewById(R.id.text_stroskiMesec);
        pokaziMesecniPrihodek = findViewById(R.id.text_prihodkiMesec);
        chartStroski = findViewById(R.id.graf_stroski);
    }

    private void pokaziDnevneStroske(){
        int strosek = Stroski.dobiStroskeDanes();
        pokaziDnevniStrosek.setText("Danasnji stroski: " + strosek);
    }

    private void pokaziDnevnePrihodke(){
        int prihodek = Prihodki.dobiPrihodekDanes();
        pokaziDnevniPrihodek.setText("Danasnji prihodek: " + prihodek);
    }

    private void pokaziMesecStroske(){
        int strosek = Stroski.dobiMesecStroske();
        pokaziMesecniStrosek.setText("Mesecni strosek: " + strosek);
    }

    private void pokaziMesecPrihodek(){
        int prihodek = Prihodki.dobiMesecPrihodke();
        pokaziMesecniPrihodek.setText("Mesecni prihodek: " + prihodek);
    }

    private void pokaziGrafStroskov(){
        List<PieEntry> entries = new ArrayList<>();
        ArrayList<String> stroski = Stroski.dobiTipeStroskov();

        int i = 0;
        for(String tipStroska : stroski){
            entries.add(new PieEntry(Stroski.dobiStroskeGledeNaTip(tipStroska), stroski.get(i)));
            i++;
        }

        PieDataSet set = new PieDataSet(entries, "Stroski za " + Stroski.dobiTrenutniMesec());
        set.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(set);

        Legend legend = chartStroski.getLegend();
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART_INSIDE);
        chartStroski.setData(data);
        chartStroski.invalidate();
    }

}
