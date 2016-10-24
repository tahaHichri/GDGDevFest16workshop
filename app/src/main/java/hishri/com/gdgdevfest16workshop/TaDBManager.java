package hishri.com.gdgdevfest16workshop;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by taha on 24/10/16.
 * awesome code \m/
 */

public class TaDBManager extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.



    public TaDBManager(Context context) {
        super(context, FeedEntry.DATABASE_NAME, null, FeedEntry.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FeedEntry.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "testDB";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";

        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "GDGDevFest.db";


        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ",";
        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                        FeedEntry._ID + " INTEGER PRIMARY KEY," +
                        FeedEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                        FeedEntry.COLUMN_NAME_SUBTITLE + TEXT_TYPE + " )";
    }
}
