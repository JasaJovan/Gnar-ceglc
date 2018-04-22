package com.jasa.jovan.gnar_ceglc;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jasa.jovan.gnar_ceglc.baza.FeedReaderContract;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Prihodki {

    public static String datum = dobiDanasnjiDatum();

    private static String dobiDanasnjiDatum(){
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        return date;
    }

    private static String dobiTrenutniMesec(){
        Calendar calendar = Calendar.getInstance();
        String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        return month;
    }

    public static void vnesiPrihodke(int kolicina){
        SQLiteDatabase db = MainActivity.getInstance().getPomocnik().getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntryPrihodki.COLUMN_NAME_KOLICINA, kolicina);
        values.put(FeedReaderContract.FeedEntryPrihodki.COLUMN_NAME_DATUM, datum);
        values.put(FeedReaderContract.FeedEntryPrihodki.COLUMN_NAME_MESEC, dobiTrenutniMesec());

        db.insert(FeedReaderContract.FeedEntryPrihodki.TABLE_NAME, null, values);
    }

    public static int dobiPrihodekDanes(){
        SQLiteDatabase db = MainActivity.getInstance().getPomocnik().getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + FeedReaderContract.FeedEntryPrihodki.TABLE_NAME + " WHERE " + FeedReaderContract.FeedEntryPrihodki.COLUMN_NAME_DATUM + " = '" + dobiDanasnjiDatum() + "'", null);
        int prihodki = 0;
        while(cursor.moveToNext()){
            prihodki += cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntryPrihodki.COLUMN_NAME_KOLICINA));
        }

        return prihodki;
    }

    public static int dobiMesecPrihodke() {
        SQLiteDatabase db = MainActivity.getInstance().getPomocnik().getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + FeedReaderContract.FeedEntryPrihodki.TABLE_NAME + " WHERE " + FeedReaderContract.FeedEntryPrihodki.COLUMN_NAME_MESEC + " = '" + dobiTrenutniMesec() + "'", null);
        int prihodki = 0;
        while(cursor.moveToNext()){
            prihodki += cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntryPrihodki.COLUMN_NAME_KOLICINA));
        }

        return prihodki;
    }
}
