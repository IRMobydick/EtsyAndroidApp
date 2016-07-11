package com.etsy.android.lib.core.posts;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.core.ae;
import com.etsy.android.lib.core.ap;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.EtsyLogger;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.lib.core.posts.a */
public class EtsyPostManager {
    private static final String f1638a;
    private final Context f1639b;

    /* renamed from: com.etsy.android.lib.core.posts.a.1 */
    class EtsyPostManager extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ PersistentRequest f1636a;
        final /* synthetic */ EtsyPostManager f1637b;

        EtsyPostManager(EtsyPostManager etsyPostManager, PersistentRequest persistentRequest) {
            this.f1637b = etsyPostManager;
            this.f1636a = persistentRequest;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m1652a((Void[]) objArr);
        }

        protected Void m1652a(Void... voidArr) {
            this.f1637b.m1664a(this.f1636a, true);
            return null;
        }
    }

    static {
        f1638a = EtsyDebug.m1891a(EtsyPostManager.class);
    }

    public EtsyPostManager(Context context) {
        this.f1639b = context;
    }

    public void m1661a() {
        m1659d();
    }

    public <Request, Result> void m1663a(PersistentRequest<Request, Result> persistentRequest) {
        if (persistentRequest == null) {
            EtsyDebug.m1919e(f1638a, "add - Won't accept null posts");
        } else {
            ap.m1142a(new EtsyPostManager(this, persistentRequest), new Void[0]);
        }
    }

    <Request, Result> void m1664a(PersistentRequest<Request, Result> persistentRequest, boolean z) {
        m1665a((PersistentRequest) persistentRequest, z, 0);
    }

    <Request, Result> void m1665a(PersistentRequest<Request, Result> persistentRequest, boolean z, long j) {
        byte[] b = EtsyPostManager.m1657b((PersistentRequest) persistentRequest);
        if (b == null) {
            EtsyDebug.m1920e(f1638a, "Post could not be serialized to JSON - %s", persistentRequest);
            persistentRequest.onPersistentResult(this.f1639b, null);
            return;
        }
        EtsyDebug.m1914c(f1638a, "Added post with length: %d", Integer.valueOf(b.length));
        EtsyDebug.m1914c(f1638a, "Added post with version %s", Integer.valueOf(persistentRequest.getVersionCode()));
        EtsyDebug.m1914c(f1638a, "Added post with runAfterTime %s", Long.valueOf(j));
        if (m1660a(b, persistentRequest.getVersionCode(), j) < 0) {
            EtsyDebug.m1920e(f1638a, "Couldn't put post in db = %s", b);
            persistentRequest.onPersistentResult(this.f1639b, null);
            return;
        }
        if (z) {
            m1659d();
        }
        persistentRequest.onPersisted(this.f1639b);
    }

    void m1662a(long j) {
        m1656b(j);
    }

    EtsyPostManager<?, ?> m1667b() {
        return m1658c();
    }

    protected static <Request, Result> byte[] m1657b(PersistentRequest<Request, Result> persistentRequest) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(persistentRequest);
            objectOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable e) {
            EtsyDebug.m1917d(f1638a, "Error writing full body; sent nothing", e);
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static com.etsy.android.lib.core.posts.PersistentRequest m1654a(@android.support.annotation.NonNull byte[] r3) {
        /*
        r0 = new java.io.ByteArrayInputStream;
        r0.<init>(r3);
        r1 = new java.io.ObjectInputStream;	 Catch:{ IOException -> 0x0014, ClassNotFoundException -> 0x001e }
        r1.<init>(r0);	 Catch:{ IOException -> 0x0014, ClassNotFoundException -> 0x001e }
        r0 = r1.readObject();	 Catch:{ IOException -> 0x0014, ClassNotFoundException -> 0x001e }
        r0 = (com.etsy.android.lib.core.posts.PersistentRequest) r0;	 Catch:{ IOException -> 0x0014, ClassNotFoundException -> 0x001e }
        r1.close();	 Catch:{ IOException -> 0x0014, ClassNotFoundException -> 0x001e }
    L_0x0013:
        return r0;
    L_0x0014:
        r0 = move-exception;
    L_0x0015:
        r1 = f1638a;
        r2 = "Error writing full body; sent nothing";
        com.etsy.android.lib.logger.EtsyDebug.m1917d(r1, r2, r0);
        r0 = 0;
        goto L_0x0013;
    L_0x001e:
        r0 = move-exception;
        goto L_0x0015;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.lib.core.posts.a.a(byte[]):com.etsy.android.lib.core.posts.PersistentRequest");
    }

    @Deprecated
    public static PersistentRequest m1653a(String str) {
        try {
            return (PersistentRequest) new ae().m1086a(str, PersistentRequest.class);
        } catch (Throwable e) {
            EtsyDebug.m1917d(f1638a, "readPostFromJson - read error.", e);
            return null;
        }
    }

    protected long m1660a(byte[] bArr, int i, long j) {
        SQLiteDatabase writableDatabase = PostDatabase.getInstance(this.f1639b).getWritableDatabase();
        if (writableDatabase != null) {
            try {
                return writableDatabase.insert("post", null, EtsyPostManager.m1655b(bArr, i, j));
            } catch (Throwable e) {
                EtsyDebug.m1917d(f1638a, "insertPostToDb - db error.", e);
                EtsyLogger.m1966a().m1986a("EtsyPostManager", "insertPostToDb - db error." + e.toString());
            }
        }
        return -1;
    }

    protected static ContentValues m1655b(byte[] bArr, int i, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("serialized_post", bArr);
        contentValues.put("version_code", Integer.valueOf(i));
        contentValues.put("post_json", StringUtils.EMPTY);
        if (j > 0) {
            contentValues.put("run_after_time", Long.valueOf(j));
        }
        return contentValues;
    }

    private EtsyPostManager<?, ?> m1658c() {
        EtsyPostManager<?, ?> etsyPostManager;
        SQLiteDatabase readableDatabase = PostDatabase.getInstance(this.f1639b).getReadableDatabase();
        if (readableDatabase == null) {
            return null;
        }
        readableDatabase.beginTransaction();
        try {
            new SQLiteQueryBuilder().setTables("post");
            Cursor rawQuery = readableDatabase.rawQuery(EtsyPostManager.f1644d, new String[]{String.valueOf(System.currentTimeMillis())});
            if (rawQuery == null || !rawQuery.moveToFirst()) {
                if (rawQuery != null) {
                    rawQuery.close();
                }
                etsyPostManager = null;
            } else {
                long j = rawQuery.getLong(0);
                int i = rawQuery.getInt(1);
                int i2 = rawQuery.getInt(2);
                rawQuery.close();
                byte[] a = m1666a(readableDatabase, j, i);
                if (a != null) {
                    etsyPostManager = EtsyPostManager.m1668a(j, EtsyPostManager.m1654a(a), i2);
                }
                etsyPostManager = null;
            }
            readableDatabase.setTransactionSuccessful();
            return etsyPostManager;
        } catch (Throwable e) {
            EtsyDebug.m1917d(f1638a, "queryNextQueuedPost - db error.", e);
            etsyPostManager = "deserializePost - read error." + e.toString();
            EtsyLogger.m1966a().m1986a("EtsyPostManager", (String) etsyPostManager);
            return null;
        } finally {
            readableDatabase.endTransaction();
        }
    }

    @Nullable
    protected byte[] m1666a(@NonNull SQLiteDatabase sQLiteDatabase, long j, int i) {
        Object obj = new byte[i];
        int i2 = 0;
        while (i - i2 > 0) {
            int i3 = i - i2;
            Cursor rawQuery = sQLiteDatabase.rawQuery(EtsyPostManager.f1640a, new String[]{String.valueOf(i2 + 1), String.valueOf(Math.min(1000000, i3)), String.valueOf(j)});
            if (rawQuery != null && rawQuery.moveToFirst()) {
                Object blob = rawQuery.getBlob(0);
                System.arraycopy(blob, 0, obj, i2, blob.length);
                i2 += blob.length;
                rawQuery.close();
            } else if (rawQuery != null) {
                rawQuery.close();
            }
        }
        if (i <= 0 || i2 != i) {
            return null;
        }
        return obj;
    }

    private void m1656b(long j) {
        SQLiteDatabase writableDatabase = PostDatabase.getInstance(this.f1639b).getWritableDatabase();
        if (writableDatabase != null) {
            try {
                String str = "post";
                writableDatabase.delete(str, "_id = ?", new String[]{String.valueOf(j)});
            } catch (Throwable e) {
                EtsyDebug.m1917d(f1638a, "deletePostFromDb - db error - assume it doesn't exist.", e);
                EtsyLogger.m1966a().m1986a("EtsyPostManager", "deletePostFromDb failed " + e.toString());
            }
        }
    }

    private void m1659d() {
        EtsyDebug.m1912c(f1638a, "Starting EtsyPostService with action = ACTION_RUN_NEXT");
        Intent intent = new Intent(this.f1639b, EtsyPostService.class);
        intent.setAction(EtsyPostService.ACTION_RUN_NEXT);
        this.f1639b.startService(intent);
    }
}
