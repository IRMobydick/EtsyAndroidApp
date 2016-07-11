package com.foresee.mobileReplay.perfLog;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.google.inject.Inject;

public class PerfDb extends SQLiteOpenHelper {
    public static final String COL_DURATION = "DURATION";
    public static final String COL_ID = "_id";
    public static final String COL_OP_TYPE = "TYPE";
    public static final String COL_SESSION_ID = "SESSION_ID";
    private static final String CREATE_OPS_TABLE;
    private static final String DATABASE_NAME = "perf.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_OPS = "TIMED_OPS";

    static {
        Object[] objArr = new Object[DATABASE_VERSION];
        objArr[0] = TABLE_OPS;
        CREATE_OPS_TABLE = String.format("create table %s (_id INTEGER PRIMARY KEY AUTOINCREMENT,SESSION_ID TEXT, TYPE TEXT, DURATION INTEGER, CREATED_AT INTEGER)", objArr);
    }

    @Inject
    public PerfDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(CREATE_OPS_TABLE);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        throw new RuntimeException("not implemented");
    }
}
