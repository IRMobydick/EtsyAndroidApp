package com.google.android.gms.ads.internal.purchase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.SystemClock;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.internal.jw;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@jw
public class zzh {
    private static final String f4588a;
    private static final Object f4589c;
    private static zzh f4590d;
    private final zza f4591b;

    static {
        f4588a = String.format(Locale.US, "CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s INTEGER)", new Object[]{"InAppPurchase", "purchase_id", ResponseConstants.PRODUCT_ID, "developer_payload", "record_time"});
        f4589c = new Object();
    }

    zzh(Context context) {
        this.f4591b = new zza(this, context, "google_inapp_purchase.db");
    }

    public static zzh m6109a(Context context) {
        zzh com_google_android_gms_ads_internal_purchase_zzh;
        synchronized (f4589c) {
            if (f4590d == null) {
                f4590d = new zzh(context);
            }
            com_google_android_gms_ads_internal_purchase_zzh = f4590d;
        }
        return com_google_android_gms_ads_internal_purchase_zzh;
    }

    public SQLiteDatabase m6111a() {
        try {
            return this.f4591b.getWritableDatabase();
        } catch (SQLiteException e) {
            C1129c.m6192d("Error opening writable conversion tracking database");
            return null;
        }
    }

    public C1113b m6112a(Cursor cursor) {
        return cursor == null ? null : new C1113b(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
    }

    public List<C1113b> m6113a(long j) {
        Cursor query;
        SQLiteException e;
        String str;
        String valueOf;
        Throwable th;
        synchronized (f4589c) {
            List<C1113b> linkedList = new LinkedList();
            if (j <= 0) {
                return linkedList;
            }
            SQLiteDatabase a = m6111a();
            if (a == null) {
                return linkedList;
            }
            try {
                query = a.query("InAppPurchase", null, null, null, null, null, "record_time ASC", String.valueOf(j));
                try {
                    if (query.moveToFirst()) {
                        do {
                            linkedList.add(m6112a(query));
                        } while (query.moveToNext());
                    }
                    if (query != null) {
                        query.close();
                    }
                } catch (SQLiteException e2) {
                    e = e2;
                    try {
                        str = "Error extracing purchase info: ";
                        valueOf = String.valueOf(e.getMessage());
                        C1129c.m6192d(valueOf.length() == 0 ? new String(str) : str.concat(valueOf));
                        if (query != null) {
                            query.close();
                        }
                        return linkedList;
                    } catch (Throwable th2) {
                        th = th2;
                        if (query != null) {
                            query.close();
                        }
                        throw th;
                    }
                }
            } catch (SQLiteException e3) {
                e = e3;
                query = null;
                str = "Error extracing purchase info: ";
                valueOf = String.valueOf(e.getMessage());
                if (valueOf.length() == 0) {
                }
                C1129c.m6192d(valueOf.length() == 0 ? new String(str) : str.concat(valueOf));
                if (query != null) {
                    query.close();
                }
                return linkedList;
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
            return linkedList;
        }
    }

    public void m6114a(C1113b c1113b) {
        if (c1113b != null) {
            synchronized (f4589c) {
                SQLiteDatabase a = m6111a();
                if (a == null) {
                    return;
                }
                a.delete("InAppPurchase", String.format(Locale.US, "%s = %d", new Object[]{"purchase_id", Long.valueOf(c1113b.f4584a)}), null);
            }
        }
    }

    public int m6115b() {
        Cursor cursor = null;
        int i = 0;
        synchronized (f4589c) {
            SQLiteDatabase a = m6111a();
            if (a == null) {
            } else {
                try {
                    cursor = a.rawQuery("select count(*) from InAppPurchase", null);
                    if (cursor.moveToFirst()) {
                        i = cursor.getInt(0);
                        if (cursor != null) {
                            cursor.close();
                        }
                    } else {
                        if (cursor != null) {
                            cursor.close();
                        }
                    }
                } catch (SQLiteException e) {
                    String str = "Error getting record count";
                    String valueOf = String.valueOf(e.getMessage());
                    C1129c.m6192d(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                    if (cursor != null) {
                        cursor.close();
                    }
                } catch (Throwable th) {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
        }
        return i;
    }

    public void m6116b(C1113b c1113b) {
        if (c1113b != null) {
            synchronized (f4589c) {
                SQLiteDatabase a = m6111a();
                if (a == null) {
                    return;
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put(ResponseConstants.PRODUCT_ID, c1113b.f4586c);
                contentValues.put("developer_payload", c1113b.f4585b);
                contentValues.put("record_time", Long.valueOf(SystemClock.elapsedRealtime()));
                c1113b.f4584a = a.insert("InAppPurchase", null, contentValues);
                if (((long) m6115b()) > 20000) {
                    m6117c();
                }
            }
        }
    }

    public void m6117c() {
        Cursor query;
        SQLiteException e;
        String str;
        String valueOf;
        Throwable th;
        synchronized (f4589c) {
            SQLiteDatabase a = m6111a();
            if (a == null) {
                return;
            }
            try {
                query = a.query("InAppPurchase", null, null, null, null, null, "record_time ASC", "1");
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            m6114a(m6112a(query));
                        }
                    } catch (SQLiteException e2) {
                        e = e2;
                        try {
                            str = "Error remove oldest record";
                            valueOf = String.valueOf(e.getMessage());
                            C1129c.m6192d(valueOf.length() == 0 ? new String(str) : str.concat(valueOf));
                            if (query != null) {
                                query.close();
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (query != null) {
                                query.close();
                            }
                            throw th;
                        }
                    }
                }
                if (query != null) {
                    query.close();
                }
            } catch (SQLiteException e3) {
                e = e3;
                query = null;
                str = "Error remove oldest record";
                valueOf = String.valueOf(e.getMessage());
                if (valueOf.length() == 0) {
                }
                C1129c.m6192d(valueOf.length() == 0 ? new String(str) : str.concat(valueOf));
                if (query != null) {
                    query.close();
                }
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        }
    }
}
