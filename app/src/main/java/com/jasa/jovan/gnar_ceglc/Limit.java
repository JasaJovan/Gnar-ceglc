package com.jasa.jovan.gnar_ceglc;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jasa.jovan.gnar_ceglc.baza.FeedReaderContract;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Limit {

    private static String dobiDanasnjiDatum(){
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        return date;
    }

    private static int dobiSteviloDniVMesecu(){
        Calendar c = Calendar.getInstance();
        int maxDni = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        return maxDni;
    }

    public static void vnesiLimit(int limit){
        SQLiteDatabase db = MainActivity.getInstance().getPomocnik().getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntryLimit.COLUMN_NAME_LIMIT, limit);
        values.put(FeedReaderContract.FeedEntryLimit.COLUMN_NAME_DATUM, dobiDanasnjiDatum());

        db.insert(FeedReaderContract.FeedEntryLimit.TABLE_NAME, null, values);
    }

    public static int dobiLimitDanes(){
        SQLiteDatabase db = MainActivity.getInstance().getPomocnik().getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT " + FeedReaderContract.FeedEntryLimit.COLUMN_NAME_LIMIT + " FROM " + FeedReaderContract.FeedEntryLimit.TABLE_NAME + " WHERE " + FeedReaderContract.FeedEntryLimit.COLUMN_NAME_DATUM + " = '" + dobiDanasnjiDatum() + "' ORDER BY " + FeedReaderContract.FeedEntryLimit._ID + " DESC LIMIT 1", null);
        int limit = 0;
        while(cursor.moveToNext()){
            limit = cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntryLimit.COLUMN_NAME_LIMIT));
        }

        return limit;
    }

    public static int izracunajLimit(int prihranek){
        int limit = prihranek/dobiSteviloDniVMesecu();
        return limit;
    }

}
