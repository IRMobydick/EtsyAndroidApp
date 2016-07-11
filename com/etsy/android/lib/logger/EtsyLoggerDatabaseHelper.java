package com.etsy.android.lib.logger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class EtsyLoggerDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_SQL = "CREATE TABLE IF NOT EXISTS analytics_log (_id integer primary key autoincrement, log text not null);";
    private static final String DATABASE_NAME = "analytics_log.db";
    private static final int DATABASE_VERSION = 1;
    public static final String ID = "_id";
    public static final String LOG = "log";
    public static final String TABLE = "analytics_log";
    private static EtsyLoggerDatabaseHelper mInstance;

    static {
        mInstance = null;
    }

    public static EtsyLoggerDatabaseHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new EtsyLoggerDatabaseHelper(context.getApplicationContext(), null, null, DATABASE_VERSION);
        }
        return mInstance;
    }

    public EtsyLoggerDatabaseHelper(Context context, String str, CursorFactory cursorFactory, int i) {
        super(context, DATABASE_NAME, cursorFactory, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(CREATE_SQL);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public static void setInstance(EtsyLoggerDatabaseHelper etsyLoggerDatabaseHelper) {
        mInstance = etsyLoggerDatabaseHelper;
    }

    public static void resetInstance() {
        mInstance = null;
    }
}
