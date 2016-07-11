package com.foresee.mobileReplay.perfLog;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.foresee.mobileReplay.services.PerfLogService;
import com.google.inject.Inject;

public class PerfDataSqliteRepo implements PerfDataRepository {
    private SQLiteDatabase database;
    private PerfDb perfDb;

    @Inject
    public PerfDataSqliteRepo(Context context) {
        this.perfDb = new PerfDb(context);
    }

    public void open() {
        this.database = this.perfDb.getWritableDatabase();
    }

    public void close() {
        this.database.close();
    }

    public void insertOperation(String str, String str2, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PerfDb.COL_OP_TYPE, str2);
        contentValues.put(PerfDb.COL_DURATION, Long.valueOf(j));
        contentValues.put(PerfLogService.EXTRA_SESSION_ID, str);
        this.database.insert(PerfDb.TABLE_OPS, null, contentValues);
    }
}
