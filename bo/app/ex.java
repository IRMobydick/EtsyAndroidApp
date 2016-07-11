package bo.app;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import org.json.JSONException;

public class ex implements ew {
    private static final String f422a;
    private static final String[] f423b;
    private static final String[] f424c;
    private SQLiteDatabase f425d;
    private final el f426e;

    static {
        f422a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, ex.class.getName()});
        f423b = new String[]{"session_id", "start_time", "end_time", "new_sent", "endtime_sent"};
        f424c = new String[]{"session_id", "event_type", "event_data", "timestamp"};
    }

    public ex(el elVar) {
        this.f426e = elVar;
    }

    public final synchronized SQLiteDatabase m290c() {
        if (this.f425d == null || !this.f425d.isOpen()) {
            this.f425d = this.f426e.getWritableDatabase();
        }
        return this.f425d;
    }

    public final void m286a(ct ctVar) {
        int i = 1;
        if (ctVar != null) {
            m290c().beginTransaction();
            try {
                int i2;
                m289b(ctVar);
                cm e = ctVar.m140e();
                ContentValues contentValues = new ContentValues();
                contentValues.put("session_id", ctVar.f257d.toString());
                contentValues.put("start_time", Double.valueOf(ctVar.f258e));
                contentValues.put("end_time", m283c(ctVar));
                String str = "new_sent";
                if (ctVar.m137b()) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                contentValues.put(str, Integer.valueOf(i2));
                contentValues.put("endtime_sent", Integer.valueOf(ctVar.m139d() ? 1 : 0));
                if (!ctVar.m138c()) {
                    i = 0;
                }
                contentValues.put("sealed", Integer.valueOf(i));
                m290c().beginTransaction();
                long insertWithOnConflict = m290c().insertWithOnConflict("ab_sessions", null, contentValues, 5);
                String str2 = f422a;
                String.format("Inserted session into row %d", new Object[]{Long.valueOf(insertWithOnConflict)});
                for (cp a : e.f240a) {
                    long insert = m290c().insert("ab_events", null, m279a(ctVar.f257d, a));
                    String str3 = f422a;
                    String.format("Inserted event into row %d", new Object[]{Long.valueOf(insert)});
                }
                m290c().setTransactionSuccessful();
                m290c().setTransactionSuccessful();
                m290c().endTransaction();
            } catch (Throwable th) {
            } finally {
                m290c().endTransaction();
            }
        } else {
            AppboyLogger.m664e(f422a, "Erroneously received upsertSession call with null session value.  Please report this result to Appboy.");
        }
    }

    public final void m287a(ct ctVar, cp cpVar) {
        if (!da.m163a(cpVar)) {
            if (m290c().insert("ab_events", null, m279a(ctVar.f257d, cpVar)) == -1) {
                AppboyLogger.m670w(f422a, String.format("Failed to insert event [%s] for session with ID [%s]", new Object[]{cpVar.toString(), r0.toString()}));
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("end_time", m283c(ctVar));
            int updateWithOnConflict = m290c().updateWithOnConflict("ab_sessions", contentValues, String.format("%s = ? AND %s = ?", new Object[]{"sealed", "session_id"}), new String[]{"0", String.valueOf(ctVar.f257d)}, 2);
            if (ctVar.m138c()) {
                if (updateWithOnConflict == 0) {
                    String str = f422a;
                }
            } else if (updateWithOnConflict != 1) {
                AppboyLogger.m670w(f422a, String.format("Attempt to update end time affected %d rows, expected just one.", new Object[]{Integer.valueOf(updateWithOnConflict)}));
            }
        }
    }

    public final ct m285a() {
        try {
            Cursor query = m290c().query("ab_sessions", f423b, String.format("%s = ?", new Object[]{"sealed"}), new String[]{"0"}, null, null, null);
            if (query.getCount() > 1) {
                AppboyLogger.m664e(f422a, "Got > 1 session while trying to get stored open session. " + query.getCount() + " open sessions in database");
            }
            return m280a(query);
        } catch (Throwable e) {
            AppboyLogger.m665e(f422a, "Failed to retrieve stored open session.", e);
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Collection<bo.app.ct> m288b() {
        /*
        r16 = this;
        r13 = new java.util.ArrayList;
        r13.<init>();
        r2 = "%s = ?";
        r3 = 1;
        r3 = new java.lang.Object[r3];
        r4 = 0;
        r5 = "sealed";
        r3[r4] = r5;
        r5 = java.lang.String.format(r2, r3);
        r2 = "%s = ?";
        r3 = 1;
        r3 = new java.lang.Object[r3];
        r4 = 0;
        r6 = "session_id";
        r3[r4] = r6;
        r14 = java.lang.String.format(r2, r3);
        r2 = 1;
        r6 = new java.lang.String[r2];
        r2 = 0;
        r3 = "1";
        r6[r2] = r3;
        r2 = f422a;
        r10 = 0;
        r2 = r16.m290c();	 Catch:{ all -> 0x00d2 }
        r3 = "ab_sessions";
        r4 = f423b;	 Catch:{ all -> 0x00d2 }
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r12 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ all -> 0x00d2 }
        r2 = f422a;	 Catch:{ all -> 0x00ba }
        r3 = "Found %d sealed session rows.";
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ all -> 0x00ba }
        r5 = 0;
        r6 = r12.getCount();	 Catch:{ all -> 0x00ba }
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ all -> 0x00ba }
        r4[r5] = r6;	 Catch:{ all -> 0x00ba }
        r3 = java.lang.String.format(r3, r4);	 Catch:{ all -> 0x00ba }
        com.appboy.support.AppboyLogger.m666i(r2, r3);	 Catch:{ all -> 0x00ba }
        r2 = m282b(r12);	 Catch:{ all -> 0x00ba }
        r12.close();	 Catch:{ all -> 0x00ba }
        r15 = r2.iterator();	 Catch:{ all -> 0x00ba }
    L_0x005f:
        r2 = r15.hasNext();	 Catch:{ all -> 0x00ba }
        if (r2 == 0) goto L_0x00cc;
    L_0x0065:
        r2 = r15.next();	 Catch:{ all -> 0x00ba }
        r0 = r2;
        r0 = (bo.app.ct) r0;	 Catch:{ all -> 0x00ba }
        r10 = r0;
        r2 = 1;
        r6 = new java.lang.String[r2];	 Catch:{ all -> 0x00ba }
        r2 = 0;
        r3 = r10.f257d;	 Catch:{ all -> 0x00ba }
        r3 = r3.toString();	 Catch:{ all -> 0x00ba }
        r6[r2] = r3;	 Catch:{ all -> 0x00ba }
        r11 = 0;
        r2 = r16.m290c();	 Catch:{ all -> 0x00c4 }
        r3 = "ab_events";
        r4 = f424c;	 Catch:{ all -> 0x00c4 }
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r5 = r14;
        r11 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ all -> 0x00c4 }
        r2 = m284c(r11);	 Catch:{ all -> 0x00d5 }
        r7 = new bo.app.cm;	 Catch:{ all -> 0x00d5 }
        r3 = new java.util.HashSet;	 Catch:{ all -> 0x00d5 }
        r3.<init>(r2);	 Catch:{ all -> 0x00d5 }
        r7.<init>(r3);	 Catch:{ all -> 0x00d5 }
        r2 = new bo.app.ct;	 Catch:{ all -> 0x00d5 }
        r3 = r10.f257d;	 Catch:{ all -> 0x00d5 }
        r4 = r10.f258e;	 Catch:{ all -> 0x00d5 }
        r6 = r10.m134a();	 Catch:{ all -> 0x00d5 }
        r8 = r10.m137b();	 Catch:{ all -> 0x00d5 }
        if (r8 != 0) goto L_0x00c2;
    L_0x00a8:
        r8 = 1;
    L_0x00a9:
        r9 = 1;
        r10 = r10.m139d();	 Catch:{ all -> 0x00d5 }
        r2.<init>(r3, r4, r6, r7, r8, r9, r10);	 Catch:{ all -> 0x00d5 }
        r13.add(r2);	 Catch:{ all -> 0x00d5 }
        if (r11 == 0) goto L_0x005f;
    L_0x00b6:
        r11.close();	 Catch:{ all -> 0x00ba }
        goto L_0x005f;
    L_0x00ba:
        r2 = move-exception;
        r3 = r12;
    L_0x00bc:
        if (r3 == 0) goto L_0x00c1;
    L_0x00be:
        r3.close();
    L_0x00c1:
        throw r2;
    L_0x00c2:
        r8 = 0;
        goto L_0x00a9;
    L_0x00c4:
        r2 = move-exception;
        r3 = r11;
    L_0x00c6:
        if (r3 == 0) goto L_0x00cb;
    L_0x00c8:
        r3.close();	 Catch:{ all -> 0x00ba }
    L_0x00cb:
        throw r2;	 Catch:{ all -> 0x00ba }
    L_0x00cc:
        if (r12 == 0) goto L_0x00d1;
    L_0x00ce:
        r12.close();
    L_0x00d1:
        return r13;
    L_0x00d2:
        r2 = move-exception;
        r3 = r10;
        goto L_0x00bc;
    L_0x00d5:
        r2 = move-exception;
        r3 = r11;
        goto L_0x00c6;
        */
        throw new UnsupportedOperationException("Method not decompiled: bo.app.ex.b():java.util.Collection<bo.app.ct>");
    }

    public final void m289b(ct ctVar) {
        m290c().beginTransaction();
        try {
            String format = String.format("%s = ?", new Object[]{"session_id"});
            String[] strArr = new String[]{ctVar.f257d.toString()};
            int delete = m290c().delete("ab_sessions", format, strArr);
            String str = f422a;
            String.format("Deleting session removed %d rows.", new Object[]{Integer.valueOf(delete)});
            delete = m290c().delete("ab_events", String.format("%s = ?", new Object[]{"session_id"}), strArr);
            String str2 = f422a;
            String.format("Deleting session events removed %d rows.", new Object[]{Integer.valueOf(delete)});
            m290c().setTransactionSuccessful();
        } finally {
            m290c().endTransaction();
        }
    }

    private ct m280a(Cursor cursor) {
        Throwable th;
        Cursor cursor2;
        try {
            String format = String.format("%s = ?", new Object[]{"session_id"});
            if (cursor.moveToFirst()) {
                Double d;
                int i;
                String string = cursor.getString(cursor.getColumnIndex("session_id"));
                double d2 = cursor.getDouble(cursor.getColumnIndex("start_time"));
                int columnIndex = cursor.getColumnIndex("end_time");
                if (cursor.isNull(columnIndex)) {
                    d = null;
                } else {
                    d = Double.valueOf(cursor.getDouble(columnIndex));
                }
                columnIndex = cursor.getColumnIndex("new_sent");
                if (cursor.isNull(columnIndex)) {
                    i = 0;
                } else {
                    i = cursor.getInt(columnIndex);
                }
                cursor.close();
                Cursor query = m290c().query("ab_events", f424c, format, new String[]{string}, null, null, null);
                try {
                    ct ctVar = new ct(cy.m145a(string), d2, d, new cm(new HashSet(m284c(query))), !m281a(i), false, false);
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (query == null) {
                        return ctVar;
                    }
                    query.close();
                    return ctVar;
                } catch (Throwable th2) {
                    th = th2;
                    cursor2 = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            cursor2 = null;
            if (cursor != null) {
                cursor.close();
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
    }

    private static Double m283c(ct ctVar) {
        Double a = ctVar.m134a();
        if (a == null) {
            return Double.valueOf(fd.m334b());
        }
        return a;
    }

    private static ContentValues m279a(cy cyVar, cp cpVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("session_id", cyVar.toString());
        contentValues.put("event_type", cpVar.m126b().f53r);
        contentValues.put("event_data", cpVar.m127c().toString());
        contentValues.put("timestamp", Double.valueOf(cpVar.m125a()));
        return contentValues;
    }

    private static Collection<ct> m282b(Cursor cursor) {
        Collection arrayList = new ArrayList();
        int columnIndex = cursor.getColumnIndex("session_id");
        int columnIndex2 = cursor.getColumnIndex("start_time");
        int columnIndex3 = cursor.getColumnIndex("end_time");
        int columnIndex4 = cursor.getColumnIndex("new_sent");
        int columnIndex5 = cursor.getColumnIndex("endtime_sent");
        while (cursor.moveToNext()) {
            String string = cursor.getString(columnIndex);
            double d = cursor.getDouble(columnIndex2);
            double d2 = cursor.getDouble(columnIndex3);
            int i = cursor.getInt(columnIndex4);
            int i2 = cursor.getInt(columnIndex5);
            arrayList.add(new ct(cy.m145a(string), d, Double.valueOf(d2), new cm(new HashSet()), !m281a(i), true, m281a(i2)));
        }
        return arrayList;
    }

    private static Collection<cp> m284c(Cursor cursor) {
        Collection arrayList = new ArrayList();
        int columnIndex = cursor.getColumnIndex("event_type");
        int columnIndex2 = cursor.getColumnIndex("event_data");
        int columnIndex3 = cursor.getColumnIndex("timestamp");
        while (cursor.moveToNext()) {
            try {
                arrayList.add(da.m155a(cursor.getString(columnIndex), cursor.getString(columnIndex2), cursor.getDouble(columnIndex3)));
            } catch (JSONException e) {
                AppboyLogger.m664e(f422a, String.format("Could not create AppboyEvent from [type=%s, data=%s, timestamp=%f] ... Skipping", new Object[]{r4, r5, Double.valueOf(r6)}));
            }
        }
        return arrayList;
    }

    private static boolean m281a(int i) {
        return i == 1;
    }
}
