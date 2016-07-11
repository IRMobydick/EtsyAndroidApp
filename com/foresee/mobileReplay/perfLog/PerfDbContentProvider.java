package com.foresee.mobileReplay.perfLog;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;
import com.google.inject.Inject;
import fs.org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import roboguice.content.RoboContentProvider;

public class PerfDbContentProvider extends RoboContentProvider {
    private static final String AUTHORITY = "com.foresee.mobileReplay";
    private static final String BASE_PATH = "perf";
    public static final String COL_2ND_QUARTILE = "secondQuartile";
    public static final String COL_3RD_QUARTILE = "thirdQuartile";
    public static final String COL_MAX = "max";
    public static final String COL_MEAN = "mean";
    public static final String COL_MIN = "min";
    public static final String COL_N = "n";
    public static final Uri CONTENT_URI;
    private static final int OPERATIONS = 10;
    public static final String OPERATION_TYPE_PATH = "operations";
    private static final int OP_TYPES = 30;
    public static final Uri OP_TYPES_URI;
    private static final String PATH_STATS = "stats";
    private static final int STATS = 20;
    public static final String STATS_PATH = "stats";
    public static final Uri STATS_URI;
    private static final UriMatcher matcher;
    private SQLiteDatabase database;
    @Inject
    private PerfDb perfDb;

    static {
        CONTENT_URI = Uri.parse(String.format("content://%s/%s", new Object[]{AUTHORITY, BASE_PATH}));
        STATS_URI = CONTENT_URI.buildUpon().appendPath(STATS_PATH).build();
        OP_TYPES_URI = CONTENT_URI.buildUpon().appendPath(OPERATION_TYPE_PATH).build();
        matcher = new UriMatcher(-1);
        matcher.addURI(AUTHORITY, String.format("%s/%s", new Object[]{BASE_PATH, STATS_PATH}), STATS);
        matcher.addURI(AUTHORITY, String.format("%s/%s", new Object[]{BASE_PATH, OPERATION_TYPE_PATH}), OP_TYPES);
        matcher.addURI(AUTHORITY, BASE_PATH, OPERATIONS);
    }

    public boolean onCreate() {
        super.onCreate();
        this.database = this.perfDb.getReadableDatabase();
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        int match = matcher.match(uri);
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        switch (match) {
            case OPERATIONS /*10*/:
                sQLiteQueryBuilder.setTables(PerfDb.TABLE_OPS);
                break;
            case STATS /*20*/:
                return computeOperationStats(this.database, str, strArr2);
            case OP_TYPES /*30*/:
                return queryOperationTypes(this.database);
            default:
                Log.d("FORESEE_PERF_LOG", uri.toString());
                break;
        }
        Cursor query = sQLiteQueryBuilder.query(this.database, strArr, str, strArr2, null, null, str2);
        query.setNotificationUri(getContext().getContentResolver(), uri);
        return query;
    }

    public String getType(Uri uri) {
        throw new RuntimeException("not implemented");
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new RuntimeException("not implemented");
    }

    public int delete(Uri uri, String str, String[] strArr) {
        throw new RuntimeException("not implemented");
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new RuntimeException("not implemented");
    }

    private Cursor queryOperationTypes(SQLiteDatabase sQLiteDatabase) {
        return sQLiteDatabase.query(true, PerfDb.TABLE_OPS, new String[]{PerfDb.COL_OP_TYPE}, null, null, null, null, null, null);
    }

    private Cursor computeOperationStats(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables(PerfDb.TABLE_OPS);
        Cursor query = sQLiteQueryBuilder.query(sQLiteDatabase, new String[]{PerfDb.COL_OP_TYPE, PerfDb.COL_DURATION}, str, strArr, null, null, null);
        Cursor matrixCursor = new MatrixCursor(new String[]{PerfDb.COL_ID, PerfDb.COL_OP_TYPE, COL_N, COL_MEAN, COL_MAX, COL_MIN, COL_2ND_QUARTILE, COL_3RD_QUARTILE});
        Map hashMap = new HashMap();
        while (query.moveToNext()) {
            String string = query.getString(0);
            int i = query.getInt(1);
            if (hashMap.get(string) == null) {
                hashMap.put(string, new ArrayList());
            }
            ((List) hashMap.get(string)).add(Integer.valueOf(i));
        }
        int i2 = 0;
        for (Entry entry : hashMap.entrySet()) {
            DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics();
            for (Integer intValue : (List) entry.getValue()) {
                descriptiveStatistics.addValue((double) intValue.intValue());
            }
            matrixCursor.addRow(new Object[]{Integer.valueOf(i2), entry.getKey(), Long.valueOf(descriptiveStatistics.getN()), Double.valueOf(descriptiveStatistics.getMean()), Double.valueOf(descriptiveStatistics.getMax()), Double.valueOf(descriptiveStatistics.getMin()), Double.valueOf(descriptiveStatistics.getPercentile(50.0d)), Double.valueOf(descriptiveStatistics.getPercentile(75.0d))});
            i2++;
        }
        return matrixCursor;
    }
}
