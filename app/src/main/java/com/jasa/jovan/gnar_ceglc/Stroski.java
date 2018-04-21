package com.jasa.jovan.gnar_ceglc;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jasa.jovan.gnar_ceglc.baza.FeedReaderContract;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Stroski {

    private static String dobiDanasnjiDatum(){
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        return date;
    }

    public static void vnesiStroske(String tipStroskov, int kolicina){
        SQLiteDatabase db = MainActivity.getInstance().getPomocnik().getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntryStroski.COLUMN_NAME_TIP_STROSKOV, tipStroskov);
        values.put(FeedReaderContract.FeedEntryStroski.COLUMN_NAME_KOLICINA, kolicina);
        values.put(FeedReaderContract.FeedEntryStroski.COLUMN_NAME_DATUM, dobiDanasnjiDatum());

        db.insert(FeedReaderContract.FeedEntryStroski.TABLE_NAME, null, values);
    }

    public static ArrayList<String> dobiTipeStroskov(){
        ArrayList<String> stroski = new ArrayList<>();
        SQLiteDatabase db = MainActivity.getInstance().getPomocnik().getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT DISTINCT " + FeedReaderContract.FeedEntryStroski.COLUMN_NAME_TIP_STROSKOV + " FROM " + FeedReaderContract.FeedEntryStroski.TABLE_NAME, null);
        while(cursor.moveToNext()){
            String tipStroska = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntryStroski.COLUMN_NAME_TIP_STROSKOV));
            if(tipStroska != "") {
                stroski.add(tipStroska);
            }
        }

        return stroski;
    }

}