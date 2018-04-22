package com.jasa.jovan.gnar_ceglc;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);

        return dialog;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String mesec;
        String dan;
        if(month < 10){
            mesec = "0"+(month+1);
        }
        else{
            mesec = (month+1) + "";
        }

        if(dayOfMonth < 10){
            dan = "0" + dayOfMonth;
        }
        else{
            dan = dayOfMonth + "";
        }

        Stroski.datum = year + "-" + mesec + "-" + dan;
        Prihodki.datum = year + "-" + mesec + "-" + dan;
    }
}
