package com.jasa.jovan.gnar_ceglc;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jasa.jovan.gnar_ceglc.baza.FeedReaderContract;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Stroski {

    public static String datum = dobiDanasnjiDatum();

    public static String dobiDanasnjiDatum(){
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        return date;
    }

    public static String dobiTrenutniMesec(){
        Calendar calendar = Calendar.getInstance();
        String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        return month;
    }

    public static void vnesiStroske(String tipStroskov, int kolicina){
        SQLiteDatabase db = MainActivity.getInstance().getPomocnik().getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntryStroski.COLUMN_NAME_TIP_STROSKOV, tipStroskov);
        values.put(FeedReaderContract.FeedEntryStroski.COLUMN_NAME_KOLICINA, kolicina);
        values.put(FeedReaderContract.FeedEntryStroski.COLUMN_NAME_DATUM, datum);
        values.put(FeedReaderContract.FeedEntryStroski.COLUMN_NAME_MESEC, dobiTrenutniMesec());

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

    public static int dobiStroskeDanes() {
        SQLiteDatabase db = MainActivity.getInstance().getPomocnik().getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + FeedReaderContract.FeedEntryStroski.TABLE_NAME + " WHERE " + FeedReaderContract.FeedEntryStroski.COLUMN_NAME_DATUM + " = '" + dobiDanasnjiDatum() + "'", null);
        int stroski = 0;
        while(cursor.moveToNext()){
            stroski += cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntryStroski.COLUMN_NAME_KOLICINA));
        }

        return stroski;
    }

    public static int dobiMesecStroske() {
        SQLiteDatabase db = MainActivity.getInstance().getPomocnik().getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + FeedReaderContract.FeedEntryStroski.TABLE_NAME + " WHERE " + FeedReaderContract.FeedEntryStroski.COLUMN_NAME_MESEC + " = '" + dobiTrenutniMesec() + "'", null);
        int stroski = 0;
        while(cursor.moveToNext()){
            stroski += cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntryStroski.COLUMN_NAME_KOLICINA));
        }

        return stroski;
    }

    public static int dobiStroskeGledeNaTip(String tip){
        SQLiteDatabase db = MainActivity.getInstance().getPomocnik().getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + FeedReaderContract.FeedEntryStroski.TABLE_NAME + " WHERE " + FeedReaderContract.FeedEntryStroski.COLUMN_NAME_MESEC + " = '" + dobiTrenutniMesec() + "' AND " + FeedReaderContract.FeedEntryStroski.COLUMN_NAME_TIP_STROSKOV + " = '" + tip + "'", null);
        int stroski = 0;
        while(cursor.moveToNext()){
            stroski += cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntryStroski.COLUMN_NAME_KOLICINA));
        }

        return stroski;
    }
}
