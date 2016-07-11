package com.etsy.android.lib.convos.contentprovider;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import com.etsy.android.lib.convos.snippets.SnippetRequester;
import com.etsy.android.lib.logger.EtsyDebug;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class ConvoProvider extends ContentProvider {
    private static final String MULTIPLE_RECORDS_MIME_TYPE = "vnd.android.cursor.dir/vnd.etsy.android.contentproviders.EtsyConvoProvider.item";
    private static final String SINGLE_RECORD_MIME_TYPE = "vnd.android.cursor.item/vnd.etsy.android.contentproviders.EtsyConvoProvider.item";
    private static final String TAG;
    private final Object dbLock;
    private ConvoDatabase mDb;
    private boolean mSnippetsNeedUpdate;
    private UriMatcher sUriMatcher;

    /* renamed from: com.etsy.android.lib.convos.contentprovider.ConvoProvider.1 */
    /* synthetic */ class C04421 {
        static final /* synthetic */ int[] f1363a;

        static {
            f1363a = new int[ConvoPaths.values().length];
            try {
                f1363a[ConvoPaths.CONVOS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f1363a[ConvoPaths.CONTACTS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f1363a[ConvoPaths.SNIPPETS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f1363a[ConvoPaths.DRAFTS.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f1363a[ConvoPaths.CONVO.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f1363a[ConvoPaths.CONTACT.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f1363a[ConvoPaths.SNIPPET.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f1363a[ConvoPaths.DRAFT.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f1363a[ConvoPaths.ERASE.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    public abstract String getAuthority();

    public ConvoProvider() {
        this.dbLock = new Object();
        this.mSnippetsNeedUpdate = true;
    }

    static {
        TAG = EtsyDebug.m1891a(ConvoProvider.class);
    }

    private ConvoPaths getMatch(Uri uri) {
        return ConvoPaths.matchPath(this.sUriMatcher.match(uri));
    }

    public boolean onCreate() {
        this.sUriMatcher = ConvoAuthority.m960a(getAuthority());
        synchronized (this.dbLock) {
            this.mDb = new ConvoDatabase(getContext());
        }
        return true;
    }

    public String getType(Uri uri) {
        switch (C04421.f1363a[getMatch(uri).ordinal()]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
            case Task.NETWORK_STATE_ANY /*2*/:
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                return MULTIPLE_RECORDS_MIME_TYPE;
            case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
            case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
            case CommonStatusCodes.NETWORK_ERROR /*7*/:
            case CommonStatusCodes.INTERNAL_ERROR /*8*/:
                return SINGLE_RECORD_MIME_TYPE;
            default:
                return MULTIPLE_RECORDS_MIME_TYPE;
        }
    }

    private SQLiteDatabase getDb(boolean z) {
        SQLiteDatabase writableDatabase;
        synchronized (this.dbLock) {
            if (this.mDb == null) {
                this.mDb = new ConvoDatabase(getContext());
            }
            writableDatabase = z ? this.mDb.getWritableDatabase() : this.mDb.getReadableDatabase();
        }
        return writableDatabase;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        Cursor cursor;
        Throwable th;
        Cursor cursor2 = null;
        EtsyDebug.m1912c(TAG, "query - uri:" + uri + " projection:" + Arrays.toString(strArr));
        ConvoPaths match = getMatch(uri);
        SQLiteDatabase db = getDb(false);
        if (db == null) {
            return cursor2;
        }
        db.beginTransaction();
        try {
            SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
            switch (C04421.f1363a[match.ordinal()]) {
                case Task.NETWORK_STATE_UNMETERED /*1*/:
                case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                    sQLiteQueryBuilder.setTables("convos");
                    cursor2 = sQLiteQueryBuilder.query(db, strArr, str, strArr2, null, null, str2);
                    notifyUri(cursor2, uri);
                    cursor = cursor2;
                    break;
                case Task.NETWORK_STATE_ANY /*2*/:
                case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                    sQLiteQueryBuilder.setDistinct(true);
                    sQLiteQueryBuilder.setTables("convos");
                    cursor2 = sQLiteQueryBuilder.query(db, strArr, str, strArr2, "_id, other_user_name_user, other_user_name_full, other_user_avatar_url", null, str2);
                    try {
                        notifyUri(cursor2, uri);
                        cursor = cursor2;
                        break;
                    } catch (Throwable e) {
                        th = e;
                        cursor = cursor2;
                        try {
                            EtsyDebug.m1917d(TAG, "query error", th);
                            return cursor;
                        } finally {
                            db.endTransaction();
                        }
                    }
                case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                case CommonStatusCodes.NETWORK_ERROR /*7*/:
                    checkSnippetsNeedUpdate(uri);
                    sQLiteQueryBuilder.setTables("snippets");
                    cursor2 = sQLiteQueryBuilder.query(db, strArr, str, strArr2, null, null, str2);
                    notifyUri(cursor2, uri);
                    cursor = cursor2;
                    break;
                case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                case CommonStatusCodes.INTERNAL_ERROR /*8*/:
                    sQLiteQueryBuilder.setTables("convo_draft");
                    cursor2 = sQLiteQueryBuilder.query(db, strArr, str, strArr2, null, null, str2);
                    notifyUri(cursor2, uri);
                    cursor = cursor2;
                    break;
                default:
                    EtsyDebug.m1916d(TAG, "query() - UNKNOWN MATCH FOR URI" + uri);
                    cursor = cursor2;
                    break;
            }
            try {
                db.setTransactionSuccessful();
                db.endTransaction();
                return cursor;
            } catch (Exception e2) {
                th = e2;
                EtsyDebug.m1917d(TAG, "query error", th);
                return cursor;
            }
        } catch (Throwable e3) {
            th = e3;
            cursor = cursor2;
            EtsyDebug.m1917d(TAG, "query error", th);
            return cursor;
        }
    }

    private void notifyUri(Cursor cursor, Uri uri) {
        if (cursor != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
    }

    private void checkSnippetsNeedUpdate(Uri uri) {
        try {
            if (!TextUtils.isEmpty(uri.getQueryParameter("update")) && this.mSnippetsNeedUpdate) {
                EtsyDebug.m1912c(TAG, "running an update for snippets");
                SnippetRequester.m1028a(getContext());
                this.mSnippetsNeedUpdate = false;
            }
        } catch (UnsupportedOperationException e) {
        } catch (NullPointerException e2) {
        }
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        EtsyDebug.m1912c(TAG, "insert - uri:" + uri);
        ConvoPaths match = getMatch(uri);
        SQLiteDatabase db = getDb(true);
        if (db != null) {
            db.beginTransaction();
            try {
                switch (C04421.f1363a[match.ordinal()]) {
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                    case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                        db.insertWithOnConflict("convos", null, contentValues, 5);
                        break;
                    case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                    case CommonStatusCodes.NETWORK_ERROR /*7*/:
                        db.insertWithOnConflict("snippets", null, contentValues, 5);
                        break;
                    case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                    case CommonStatusCodes.INTERNAL_ERROR /*8*/:
                        db.insertWithOnConflict("convo_draft", null, contentValues, 5);
                        break;
                    default:
                        EtsyDebug.m1916d(TAG, "insert() - UNKNOWN MATCH FOR URI" + uri);
                        break;
                }
                getContext().getContentResolver().notifyChange(uri, null);
                db.setTransactionSuccessful();
            } catch (Throwable e) {
                EtsyDebug.m1917d(TAG, "insert error", e);
            } finally {
                db.endTransaction();
            }
        }
        return uri;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int delete(android.net.Uri r7, java.lang.String r8, java.lang.String[] r9) {
        /*
        r6 = this;
        r0 = TAG;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "delete - uri:";
        r1 = r1.append(r2);
        r1 = r1.append(r7);
        r1 = r1.toString();
        com.etsy.android.lib.logger.EtsyDebug.m1912c(r0, r1);
        r1 = r6.getMatch(r7);
        r0 = 1;
        r2 = r6.getDb(r0);
        r0 = -1;
        if (r2 == 0) goto L_0x0050;
    L_0x0024:
        r2.beginTransaction();
        r3 = com.etsy.android.lib.convos.contentprovider.ConvoProvider.C04421.f1363a;	 Catch:{ Exception -> 0x008d }
        r1 = r1.ordinal();	 Catch:{ Exception -> 0x008d }
        r1 = r3[r1];	 Catch:{ Exception -> 0x008d }
        switch(r1) {
            case 1: goto L_0x0051;
            case 2: goto L_0x0032;
            case 3: goto L_0x0099;
            case 4: goto L_0x0067;
            case 5: goto L_0x0051;
            case 6: goto L_0x0032;
            case 7: goto L_0x0099;
            case 8: goto L_0x0067;
            case 9: goto L_0x007b;
            default: goto L_0x0032;
        };	 Catch:{ Exception -> 0x008d }
    L_0x0032:
        r1 = TAG;	 Catch:{ Exception -> 0x008d }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x008d }
        r3.<init>();	 Catch:{ Exception -> 0x008d }
        r4 = "delete() - UNKNOWN MATCH FOR URI";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x008d }
        r3 = r3.append(r7);	 Catch:{ Exception -> 0x008d }
        r3 = r3.toString();	 Catch:{ Exception -> 0x008d }
        com.etsy.android.lib.logger.EtsyDebug.m1916d(r1, r3);	 Catch:{ Exception -> 0x008d }
    L_0x004a:
        r2.setTransactionSuccessful();	 Catch:{ Exception -> 0x008d }
        r2.endTransaction();
    L_0x0050:
        return r0;
    L_0x0051:
        r1 = "convos";
        r1 = r2.delete(r1, r8, r9);	 Catch:{ Exception -> 0x008d }
        if (r8 == 0) goto L_0x00b7;
    L_0x0059:
        r0 = r6.getContext();	 Catch:{ Exception -> 0x00b2 }
        r0 = r0.getContentResolver();	 Catch:{ Exception -> 0x00b2 }
        r3 = 0;
        r0.notifyChange(r7, r3);	 Catch:{ Exception -> 0x00b2 }
        r0 = r1;
        goto L_0x004a;
    L_0x0067:
        r1 = "convo_draft";
        r1 = r2.delete(r1, r8, r9);	 Catch:{ Exception -> 0x008d }
        r0 = r6.getContext();	 Catch:{ Exception -> 0x00b2 }
        r0 = r0.getContentResolver();	 Catch:{ Exception -> 0x00b2 }
        r3 = 0;
        r0.notifyChange(r7, r3);	 Catch:{ Exception -> 0x00b2 }
        r0 = r1;
        goto L_0x004a;
    L_0x007b:
        r1 = r6.mDb;	 Catch:{ Exception -> 0x008d }
        r1.recreateTables();	 Catch:{ Exception -> 0x008d }
        r1 = r6.getContext();	 Catch:{ Exception -> 0x008d }
        r1 = r1.getContentResolver();	 Catch:{ Exception -> 0x008d }
        r3 = 0;
        r1.notifyChange(r7, r3);	 Catch:{ Exception -> 0x008d }
        goto L_0x004a;
    L_0x008d:
        r1 = move-exception;
    L_0x008e:
        r3 = TAG;	 Catch:{ all -> 0x00ad }
        r4 = "delete error";
        com.etsy.android.lib.logger.EtsyDebug.m1917d(r3, r4, r1);	 Catch:{ all -> 0x00ad }
        r2.endTransaction();
        goto L_0x0050;
    L_0x0099:
        r1 = "snippets";
        r1 = r2.delete(r1, r8, r9);	 Catch:{ Exception -> 0x008d }
        r0 = r6.getContext();	 Catch:{ Exception -> 0x00b2 }
        r0 = r0.getContentResolver();	 Catch:{ Exception -> 0x00b2 }
        r3 = 0;
        r0.notifyChange(r7, r3);	 Catch:{ Exception -> 0x00b2 }
        r0 = r1;
        goto L_0x004a;
    L_0x00ad:
        r0 = move-exception;
        r2.endTransaction();
        throw r0;
    L_0x00b2:
        r0 = move-exception;
        r5 = r0;
        r0 = r1;
        r1 = r5;
        goto L_0x008e;
    L_0x00b7:
        r0 = r1;
        goto L_0x004a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.lib.convos.contentprovider.ConvoProvider.delete(android.net.Uri, java.lang.String, java.lang.String[]):int");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int update(android.net.Uri r7, android.content.ContentValues r8, java.lang.String r9, java.lang.String[] r10) {
        /*
        r6 = this;
        r0 = TAG;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "update - uri:";
        r1 = r1.append(r2);
        r1 = r1.append(r7);
        r1 = r1.toString();
        com.etsy.android.lib.logger.EtsyDebug.m1912c(r0, r1);
        r1 = r6.getMatch(r7);
        r0 = 1;
        r2 = r6.getDb(r0);
        r0 = -1;
        if (r2 == 0) goto L_0x0050;
    L_0x0024:
        r2.beginTransaction();
        r3 = com.etsy.android.lib.convos.contentprovider.ConvoProvider.C04421.f1363a;	 Catch:{ Exception -> 0x008d }
        r1 = r1.ordinal();	 Catch:{ Exception -> 0x008d }
        r1 = r3[r1];	 Catch:{ Exception -> 0x008d }
        switch(r1) {
            case 1: goto L_0x0051;
            case 2: goto L_0x0032;
            case 3: goto L_0x0079;
            case 4: goto L_0x0065;
            case 5: goto L_0x0051;
            case 6: goto L_0x0032;
            case 7: goto L_0x0079;
            case 8: goto L_0x0065;
            default: goto L_0x0032;
        };	 Catch:{ Exception -> 0x008d }
    L_0x0032:
        r1 = TAG;	 Catch:{ Exception -> 0x008d }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x008d }
        r3.<init>();	 Catch:{ Exception -> 0x008d }
        r4 = "update() - UNKNOWN MATCH FOR URI";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x008d }
        r3 = r3.append(r7);	 Catch:{ Exception -> 0x008d }
        r3 = r3.toString();	 Catch:{ Exception -> 0x008d }
        com.etsy.android.lib.logger.EtsyDebug.m1916d(r1, r3);	 Catch:{ Exception -> 0x008d }
    L_0x004a:
        r2.setTransactionSuccessful();	 Catch:{ Exception -> 0x008d }
        r2.endTransaction();
    L_0x0050:
        return r0;
    L_0x0051:
        r1 = "convos";
        r1 = r2.update(r1, r8, r9, r10);	 Catch:{ Exception -> 0x008d }
        r0 = r6.getContext();	 Catch:{ Exception -> 0x009e }
        r0 = r0.getContentResolver();	 Catch:{ Exception -> 0x009e }
        r3 = 0;
        r0.notifyChange(r7, r3);	 Catch:{ Exception -> 0x009e }
        r0 = r1;
        goto L_0x004a;
    L_0x0065:
        r1 = "convo_draft";
        r1 = r2.update(r1, r8, r9, r10);	 Catch:{ Exception -> 0x008d }
        r0 = r6.getContext();	 Catch:{ Exception -> 0x009e }
        r0 = r0.getContentResolver();	 Catch:{ Exception -> 0x009e }
        r3 = 0;
        r0.notifyChange(r7, r3);	 Catch:{ Exception -> 0x009e }
        r0 = r1;
        goto L_0x004a;
    L_0x0079:
        r1 = "snippets";
        r1 = r2.update(r1, r8, r9, r10);	 Catch:{ Exception -> 0x008d }
        r0 = r6.getContext();	 Catch:{ Exception -> 0x009e }
        r0 = r0.getContentResolver();	 Catch:{ Exception -> 0x009e }
        r3 = 0;
        r0.notifyChange(r7, r3);	 Catch:{ Exception -> 0x009e }
        r0 = r1;
        goto L_0x004a;
    L_0x008d:
        r1 = move-exception;
    L_0x008e:
        r3 = TAG;	 Catch:{ all -> 0x0099 }
        r4 = "update error";
        com.etsy.android.lib.logger.EtsyDebug.m1917d(r3, r4, r1);	 Catch:{ all -> 0x0099 }
        r2.endTransaction();
        goto L_0x0050;
    L_0x0099:
        r0 = move-exception;
        r2.endTransaction();
        throw r0;
    L_0x009e:
        r0 = move-exception;
        r5 = r0;
        r0 = r1;
        r1 = r5;
        goto L_0x008e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.lib.convos.contentprovider.ConvoProvider.update(android.net.Uri, android.content.ContentValues, java.lang.String, java.lang.String[]):int");
    }

    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> arrayList) {
        ContentProviderResult[] contentProviderResultArr = "applyBatch";
        EtsyDebug.m1912c(TAG, contentProviderResultArr);
        SQLiteDatabase db = getDb(true);
        if (db != null) {
            db.beginTransaction();
            try {
                int size = arrayList.size();
                contentProviderResultArr = new ContentProviderResult[size];
                for (int i = 0; i < size; i++) {
                    contentProviderResultArr[i] = ((ContentProviderOperation) arrayList.get(i)).apply(this, contentProviderResultArr, i);
                }
                db.setTransactionSuccessful();
                return contentProviderResultArr;
            } catch (Throwable e) {
                contentProviderResultArr = TAG;
                EtsyDebug.m1917d((String) contentProviderResultArr, "applyBatch error", e);
            } finally {
                db.endTransaction();
            }
        }
        return new ContentProviderResult[0];
    }
}
