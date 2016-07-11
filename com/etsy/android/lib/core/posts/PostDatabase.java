package com.etsy.android.lib.core.posts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.EtsyLogger;
import com.foresee.mobileReplay.perfLog.PerfDb;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class PostDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "etsy_post_queue";
    private static final int DB_VERSION = 5;
    private static final int DB_VERSION_BECAME_BLOB = 5;
    private static final int DB_VERSION_RUN_AFTER_ADDED = 4;
    private static final String TAG;
    private static PostDatabase mInstance;

    static {
        TAG = EtsyDebug.m1891a(PostDatabase.class);
    }

    public static PostDatabase getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new PostDatabase(context.getApplicationContext());
        }
        return mInstance;
    }

    @VisibleForTesting
    protected static void clearInstance() {
        mInstance = null;
    }

    private PostDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION_BECAME_BLOB);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE  TABLE post (_id integer primary key autoincrement, post_json text, serialized_post blob,version_code integer not null, run_after_time integer);");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        EtsyDebug.m1906b(TAG, "Upgrading database. Existing contents will be lost. [" + i + "]->[" + i2 + "]");
        if (i < DB_VERSION_BECAME_BLOB) {
            EtsyDebug.m1906b(TAG, "Upgrading post database to use blob instead of text. [" + i + "]->[" + i2 + "]");
            migrateToSerializable(sQLiteDatabase);
            if (i < DB_VERSION_RUN_AFTER_ADDED) {
                EtsyDebug.m1906b(TAG, "Upgrading post database to have new columns. [" + i + "]->[" + i2 + "]");
                try {
                    sQLiteDatabase.execSQL("ALTER TABLE post ADD COLUMN run_after_time integer");
                } catch (Throwable e) {
                    EtsyDebug.m1917d(TAG, "Error adding column on run_after_time to DB upgrade to PostTable", e);
                    recreateDatabase(sQLiteDatabase);
                }
            }
        } else if (i != DB_VERSION_BECAME_BLOB) {
            recreateDatabase(sQLiteDatabase);
        }
    }

    private void migrateToSerializable(@NonNull SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE post ADD COLUMN serialized_post blob");
            sQLiteDatabase.beginTransaction();
            Map hashMap = new HashMap();
            try {
                SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
                sQLiteQueryBuilder.setTables("post");
                SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
                Cursor query = sQLiteQueryBuilder.query(sQLiteDatabase2, new String[]{PerfDb.COL_ID, "post_json"}, null, null, null, null, PerfDb.COL_ID);
                if (query != null && query.moveToFirst()) {
                    int i = query.getInt(0);
                    String string = query.getString(1);
                    query.close();
                    hashMap.put(Integer.valueOf(i), EtsyPostManager.m1653a(string));
                } else if (query != null) {
                    query.close();
                }
                sQLiteDatabase.setTransactionSuccessful();
                for (Entry entry : hashMap.entrySet()) {
                    try {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("serialized_post", EtsyPostManager.m1657b((PersistentRequest) entry.getValue()));
                        sQLiteDatabase.update("post", contentValues, "_id = ?", new String[]{String.valueOf(entry.getKey())});
                    } catch (Throwable e) {
                        EtsyDebug.m1917d(TAG, "migrateToSerializable - db error in serialization conversion.", e);
                        EtsyLogger.m1966a().m1986a("EtsyPostManager", "migrateToSerializable - db error in serialization conversion." + e.toString());
                        recreateDatabase(sQLiteDatabase);
                        return;
                    }
                }
            } catch (Throwable e2) {
                EtsyDebug.m1917d(TAG, "migrateToSerializable - db error.", e2);
                EtsyLogger.m1966a().m1986a("EtsyPostManager", "migrateToSerializable - read error." + e2.toString());
                recreateDatabase(sQLiteDatabase);
            } finally {
                sQLiteDatabase.endTransaction();
            }
        } catch (Throwable e22) {
            EtsyDebug.m1917d(TAG, "Error adding blob column to DB upgrade to PostTable", e22);
            recreateDatabase(sQLiteDatabase);
        }
    }

    private void recreateDatabase(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP  TABLE  IF  EXISTS post");
        onCreate(sQLiteDatabase);
    }
}
