package com.etsy.android.contentproviders;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.SearchRecentSuggestionsProvider;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.ArrayList;
import java.util.Arrays;

public class EtsyProvider extends SearchRecentSuggestionsProvider {
    public static final String AUTHORITY = "com.etsy.android.contentproviders.EtsyProvider";
    private static final Uri BASE_CONTENT_URI;
    public static final int MODE = 1;
    private static final String MULTIPLE_RECORDS_MIME_TYPE = "vnd.android.cursor.dir/vnd.etsy.android.contentproviders.EtsyProvider.item";
    private static final String SINGLE_RECORD_MIME_TYPE = "vnd.android.cursor.item/vnd.etsy.android.contentproviders.EtsyProvider.item";
    private static final String TAG;
    private static final UriMatcher sUriMatcher;
    private EtsyDatabase mDb;

    static {
        TAG = EtsyDebug.m1891a(EtsyProvider.class);
        BASE_CONTENT_URI = Uri.parse("content://com.etsy.android.contentproviders.EtsyProvider");
        sUriMatcher = buildUriMatcher();
    }

    public boolean onCreate() {
        setupSuggestions(AUTHORITY, MODE);
        super.onCreate();
        this.mDb = new EtsyDatabase(getContext());
        return true;
    }

    private static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(-1);
        uriMatcher.addURI(AUTHORITY, "history/listing", MODE);
        uriMatcher.addURI(AUTHORITY, "history/local_market", 10);
        uriMatcher.addURI(AUTHORITY, "search_suggest_query", 2);
        uriMatcher.addURI(AUTHORITY, ResponseConstants.SUGGESTIONS, 2);
        uriMatcher.addURI(AUTHORITY, ActivityFeedEntity.LISTING, 3);
        uriMatcher.addURI(AUTHORITY, "listing/*", 3);
        uriMatcher.addURI(AUTHORITY, "activity", 4);
        uriMatcher.addURI(AUTHORITY, ActivityFeedEntity.SHOP, 5);
        uriMatcher.addURI(AUTHORITY, "shop/user/*", 5);
        uriMatcher.addURI(AUTHORITY, ActivityFeedEntity.USER, 6);
        uriMatcher.addURI(AUTHORITY, ActivityFeedEntity.TREASURY, 7);
        uriMatcher.addURI(AUTHORITY, "segment", 8);
        uriMatcher.addURI(AUTHORITY, "activity_feed", 9);
        return uriMatcher;
    }

    private static int getMatch(Uri uri) {
        return sUriMatcher.match(uri);
    }

    public String getType(Uri uri) {
        switch (getMatch(uri)) {
            case MODE /*1*/:
            case Task.NETWORK_STATE_ANY /*2*/:
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
            case CommonStatusCodes.SERVICE_INVALID /*9*/:
            case CommonStatusCodes.DEVELOPER_ERROR /*10*/:
                return MULTIPLE_RECORDS_MIME_TYPE;
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
            case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
            case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
            case CommonStatusCodes.NETWORK_ERROR /*7*/:
                return SINGLE_RECORD_MIME_TYPE;
            default:
                return MULTIPLE_RECORDS_MIME_TYPE;
        }
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        Throwable e;
        EtsyDebug.m1912c(TAG, "query - uri:" + uri + " projection:" + Arrays.toString(strArr));
        int match = getMatch(uri);
        SQLiteDatabase readableDatabase = this.mDb.getReadableDatabase();
        if (readableDatabase == null) {
            return null;
        }
        readableDatabase.beginTransaction();
        Cursor query;
        try {
            SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
            switch (match) {
                case MODE /*1*/:
                    sQLiteQueryBuilder.setTables("history INNER JOIN listing ON history.listing_id = listing.listing_id");
                    query = sQLiteQueryBuilder.query(readableDatabase, strArr, str, strArr2, null, null, str2);
                    break;
                case Task.NETWORK_STATE_ANY /*2*/:
                    if (strArr2 != null && strArr2.length > 0 && !TextUtils.isEmpty(strArr2[0])) {
                        query = SearchSuggestionCursorProvider.m760a(getContext(), strArr2[0]);
                        break;
                    }
                    query = super.query(uri, strArr, str, strArr2, str2);
                    break;
                case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                    sQLiteQueryBuilder.setTables("activity LEFT  JOIN listing ON activity.listing_id = listing.listing_id LEFT  JOIN shop ON activity.shop_id = shop.shop_id LEFT  JOIN user ON activity.user_id = user.user_id LEFT  JOIN treasury ON activity.treasury_id = treasury.treasury_id");
                    query = sQLiteQueryBuilder.query(readableDatabase, strArr, str, strArr2, null, null, str2);
                    break;
                case CommonStatusCodes.INTERNAL_ERROR /*8*/:
                    sQLiteQueryBuilder.setTables("segment");
                    query = sQLiteQueryBuilder.query(readableDatabase, strArr, str, strArr2, null, null, str2);
                    break;
                case CommonStatusCodes.SERVICE_INVALID /*9*/:
                    sQLiteQueryBuilder.setTables("activity_feed LEFT  JOIN listing ON  (activity_feed.object_id = listing.listing_id AND activity_feed.object_type = \"listing\")  LEFT  JOIN shop ON  (activity_feed.object_id = shop.shop_id AND activity_feed.object_type = \"shop\")  LEFT  JOIN user ON  (activity_feed.object_id = user.user_id AND activity_feed.object_type = \"user\")  LEFT  JOIN treasury ON  (activity_feed.object_id = treasury.treasury_id AND activity_feed.object_type = \"treasury\")");
                    query = sQLiteQueryBuilder.query(readableDatabase, strArr, str, strArr2, null, null, str2);
                    break;
                case CommonStatusCodes.DEVELOPER_ERROR /*10*/:
                    sQLiteQueryBuilder.setTables("local_market_history");
                    query = sQLiteQueryBuilder.query(readableDatabase, strArr, str, strArr2, null, null, str2);
                    break;
                default:
                    EtsyDebug.m1916d(TAG, "query() - UNKNOWN MATCH FOR URI" + uri);
                    query = null;
                    break;
            }
            if (query != null) {
                try {
                    query.setNotificationUri(getContext().getContentResolver(), uri);
                } catch (Exception e2) {
                    e = e2;
                    try {
                        EtsyDebug.m1917d(TAG, "query error", e);
                        return query;
                    } finally {
                        readableDatabase.endTransaction();
                    }
                }
            }
            readableDatabase.setTransactionSuccessful();
            readableDatabase.endTransaction();
            return query;
        } catch (Throwable e3) {
            e = e3;
            query = null;
            EtsyDebug.m1917d(TAG, "query error", e);
            return query;
        }
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        EtsyDebug.m1912c(TAG, "insert - uri:" + uri);
        int match = getMatch(uri);
        SQLiteDatabase writableDatabase = this.mDb.getWritableDatabase();
        if (writableDatabase != null) {
            switch (match) {
                case MODE /*1*/:
                    uri = Uri.withAppendedPath(EtsyProvider.f1097a, String.valueOf(writableDatabase.insertWithOnConflict("history", null, contentValues, 5)));
                    break;
                case Task.NETWORK_STATE_ANY /*2*/:
                    super.insert(uri, contentValues);
                    break;
                case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                    uri = Uri.withAppendedPath(EtsyProvider.f1096a, String.valueOf(writableDatabase.insertWithOnConflict(ActivityFeedEntity.LISTING, null, contentValues, 5)));
                    break;
                case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                    writableDatabase.insertWithOnConflict("activity", null, contentValues, 5);
                    break;
                case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                    uri = Uri.withAppendedPath(EtsyProvider.f1100a, String.valueOf(writableDatabase.insertWithOnConflict(ActivityFeedEntity.SHOP, null, contentValues, 5)));
                    break;
                case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                    uri = Uri.withAppendedPath(EtsyProvider.f1102a, String.valueOf(writableDatabase.insertWithOnConflict(ActivityFeedEntity.USER, null, contentValues, 5)));
                    break;
                case CommonStatusCodes.NETWORK_ERROR /*7*/:
                    uri = Uri.withAppendedPath(EtsyProvider.f1101a, String.valueOf(writableDatabase.insertWithOnConflict(ActivityFeedEntity.TREASURY, null, contentValues, 5)));
                    break;
                case CommonStatusCodes.INTERNAL_ERROR /*8*/:
                    writableDatabase.insertWithOnConflict("segment", null, contentValues, 5);
                    break;
                case CommonStatusCodes.SERVICE_INVALID /*9*/:
                    writableDatabase.insertWithOnConflict("activity_feed", null, contentValues, 5);
                    break;
                case CommonStatusCodes.DEVELOPER_ERROR /*10*/:
                    uri = Uri.withAppendedPath(EtsyProvider.f1098a, String.valueOf(writableDatabase.insertWithOnConflict("local_market_history", null, contentValues, 5)));
                    break;
                default:
                    try {
                        EtsyDebug.m1916d(TAG, "insert() - UNKNOWN MATCH FOR URI" + uri);
                        break;
                    } catch (Throwable e) {
                        EtsyDebug.m1917d(TAG, "insert error", e);
                        break;
                    }
            }
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return uri;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        EtsyDebug.m1912c(TAG, "delete - uri:" + uri);
        int match = getMatch(uri);
        SQLiteDatabase writableDatabase = this.mDb.getWritableDatabase();
        if (writableDatabase == null) {
            return -1;
        }
        int delete;
        switch (match) {
            case MODE /*1*/:
                delete = writableDatabase.delete("history", str, strArr);
                getContext().getContentResolver().notifyChange(uri, null);
                return delete;
            case Task.NETWORK_STATE_ANY /*2*/:
                super.delete(uri, str, strArr);
                return -1;
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                return writableDatabase.delete("activity", str, strArr);
            case CommonStatusCodes.INTERNAL_ERROR /*8*/:
                return writableDatabase.delete("segment", str, strArr);
            case CommonStatusCodes.SERVICE_INVALID /*9*/:
                return writableDatabase.delete("activity_feed", str, strArr);
            case CommonStatusCodes.DEVELOPER_ERROR /*10*/:
                delete = writableDatabase.delete("local_market_history", str, strArr);
                getContext().getContentResolver().notifyChange(uri, null);
                return delete;
            default:
                try {
                    EtsyDebug.m1916d(TAG, "delete() - UNKNOWN MATCH FOR URI" + uri);
                    return -1;
                } catch (Throwable e) {
                    EtsyDebug.m1917d(TAG, "delete error", e);
                    return -1;
                }
        }
        EtsyDebug.m1917d(TAG, "delete error", e);
        return -1;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        EtsyDebug.m1912c(TAG, "update - uri:" + uri);
        int match = getMatch(uri);
        SQLiteDatabase writableDatabase = this.mDb.getWritableDatabase();
        if (writableDatabase == null) {
            return -1;
        }
        int update;
        switch (match) {
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                update = writableDatabase.update(ActivityFeedEntity.LISTING, contentValues, str, strArr);
                getContext().getContentResolver().notifyChange(uri, null);
                return update;
            case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                update = writableDatabase.update(ActivityFeedEntity.SHOP, contentValues, str, strArr);
                getContext().getContentResolver().notifyChange(uri, null);
                return update;
            case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                update = writableDatabase.update(ActivityFeedEntity.USER, contentValues, str, strArr);
                getContext().getContentResolver().notifyChange(uri, null);
                return update;
            case CommonStatusCodes.NETWORK_ERROR /*7*/:
                update = writableDatabase.update(ActivityFeedEntity.TREASURY, contentValues, str, strArr);
                getContext().getContentResolver().notifyChange(uri, null);
                return update;
            default:
                try {
                    EtsyDebug.m1916d(TAG, "update() - UNKNOWN MATCH FOR URI" + uri);
                    return -1;
                } catch (Throwable e) {
                    EtsyDebug.m1917d(TAG, "update error", e);
                    return -1;
                }
        }
        EtsyDebug.m1917d(TAG, "update error", e);
        return -1;
    }

    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> arrayList) {
        ContentProviderResult[] contentProviderResultArr = "applyBatch";
        EtsyDebug.m1912c(TAG, contentProviderResultArr);
        SQLiteDatabase writableDatabase = this.mDb.getWritableDatabase();
        if (writableDatabase != null) {
            writableDatabase.beginTransaction();
            try {
                int size = arrayList.size();
                contentProviderResultArr = new ContentProviderResult[size];
                for (int i = 0; i < size; i += MODE) {
                    contentProviderResultArr[i] = ((ContentProviderOperation) arrayList.get(i)).apply(this, contentProviderResultArr, i);
                }
                writableDatabase.setTransactionSuccessful();
                return contentProviderResultArr;
            } catch (Throwable e) {
                contentProviderResultArr = TAG;
                EtsyDebug.m1917d((String) contentProviderResultArr, "applyBatch error", e);
            } finally {
                writableDatabase.endTransaction();
            }
        }
        return new ContentProviderResult[0];
    }
}
