package com.jasa.jovan.gnar_ceglc;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.jasa.jovan.gnar_ceglc.baza.FeedReaderContract;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Prihodki {

    private static String dobiDanasnjiDatum(){
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        return date;
    }

    public static void vnesiPrihodke(int kolicina){
        SQLiteDatabase db = MainActivity.getInstance().getPomocnik().getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntryPrihodki.COLUMN_NAME_KOLICINA, kolicina);
        values.put(FeedReaderContract.FeedEntryPrihodki.COLUMN_NAME_DATUM, dobiDanasnjiDatum());

        db.insert(FeedReaderContract.FeedEntryPrihodki.TABLE_NAME, null, values);
    }

}
