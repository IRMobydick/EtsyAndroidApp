package com.etsy.android.lib.logger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.etsy.android.lib.core.EtsyApplication;

public class AnalyticsLogDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_SQL = "CREATE TABLE IF NOT EXISTS analytics_log (_id integer primary key autoincrement, log text not null);";
    private static final String DATABASE_NAME = "analytics_logs.db";
    private static final int DATABASE_VERSION = 1;
    public static final String ID = "_id";
    public static final String LOG = "log";
    public static final String TABLE = "analytics_log";
    private static AnalyticsLogDatabaseHelper mInstance;

    static {
        mInstance = null;
    }

    @NonNull
    public static AnalyticsLogDatabaseHelper getInstance() {
        if (mInstance == null) {
            mInstance = new AnalyticsLogDatabaseHelper(EtsyApplication.get(), null, null, DATABASE_VERSION);
        }
        return mInstance;
    }

    public AnalyticsLogDatabaseHelper(Context context, String str, CursorFactory cursorFactory, int i) {
        super(context, DATABASE_NAME, cursorFactory, DATABASE_VERSION);
    }

    public void onCreate(@NonNull SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(CREATE_SQL);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    @VisibleForTesting
    public static void setInstance(AnalyticsLogDatabaseHelper analyticsLogDatabaseHelper) {
        mInstance = analyticsLogDatabaseHelper;
    }

    @VisibleForTesting
    public static void resetInstance() {
        mInstance = null;
    }
}
