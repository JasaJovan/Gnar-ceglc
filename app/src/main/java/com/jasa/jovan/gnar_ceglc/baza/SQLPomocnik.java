package com.jasa.jovan.gnar_ceglc.baza;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLPomocnik extends SQLiteOpenHelper{

    private static final String SQL_CREATE_STAVEK_STROSKI =
            "CREATE TABLE " + FeedReaderContract.FeedEntryStroski.TABLE_NAME + " ("
            + FeedReaderContract.FeedEntryStroski._ID + " INTEGER PRIMARY KEY,"
            + FeedReaderContract.FeedEntryStroski.COLUMN_NAME_TIP_STROSKOV + " TEXT,"
            + FeedReaderContract.FeedEntryStroski.COLUMN_NAME_KOLICINA + " INT,"
            + FeedReaderContract.FeedEntryStroski.COLUMN_NAME_DATUM + " TEXT)";

    private static final String SQL_DELETE_ENTRIES_STROSKI =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntryStroski.TABLE_NAME;





    private static final String SQL_CREATE_STAVEK_PRIHODKI =
            "CREATE TABLE " + FeedReaderContract.FeedEntryPrihodki.TABLE_NAME + " ("
                    + FeedReaderContract.FeedEntryPrihodki._ID + " INTEGER PRIMARY KEY,"
                    + FeedReaderContract.FeedEntryPrihodki.COLUMN_NAME_KOLICINA + " INT,"
                    + FeedReaderContract.FeedEntryPrihodki.COLUMN_NAME_DATUM + " TEXT)";

    private static final String SQL_DELETE_ENTRIES_PRIHODKI =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntryPrihodki.TABLE_NAME;





    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "finance.db";

    public SQLPomocnik(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_STAVEK_STROSKI);
        db.execSQL(SQL_CREATE_STAVEK_PRIHODKI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES_STROSKI);
        db.execSQL(SQL_DELETE_ENTRIES_PRIHODKI);
        onCreate(db);
    }
}
