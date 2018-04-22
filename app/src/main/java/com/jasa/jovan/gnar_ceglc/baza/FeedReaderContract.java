package com.jasa.jovan.gnar_ceglc.baza;

import android.provider.BaseColumns;

public class FeedReaderContract {

    public FeedReaderContract() {}

    public static class FeedEntryStroski implements BaseColumns{
        public static final String TABLE_NAME = "stroski";
        public static final String COLUMN_NAME_TIP_STROSKOV = "tip_stroskov";
        public static final String COLUMN_NAME_KOLICINA = "kolicina";
        public static final String COLUMN_NAME_DATUM = "datum";
        public static final String COLUMN_NAME_MESEC = "mesec";
    }

    public static class FeedEntryPrihodki implements BaseColumns {
        public static final String TABLE_NAME = "prihodki";
        public static final String COLUMN_NAME_KOLICINA = "kolicina";
        public static final String COLUMN_NAME_DATUM = "datum";
        public static final String COLUMN_NAME_MESEC = "mesec";
    }

    public static class FeedEntryLimit implements BaseColumns{
        public static final String TABLE_NAME = "limiti";
        public static final String COLUMN_NAME_LIMIT = "kolicina";
        public static final String COLUMN_NAME_DATUM = "datum";
    }

}
